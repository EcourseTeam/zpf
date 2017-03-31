package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

public class SchoolRollInfo implements Entry {

    public int pk_SchoolRollId;
    public int fk_SchoolId;
    public int fk_MajorId;

    public SchoolRollInfo(int schoolId, int majorId) {
        fk_SchoolId = schoolId;
        fk_MajorId = majorId;
    }

    public SchoolRollInfo(Cursor c) {
        pk_SchoolRollId = c.getInt(c.getColumnIndex("pk_SchoolRollId"));
        fk_SchoolId = c.getInt(c.getColumnIndex("fk_SchoolId"));
        fk_MajorId = c.getInt(c.getColumnIndex("fk_MajorId"));
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("pk_SchoolRollId", pk_SchoolRollId);
        cv.put("fk_SchoolId", fk_SchoolId);
        cv.put("fk_MajorId", fk_MajorId);
        return cv;
    }

    public String toString() {
        return pk_SchoolRollId + ", " + fk_SchoolId + ", " + fk_MajorId;
    }

}
