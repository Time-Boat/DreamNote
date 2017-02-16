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
import com.dreamnote.common.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import in.srain.cube.views.ptr.PtrFrameLayout;


public class PersonalInfoFragment extends BaseFragment<PersonalInfoContract.Presenter> implements PersonalInfoContract.View {

    private static final String TAG = PersonalInfoFragment.class.getSimpleName();
    @BindView(R.id.toolbar_toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.recycler_personal)
    RecyclerView mRecyclerView;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout mPtrframelayout;

    //判断是否是加载状态
    private boolean isLoading;

    //判断当前页显示的最后一条数据是第几条
    private int lastVisibleItemPosition;

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
        setLoadMore();
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(_mActivity,"点击Item:"+position,Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(mPersonalInfoAdapter);
//        initPageStateLayout(pagestatelayout);
        initPtrFrameLayout(mPtrframelayout);
    }

    private void setAdapter() {
        mPersonalInfoAdapter = new PersonalInfoAdapter();
        mPersonalInfoAdapter.removeAllFooterView();
    }

    private void setLoadMore() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mPersonalInfoAdapter == null) {
                    return;
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition + 1 == mPersonalInfoAdapter.getItemCount()
                        //目的是判断第一页数据条数是否满足一整页。
                        && mPersonalInfoAdapter.getItemCount() >= Constants.PAGE_SIZE) {
                    if (!isLoading) {
                        isLoading = true;
                        pagination += 1;
                        mPresenter.queryData(pagination);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLinearLayoutManager == null) {
                    return;
                }
                lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void refresh(List<DreamInfo> mDreamInfo, int pagination) {
        if (mDreamInfo.size() > 0) {
            if (pagination == 0) {
                //说明是第一页，或者是刷新,把页码重置为0，代表第一页。
                if (mDreamInfo.size() >= Constants.PAGE_SIZE) {
                    mPersonalInfoAdapter.removeAllFooterView();
                    mPersonalInfoAdapter.addFooterView(mFooterLoading);
                }
                this.pagination = 0;
                mPersonalInfoAdapter.setNewData(mDreamInfo);
                //设置一下会重新刷新整个item的位置，即使不是第一个item位置刷新，也会重新刷新定位到第一个。
                mRecyclerView.setAdapter(mPersonalInfoAdapter);
            } else {
                mPersonalInfoAdapter.addData(mDreamInfo);
            }
            //靠这个参数控制最后不需要请求数据
            isLoading = false;
        } else {
            if (pagination == 0) {
                Toast.makeText(_mActivity,"当前页无数据",Toast.LENGTH_SHORT).show();
            } else {
                //此处一定要先清除之前加载的FooterView，否则会报错。
                mPersonalInfoAdapter.removeAllFooterView();
                mPersonalInfoAdapter.addFooterView(mFooterNotLoading);
            }
        }
    }

    @Override
    public void end() {

    }

    @Override
    public void error(Throwable t) {

    }
}
