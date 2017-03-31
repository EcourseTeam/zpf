package com.ecourse.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;

/**
 * Created by zpf on 2016/10/18.
 */

public class MyAdapter {

    private Context context;
    private CourseTable main;
    private Cursor[] cursor=new Cursor[7];
    private SimpleCursorAdapter[] adapter;

    private SharedPreferences preferences;

    public MyAdapter(Context context){
        this.context=context;
        main=(CourseTable) context;
    }
    public void test(){



    }

}
