package com.example.my0510.assets;

import com.example.my0510.R;
import com.example.my0510.aboutme.SettingActivity;
import com.example.my0510.dao.AssetsDao;
import com.example.my0510.entity.Assets;
import com.example.my0510.login.LoginActivity;
import com.example.my0510.util.AnalysisUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 账户加载信息页
 */
public class AssetsList extends Activity {

    private TextView tv_main_title,tv_back;
    private RelativeLayout rl_title_bar;
    ListView showLv;
    List<Assets> mDatas;
    List<Assets>allList;
    AssetsDao dao = new AssetsDao(this);

    private AssetsAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_list);
        //查找控件
        initView();
        //  2.找到ListView对应的数据源
        mDatas = new ArrayList<>();
        allList = dao.findAssAll(LoginActivity.getmUsername());
        mDatas.addAll(allList);
//        3.创建适配器  BaseAdapter的子类
        adapter = new AssetsAdapter(this, mDatas);
        showLv.setAdapter(adapter); //4.设置适配器
//        设置单向点击监听功能
        setListener();
    }

    private void setListener() {
        showLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Assets abean = mDatas.get(position);
                Intent intent = new Intent(AssetsList.this, AssetsDescActivity.class);
//                intent.putExtra("assets",Abean);
                intent.putExtra("assets",abean);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("账户详情");
        tv_back=(TextView) findViewById(R.id.tv_back);
        rl_title_bar=(RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetsList.this.finish();
            }
        });
        showLv = findViewById(R.id.ass_infolist_lv);
    }
}
