package com.example.wzw.biubiubiu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wzw.biubiubiu.recycle.RecycleActivity;

import java.util.List;

/**
 * Created by wzw on 2018/3/20.
 */

public class MyAdapter extends BaseAdapter {
    private List<Bean> mDatas;
    private LayoutInflater mInflater;
    private Context mContext;
    public MyAdapter(Context context,List<Bean> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.acticity_listview,parent,false);
            holder  = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.list_image);
            holder.text = (TextView) convertView.findViewById(R.id.list_text);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
            Bean bean = mDatas.get(position);
            holder.image.setImageResource(bean.getImageId());
            holder.text.setText(bean.getConte());
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean.getConte().equals("基本操作")){
                        Intent intent = new Intent(mContext, RecycleActivity.class);
                        mContext.startActivity(intent);
                    }
                }
            });

        return convertView;
    }

    public final class ViewHolder{
        public ImageView image;
        public TextView text;
    }
    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
