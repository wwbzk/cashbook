package com.example.my0510;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.my0510.dao.AccountDao;
import com.example.my0510.entity.Account;
import com.example.my0510.util.MySqliteHelper;
import com.example.my0510.dao.AssetsDao;
import com.example.my0510.entity.Assets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class AssetsDaoTest {
    //创建数据库
    @Test
    public void createDBTest(){
        MySqliteHelper helper = new MySqliteHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        helper.getWritableDatabase();
    }
    private AssetsDao assetsDao;
    private AccountDao accountDao;

    @Before
    public void init(){
        assetsDao = new AssetsDao(InstrumentationRegistry.getInstrumentation().getTargetContext());
        accountDao = new AccountDao(InstrumentationRegistry.getInstrumentation().getTargetContext());
    }

    @Test
    public void addTest(){
        assetsDao.addAssets("测试名称1111","现金",11.0,"您备注55555");
        Log.i("name","-->添加成功");
    }
    @Test
    public void deleteTest(){
        assetsDao.deleteAssets(2);
        Log.i("name","-->删除成功");
    }
    @Test
    public void update(){
        assetsDao.updateAssets(3,"测试名称22221111","现金223333322",12.0,"您备注2223442222");
        Log.i("name","-->更新成功");
    }
    @Test
    public void findbyId(){
        Assets assets = assetsDao.findByid(1);
        Log.i("name","--findbyId查询结果 --> "+assets.toString());
    }
    @Test
    public void findAll(){
        List<Assets> assets = assetsDao.findAssAll();
        for(Assets u:assets){
            Log.i("name","--->findAll查询结果是-->"+u.toString());
        }
    }

    @Test
    public void findsumAll(){
        List<Assets> assets = assetsDao.findAssAll();
        for(Assets u:assets){
            Log.i("name","--->findAll查询结果是-->"+u.toString());
        }
    }

    @Test
    public void addAccount(){
        accountDao.addAccount(12.0,"dd","1dw","qdw","1","1");
        Log.i("name","-->添加成功");
        List<Account> accounts = accountDao.findAccAll();
        for(Account u:accounts){
            Log.i("name","--->findAll查询结果是-->"+u.toString());
        }

    }

    @Test
    public void listAssName(){
        accountDao.findAccAssName();

    }

}
