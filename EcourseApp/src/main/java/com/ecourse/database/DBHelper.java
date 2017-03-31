package com.ecourse.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME ="offline.db"; //数据库名
    private final static int VERSION = 1; //版本号

    private final static String tag = "DBHelper";

    //自带的构造方法
    public DBHelper(Context ctx, String name, CursorFactory factory,
                    int version) {
        super(ctx, name, factory, version);
    }

    //为了每次构造时不用传入dbName和版本号，自己得新定义一个构造方法
    public DBHelper(Context ctx){
        this(ctx, DB_NAME, null, VERSION);//调用上面的构造方法
    }

    //版本变更时
    public DBHelper(Context ctx,int version) {
        this(ctx, DB_NAME, null, version);
    }

    //当数据库创建的时候调用
    public void onCreate(SQLiteDatabase db) {
        Log.i(tag, "Create Table!");
        ContentValues cv;
        db.execSQL("create table " + OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_KEY_POOL] + "(" +
                "pk_TableName varchar(32) primary key," +
                "idx_KeyMax int)");
        Log.i(tag, OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_KEY_POOL] + " created!");
        /* UserInfo */
        db.execSQL("create table " + OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_USER_INFO] + "(" +
                "pk_UserId int primary key," +
                "uk_Username varchar(32)," +
                "idx_Nickname text," +
                "idx_Password varchar(32)," +
                "idx_Email varchar(64)," +
                "fk_SchoolRollId int," +
                "idx_StudentNumber varchar(32)," +
                "idx_ShareCourse int," +
                "idx_Permission int)");
        cv = new ContentValues();
        cv.put("pk_TableName", OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_USER_INFO]);
        cv.put("idx_KeyMax", 0);
        db.insert(OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_KEY_POOL], null, cv);
        Log.i(tag, OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_USER_INFO] + " created!");
        /* SchoolRollInfo */
        db.execSQL("create table " + OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_SCHOOL_ROLL_INFO] + "(" +
                "pk_SchoolRollId int primary key," +
                "fk_SchoolId int," +
                "fk_MajorId int)");
        cv = new ContentValues();
        cv.put("pk_TableName", OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_SCHOOL_ROLL_INFO]);
        cv.put("idx_KeyMax", 0);
        db.insert(OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_KEY_POOL], null, cv);
        Log.i(tag, OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_SCHOOL_ROLL_INFO] + " created!");
        /* SchoolInfo */
        db.execSQL("create table " + OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_SCHOLL_INFO] + "(" +
                "pk_SchoolId int primary key," +
                "uk_SchoolName text)");
        cv = new ContentValues();
        cv.put("pk_TableName", OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_SCHOOL_ROLL_INFO]);
        cv.put("idx_KeyMax", 0);
        db.insert(OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_KEY_POOL], null, cv);
        Log.i(tag, OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_USER_INFO] + " created!");
        /* AcademyInfo */
        db.execSQL("create table " + OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_ACADEMY_INFO] + "(" +
                "pk_AcademyId int primary key," +
                "fk_SchoolId int," +
                "idx_AcademyName text)");
        cv = new ContentValues();
        cv.put("pk_TableName", OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_ACADEMY_INFO]);
        cv.put("idx_KeyMax", 0);
        db.insert(OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_KEY_POOL], null, cv);
        Log.i(tag, OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_ACADEMY_INFO] + " created!");
        /* MajorInfo */
        db.execSQL("create table " + OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_MAJOR_INFO] + "(" +
                "pk_MajorId int primary key," +
                "fk_AcademyId int," +
                "idx_MajorName text," +
                "idx_Education text," +
                "idx_Semester int," +
                "idx_Week int)");
        cv = new ContentValues();
        cv.put("pk_TableName", OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_MAJOR_INFO]);
        cv.put("idx_KeyMax", 0);
        db.insert(OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_KEY_POOL], null, cv);
        Log.i(tag, OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_MAJOR_INFO] + " created!");
        /* TeacherInfo */
        db.execSQL("create table " + OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_TEACHER_INFO] + "(" +
                "pk_TeacherId int primary key," +
                "fk_AcademyId int," +
                "idx_TeacherName text," +
                "idx_OfficePhone var(32)," +
                "idx_CellPhone var(32)," +
                "idx_Email var(32))");
        cv = new ContentValues();
        cv.put("pk_TableName", OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_TEACHER_INFO]);
        cv.put("idx_KeyMax", 0);
        db.insert(OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_KEY_POOL], null, cv);
        Log.i(tag, OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_TEACHER_INFO] + " created!");
        /* CourseInfo */
        db.execSQL("create table " + OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_COURSE_INFO] + "(" +
                "pk_CourseId int primary key," +
                "fk_AcademyId int," +
                "idx_CourseName text," +
                "idx_CourseShortName text," +
                "idx_CourseNumber var(16)," +
                "fk_TeacherId int," +
                "idx_Semester int," +
                "idx_CourseHours int," +
                "idx_Materials text)");
        cv = new ContentValues();
        cv.put("pk_TableName", OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_COURSE_INFO]);
        cv.put("idx_KeyMax", 0);
        db.insert(OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_KEY_POOL], null, cv);
        Log.i(tag, OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_COURSE_INFO] + " created!");
        /* CoursePeriodInfo */
        db.execSQL("create table " + OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_COURSE_PERIOD_INFO] + "(" +
                "pk_CoursePeriodId int primary key," +
                "fk_CourseId int," +
                "idx_WeekFrom int," +
                "idx_WeekTo int," +
                "idx_Week int," +
                "idx_TimeFrom int," +
                "idx_TimeTo int," +
                "idx_Alarm int," +
                "idx_Place text)");
        cv = new ContentValues();
        cv.put("pk_TableName", OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_COURSE_PERIOD_INFO]);
        cv.put("idx_KeyMax", 0);
        db.insert(OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_KEY_POOL], null, cv);
        Log.i(tag, OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_COURSE_PERIOD_INFO] + " created!");
        /* CourseTableInfo */
        db.execSQL("create table " + OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_COURSE_TABLE_INFO] + "(" +
                "pk_CourseTableId int primary key," +
                "fk_UserId int," +
                "idx_CourseTableName text)");
        cv = new ContentValues();
        cv.put("pk_TableName", OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_COURSE_TABLE_INFO]);
        cv.put("idx_KeyMax", 0);
        db.insert(OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_KEY_POOL], null, cv);
        Log.i(tag, OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_COURSE_TABLE_INFO] + " created!");
        /* CourseTableEntryInfo */
        db.execSQL("create table " + OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_COURSE_TABLE_ENTRY_INFO] + "(" +
                "pk_CourseTableEntryId int primary key," +
                "fk_CourseTableId int," +
                "fk_CoursePeriodId int)");
        cv = new ContentValues();
        cv.put("pk_TableName", OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_COURSE_TABLE_ENTRY_INFO]);
        cv.put("idx_KeyMax", 0);
        db.insert(OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_KEY_POOL], null, cv);
        Log.i(tag, OfflineDao.TABLE_ARRAY[OfflineDao.TABLE_COURSE_TABLE_ENTRY_INFO] + " created!");
    }

    //版本更新时调用
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql  = "update student ....";//自己的Update操作
        db.execSQL(sql);
    }

}