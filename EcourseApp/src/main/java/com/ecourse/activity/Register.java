package com.ecourse.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.source.code.activity.R;
import com.ecourse.database.UserInfoDao;


public class Register extends Activity {
    private EditText username;
    private EditText password;
    private EditText nickname;
    private EditText schoolid;
    private EditText schoolnum;
    private EditText email;
    private Button button_reg;
    private Button button_back;
    private int isFromBack = 1;
    private int result_code1 = 101;
    private int result_code2 = 101;

//    private OfflineDao offlineDao;
    private UserInfoDao userInfoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        button_reg = (Button)findViewById(R.id.button_reg);
        button_back = (Button)findViewById(R.id.button_back);

//        offlineDao = new OfflineDaoImpl(this);
        userInfoDao = new UserInfoDao(this);

        button_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String str1 = username.getText().toString();
                String str2 = password.getText().toString();
//                String str3 = nickname.getText().toString();
//                String str4 = schoolid.getText().toString();
//                String str5 = schoolnum.getText().toString();
//                String str6 = email.getText().toString();

//                ContentValues filter = new ContentValues();
//                filter.put("uk_Username", "q");
//                UserInfo[] entries = (UserInfo[])offlineDao.getEntries(OfflineDao.TABLE_USER_INFO);

                if (!checkString(str1)) {
                    username.setError("输入内容不符合规则");
                }else if (userInfoDao.has(str1)) {
                    Toast.makeText(Register.this, "用户名已被占用!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!checkString(str2)) {
                    password.setError("输入内容不符合规则");
                }
//                else if (str3 == null){
//                    Toast.makeText(Register.this, "昵称不能为空!", Toast.LENGTH_SHORT).show();
//                    return;
//                }else if (str4 == null) {
//                    Toast.makeText(Register.this, "学校不能为空!", Toast.LENGTH_SHORT).show();
//                    return;
//                }else if (str5 == null) {
//                    Toast.makeText(Register.this, "学号不能为空!", Toast.LENGTH_SHORT).show();
//                    return;
//                }else if (str6 == null) {
//                    Toast.makeText(Register.this, "邮箱不能为空!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                else {
//                    UserInfo userInfo = new UserInfo("q", "q");
//                    long tmp = offlineDao.addEntry(OfflineDao.TABLE_USER_INFO, userInfo);
//                    Log.i("test", tmp + "");
                    userInfoDao.add(str1, str2);

//                    UserInfo tmp = getEntries(OfflineDao.TABLE_USER_INFO);
//
//                    for (int i=0; i<tmp.length; ++i) {
//                        Log.i("test", tmp[i].toString());
//                    }
                    Toast.makeText(Register.this, "注册成功!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(Register.this,Login.class);
//                    intent.putExtra("loginState",1);
//                    setResult(result_code1,intent);
                    startActivity(intent);
                }

                }


        });


        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putString("Key3","Return");
                bundle.putInt("isFromBack",isFromBack);
                intent.putExtras(bundle);
                setResult(result_code2,intent);
                finish();
            }
        });

    }


    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
    private boolean checkString(String s) {
        return s.matches("^[a-zA-Z0-9]+$");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

}
