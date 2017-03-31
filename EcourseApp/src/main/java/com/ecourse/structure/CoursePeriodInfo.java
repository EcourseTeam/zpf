package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

public class CoursePeriodInfo implements Entry {

    public int pk_CoursePeriodId;
    public int fk_CourseId;
    public int idx_WeekFrom;
    public int idx_WeekTo;
    public int idx_Week;
    public int idx_TimeFrom;
    public int idx_TimeTo;
    public int idx_Alarm;
    public String idx_Place;

    public CoursePeriodInfo(int courseId, int weekFrom, int weekTo, int week,
                            int timeFrom, int timeTo, int alarm,
                            String place) {
        fk_CourseId = courseId;
        idx_WeekFrom = weekFrom;
        idx_WeekTo = weekTo;
        idx_Week = week;
        idx_TimeFrom = timeFrom;
        idx_TimeTo = timeTo;
        idx_Alarm = alarm;
        idx_Place = place;
    }

    public CoursePeriodInfo(Cursor c) {
        pk_CoursePeriodId = c.getInt(c.getColumnIndex("pk_CoursePeriodId"));
        fk_CourseId = c.getInt(c.getColumnIndex("fk_CourseId"));
        idx_WeekFrom = c.getInt(c.getColumnIndex("idx_WeekFrom"));
        idx_WeekTo = c.getInt(c.getColumnIndex("idx_WeekTo"));
        idx_Week = c.getInt(c.getColumnIndex("idx_Week"));
        idx_TimeFrom = c.getInt(c.getColumnIndex("idx_TimeFrom"));
        idx_TimeTo = c.getInt(c.getColumnIndex("idx_TimeTo"));
        idx_Alarm = c.getInt(c.getColumnIndex("idx_Alarm"));
        idx_Place = c.getString(c.getColumnIndex("idx_Place"));
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("pk_CoursePeriodId", pk_CoursePeriodId);
        cv.put("fk_CourseId", fk_CourseId);
        cv.put("idx_WeekFrom", idx_WeekFrom);
        cv.put("idx_WeekTo", idx_WeekTo);
        cv.put("idx_Week", idx_Week);
        cv.put("idx_TimeFrom", idx_TimeFrom);
        cv.put("idx_TimeTo", idx_TimeTo);
        cv.put("idx_Alarm", idx_Alarm);
        cv.put("idx_Place", idx_Place);
        return cv;
    }

    public String toString() {
        return pk_CoursePeriodId + ", " + fk_CourseId + ", " + idx_WeekFrom + ", " + idx_WeekTo + ", " +
                idx_Week + ", " + idx_TimeFrom + ", " + idx_TimeTo + ", " +
                idx_Alarm + ", " + idx_Place;
    }

}
