package com.xiuxiuing.hotlook.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wang on 2017/9/21.
 */

public class OkHttpUtils {
    private static final String TAG = "OkHttpUtils";
    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;

    private OkHttpUtils() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        mOkHttpClient = builder.build();
    }

    public static OkHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    public void get(String url, final BaseCallBack callBack) {
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailed("", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onResolve(response.body().string());
            }
        });
    }
}
