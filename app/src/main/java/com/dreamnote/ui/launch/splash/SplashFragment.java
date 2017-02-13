package com.dreamnote.ui.launch.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreamnote.BaseApplication;
import com.dreamnote.ui.main.MainActivity;
import com.dreamnote.R;
import com.dreamnote.common.Constants;

import butterknife.ButterKnife;
import cn.itsite.abase.cache.SPCache;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class SplashFragment extends BaseFragment<SplashContract.Presenter> implements SplashContract.View {

    private static final String TAG = SplashFragment.class.getSimpleName();
    //目的是为了判断网络请求时，用户是否退出
    private boolean isExit;
    private boolean isFirstentry;

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @NonNull
    @Override
    protected SplashContract.Presenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        isFirstentry = (boolean) SPCache.get(BaseApplication.mContext, Constants.ISFIRSTENTRY, true);
        //严格按照流程来，Presenter层的代码应该在View层代码的必要数据加载完成之后才调用
//        mPresenter.start();

        //test
        go2LoginOrGuide();
    }

    @Override
    public void go2LoginOrGuide() {
        //目的是为了判断网络请求时，用户是否退出
        if (isExit) {
            return;
        }
        if (isFirstentry) {
//            start(GuideFragment.newInstance());
            _mActivity.startActivity(new Intent(_mActivity, MainActivity.class));
        } else {
//            start(LoginFragment.newInstance(Constants.FROM_SPLASH));
            _mActivity.startActivity(new Intent(_mActivity, MainActivity.class));
        }
    }

    @Override
    public void go2Main() {
        if (isExit) {
            return;
        }
        _mActivity.startActivity(new Intent(_mActivity, MainActivity.class));
    }

    @Override
    public void onDestroyView() {
        isExit = true;
        super.onDestroyView();
    }

    @Override
    public void end() {

    }

    @Override
    public void error(Throwable t) {

    }
}
