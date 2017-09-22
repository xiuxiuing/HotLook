package com.xiuxiuing.hotlook.bean;

import java.util.List;

/**
 * Created by wang on 2017/9/21.
 */

public class MobPageBean {
    private int curPage;
    private int total;
    private List<MobPageItemBean> list;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MobPageItemBean> getList() {
        return list;
    }

    public void setList(List<MobPageItemBean> list) {
        this.list = list;
    }
}
