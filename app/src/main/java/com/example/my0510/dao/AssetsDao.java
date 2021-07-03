package com.example.my0510.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.my0510.util.MySqliteHelper;
import com.example.my0510.entity.Assets;

import java.util.ArrayList;
import java.util.List;


public class AssetsDao {
    private MySqliteHelper helper;

    public AssetsDao(Context context){
        helper = new MySqliteHelper(context);
    }

    public void addAssets(String assetsName,String assetsType,Double assetsMoney,String Remarks,String username){
        String sql = "insert into tb_assets(assetsName,assetsType,assetsMoney,Remarks,username) values (?,?,?,?,?)";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql,new Object[]{assetsName,assetsType,assetsMoney,Remarks,username});
        db.close();
    }
//    public void addAssets(Assets assets){
//        String sql = "insert into tb_assets(assetsName,assetsType,assetsMoney,Remarks) values (?,?,?,?)";
//        SQLiteDatabase db = helper.getWritableDatabase();
//        db.execSQL(sql,new Object[]{assets.getAssetsName(),assets.getAssetsType(),assets.getAssetsMoney(),assets.getRemarks()});
//        db.close();
//    }

    public void deleteAssets(int id){
        String sql = "delete from tb_assets where id = ? ";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql,new Object[]{id});
        db.close();
    }

    public void updateAssets(int id,String assetsName,String assetsType,Double assetsMoney,String Remarks){
        String sql = "update tb_assets set  assetsName = ?, assetsType = ?, assetsMoney = ?, Remarks = ? where id =? ";
        SQLiteDatabase db = helper.getWritableDatabase();
        //要跟sql语句里面的对齐
        db.execSQL(sql,new Object[]{assetsName,assetsType,assetsMoney,Remarks,id});
        db.close();
    }

    // 查询所有的联系人信息
    public List<Assets> findAssAll(String username) {
        ArrayList<Assets> assetsList = new ArrayList<Assets>();
        String sql = "select * from tb_assets where username ="+username +" ";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String assetsName = cursor.getString(1);
            String assetsType = cursor.getString(2);
            Double assetsMoney = cursor.getDouble(3);
            String Remarks = cursor.getString(4);
            Assets assets = new Assets(id,assetsName,assetsType,assetsMoney,Remarks);
            assetsList.add(assets);
        }
        cursor.close();
        return assetsList;
    }

    public Assets findByid(int id){
        String sql = "select * from tb_assets where id = ?";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,new String[]{String.valueOf(id)});
        //判断cursor.moveToFirst()的值为true或false来确定查询结果是否为空
        //cursor.moveToNext()是用来做循环的
        if(cursor.moveToNext()){
            String assetsName = cursor.getString(1);
            String assetsType = cursor.getString(2);
            Double assetsMoney = cursor.getDouble(3);
            String Remarks = cursor.getString(4);
            Assets assets = new Assets(id,assetsName,assetsType,assetsMoney,Remarks);
            return assets;
        }
        db.close();
        return null;
    }

    // 查询所有账户余额总
    public Double findAssSumAll(String username) {
        Double moneySum = null;
        String sql = "select SUM(assetsMoney) from tb_assets where username ="+username;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            moneySum = cursor.getDouble(0);
        }
        Log.i("sumdao", String.valueOf(moneySum));
        cursor.close();
        return moneySum;
    }


    public Assets findByAssName(String name) {
        String sql = "select * from tb_assets where assetsName = ?";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,new String[]{name});
        //判断cursor.moveToFirst()的值为true或false来确定查询结果是否为空
        //cursor.moveToNext()是用来做循环的
        if(cursor.moveToNext()){
            int id =  cursor.getInt(0);
            String assetsName = cursor.getString(1);
            String assetsType = cursor.getString(2);
            Double assetsMoney = cursor.getDouble(3);
            String Remarks = cursor.getString(4);
            Assets assets = new Assets(id,assetsName,assetsType,assetsMoney,Remarks);
            return assets;
        }
        db.close();
        return null;
    }
}
