package cn.itsite.abase.mvp.view.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.WindowManager;

import cn.itsite.abase.common.ActivityManager;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.utils.NetworkUtils;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public abstract class BaseActivity<P extends BaseContract.Presenter> extends SwipeBackActivity {
    private final String TAG = this.getClass().getSimpleName();
    public P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
        initStateBar();
        netWorkTips();
        mPresenter = createPresenter();
    }

    private void initStateBar() {
        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @NonNull
    protected abstract P createPresenter();

    private void initActivity() {
        //把每一个Activity加入栈中
        ActivityManager.getInstance().addActivity(this);

        //一旦启动某个Activity就打印Log，方便找到该类
        ALog.e(getClass().getName());
    }

    public void netWorkTips() {
        if (!NetworkUtils.isConnected(getApplicationContext())) {
            View view = getWindow().getDecorView();
            Snackbar mSnackbar = Snackbar.make(view, "当前网络已断开！", Snackbar.LENGTH_LONG)
                    .setAction("设置网络", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 跳转到系统的网络设置界面
                            NetworkUtils.openSetting(BaseActivity.this);
                        }
                    });
            View v = mSnackbar.getView();
            v.setBackgroundColor(Color.parseColor("#FFCC00"));
            mSnackbar.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {

            mPresenter.detachView();
            mPresenter = null;
        }
        //把每一个Activity弹出栈
        ActivityManager.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
