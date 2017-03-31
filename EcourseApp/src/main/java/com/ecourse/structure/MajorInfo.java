package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

public class MajorInfo implements Entry {

    public int pk_MajorId;
    public int fk_AcademyId;
    public String idx_MajorName;
    public String idx_Education;
    public int idx_Semester = 1;
    public int idx_Week = 1;

    public MajorInfo(int academyId, String majorName, String education, int semester, int week) {
        fk_AcademyId = academyId;
        idx_MajorName = majorName;
        idx_Education = education;
        idx_Semester = semester;
        idx_Week = week;
    }

    public MajorInfo(Cursor c) {
        pk_MajorId = c.getInt(c.getColumnIndex("pk_MajorId"));
        fk_AcademyId = c.getInt(c.getColumnIndex("fk_AcademyId"));
        idx_MajorName = c.getString(c.getColumnIndex("idx_MajorName"));
        idx_Education = c.getString(c.getColumnIndex("idx_Education"));
        idx_Semester = c.getInt(c.getColumnIndex("idx_Semester"));
        idx_Week = c.getInt(c.getColumnIndex("idx_Week"));
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("pk_MajorId", pk_MajorId);
        cv.put("fk_AcademyId", fk_AcademyId);
        cv.put("idx_MajorName", idx_MajorName);
        cv.put("idx_Education", idx_Education);
        cv.put("idx_Semester", idx_Semester);
        cv.put("idx_Week", idx_Week);
        return cv;
    }

    public String toString() {
        return pk_MajorId + ", " + fk_AcademyId + ", " + idx_MajorName + ", " +
                idx_Education + ", " + idx_Semester + ", " + idx_Week;
    }
}
