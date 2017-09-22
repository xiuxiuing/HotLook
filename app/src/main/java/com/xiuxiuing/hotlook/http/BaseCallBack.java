package com.xiuxiuing.hotlook.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Created by wang on 2017/9/20.
 */

public abstract class BaseCallBack<T> {
    protected Type genericityType;

    public BaseCallBack() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            this.genericityType = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        } else {
            this.genericityType = Object.class;
        }
    }

    public abstract void onResolve(String t);

    public abstract void onFailed(String errcode, String msg);

    public Type getGenericityType() {
        return genericityType;
    }

}
