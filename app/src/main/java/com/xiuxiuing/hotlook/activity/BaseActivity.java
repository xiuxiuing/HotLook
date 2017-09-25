package com.xiuxiuing.hotlook.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.xiuxiuing.hotlook.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wang on 2017/9/25.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private View mContextView = null;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContextView = LayoutInflater.from(this)
                .inflate(bindLayout(), null);
        setContentView(mContextView);
        ButterKnife.bind(this);

        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        showBack();
    }

    public abstract int bindLayout();

    public void initTitle(String text) {
        getSupportActionBar().setTitle(text);
    }

    public void showBack() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
