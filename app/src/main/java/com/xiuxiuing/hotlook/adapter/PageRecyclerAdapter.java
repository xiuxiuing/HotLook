package com.xiuxiuing.hotlook.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiuxiuing.hotlook.R;
import com.xiuxiuing.hotlook.activity.WebActivity;
import com.xiuxiuing.hotlook.bean.MobPageItemBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wang on 2017/9/21.
 */

public class PageRecyclerAdapter extends RecyclerView.Adapter<PageRecyclerAdapter.PageViewHolder> {

    private Context mContext;
    private List<MobPageItemBean> lists;

    public PageRecyclerAdapter(List lists) {
        this.lists = lists;
    }

    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_title, parent, false);
        return new PageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PageViewHolder holder, final int position) {
        holder.tvTitle.setText(lists.get(position).getTitle());
        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWebActivity(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class PageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv_title_item)
        CardView cvItem;

        @BindView(R.id.tv_title)
        TextView tvTitle;


        public PageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    private void startWebActivity(int position) {
        Intent intent = new Intent();
        intent.putExtra("web", lists.get(position));
        intent.setClass(mContext, WebActivity.class);
        mContext.startActivity(intent);
    }
}
