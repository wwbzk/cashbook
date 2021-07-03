package com.example.my0510.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.my0510.entity.Account;
import com.example.my0510.util.MySqliteHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountDao {

    private MySqliteHelper helper;

    public AccountDao(Context context){
        helper = new MySqliteHelper(context);
    }

    public void addAccount(double accountMoney,String accountType,String payType,String assetsName,String time,String Remarks,String username){
        String sql = "insert into tb_account(accountMoney,accountType,payType,assetsName,time,Remarks,username) values (?,?,?,?,?,?,?)";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql,new Object[]{accountMoney,accountType,payType,assetsName,time,Remarks,username});
        db.close();
    }

    public void deleteAccount(int id){
        String sql = "delete from tb_account where id = ? ";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql,new Object[]{id});
        db.close();
    }

    public void updateAccount(int id,double accountMoney,String accountType,String payType,String Remarks){
        String sql = "update tb_account set  accountMoney = ?, accountType = ?, payType = ?,Remarks = ?  where id =? ";
        SQLiteDatabase db = helper.getWritableDatabase();
        //要跟sql语句里面的对齐
        db.execSQL(sql,new Object[]{accountMoney,accountType,payType,Remarks,id});
        db.close();
    }

    // 查询所有的联系人信息
    public List<Account> findAccAll(String username) {
        ArrayList<Account> assetsList = new ArrayList<Account>();
        String sql = "select * from tb_account where username ="+username;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            Double accountMoney = cursor.getDouble(1);
            String accountType = cursor.getString(2);
            String payType = cursor.getString(3);
            String assetsName = cursor.getString(4);
            String time = cursor.getString(5);
            String Remarks = cursor.getString(6);
            Account account = new Account(id,accountMoney,accountType,payType,assetsName,time,Remarks);
            assetsList.add(account);
        }
        cursor.close();
        return assetsList;
    }

    public Account findByid(int id){
        String sql = "select * from tb_account where id = ?";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,new String[]{String.valueOf(id)});
        //判断cursor.moveToFirst()的值为true或false来确定查询结果是否为空
        //cursor.moveToNext()是用来做循环的
        if(cursor.moveToNext()){
            Double accountMoney = cursor.getDouble(1);
            String accountType = cursor.getString(2);
            String payType = cursor.getString(3);
            String assetsName = cursor.getString(4);
            String time = cursor.getString(5);
            String Remarks = cursor.getString(6);
            Account account = new Account(id,accountMoney,accountType,payType,assetsName,time,Remarks);
            return account;
        }
        db.close();
        return null;
    }

    // 查询所有账户余额总
    public Double findAccSumAll(String payType,String username) {
        Double moneySum = null;
        //SELECT sum(accountMoney) FROM tb_account WHERE payType = '收入' 或者支出
        String sql = "select SUM(accountMoney) from tb_account WHERE payType ='"+payType+"' and username ="+username;
        Log.i("该月收支sql语句",sql);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            moneySum = cursor.getDouble(0);
        }
        Log.i("该月收支sql语句", String.valueOf(moneySum));
        cursor.close();
        return moneySum;
    }


    private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    // 查询所有的联系人信息
    public ArrayList<HashMap<String, String>> findAccAssName(String username) {
        String sql = "SELECT assetsName FROM tb_assets where username ="+username;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("code", cursor.getString(0));
//            String assetsName = cursor.getString(0);
            list.add(map);
        }
        for(int i = 0;i<list.size();i++){
            Log.i("name","--->findAll查询结果是-->"+list.toString());
        }
        cursor.close();
        return list;
    }
}
