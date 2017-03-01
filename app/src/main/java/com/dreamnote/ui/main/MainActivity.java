package com.dreamnote.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.dreamnote.R;
import com.dreamnote.common.Constants;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import cn.itsite.abase.event.EventData;
import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.mvp.view.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //在MainActivity中使用loadRootFragment方法加载Fragment，被加载的页面不可被点击和滑动
        //使用loadMultipleRootFragment则可以，什么鬼...
        //更新了版本就可以了
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, MainFragment.newInstance());
        }

        closeLunch();
    }

    private void closeLunch() {
        EventBus.getDefault().postSticky(new EventData(Constants.EVENT_FINISH_LAUNCH));
    }

    @NonNull
    @Override
    protected BaseContract.Presenter createPresenter() {
        return null;
    }

}
