package com.ecourse.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import es.source.code.activity.R;

public class More extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        //将该activity加入到MyApplication对象实例容器中
        MyApplication.getInstance().addActivity(this);

        TextView backButton = (TextView) findViewById(R.id.backtoMainButton);


        //为返回按钮绑定监听器
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
//				Intent intent = new Intent(Set.this,MainActivity.class);
//				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新
//				startActivity(intent);
            }
        });
    }

    public void click_note(View v){
        Intent intent = new Intent(More.this, NoteMainActivity.class);
        startActivity(intent);
    }

//    //如果点击“关于我们”的TextView，则跳转
//    public void click_us(View v){
//        Intent intent = new Intent(More.this, AboutUsActivity.class);
//        startActivity(intent);
//    }
//    //如果点击“版本支持”的TextView，则跳转
//    public void click_version(View v){
//        Intent intent = new Intent(More.this, VersionActivity.class);
//        startActivity(intent);
//    }
//    public void click_revision(View v){
//        Log.i("MyDebug", "revision");
//    }
}
