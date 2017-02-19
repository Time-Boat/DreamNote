package com.dreamnote.ui.main.add;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreamnote.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.mvp.view.base.BaseFragment;

public class AddChooseFragment extends BaseFragment {

    private static final String TAG = AddChooseFragment.class.getSimpleName();

    @BindView(R.id.toolbar_toolbar)
    Toolbar toolbar;

    public static AddChooseFragment newInstance() {
        return new AddChooseFragment();
    }

    @NonNull
    @Override
    protected BaseContract.Presenter createPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_choose, container, false);
        ButterKnife.bind(this, view);
        initStateBar(toolbar);
        return view;
    }

}
