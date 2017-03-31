package com.ecourse.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.source.code.activity.R;
import com.ecourse.database.UserInfoDao;
import com.ecourse.structure.UserInfo;

public class Login extends Activity {
    private EditText username;
    private EditText password;
    private Button button_ok;
    private Button button_back;
//    private OfflineDao offlineDao;
    private UserInfoDao userInfoDao;
    public Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        button_ok = (Button)findViewById(R.id.button_ok);
        button_back = (Button)findViewById(R.id.button_back);
//        offlineDao = new OfflineDaoImpl(this);
        userInfoDao = new UserInfoDao(this);


        button_ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String str1 = username.getText().toString();
                String str2 = password.getText().toString();

                ContentValues filter = new ContentValues();
                UserInfo userInfo = userInfoDao.get(str1);
//                filter.put("uk_Username", str1);
//                filter.put("idx_Password", str2);
//                UserInfo[] entries = (UserInfo[])offlineDao.getEntries(OfflineDao.TABLE_USER_INFO);
                //Log.i("test", entries[0].toString());

                if (userInfoDao.check(str1, str2)){
                    Intent intent = new Intent();
                    intent.setClass(Login.this,CourseTable.class);
                    startActivity(intent);
                    Toast.makeText(Login.this, "登录成功!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login.this, "用户名或密码错误!", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });


        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                Bundle bundle = new Bundle();
//
//                    bundle.putString("Key3","Return");
//                    bundle.putInt("isFromBack",isFromBack);
//                    intent.putExtras(bundle);
//                    setResult(result_code2,intent);
                    finish();
            }
        });

    }
}
