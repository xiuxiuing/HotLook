package com.xiuxiuing.hotlook.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiuxiuing.hotlook.fragment.PageFragment;
import com.xiuxiuing.hotlook.bean.MobCategoryBean;

import java.util.List;

/**
 * Created by wang on 2017/9/15.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    //    final int PAGE_COUNT = 3;
//    private String tabTitles[] = new String[]{"tab1", "tab2", "tab3"};
    private List tablists;
    private Context context;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm, List lists) {
        super(fm);
        this.context = context;
        this.tablists = lists;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(((MobCategoryBean) tablists.get(position)).getCid());
    }

    @Override
    public int getCount() {
        return tablists.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((MobCategoryBean) tablists.get(position)).getName();
    }
}
