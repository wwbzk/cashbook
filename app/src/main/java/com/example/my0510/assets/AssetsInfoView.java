package com.example.my0510.assets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my0510.R;
import com.example.my0510.util.AnalysisUtils;

/**
 * 菜单栏2
 */
public class AssetsInfoView extends Activity  {

    private RelativeLayout iv_toAddass,btn_ass;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private TextView tv_assets_moneySum;


    public  AssetsInfoView(Activity context){
        mContext = context;
        //为以后将Layout转换为view时用
        mInflater = LayoutInflater.from(mContext);
    }

    //创建视图
    private void createView(){
        //1.初始化以及单向监听
        initView();
    }

    //获取界面控件
    private void initView() {
        //设置布局文件
        mCurrentView = mInflater.inflate(R.layout.main_view_assets,null);
        iv_toAddass = (RelativeLayout) mCurrentView.findViewById(R.id.iv_toAddass);
        btn_ass = (RelativeLayout)mCurrentView.findViewById(R.id.btn_ass_list);

        //添加的点击事件
        iv_toAddass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(readLoginStatus()){
                    //跳转到添加界面
                    Intent intent = new Intent(mContext, AssetsAddActivity.class);
                    mContext.startActivityForResult(intent,1);
                }else{
                    Toast.makeText(mContext,"您还未登录，请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_ass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(readLoginStatus()){
                    //跳转到详情界面
                    Intent intent = new Intent(mContext, AssetsList.class);
                    mContext.startActivityForResult(intent,1);
                }else{
                    Toast.makeText(mContext,"您还未登录，请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    //获取当前导航栏上方显示对应的View
    public View getView(){
        if (mCurrentView == null){
            createView();
        }
        return mCurrentView;
    }

    //显示当前导航栏上方显示对应的View界面
    public  void showView() {
        if(mCurrentView == null){
            createView();
        }
        tv_assets_moneySum = (TextView) mCurrentView.findViewById(R.id.tv_assets_moneySum);
        //查询全部金额
        if(readLoginStatus()) {
            tv_assets_moneySum.setText(String.valueOf(AnalysisUtils.showSum(mContext)));
        }else {
            tv_assets_moneySum.setText("0.00");
        }

//        tv_assets_moneySum.setText(LoginActivity.getmUsername());
        mCurrentView.setVisibility(View.VISIBLE);
    }

    //从SharedPreferences中读取登录状态
    private boolean readLoginStatus(){
        SharedPreferences sp=mContext.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin=sp.getBoolean("isLogin",false);
        return isLogin;
    }


}
