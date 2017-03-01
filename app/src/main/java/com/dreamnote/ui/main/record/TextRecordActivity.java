package com.dreamnote.ui.main.record;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dreamnote.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itsite.abase.mvp.view.base.BaseActivity;

/**
 * @author: Administrator
 * @time 2017-02-24 11:27
 * @email 770164810@qq.com
 */

public class TextRecordActivity extends BaseActivity<TextRecordContract.Presenter> implements TextRecordContract.View {

    @BindView(R.id.toolbar_personal_img)
    TextView toolbarPersonalImg;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_setting_img)
    TextView toolbarSettingImg;
    @BindView(R.id.toolbar_toolbar)
    Toolbar toolbarToolbar;
    @BindView(R.id.record_text_content)
    EditText recordTextContent;
    @BindView(R.id.record_text_other_visible)
    TextView recordTextOtherVisible;
    @BindView(R.id.record_text_soft_keyboard)
    View recordTextSoftKeyboard;

    @NonNull
    @Override
    protected TextRecordContract.Presenter createPresenter() {
        return new TextRecordPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_text);
        ButterKnife.bind(this);

        initData();
        initView();

    }

    private void initView() {

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
