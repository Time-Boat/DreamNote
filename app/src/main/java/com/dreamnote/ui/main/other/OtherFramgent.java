package com.dreamnote.ui.main.other;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamnote.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class OtherFramgent extends BaseFragment {
    private static final String TAG = OtherFramgent.class.getSimpleName();
    @BindView(R.id.toolbar_toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;

    public static OtherFramgent newInstance() {
        return new OtherFramgent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initStateBar(toolbar);
        mTitle.setText("全部动态");
    }

    @NonNull
    @Override
    protected BaseContract.Presenter createPresenter() {
        return null;
    }
}
