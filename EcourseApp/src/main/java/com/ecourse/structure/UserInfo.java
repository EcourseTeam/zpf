package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

public class UserInfo implements Entry {

    public int pk_UserId;
    public String uk_Username;
    public String idx_Nickname = "";
    public String idx_Password;
    public String idx_Email = "";
    public int fk_SchoolRollId = -1;
    public String idx_StudentNumber = "";
    public int idx_ShareCourse = 1;
    public int idx_Permission = 0;

    public UserInfo(String username, String password) {
        uk_Username = username;
        idx_Password = password;
    }

    public UserInfo(String username, String nickname,
                    String password, String email, int schoolRollId,
                    String studentNumber, int shareCourse, int permission) {
        uk_Username = username;
        idx_Nickname = nickname;
        idx_Password = password;
        idx_Email = email;
        fk_SchoolRollId = schoolRollId;
        idx_StudentNumber = studentNumber;
        idx_ShareCourse = shareCourse;
        idx_Permission = permission;
    }

    public UserInfo(Cursor c) {
        pk_UserId = c.getInt(c.getColumnIndex("pk_UserId"));
        uk_Username = c.getString(c.getColumnIndex("uk_Username"));
        idx_Nickname = c.getString(c.getColumnIndex("idx_Nickname"));
        idx_Password = c.getString(c.getColumnIndex("idx_Password"));
        idx_Email = c.getString(c.getColumnIndex("idx_Email"));
        fk_SchoolRollId = c.getInt(c.getColumnIndex("fk_SchoolRollId"));
        idx_StudentNumber = c.getString(c.getColumnIndex("idx_StudentNumber"));
        idx_ShareCourse = c.getInt(c.getColumnIndex("idx_ShareCourse"));
        idx_Permission = c.getInt(c.getColumnIndex("idx_Permission"));
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("pk_UserId", pk_UserId);
        cv.put("uk_Username", uk_Username);
        cv.put("idx_Nickname", idx_Nickname);
        cv.put("idx_Password", idx_Password);
        cv.put("idx_Email", idx_Email);
        cv.put("fk_SchoolRollId", fk_SchoolRollId);
        cv.put("idx_StudentNumber", idx_StudentNumber);
        cv.put("idx_ShareCourse", idx_ShareCourse);
        cv.put("idx_Permission", idx_Permission);
        return cv;
    }

    public String toString() {
        return pk_UserId + ", " + uk_Username + ", " + idx_Nickname + ", " +
                idx_Password + ", " + idx_Email + ", " + fk_SchoolRollId + ", " +
                idx_StudentNumber + ", " + idx_ShareCourse + ", " + idx_Permission;
    }
}
