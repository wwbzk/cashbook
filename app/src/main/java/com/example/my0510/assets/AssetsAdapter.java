package com.example.my0510.assets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my0510.R;
import com.example.my0510.entity.Assets;

import java.util.List;

public class AssetsAdapter extends BaseAdapter {

    Context context;
    private List<Assets> mDatas;

    public AssetsAdapter(Context context, List<Assets> mDatas) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            //将布局转换成view对象的方法
            convertView = LayoutInflater.from(context).inflate(R.layout.item_infolist_lv,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 加载控件显示的内容
        // 获取集合指定位置的数据
        final Assets assets = mDatas.get(position);

        int picId = R.drawable.assets_other;
        String as = assets.getAssetsType();
        if (as.equals("现金")){
            picId =  R.drawable.assets_xianjin;
            }else if (as.equals("银行卡")){
            picId= R.drawable.assets_yinhanka;
            } else if (as.equals("支付宝")){
            picId= R.drawable.assets_zfb;
            }else if (as.equals("微信")){
                picId= R.drawable.assets_wx;
            }else if (as.equals("其他")){
                picId= R.drawable.assets_other;
        }

        holder.tvTitle.setText(assets.getAssetsType()+" : "+assets.getAssetsName());
        holder.tvTitle2.setText(String.valueOf(assets.getAssetsMoney()));
        holder.tvTitle3.setText(assets.getRemarks());
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
            //隐藏time组件
            time.setVisibility(View.GONE);
        }
    }


}
