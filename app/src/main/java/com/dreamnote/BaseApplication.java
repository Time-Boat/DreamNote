package com.dreamnote;


import android.app.Application;
import android.content.Context;

import com.dreamnote.bean.UserInfoBean;

import cn.itsite.abase.exception.AppExceptionHandler;
import cn.itsite.abase.mvp.model.HttpHelper;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();
    public static Context mContext;

    //用户信息
    public static UserInfoBean mUserInfoBean;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
//        Thread.setDefaultUncaughtExceptionHandler(AppExceptionHandler.getInstance(this));

    }
}
