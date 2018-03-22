package com.example.wzw.biubiubiu.recycle.movie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wzw.biubiubiu.R;
import com.example.wzw.biubiubiu.recycle.movie.Girl.ResultsBean;
import java.util.List;

/**
 * Created by wzw on 2018/3/22.
 */

public class Girladapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ResultsBean> mList;

    public Girladapter(Context context, List<ResultsBean> list){
        mContext = context;
        mList = list;
    }

    public void setList(List<Girl.ResultsBean> list){
        mList.addAll(list);
    }

    public void cleanList(){
        mList.clear();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new girlViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_girl,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ResultsBean resultsBean = mList.get(position);
        girlViewHolder viewHolder = (girlViewHolder) holder;

        Glide.with(mContext).load(resultsBean.getUrl()).asBitmap().into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    public class girlViewHolder extends RecyclerView.ViewHolder{

        private final ImageView mImageView;

        public girlViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.girl_item);
        }
    }
}
