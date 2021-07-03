package com.example.my0510.account;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my0510.R;
import com.example.my0510.assets.AssetsList;
import com.example.my0510.dao.AccountDao;
import com.example.my0510.dao.AssetsDao;
import com.example.my0510.entity.Account;
import com.example.my0510.entity.Assets;

public class AccountDescActivity extends Activity {

    private AlertDialog.Builder builder;
    EditText et_accdesc_money,et_accdesc_remarks;
    Spinner spinner_accdesc_type,spinner_accPaydesc_type;
    private TextView tv_main_title,tv_back;
    private RelativeLayout rl_title_bar;
    ImageView backIv;
    Button btn_accdesc_del,btn_accdesc_updata;
    private String  etAccRemarks="",spAccType="",spAccPayType="";
    private Double  etAccMoney= 0.00;
    int id;
    private AccountDao accountDao = new AccountDao(this);

    //获取控件上的字符串
    private void getEditString() {
        etAccMoney=Double.valueOf(et_accdesc_money.getText().toString().trim());
        etAccRemarks=et_accdesc_remarks.getText().toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_desc);
        initView();
//        接受上一级页面传来的数据
        Intent intent = getIntent();
        Account Abean = (Account) intent.getSerializableExtra("account");
//        设置显示控件
        id = Abean.getId();
        et_accdesc_money.setText(String.valueOf(Abean.getAccountMoney()));
        et_accdesc_remarks.setText(Abean.getRemarks());

        spinner_accdesc_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,  int position, long id) {
                //拿到被选择项的值
                spAccType = (String) spinner_accdesc_type.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        spinner_accPaydesc_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,  int position, long id) {
                //拿到被选择项的值
                spAccPayType = (String) spinner_accPaydesc_type.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }
    private void initView() {
        et_accdesc_money = findViewById(R.id.et_accdesc_money);
        et_accdesc_remarks = findViewById(R.id.et_accdesc_remarks);
        spinner_accdesc_type = findViewById(R.id.spinner_accdesc_type);
        spinner_accPaydesc_type = findViewById(R.id.spinner_accPaydesc_type);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        btn_accdesc_updata = (Button) findViewById(R.id.btn_accdesc_updata);
        btn_accdesc_del = (Button) findViewById(R.id.btn_accdesc_del);
        tv_main_title.setText("收支详情");
        tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDescActivity.this.finish();
            }
        });


        //保存按钮的点击事件
        btn_accdesc_updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(String.valueOf(etAccMoney))) {
                    Toast.makeText(AccountDescActivity.this, "请输入收支费用", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(etAccRemarks)) {
                    Toast.makeText(AccountDescActivity.this, "请输入备注", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    /**
                     * 两个按钮的 dialog
                     */
                    builder = new AlertDialog.Builder(AccountDescActivity.this).setIcon(R.mipmap.ic_launcher).setTitle("修改收支费用信息")
                            .setMessage("正在修改账户："+etAccMoney+"，该操作不可撤销，是否确定修改？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //ToDo: 你想做的事情
                                    Toast.makeText(AccountDescActivity.this, "收支费用信息修改成功", Toast.LENGTH_SHORT).show();
                                    //保存
                                    Double s = 0.0;
                                    if (spAccPayType.equals("收入")){
                                        s = Double.valueOf(etAccMoney);
                                    }else {
                                        s = 0.0 - Double.valueOf(etAccMoney);
                                    }
                                  accountDao.updateAccount(id,s, spAccType, spAccPayType, etAccRemarks);
                                  AccountDescActivity.this.finish();
                                  // 重新启动详情页面
                                  Intent intent = new Intent(AccountDescActivity.this, AccountList.class);
                                  startActivity(intent);
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //ToDo: 你想做的事情
                                    Toast.makeText(AccountDescActivity.this, "已取消修改", Toast.LENGTH_LONG).show();
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create().show();
                }

            }
        });


            //删除按钮的点击事件
        btn_accdesc_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            getEditString();
            builder = new AlertDialog.Builder(AccountDescActivity.this).setIcon(R.mipmap.ic_launcher).setTitle("删除账户信息")
                    .setMessage("正在删除账户："+etAccMoney+"，该操作不可撤销，是否确定删除？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //ToDo: 你想做的事情
                            Toast.makeText(AccountDescActivity.this, "资产账户删除成功", Toast.LENGTH_SHORT).show();
                            //保存
                            accountDao.deleteAccount(id);
                            AccountDescActivity.this.finish();
                            // 重新启动详情页面
                            Intent intent = new Intent(AccountDescActivity.this, AccountList.class);
                            startActivity(intent);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //ToDo: 你想做的事情
                            Toast.makeText(AccountDescActivity.this, "已取消删除", Toast.LENGTH_LONG).show();
                            dialogInterface.dismiss();
                        }
                    });
            builder.create().show();
        }
        });


    }

}
