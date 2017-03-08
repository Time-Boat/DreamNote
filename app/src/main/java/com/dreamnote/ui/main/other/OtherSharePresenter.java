package com.dreamnote.ui.main.other;

import com.dreamnote.bean.DreamInfoBean;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;

/**
 * @author: Administrator
 * @time 2017-03-06 15:08
 * @email 770164810@qq.com
 */

public class OtherSharePresenter extends BasePresenter<OtherShareFramgent> implements OtherShareContract.Presenter{

    public OtherSharePresenter(OtherShareFramgent mView) {
        super(mView);
    }

    @Override
    public void queryData(int pagination) {
        //根据页数进行网络请求
        //模拟一条数据
        List<DreamInfoBean> mDreamInfo = new ArrayList<>();
        if(pagination == 0){
            DreamInfoBean d = new DreamInfoBean();
            d.setName("Timer");
            d.setContent("跟着在旅途中遇到的姐姐，走进一家日式房间。里面有一张圆桌，摆了一锅粥。我们每个人分到一碗。喝着喝着，开始了欢送会。原来就是这个姐姐的澳洲毕业欢送会。");
            d.setReleaseTime("2017-1-1");
            for (int i = 0; i < 10; i++) {
                mDreamInfo.add(d);
            }
        }
        getView().refresh(mDreamInfo,pagination);
    }

    @Override
    public void start() {
        queryData(0);
    }

}
