package com.example.my0510.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySqliteHelper extends SQLiteOpenHelper {
    public MySqliteHelper( Context context) {
        //版本号大于一执行，下次执行修改版本号
        super(context, "assetsDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*创建数据库*/
        db.execSQL("create table tb_assets (id integer primary key autoincrement," +
                "assetsName varchar(255),assetsType varchar(255),assetsMoney varchar(255),Remarks varchar(255),username varchar(255))");
        db.execSQL("create table tb_account (id integer primary key autoincrement," +
                "accountMoney varchar(255),accountType varchar(255),payType varchar(255)," +
                "assetsName varchar(255),time varchar(255),Remarks varchar(255),username varchar(255))");
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
