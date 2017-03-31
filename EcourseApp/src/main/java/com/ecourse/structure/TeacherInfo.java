package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

public class TeacherInfo implements Entry {

    public int pk_TeacherId;
    public int fk_AcademyId;
    public String idx_TeacherName;
    public String idx_OfficePhone;
    public String idx_CellPhone;
    public String idx_Email;

    public TeacherInfo(int academyId, String teacherName, String officePhone,
                       String cellPhone, String email) {
        fk_AcademyId = academyId;
        idx_TeacherName = teacherName;
        idx_OfficePhone = officePhone;
        idx_CellPhone = cellPhone;
        idx_Email = email;
    }

    public TeacherInfo(Cursor c) {
        pk_TeacherId = c.getInt(c.getColumnIndex("pk_TeacherId"));
        fk_AcademyId = c.getInt(c.getColumnIndex("fk_AcademyId"));
        idx_TeacherName = c.getString(c.getColumnIndex("idx_TeacherName"));
        idx_OfficePhone = c.getString(c.getColumnIndex("idx_OfficePhone"));
        idx_CellPhone = c.getString(c.getColumnIndex("idx_CellPhone"));
        idx_Email = c.getString(c.getColumnIndex("idx_Email"));
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("pk_TeacherId", pk_TeacherId);
        cv.put("fk_AcademyId", fk_AcademyId);
        cv.put("idx_TeacherName", idx_TeacherName);
        cv.put("idx_OfficePhone", idx_OfficePhone);
        cv.put("idx_CellPhone", idx_CellPhone);
        cv.put("idx_Email", idx_Email);
        return cv;
    }

    public String toString() {
        return pk_TeacherId + ", " + fk_AcademyId + ", " + idx_TeacherName + ", " +
                idx_OfficePhone + ", " + idx_CellPhone + ", " + idx_Email;
    }
}
