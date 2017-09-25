package com.xiuxiuing.hotlook;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by wang on 2017/9/22.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        QbSdk.initX5Environment(this, null);
    }
}
