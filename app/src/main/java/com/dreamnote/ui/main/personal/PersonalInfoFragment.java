package com.dreamnote.ui.main.personal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.dreamnote.R;
import com.dreamnote.adapter.PersonalInfoAdapter;
import com.dreamnote.bean.DreamInfo;
import com.dreamnote.common.Constants;
import com.dreamnote.widget.gradation.GradationScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import in.srain.cube.views.ptr.PtrFrameLayout;


public class PersonalInfoFragment extends BaseFragment<PersonalInfoContract.Presenter> implements PersonalInfoContract.View,GradationScrollView.ScrollViewListener{

    private static final String TAG = PersonalInfoFragment.class.getSimpleName();
    //    @BindView(R.id.toolbar_toolbar)
//    Toolbar toolbar;
    @BindView(R.id.recycler_personal)
    RecyclerView mRecyclerView;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout mPtrframelayout;
    @BindView(R.id.scrollview)
    GradationScrollView mGradationScrollView;

//    @BindView(R.id.appbar_toolbar)
//    AppBarLayout mAppBarLayout;

    //判断是否是加载状态
    private boolean isLoading;

    //判断当前页显示的最后一条数据是第几条
    private int lastVisibleItemPosition;

    private View mFooterLoading, mFooterNotLoading, mFooterError;

    LinearLayoutManager mLinearLayoutManager;

    PersonalInfoAdapter mPersonalInfoAdapter;

    private int pagination = 0;

    //渐隐效果涉及到的控件
    @BindView(R.id.textview)
    TextView textView;

    private int height;

    @BindView(R.id.iv_banner)
    ImageView ivBanner;


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
//        initStateBar(toolbar);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initListener();

        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 获取顶部图片高度后，设置滚动监听
     */
    private void initListener() {

        ViewTreeObserver vto = ivBanner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = ivBanner.getHeight();

                mGradationScrollView.setScrollViewListener(PersonalInfoFragment.this);
            }
        });
    }

    /**
     * 滑动监听
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y,
                                int oldx, int oldy) {

        if (mRecyclerView == null) return;
//        //当appBar的折叠效果完全展开的时候才允许下拉刷新
        mPtrframelayout.setEnabled(y == 0 /*|| DesignViewUtils.isSlideToBottom(mRecyclerView)*/ ? true : false);

        //DesignViewUtils.isSlideToBottom(mRecyclerView)始终为true后期在做修改
//        ALog.e("setEnabled:",y == 0|| DesignViewUtils.isSlideToBottom(mRecyclerView) ? true : false);
//        ALog.e("onScrollChanged","x:"+x+"   y:"+y+"   oldx:"+oldx+"   oldy:"+oldy);
        // TODO Auto-generated method stub
        if (y <= 0) {
            //设置标题的背景颜色
            textView.setBackgroundColor(Color.argb((int) 0, 144,151,166));
            textView.setTextColor(Color.argb((int) 0, 255,255,255));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (232 * scale);
            textView.setTextColor(Color.argb((int) alpha, 255,255,255));
            textView.setBackgroundColor(Color.argb((int) alpha, 219,219,219));
        } else {    //滑动到banner下面设置普通颜色
            textView.setBackgroundColor(Color.argb((int) 232, 219,219,219));
        }


    }

//    //能监听appBar折叠的上下偏移量
//    @Override
//    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
//        if (mRecyclerView == null) return;
//        //当appBar的折叠效果完全展开的时候才允许下拉刷新
//        mPtrframelayout.setEnabled(i >= 0|| DesignViewUtils.isSlideToBottom(mRecyclerView) ? true : false);
//
////        ALog.e("-------->off:" + i + "  ScrollRange:" + appBarLayout.getTotalScrollRange() + "  height:" + appBarLayout.getHeight());
//    }

    //沉降的initData方法是在懒加载中实现的，后期在做修改
    private void initData() {

        //为recyclerView设置禁止嵌套滑动     解决了滑动不流畅问题，具体为什么不知道。。。
        mRecyclerView.setNestedScrollingEnabled(false);

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
        ALog.e("refresh:",mDreamInfo.size());
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
            mPersonalInfoAdapter.removeAllFooterView();
            mPersonalInfoAdapter.addFooterView(mFooterNotLoading);
//            if (pagination == 0) {
//                Toast.makeText(_mActivity,"当前页无数据",Toast.LENGTH_SHORT).show();
//            } else {
//                //此处一定要先清除之前加载的FooterView，否则会报错。
//                mPersonalInfoAdapter.removeAllFooterView();
//                mPersonalInfoAdapter.addFooterView(mFooterNotLoading);
//            }
        }
    }

    @Override
    public void end() {

    }

    @Override
    public void error(Throwable t) {

    }
}
