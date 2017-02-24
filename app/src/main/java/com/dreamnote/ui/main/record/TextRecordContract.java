package com.dreamnote.ui.main.record;

import com.dreamnote.bean.NewRecordBean;

import cn.itsite.abase.mvp.contract.base.BaseContract;

/**
 * @author: Administrator
 * @time 2017-02-24 11:35
 * @email 770164810@qq.com
 */

public interface TextRecordContract extends BaseContract {

    interface View extends BaseContract.View {
        void finish();

    }

    interface Presenter extends BaseContract.Presenter {
        void upload(NewRecordBean nb);
    }

}
