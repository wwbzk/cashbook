package com.example.my0510.login;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.my0510.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        TextView tv_version = (TextView) findViewById(R.id.tv_version);
        try {
            //获取程序包信息
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);//getPackageManager()获取设备安装应用程序包对象
            // getPackageInfo：根据包名获取此处flag标签
            tv_version.setText("V" + info.versionName);//程序版本信息
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tv_version.setText("V");
        }
        //利用timer让此界面延迟3秒后跳转，timer有一个线程，这个线程不断执行task
        Timer timer = new Timer();//Timer类是JDK中提供的一个定时器功能，使用时会在主线程之外开启一个单独的线程执行指定任务，任务可以执行一次或者多次
        //TimerTask实现runnable接口，TimerTask类表示在一个指定时间内执行的task
        TimerTask task = new TimerTask() {
            @Override
            public void run() {//跳转主界面的任务代码写在TimerTask的run()方法中
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        timer.schedule(task, 1000);//timer.schedule用于开启TimerTask类 传递两个参数，第一个参数为TimerTask的对象，第二个参数为TimerTask和run()之间的时间差为3秒。
        //设置这个task在延迟3秒后自动执行
    }
}