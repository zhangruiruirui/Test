package com.lanou.chenfengyao.okhttp3demo;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/21,
 * otherwise, I do not know who create it either.
 */

public class VolleyManager extends NetManager{
    public static VolleyManager getInstance(){
        return new VolleyManager();
    }
    @Override
    protected <Bean> void get(String url, Class<Bean> clazz, ResponseCallBack<Bean> responseCallBack) {

    }
}
