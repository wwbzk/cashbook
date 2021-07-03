package com.example.my0510.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my0510.R;
import com.example.my0510.entity.Account;

import java.util.List;

public class AccountAdapter extends BaseAdapter {
    Context context;
    private List<Account> mDatas;

    public AccountAdapter(Context context, List<Account> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    //  决定了ListView列表展示的行数
    @Override
    public int getCount() {
        return mDatas.size();
    }

    //返回指定位置对应的数据
    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    // 返回指定位置所对应的id
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AccountAdapter.ViewHolder holder = null;
        if (convertView == null) {
            //将布局转换成view对象的方法
            convertView = LayoutInflater.from(context).inflate(R.layout.item_infolist_lv,null);
            holder=new AccountAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (AccountAdapter.ViewHolder) convertView.getTag();
        }
        // 加载控件显示的内容
        // 获取集合指定位置的数据
        final Account account = mDatas.get(position);

        int picId = R.drawable.acc_other;
        String as = account.getAccountType();
        if (as.equals("饮食")){
            picId =  R.drawable.acc_eat;
        }else if (as.equals("工资")){
            picId= R.drawable.acc_gongzi;
        } else if (as.equals("交通")){
            picId= R.drawable.acc_jiaotong;
        }else if (as.equals("医疗")){
            picId= R.drawable.acc_yiliao;
        }else if (as.equals("其他")){
            picId= R.drawable.acc_other;
        }

        holder.tvTitle.setText(account.getAssetsName());
        holder.tvTitle2.setText(String.valueOf(account.getAccountMoney()));
        holder.tvTitle3.setText(account.getRemarks());
        holder.time.setText(account.getTime());
        holder.iv.setImageResource(picId);
        return convertView;

    }

    class ViewHolder{
        ImageView iv;
        TextView tvTitle,tvTitle2,tvTitle3,time;
        public ViewHolder(View view){
            iv = view.findViewById(R.id.item_info_iv);
            tvTitle = view.findViewById(R.id.item_info_tv_title);
            tvTitle2 = view.findViewById(R.id.item_info_tv_title2);
            tvTitle3 = view.findViewById(R.id.item_info_tv_title3);
            time = view.findViewById(R.id.item_info_tv_time);
        }
    }
}
