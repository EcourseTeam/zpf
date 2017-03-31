package com.ecourse.database;

import android.content.ContentValues;
import android.content.Context;

import com.ecourse.structure.UserInfo;

public class UserInfoDao extends OfflineDaoImpl {

    public UserInfoDao(Context ctx) {
        super(ctx);
    }

    public long add(String username, String password) {
        return add(username, username, password, "", -1, "", 1, 0);
    }

    public long add(String username, String nickname,
                    String password, String email, int schoolRollId,
                    String studentNumber, int shareCourse, int permission) {
        UserInfo userInfo = new UserInfo(username, nickname, password, email, schoolRollId, studentNumber, shareCourse, permission);
        return this.addEntry(TABLE_USER_INFO, userInfo);
    }

    public boolean has(String username) {
        UserInfo userInfo = get(username);
        return (userInfo != null);
    }

    public UserInfo get(String username) {
        ContentValues filter = new ContentValues();
        filter.put("uk_Username", username);
        UserInfo[] userInfos = (UserInfo[]) getEntries(TABLE_USER_INFO, filter);
        if (userInfos.length > 0) {
            return userInfos[0];
        } else {
            return null;
        }
    }

    public boolean check(String username, String password) {
        UserInfo userInfo = get(username);
        if (username.equals(userInfo.uk_Username) && password.equals(userInfo.idx_Password)) {
            return true;
        } else {
            return false;
        }
    }

}
