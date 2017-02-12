package com.dreamnote.launch.splash;

import android.text.TextUtils;

import com.dreamnote.BaseApplication;
import com.dreamnote.bean.CheckUpdateBean;
import com.dreamnote.bean.UserInfoBean;
import com.dreamnote.common.ApiService;
import com.dreamnote.common.Constants;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.security.GeneralSecurityException;

import cn.itsite.abase.cache.SPCache;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.model.HttpHelper;
import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.utils.AESCryptUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    private static final String TAG = SplashPresenter.class.getSimpleName();
    private int intRetry = 0;

    public SplashPresenter(SplashContract.View mView) {
        super(mView);
    }

    public void checkLogin() {
        String usernameEncrypted = (String) SPCache.get(BaseApplication.mContext, Constants.USERNAME, "");
        String passwordEncrypted = (String) SPCache.get(BaseApplication.mContext, Constants.PASSWORD, "");
        String userInfoEncrypted = (String) SPCache.get(BaseApplication.mContext, Constants.USER_INFO_BEAN, "");

        ALog.e("username加密从sp中:" + usernameEncrypted);
        ALog.e("password加密从sp中:" + passwordEncrypted);

        if (TextUtils.isEmpty(usernameEncrypted) || TextUtils.isEmpty(passwordEncrypted)
                || TextUtils.isEmpty(userInfoEncrypted)) {

            getView().go2LoginOrGuide();

        } else {
            //进行解密
            String userInfo = "";
            try {
                userInfo = AESCryptUtils.decrypt(Constants.ENCRYPT_KEY, userInfoEncrypted);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
                getView().go2LoginOrGuide();
                return;
            }

            //后面下载会用到这个种的信息作为参数，所以提前加载到内存中。
            BaseApplication.mUserInfoBean = new Gson().fromJson(userInfo, UserInfoBean.class);
            getView().go2Main();
        }
    }

    public void checkUpdate() {
        HttpHelper.getInstance(ApiService.class).checkUpdate().enqueue(new Callback<CheckUpdateBean>() {
            @Override
            public void onResponse(Call<CheckUpdateBean> call, Response<CheckUpdateBean> response) {
                if (response.isSuccessful()) {
                    CheckUpdateBean mCheckUpdateBean = response.body();
                    if (mCheckUpdateBean.getStatus() == 0) {

                        EventBus.getDefault().postSticky(mCheckUpdateBean.getUpdateInfo());
                    }
                }
                checkLogin();
            }

            @Override
            public void onFailure(Call<CheckUpdateBean> call, Throwable t) {
                t.printStackTrace();
                ALog.e(t);
                checkLogin();
            }
        });
    }

    @Override
    public void start() {
        //在异步线程中初始化数据库中的数据
        mRxManager.add(Observable.create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        try {
                            subscriber.onCompleted();

                        } catch (Exception ex) {
                            subscriber.onError(ex);
                        }
                    }
                }).subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Object>() {
                            @Override
                            public void onCompleted() {
                                checkUpdate();
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                ALog.e(e);
                                checkUpdate();
                            }

                            @Override
                            public void onNext(Object o) {
                                ALog.e("111111");
                            }
                        })

        );
    }
}
