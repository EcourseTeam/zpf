package com.ecourse.database;

import android.content.ContentValues;

import com.ecourse.structure.Entry;

public interface OfflineDao {

    int TABLE_KEY_POOL = 0;
    int TABLE_USER_INFO = 1;
    int TABLE_SCHOOL_ROLL_INFO = 2;
    int TABLE_SCHOLL_INFO = 3;
    int TABLE_ACADEMY_INFO = 4;
    int TABLE_MAJOR_INFO = 5;
    int TABLE_TEACHER_INFO = 6;
    int TABLE_COURSE_INFO = 7;
    int TABLE_COURSE_PERIOD_INFO = 8;
    int TABLE_COURSE_EVALUATION_INFO = 9;
    int TABLE_NOTES_INFO = 10;
    int TABLE_COURSE_TABLE_INFO = 11;
    int TABLE_COURSE_TABLE_ENTRY_INFO = 12;
    int TABLE_MEMO_INFO = 13;
    int TABLE_GROUP_INFO = 14;
    int TABLE_GROUP_TOPIC_INFO = 15;
    int TABLE_NOTICE_INFO = 16;
    int TABLE_HOT_SPOT_INFO = 17;
    int TABLE_HOT_SPOT_COMMENT_INFO = 18;
    int TABLE_MIS_INFO = 19;

    String [] TABLE_ARRAY = {
            "tbl_KeyPool",          /* 0 键池 */
            "tbl_UserInfo",         /* 1 用户信息 */
            "tbl_SchoolRollInfo",   /* 2 学籍信息 */
            "tbl_SchoolInfo",       /* 3 学校信息 */
            "tbl_AcademyInfo",      /* 4 学院信息 */
            "tbl_MajorInfo",        /* 5 专业信息 */
            "tbl_TeacherInfo",      /* 6 教师信息 */
            "tbl_CourseInfo",       /* 7 课程信息 */
            "tbl_CoursePeriodInfo", /* 8 课时信息 */
            "tbl_CourseEvaluation", /* 9 课程评价信息 */
            "tbl_NotesInfo",        /* 10 笔记信息 */
            "tbl_CourseTableInfo",  /* 11 课程表信息 */
            "tbl_CourseTableEntryInfo", /* 12 课程表记录信息 */
            "tbl_MemoInfo",         /* 13 备忘信息 */
            "tbl_GroupInfo",        /* 14 讨论组信息 */
            "tbl_GroupTopicInfo",   /* 15 讨论组内容信息 */
            "tbl_NoticeInfo",       /* 16 公告信息 */
            "tbl_HotSpotInfo",      /* 17 校园热点信息 */
            "tbl_HotSpotCommentInfo",   /* 18 校园热点评论信息 */
            "tbl_MISInfo"           /* 19 教务系统信息 */
    };

    long addEntry(int table, Entry newEntry);
    long addEntry(int table, ContentValues newEntryCV);
    int updateEntries(int table, ContentValues filter, Entry newEntry);
    int updateEntries(int table, ContentValues filter, ContentValues newEntryCV);
    int deleteEntries(int table, ContentValues filter);
    Entry[] getEntries(int table);
    Entry[] getEntries(int table, ContentValues filter);

    void closeDB();
}
