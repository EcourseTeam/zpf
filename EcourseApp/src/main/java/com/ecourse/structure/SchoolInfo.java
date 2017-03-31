package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

public class SchoolInfo implements Entry {

    public int pk_SchoolId;
    public String uk_SchoolName;

    public SchoolInfo(String schoolName) {
        uk_SchoolName = schoolName;
    }

    public SchoolInfo(Cursor c) {
        pk_SchoolId = c.getInt(c.getColumnIndex("pk_SchoolId"));
        uk_SchoolName = c.getString(c.getColumnIndex("uk_SchoolName"));
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("pk_SchoolId", pk_SchoolId);
        cv.put("uk_SchoolName", uk_SchoolName);
        return cv;
    }

    public String toString() {
        return pk_SchoolId + ", " + uk_SchoolName;
    }

}
