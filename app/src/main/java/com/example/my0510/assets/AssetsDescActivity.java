package com.example.my0510.assets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.example.my0510.dao.AssetsDao;
import com.example.my0510.entity.Assets;

public class AssetsDescActivity extends Activity {

    private AlertDialog.Builder builder;
    EditText et_assdesc_name,et_assdesc_money,et_assdesc_remarks;
    Spinner spinner_assdesc_type;
    private TextView tv_main_title,tv_back;
    private RelativeLayout rl_title_bar;
    ImageView backIv;
    Button btn_assdesc_del,btn_assdesc_updata;
    private String etAssName ="",etAssRemarks="",spAssType="";
    private Double  etAssMoney= 0.00;
    int id;
    private AssetsDao assetsDao = new AssetsDao(this);

    //获取控件上的字符串
    private void getEditString() {
        etAssName=et_assdesc_name.getText().toString().trim();
        etAssMoney=Double.valueOf(et_assdesc_money.getText().toString().trim());
        etAssRemarks=et_assdesc_remarks.getText().toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_desc);
        initView();
//        接受上一级页面传来的数据
        Intent intent = getIntent();
        Assets Abean = (Assets) intent.getSerializableExtra("assets");
//        设置显示控件
        id = Abean.getId();
        et_assdesc_name.setText(Abean.getAssetsName());
        et_assdesc_money.setText(String.valueOf(Abean.getAssetsMoney()));
        et_assdesc_remarks.setText(Abean.getRemarks());

        spinner_assdesc_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,  int position, long id) {
                //拿到被选择项的值
                spAssType = (String) spinner_assdesc_type.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }
    private void initView() {
        et_assdesc_name = findViewById(R.id.et_assdesc_name);
        et_assdesc_money = findViewById(R.id.et_assdesc_money);
        et_assdesc_remarks = findViewById(R.id.et_assdesc_remarks);
        spinner_assdesc_type = findViewById(R.id.spinner_assdesc_type);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        btn_assdesc_updata = (Button) findViewById(R.id.btn_assdesc_updata);
        btn_assdesc_del = (Button) findViewById(R.id.btn_assdesc_del);
        tv_main_title.setText("账户详情");
        tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetsDescActivity.this.finish();
            }
        });


        //保存按钮的点击事件
        btn_assdesc_updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(etAssName)) {
                    Toast.makeText(AssetsDescActivity.this, "请输入账户名称", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(String.valueOf(etAssMoney))) {
                    Toast.makeText(AssetsDescActivity.this, "请输入账户金额", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(etAssRemarks)) {
                    Toast.makeText(AssetsDescActivity.this, "请输入备注", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    /**
                     * 两个按钮的 dialog
                     */
                    builder = new AlertDialog.Builder(AssetsDescActivity.this).setIcon(R.mipmap.ic_launcher).setTitle("修改账户信息")
                            .setMessage("正在修改账户："+etAssName+"，该操作不可撤销，是否确定修改？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //ToDo: 你想做的事情
                                    Toast.makeText(AssetsDescActivity.this, "资产账户修改成功", Toast.LENGTH_SHORT).show();
                                    //保存
                                  assetsDao.updateAssets(id,etAssName, spAssType, etAssMoney, etAssRemarks);
                                  AssetsDescActivity.this.finish();
                                  // 重新启动详情页面
                                  Intent intent = new Intent(AssetsDescActivity.this, AssetsList.class);
                                  startActivity(intent);
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //ToDo: 你想做的事情
                                    Toast.makeText(AssetsDescActivity.this, "已取消修改", Toast.LENGTH_LONG).show();
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create().show();
                }

            }
        });


            //删除按钮的点击事件
        btn_assdesc_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            getEditString();
            builder = new AlertDialog.Builder(AssetsDescActivity.this).setIcon(R.mipmap.ic_launcher).setTitle("删除账户信息")
                    .setMessage("正在删除账户："+etAssName+"，该操作不可撤销，是否确定删除？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //ToDo: 你想做的事情
                            Toast.makeText(AssetsDescActivity.this, "资产账户删除成功", Toast.LENGTH_SHORT).show();
                            //保存
                            assetsDao.deleteAssets(id);
                            AssetsDescActivity.this.finish();
                            // 重新启动详情页面
                            Intent intent = new Intent(AssetsDescActivity.this, AssetsList.class);
                            startActivity(intent);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //ToDo: 你想做的事情
                            Toast.makeText(AssetsDescActivity.this, "已取消删除", Toast.LENGTH_LONG).show();
                            dialogInterface.dismiss();
                        }
                    });
            builder.create().show();
        }
        });


    }

}
