package com.dreamnote.ui.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.dreamnote.R;
import com.dreamnote.adapter.PersonalInfoAdapter;
import com.dreamnote.bean.DreamInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.mvp.view.base.BaseFragment;


public class PersonalInfoFragment extends BaseFragment<PersonalInfoContract.Presenter> implements PersonalInfoContract.View {

    private static final String TAG = PersonalInfoFragment.class.getSimpleName();
    @BindView(R.id.toolbar_toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.recycler_personal)
    RecyclerView mRecyclerView;
    private View mFooterLoading, mFooterNotLoading, mFooterError;

    LinearLayoutManager mLinearLayoutManager;

    PersonalInfoAdapter mPersonalInfoAdapter;

    private int pagination = 0;

    public static PersonalInfoFragment newInstance() {
        return new PersonalInfoFragment();
    }

    @NonNull
    @Override
    protected PersonalInfoContract.Presenter createPresenter() {
        return new PersonalInfoPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.bind(this, view);
        initStateBar(toolbar);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTitle.setText("我");
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    //沉降的initData方法是在懒加载中实现的，后期在做修改
    private void initData() {
        mLinearLayoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mFooterLoading = getLayoutInflater(null).inflate(R.layout.item_footer_loading, (ViewGroup) mRecyclerView.getParent(), false);
        mFooterNotLoading = getLayoutInflater(null).inflate(R.layout.item_footer_not_loading, (ViewGroup) mRecyclerView.getParent(), false);
        mFooterError = getLayoutInflater(null).inflate(R.layout.item_footer_error, (ViewGroup) mRecyclerView.getParent(), false);
        mFooterError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(_mActivity,"点击FooterError重新加载",Toast.LENGTH_SHORT).show();
                mPersonalInfoAdapter.removeAllFooterView();
                mPersonalInfoAdapter.addFooterView(mFooterLoading);
                mPresenter.queryData(pagination);
            }
        });
        setAdapter();

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(_mActivity,"点击Item:"+position,Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(mPersonalInfoAdapter);
//        initPageStateLayout(pagestatelayout);
//        initPtrFrameLayout(ptrframelayout);
    }

    private void setAdapter() {
        mPersonalInfoAdapter = new PersonalInfoAdapter();
        mPersonalInfoAdapter.removeAllFooterView();
    }

    @Override
    public void refresh(List<DreamInfo> mDreamInfo, int pagination) {

    }

    @Override
    public void end() {

    }

    @Override
    public void error(Throwable t) {

    }
}
