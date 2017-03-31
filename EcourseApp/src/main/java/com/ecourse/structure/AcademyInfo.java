package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

public class AcademyInfo implements Entry {

    public int pk_AcademyId;
    public int fk_SchoolId;
    public String idx_AcademyName;

    public AcademyInfo(int schoolId, String academyName) {
        fk_SchoolId = schoolId;
        idx_AcademyName = academyName;
    }

    public AcademyInfo(Cursor c) {
        pk_AcademyId = c.getInt(c.getColumnIndex("pk_AcademyId"));
        fk_SchoolId = c.getInt(c.getColumnIndex("fk_SchoolId"));
        idx_AcademyName = c.getString(c.getColumnIndex("idx_AcademyName"));
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("pk_AcademyId", pk_AcademyId);
        cv.put("fk_SchoolId", fk_SchoolId);
        cv.put("idx_AcademyName", idx_AcademyName);
        return cv;
    }

    public String toString() {
        return pk_AcademyId + ", " + fk_SchoolId + ", " + idx_AcademyName;
    }

}
