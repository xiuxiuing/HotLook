package com.xiuxiuing.hotlook.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import android.widget.Toast;

import com.xiuxiuing.hotlook.R;
import com.xiuxiuing.hotlook.adapter.SimpleFragmentPagerAdapter;
import com.xiuxiuing.hotlook.bean.MobCategoryBean;
import com.xiuxiuing.hotlook.core.CoreConsts;
import com.xiuxiuing.hotlook.http.MobBaseRsp;
import com.xiuxiuing.hotlook.http.OkHttpUtils;
import com.xiuxiuing.hotlook.http.ServerCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navView;

    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private List<MobCategoryBean> tabLists;
    private SimpleFragmentPagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        tabLists = new ArrayList<>();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        navView.setNavigationItemSelectedListener(this);

//        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);


        OkHttpUtils.getInstance().get(CoreConsts.MOB_HTTP_CATEGORY, new ServerCallback<MobBaseRsp<List<MobCategoryBean>>, List<MobCategoryBean>>() {
            @Override
            public void onSuccess(final List<MobCategoryBean> datas) {
                System.out.println("onSuccess");
                tabLists = datas;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pagerAdapter = new SimpleFragmentPagerAdapter(MainActivity.this, getSupportFragmentManager(), datas);
                        viewPager.setAdapter(pagerAdapter);


                        tabLayout.setupWithViewPager(viewPager);
                        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                    }
                });

            }

            @Override
            public void onFailure(String errcode, String msg) {
                System.out.println("onFailure");
            }
        });

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_camera:
                    Toast.makeText(MainActivity.this, "开始游戏", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_gallery:
                    Toast.makeText(MainActivity.this, "结束游戏", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
            return true;
        }
    };


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                Toast.makeText(MainActivity.this, "开始游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_gallery:
                Toast.makeText(MainActivity.this, "结束游戏", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
