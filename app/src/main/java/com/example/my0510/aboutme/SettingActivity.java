package com.example.my0510.aboutme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my0510.R;

public class SettingActivity extends Activity {

    private TextView tv_main_title,tv_back;
    private RelativeLayout rl_title_bar,rl_modify_psw,rl_exit_login;
    public static  SettingActivity instance=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        instance = this;
        init();
    }

    //    获取界面控件
    private void init() {
        // TODO Auto-generated method stub
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("设置");
        tv_back=(TextView) findViewById(R.id.tv_back);
        rl_title_bar=(RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));
        rl_modify_psw=(RelativeLayout)findViewById(R.id.rl_modify_pwd);
        rl_exit_login=(RelativeLayout)findViewById(R.id.rl_exit_login);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });

        //修改密码的点击事件
        rl_modify_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到修改密码的界面
                Intent intent=new Intent(SettingActivity.this, ModifyPwdActivity.class);
                startActivity(intent);

            }
        });


        //退出登录的点击事件
        rl_exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this,"退出登录成功",Toast.LENGTH_SHORT).show();
                clearLoginStatus();//清除登录状态和登录时的用户名
                //退出登录成功后把退出成功状态传递到MainActivity中
                Intent data = new Intent();
                data.putExtra("isLogin",false);
                setResult(RESULT_OK,data);
                SettingActivity.this.finish();
            }

        });
    }

    //	清除SharePrederences中的登录状态和登录时的登录名
    private void clearLoginStatus() {
        // TODO Auto-generated method stub
        SharedPreferences sp = getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sp.edit();  //获取编辑器
        editor.putBoolean("isLogin",false);        //清除登录状态
        editor.putString("loginUserName","");    //清除用户名
        editor.commit();
    }

}
