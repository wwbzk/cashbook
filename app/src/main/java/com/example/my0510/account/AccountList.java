package com.example.my0510.account;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.my0510.R;
import com.example.my0510.assets.AssetsDescActivity;
import com.example.my0510.dao.AccountDao;
import com.example.my0510.entity.Account;
import com.example.my0510.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class AccountList extends Activity {

        private TextView tv_main_title,tv_back;
        private RelativeLayout rl_title_bar;
        ListView showLv;
        List<Account> mDatas;
        List<Account>allList;
        AccountDao dao = new AccountDao(this);

        private AccountAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        //查找控件
        initView();
        //  2.找到ListView对应的数据源
        mDatas = new ArrayList<>();
        allList = dao.findAccAll(LoginActivity.getmUsername());
        mDatas.addAll(allList);
//        3.创建适配器  BaseAdapter的子类
        adapter = new AccountAdapter(this, mDatas);
        showLv.setAdapter(adapter); //4.设置适配器
//        设置单向点击监听功能
        setListener();
    }

        private void setListener() {
        showLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Account abean = mDatas.get(position);
                Intent intent = new Intent(AccountList.this, AccountDescActivity.class);
//                intent.putExtra("assets",Abean);
                intent.putExtra("account",abean);
                startActivity(intent);
            }
        });
    }

        private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("收支详情");
        tv_back=(TextView) findViewById(R.id.tv_back);
        rl_title_bar=(RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountList.this.finish();
            }
        });
        showLv = findViewById(R.id.acc_infolist_lv);
    }
}
