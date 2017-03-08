package com.dreamnote.ui.main.other;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamnote.R;
import com.dreamnote.adapter.OtherShareAdapter;
import com.dreamnote.adapter.PersonalInfoAdapter;
import com.dreamnote.bean.DreamInfoBean;
import com.dreamnote.common.Constants;
import com.dreamnote.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import de.hdodenhof.circleimageview.CircleImageView;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class OtherShareFramgent extends BaseFragment<OtherShareContract.Presenter> implements OtherShareContract.View {
    private static final String TAG = OtherShareFramgent.class.getSimpleName();
    @BindView(R.id.recycler_dream_Content)
    RecyclerView recyclerDreamContent;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout ptrframelayout;
    @BindView(R.id.other_share_name_img)
    CircleImageView otherShareNameImg;
    @BindView(R.id.other_share_title)
    TextView otherShareTitle;
    @BindView(R.id.other_share_setting_img)
    ImageView otherShareSettingImg;
    @BindView(R.id.other_share_layout)
    LinearLayout otherShareLayout;
    @BindView(R.id.other_share_widget_view)
    View otherShareWidgetView;

    //判断是否是加载状态
    private boolean isLoading;

    //判断当前页显示的最后一条数据是第几条
    private int lastVisibleItemPosition;

    private View mFooterLoading, mFooterNotLoading, mFooterError;

    LinearLayoutManager mLinearLayoutManager;

    OtherShareAdapter otherShareAdapter;

    private int pagination = 0;

    public static OtherShareFramgent newInstance() {
        return new OtherShareFramgent();
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
    }

    @NonNull
    @Override
    protected OtherShareContract.Presenter createPresenter() {
        return new OtherSharePresenter(this);
    }

    @OnClick({R.id.other_share_name_img, R.id.other_share_setting_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.other_share_name_img:
                ToastUtils.showToast(_mActivity,"点击左上角头像");
                break;
            case R.id.other_share_setting_img:
                ToastUtils.showToast(_mActivity,"点击设置");
                break;
        }
    }

    @Override
    public void refresh(List<DreamInfoBean> mDreamInfo, int pagination) {
        ALog.e("refresh:",mDreamInfo.size());
        if (mDreamInfo.size() > 0) {
            if (pagination == 0) {
                //说明是第一页，或者是刷新,把页码重置为0，代表第一页。
                otherShareAdapter.removeAllFooterView();
                if (mDreamInfo.size() >= Constants.PAGE_SIZE) {
                    otherShareAdapter.addFooterView(mFooterLoading);
                }
                this.pagination = 0;
                otherShareAdapter.setNewData(mDreamInfo);
                otherShareAdapter.addFooterView(mFooterNotLoading);
                //设置一下会重新刷新整个item的位置，即使不是第一个item位置刷新，也会重新刷新定位到第一个。
                recyclerDreamContent.setAdapter(otherShareAdapter);
            } else {
                otherShareAdapter.addData(mDreamInfo);
            }
            //靠这个参数控制最后不需要请求数据
            isLoading = false;
        } else {
//            mPersonalInfoAdapter.removeAllFooterView();
//            mPersonalInfoAdapter.addFooterView(mFooterNotLoading);
            if (pagination == 0) {
                Toast.makeText(_mActivity,"当前页无数据",Toast.LENGTH_SHORT).show();
            } else {
                //此处一定要先清除之前加载的FooterView，否则会报错。
                otherShareAdapter.removeAllFooterView();
                otherShareAdapter.addFooterView(mFooterNotLoading);
                //因为修改了RecyclerView的内容，所以要通知adapter更新一下
                otherShareAdapter.notifyDataSetChanged();
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
