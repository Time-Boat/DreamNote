package com.dreamnote.ui.main.record;

import com.dreamnote.bean.NewRecordBean;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;

/**
 * @author: Administrator
 * @time 2017-02-24 11:35
 * @email 770164810@qq.com
 */

public class TextRecordPresenter extends BasePresenter<TextRecordContract.View> implements TextRecordContract.Presenter{

    public TextRecordPresenter(TextRecordContract.View view){
        super(view);
    }

    @Override
    public void upload(NewRecordBean nb) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void start() {

    }

}
