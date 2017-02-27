package com.dreamnote.ui.main.record;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.dreamnote.R;

import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseActivity;

/**
 * @author: Administrator
 * @time 2017-02-24 11:27
 * @email 770164810@qq.com
 */

public class TextRecordActivity extends BaseActivity<TextRecordContract.Presenter> implements TextRecordContract.View{

    @NonNull
    @Override
    protected TextRecordContract.Presenter createPresenter() {
        return new TextRecordPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_text);
        
        initData();
        initView();
        
    }

    private void initView() {
        ALog.e("","");
    }

    private void initData() {
    }

    @Override
    public void end() {

    }

    @Override
    public void error(Throwable t) {

    }

    @Override
    public void start() {

    }
}
