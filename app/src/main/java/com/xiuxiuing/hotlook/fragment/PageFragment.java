package com.xiuxiuing.hotlook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiuxiuing.hotlook.R;
import com.xiuxiuing.hotlook.adapter.PageRecyclerAdapter;
import com.xiuxiuing.hotlook.bean.MobPageBean;
import com.xiuxiuing.hotlook.bean.MobPageItemBean;
import com.xiuxiuing.hotlook.core.CoreConsts;
import com.xiuxiuing.hotlook.http.MobBaseRsp;
import com.xiuxiuing.hotlook.http.OkHttpUtils;
import com.xiuxiuing.hotlook.http.ServerCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wang on 2017/9/15.
 */

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage = 1;
    private String cid;

    private List<MobPageItemBean> itemLists;
    private PageRecyclerAdapter pageAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRv;

    public static PageFragment newInstance(String cid) {
        Bundle args = new Bundle();
        args.putString(ARG_PAGE, cid);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cid = getArguments().getString(ARG_PAGE);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        itemLists = new ArrayList<>();
        pageAdapter = new PageRecyclerAdapter(itemLists);
        mRv.setLayoutManager(new LinearLayoutManager(mRv.getContext()));
        mRv.setAdapter(pageAdapter);

        OkHttpUtils.getInstance().get(String.format(CoreConsts.MOB_HTTP_SEARCH, mPage, cid), new ServerCallback<MobBaseRsp<MobPageBean>, MobPageBean>() {
            @Override
            public void onSuccess(final MobPageBean data) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        itemLists.addAll(data.getList());
                        pageAdapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onFailure(String errcode, String msg) {

            }
        });

        return view;
    }
}
