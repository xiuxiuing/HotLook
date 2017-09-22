package com.xiuxiuing.hotlook.http;

import com.google.gson.Gson;

/**
 * Created by wang on 2017/9/21.
 */

public abstract class ServerCallback<T, V> extends BaseCallBack<T> {

    @Override
    public void onResolve(String json) {
        boolean returnJson = false;
        if (genericityType instanceof Class) {
            switch (((Class) genericityType).getSimpleName()) {
                case "Object":
                case "String":
                    returnJson = true;
                    break;
                default:
                    break;
            }
        }

        if (returnJson) {
            onSuccess((V) json);
        } else {
            T t = (new Gson()).fromJson(json, genericityType);
            if (t instanceof MobBaseRsp) {
                MobBaseRsp<V> callbackData = (MobBaseRsp) t;
                V result = callbackData.getResult();
                if (callbackData.getRetCode().equals("200")) {
                    this.onSuccess(result);
                } else {
                    onFailed(callbackData.getRetCode(), callbackData.getMsg());
                }
            } else {
                onSuccess((V) t);
            }
        }

    }

    @Override
    public void onFailed(String errcode, String msg) {
        onFailure(errcode, msg);
    }

    public abstract void onSuccess(V data);

    public abstract void onFailure(String errcode, String msg);
}
