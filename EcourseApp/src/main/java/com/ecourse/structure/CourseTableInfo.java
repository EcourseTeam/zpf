package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

public class CourseTableInfo implements Entry {

    public int pk_CourseTableId;
    public int fk_UserId;
    public String idx_CourseTableName;
    /*public int idx_OneClassTime;
    public int idx_CourseClassCount;
    public int idx_Class1Time;
    public int idx_Class2Time;
    public int idx_Class3Time;
    public int idx_Class4Time;
    public int idx_Class5Time;
    public int idx_Class6Time;
    public int idx_Class7Time;
    public int idx_Class8Time;
    public int idx_Class9Time;
    public int idx_Class10Time;
    public int idx_Class11Time;
    public int idx_Class12Time;
    public int idx_Class13Time;
    public int idx_Class14Time;*/

    public CourseTableInfo(int userId, String courseTableName) {
        fk_UserId = userId;
        idx_CourseTableName = courseTableName;
    }

    public CourseTableInfo(Cursor c) {
        pk_CourseTableId = c.getInt(c.getColumnIndex("pk_CourseTableId"));
        fk_UserId = c.getInt(c.getColumnIndex("fk_UserId"));
        idx_CourseTableName = c.getString(c.getColumnIndex("idx_CourseTableName"));
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("pk_CourseTableId", pk_CourseTableId);
        cv.put("fk_UserId", fk_UserId);
        cv.put("idx_CourseTableName", idx_CourseTableName);
        return cv;
    }

    public String toString() {
        return pk_CourseTableId + ", " + fk_UserId + ", " + idx_CourseTableName;
    }

}
