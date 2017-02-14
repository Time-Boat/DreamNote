package com.dreamnote.ui.main.personal;

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

    @Override
    public void queryData(int pagination) {

    }

    @Override
    public void start() {

    }
}
