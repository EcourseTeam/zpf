package com.ecourse.activity;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import es.source.code.activity.R;

public class CourseTable extends AppCompatActivity {

    public ListView list[] = new ListView[7];
    private TabHost tabs   = null;
    public static DataBase db;
    public Cursor[] cursor=new Cursor[7];
    public SimpleCursorAdapter adapter;
    private SharedPreferences pre;

    private DrawerLayout mDrawerLayout;
    //定义手势检测器实例
    private GestureDetector detector = null;
    //定义手势动作两点之间的最小距离
    private final int FLIP_DISTANCE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_info);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                switch (item.getItemId()){
                    case R.id.nav_info:
                        break;
                    case R.id.nav_course:
                        break;
                    case R.id.nav_note:
                        Intent intent_note = new Intent();
                        intent_note.setClass(CourseTable.this, NoteMainActivity.class);
                        startActivity(intent_note);
                        break;
                    case R.id.nav_feedback:
                        break;
                    case R.id.nav_about:
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        //将该activity加入到MyApplication对象实例容器中
        MyApplication.getInstance().addActivity(this);

        db=new DataBase(CourseTable.this);
        pre=getSharedPreferences("firstStart", Context.MODE_PRIVATE);
		/*
		 * 判断程序是否第一次运行，如果是创建数据库表
		 */
        if(pre.getBoolean("firstStart", true)){
            SingleInstance.createTable();
            (pre.edit()).putBoolean("firstStart",false).commit();
//			finish();
        }

        list[0] = (ListView)findViewById(R.id.list0);
        list[1] = (ListView)findViewById(R.id.list1);
        list[2] = (ListView)findViewById(R.id.list2);
        list[3] = (ListView)findViewById(R.id.list3);
        list[4] = (ListView)findViewById(R.id.list4);
        list[5] = (ListView)findViewById(R.id.list5);
        list[6] = (ListView)findViewById(R.id.list6);
        tabs  = (TabHost)findViewById(R.id.tabhost);
        //创建手势检测器
        detector = new GestureDetector(this, new DetectorGestureListener());

        //在配置任何的TabSpec之前，必须在TabHost上调用该方法
        tabs.setup();

        //为主界面注册七个选项卡
        TabHost.TabSpec  spec = null;
        addCard(spec,"tag1",R.id.list0,"日");
        addCard(spec,"tag2",R.id.list1,"一");
        addCard(spec,"tag3",R.id.list2,"二");
        addCard(spec,"tag4",R.id.list3,"三");
        addCard(spec,"tag5",R.id.list4,"四");
        addCard(spec,"tag6",R.id.list5,"五");
        addCard(spec,"tag7",R.id.list6,"六");

        //修改tabHost选项卡中的字体的颜色
        TabWidget tabWidget = tabs.getTabWidget();
        for(int i=0;i<tabWidget.getChildCount();i++){
            TextView tv = (TextView)tabWidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(0xff004499);
        }

        //设置打开时默认的选项卡是当天的选项卡
        tabs.setCurrentTab(ShareMethod.getWeekDay());

        //用适配器为各选项卡添加所要显示的内容
        for(int i=0;i<7;i++){
            cursor[i]= CourseTable.db.select(i);
            list[i].setAdapter(adapter(i));
        }

        //声明一个获取系统音频服务的类的对象
        final AudioManager audioManager = (AudioManager)getSystemService(Service.AUDIO_SERVICE);
        //获取手机之前设置好的铃声模式,该数据将用来传递给activity_set
        final int orgRingerMode = audioManager.getRingerMode();



        for( int day=0;day<7;day++){
            //为七个ListView绑定触碰监听器，将其上的触碰事件交给GestureDetector处理
            //此监听器是必须的，不然滑动手势只在ListView下的空白区域有效，而在ListView上无效
            list[day].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event)   {
                    return detector.onTouchEvent(event);
                }
            });
            //为每个ListView的每个item绑定监听器，点击则弹出由AlertDialog创建的列表对话框进行选择
            list[day].setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        final int id, long arg3) {
                    final int currentDay=tabs.getCurrentTab();
                    final int n=id;
                    final AlertDialog.Builder builder = new AlertDialog.Builder(CourseTable.this);
                    builder.setIcon(R.drawable.ic_launcher2);
                    builder.setTitle("选择");
                    TextView tv=(TextView)arg1.findViewById(R.id.ltext0);
                    Log.i("Test",(tv.getText().toString().equals(""))+"");
                    //如果课程栏目为空就启动添加对话框
                    if((tv.getText()).toString().equals("")){
                        //通过数组资源为对话框中的列表添加选项内容，这里只有一个选项
                        builder.setItems(R.array.edit_options1, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //如果单击了该列表项，则跳转到编辑课程信息的界面
                                if(which == 0){
                                    new MyDialog(CourseTable.this).add(currentDay,n);
                                }
                            }
                        });
                        builder.create().show();
                    }
                    //否则启动修改对话框，或直接删除数据
                    else{
                        builder.setItems(R.array.edit_options2, new DialogInterface.OnClickListener() {

                            @SuppressWarnings("deprecation")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //如果单击了该列表项，则跳转到编辑课程信息的界面
                                if(which == 0){
                                    new MyDialog(CourseTable.this).modify(currentDay,n);
                                }
                                if(which == 1){
                                    cursor[currentDay].moveToPosition(n);
                                    int n1=Integer.parseInt(cursor[currentDay].getString(7));//课程的总节数
                                    int n2=Integer.parseInt(cursor[currentDay].getString(8));//选中的为该课程的第几节
                                    switch(n2){
                                        case 0:
                                            for(int m=0;m<n1;m++){
                                                CourseTable.db.deleteData(currentDay,n+m+1);
                                            }
                                            break;

                                        case 1:
                                            CourseTable.db.deleteData(currentDay,n);
                                            for(int m=1;m<n1;m++){
                                                CourseTable.db.deleteData(currentDay,n+m);
                                            }
                                            break;
                                        case 2:
                                            CourseTable.db.deleteData(currentDay,n-1);
                                            CourseTable.db.deleteData(currentDay,n);
                                            for(int m=2;m<n1;m++){
                                                CourseTable.db.deleteData(currentDay,n+m-1);
                                            }
                                            break;
                                        case 3:
                                            for(int m=n2;m>=0;m--){
                                                CourseTable.db.deleteData(currentDay,n-m+1);
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                    cursor[currentDay].requery();
                                    list[currentDay].invalidate();
                                }
                            }
                        });
                        builder.create().show();
                    }
                }
            });
        }

    }
    //内部类，实现GestureDetector.OnGestureListener接口
    class DetectorGestureListener implements GestureDetector.OnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        //当用户在触屏上“滑过”时触发此方法
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            int i = tabs.getCurrentTab();
            //第一个触点事件的X坐标值减去第二个触点事件的X坐标值超过FLIP_DISTANCE，也就是手势从右向左滑动
            if(e1.getX() - e2.getX() > FLIP_DISTANCE){
                if(i<6)
                    tabs.setCurrentTab(i+1);
                //	float currentX = e2.getX();
                //	list[i].setRight((int) (inialX - currentX));
                return true;
            }

            //第二个触点事件的X坐标值减去第一个触点事件的X坐标值超过FLIP_DISTANCE，也就是手势从左向右滑动
            else if(e2.getX() - e1.getX() > FLIP_DISTANCE){
                if(i>0)
                    tabs.setCurrentTab(i-1);
                return true;
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

    }


    //覆写Activity中的onTouchEvent方法，将该Activity上的触碰事件交给GestureDetector处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    //设置菜单按钮


    //子 方法:为主界面添加选项卡
    public void addCard(TabHost.TabSpec spec,String tag,int id,String name){
        spec = tabs.newTabSpec(tag);
        spec.setContent(id);
        spec.setIndicator(name);
        tabs.addTab(spec);
    }

    /*
     * 为每一个list提供数据适配器
     */
    @SuppressWarnings("deprecation")
    public SimpleCursorAdapter adapter(int i){
        return new SimpleCursorAdapter(this, R.layout.list_v,cursor[i],new String[]{"_id","classes","location",
                "teacher","zhoushu"},new int[]{R.id.number,R.id.ltext0,R.id.ltext1,R.id.ltext6} );
    }

    /*
     * 第一次运行时创建数据库表
     */
    static class SingleInstance{
        static SingleInstance si;
        private SingleInstance(){
            for(int i=0;i<7;i++){
                db.createTable(i);
            }
        }
        static SingleInstance createTable(){
            if(si==null)
                return si=new SingleInstance();
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }


}
