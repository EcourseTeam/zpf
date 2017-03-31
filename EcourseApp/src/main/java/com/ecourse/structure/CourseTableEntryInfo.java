package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

public class CourseTableEntryInfo implements Entry {

    public int pk_CourseTableEntryId;
    public int fk_CourseTableId;
    public int fk_CoursePeriodId;

    public CourseTableEntryInfo(int courseTableId, int coursePeriodId) {
        fk_CourseTableId = courseTableId;
        fk_CoursePeriodId = coursePeriodId;
    }

    public CourseTableEntryInfo(Cursor c) {
        pk_CourseTableEntryId = c.getInt(c.getColumnIndex("pk_CourseTableEntryId"));
        fk_CourseTableId = c.getInt(c.getColumnIndex("fk_CourseTableId"));
        fk_CoursePeriodId = c.getInt(c.getColumnIndex("fk_CoursePeriodId"));
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("pk_CourseTableEntryId", pk_CourseTableEntryId);
        cv.put("fk_CourseTableId", fk_CourseTableId);
        cv.put("fk_CoursePeriodId", fk_CoursePeriodId);
        return cv;
    }

    public String toString() {
        return pk_CourseTableEntryId + ", " + fk_CourseTableId + ", " + fk_CoursePeriodId;
    }

}
