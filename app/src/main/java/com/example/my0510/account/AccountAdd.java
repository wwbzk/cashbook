package com.example.my0510.account;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my0510.R;
import com.example.my0510.dao.AccountDao;
import com.example.my0510.dao.AssetsDao;
import com.example.my0510.entity.Assets;
import com.example.my0510.login.LoginActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountAdd extends Activity {

    private TextView tv_main_title,tv_back;
    private EditText et_acc_money,et_acc_remarks;
    private Spinner spinner_acc_type,spinner_accPay_type,spinner_acc_assName;
    private Button btn_acc_save;
    private String etAccName ="",etAccRemarks="",spAccType="",spAccPayType="",spAssNmae="";
    private String etAccMoney;
    private AccountDao accountDao = new AccountDao(this);
    private AssetsDao assetsDao = new AssetsDao(this);
    List<Assets>allList;
    private ArrayAdapter<String> arr_adapter;
    private ArrayAdapter<CharSequence> spinner_acc_assName_List;
    // 为List集合添加内容，用于列表显示

    List<String> data_list = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);

        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        //初始化
        init();

        //下拉框动态赋值
        allList = assetsDao.findAssAll(LoginActivity.getmUsername());
        Log.i("数据",allList.toString());
        for (int i = 0; i <allList.size() ; i++) {
            Assets assetss = allList.get(i);
            String AssetsName =  assetss.getAssetsName();
            data_list.add(AssetsName);
        }
        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner_acc_assName.setAdapter(arr_adapter);


        //获取下拉框的值
        spinner_acc_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //拿到被选择项的值
                spAccType = (String) spinner_acc_type.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        //获取下拉框的值
        spinner_accPay_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //拿到被选择项的值
                spAccPayType = (String) spinner_accPay_type.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        //获取下拉框的值
        spinner_acc_assName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //拿到被选择项的值
                spAssNmae = (String) spinner_acc_assName.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }

    //获取界面控件并处理相关控件的点击事件
    private void init() {
        // 修改顶部样式
        tv_main_title=(TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("添加收支记录");
//        tv_main_title.setTextColor(Color.parseColor("#78A4FA"));
        tv_main_title.setBackgroundColor(Color.parseColor("#78A4FA"));
        spinner_acc_type = (Spinner) findViewById(R.id.spinner_acc_type);
        spinner_accPay_type = (Spinner) findViewById(R.id.spinner_accPay_type);
        spinner_acc_assName = (Spinner) findViewById(R.id.spinner_acc_assName);
        et_acc_money=(EditText) findViewById(R.id.et_acc_money);
        et_acc_remarks=(EditText) findViewById(R.id.et_acc_remarks);
        btn_acc_save=(Button) findViewById(R.id.btn_acc_save);
        tv_back=(TextView) findViewById(R.id.tv_back);


        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭
                AccountAdd.this.finish();
            }
        });

        //保存按钮的点击事件
        btn_acc_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(etAccMoney)) {
                    Toast.makeText(AccountAdd.this, "请输入收支金额", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(etAccRemarks)) {
                    Toast.makeText(AccountAdd.this, "请输入备注", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(AccountAdd.this, "收支添加成功", Toast.LENGTH_SHORT).show();
                    //保存
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
                    //获取当前时间
                    Date date = new Date(System.currentTimeMillis());
                    String time = simpleDateFormat.format(date);
                    Log.i("name",time);
                    Double s = 0.0;
                    if (spAccPayType.equals("收入")){
                        s = Double.valueOf(etAccMoney);
                    }else {
                         s = 0.0 - Double.valueOf(etAccMoney);
                    }
                    Assets assets = assetsDao.findByAssName(spAssNmae);//从资产获取资产名称下的多少钱
                    Double s1 = assets.getAssetsMoney() + s;//资产的和这里的相加
                    assetsDao.updateAssets(assets.getId(),assets.getAssetsName(),assets.getAssetsType(),s1,assets.getRemarks());
                    accountDao.addAccount(s,spAccType,spAccPayType,spAssNmae,time,etAccRemarks, LoginActivity.getmUsername());
//                    double accountMoney,String accountType,String payType,String assetsName,String time,String Remark
                    AccountAdd.this.finish();
                }
            }
        });
    }


    //获取控件上的字符串
    private void getEditString() {
        etAccMoney=et_acc_money.getText().toString().trim();
        etAccRemarks=et_acc_remarks.getText().toString().trim();
    }
}
