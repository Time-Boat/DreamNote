package com.dreamnote.ui.main.personal;

import com.dreamnote.bean.DreamInfo;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;

/**
 * @author: Administrator
 * @time 2017-02-14 15:58
 * @email 770164810@qq.com
 */

public class PersonalInfoPresenter extends BasePresenter<PersonalInfoContract.View> implements PersonalInfoContract.Presenter{
    private static final String TAG = PersonalInfoPresenter.class.getSimpleName();

    public PersonalInfoPresenter(PersonalInfoContract.View mView) {
        super(mView);
    }

    //查询当前页的数据...
    @Override
    public void queryData(int pagination) {
        //模拟一条数据
        List<DreamInfo> mDreamInfo = new ArrayList<>();
        DreamInfo d = new DreamInfo();
        d.setName("Timer");
        d.setContent("跟着在旅途中遇到的姐姐，走进一家日式房间。里面有一张圆桌，摆了一锅粥。我们每个人分到一碗。喝着喝着，开始了欢送会。原来就是这个姐姐的澳洲毕业欢送会。");
        d.setReleaseTime("2017-1-1");
        mDreamInfo.add(d);
        mDreamInfo.add(d);
        mDreamInfo.add(d);
        getView().refresh(mDreamInfo,pagination);
    }

    //第一次开始的时候
    @Override
    public void start() {
        queryData(0);
    }
}
