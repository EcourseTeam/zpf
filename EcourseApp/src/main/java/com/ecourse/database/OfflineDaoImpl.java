package com.ecourse.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Map;

import com.ecourse.structure.Entry;
import com.ecourse.structure.UserInfo;

public class OfflineDaoImpl implements OfflineDao {

    private static SQLiteDatabase db;

    public OfflineDaoImpl(Context ctx) {
        DBHelper helper = new DBHelper(ctx);
        if (db == null) {
            db = helper.getWritableDatabase();
            db.delete("tbl_UserInfo", null, null);
        }
    }

    public long addEntry(int table, Entry newEntry) {
        return addEntry(table, newEntry.getContentValues());
    }

    public long addEntry(int table, ContentValues newEntryCV) {
        Cursor c = db.rawQuery("select idx_KeyMax from " + TABLE_ARRAY[TABLE_KEY_POOL] +
                " where pk_TableName = ?", new String[]{TABLE_ARRAY[table]});
        c.moveToNext();
        Log.i("OfflineDaoImpl", "table:" + table);
        int id = c.getInt(c.getColumnIndex("idx_KeyMax"));
        ContentValues cv = new ContentValues();
        cv.put("pk_TableName", TABLE_ARRAY[table]);
        cv.put("idx_KeyMax", ++id);
        //Log.i("OfflineDaoImpl", "id:" + id);
        ContentValues filter = new ContentValues();
        filter.put("pk_TableName", TABLE_ARRAY[table]);
        db.update(TABLE_ARRAY[TABLE_KEY_POOL], cv, whereClause(filter), whereArgs(filter));
        newEntryCV.put(findPrimaryKeyName(newEntryCV), id);
        return db.insert(TABLE_ARRAY[table], null, newEntryCV);
    }

    public int updateEntries(int table, ContentValues filter, Entry newEntry) {
        return db.update(TABLE_ARRAY[table], newEntry.getContentValues(), whereClause(filter), whereArgs(filter));
    }

    public int updateEntries(int table, ContentValues filter, ContentValues newEntryCV) {
        return db.update(TABLE_ARRAY[table], newEntryCV, whereClause(filter), whereArgs(filter));
    }

    public int deleteEntries(int table, ContentValues filter) {
        return db.delete(TABLE_ARRAY[table], whereClause(filter), whereArgs(filter));
    }

    public Entry[] getEntries(int table) {
        return getEntries(table, null);
    }

    public Entry[] getEntries(int table, ContentValues filter) {
        String sql = "select * from " + TABLE_ARRAY[table];
        if (filter == null) {
            return getEntriesFromCursor(table, db.rawQuery(sql, null));
        } else {
            return getEntriesFromCursor(table, db.rawQuery(sql + " where " + whereClause(filter), whereArgs(filter)));
        }
    }

    public void closeDB() {
        db.close();
    }

    private String findPrimaryKeyName(ContentValues cv) {
        String str;
        for (Map.Entry<String, Object> item : cv.valueSet()) {
            str = item.getKey().toString();
            //Log.i("OfflineDaoImpl", "str:" + str);
            if (str.startsWith("pk_")) {
                return str;
            }
        }
        return null;
    }

    private String whereClause(ContentValues filter) {
        String str = "";
        int counter = 0;
        for (Map.Entry<String, Object> item : filter.valueSet()) {
            if (++counter < filter.size()) {
                str += item.getKey() + " = ? and ";
            } else {
                str += item.getKey() + " = ?";
            }
        }
        return str;
    }

    private String[] whereArgs(ContentValues filter) {
        String[] strs = new String[filter.size()];
        int counter = 0;
        for (Map.Entry<String, Object> item : filter.valueSet()) {
            strs[counter++] = item.getValue().toString();
        }
        return strs;
    }

    private Entry[] getEntriesFromCursor(int table, Cursor c) {
        Entry[] entries;
        int counter = 0;
        switch (table) {
            case TABLE_USER_INFO:
                entries = new UserInfo[c.getCount()];
                break;
            default:
                return null;
        }
        while (c.moveToNext()) {
            entries[counter++] = new UserInfo(c);
        }
        return entries;
    }
}
