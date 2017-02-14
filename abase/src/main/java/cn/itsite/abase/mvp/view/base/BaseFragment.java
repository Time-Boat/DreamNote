package cn.itsite.abase.mvp.view.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.utils.ScreenUtils;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public abstract class BaseFragment<P extends BaseContract.Presenter> extends SwipeBackFragment {
    private final String TAG = this.getClass().getSimpleName();
    public P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();

    }

    @NonNull
    protected abstract P createPresenter();

    public P getPresenter() {
        return mPresenter;
    }

    public void setPresenter(@NonNull P presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }


    public void start() {
        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    //设置toolbar的padding值为状态栏的高度
    public void initStateBar(View view) {
        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
            view.setPadding(view.getPaddingLeft(),
                    view.getPaddingTop() + ScreenUtils.getStatusBarHeight(getActivity()),
                    view.getPaddingRight(), view.getPaddingBottom());
//            View v = ((View)view.getParent());
//            v.setPadding(v.getPaddingLeft(),
//                    v.getPaddingTop() + ScreenUtils.getStatusBarHeight(getActivity()),
//                    v.getPaddingRight(), v.getPaddingBottom());
        }

    }

}
