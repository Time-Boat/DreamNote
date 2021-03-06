package com.dreamnote.ui.main.personal;

import com.dreamnote.bean.DreamInfoBean;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;

/**
 * @author: Administrator
 * @time 2017-02-14 14:53
 * @email 770164810@qq.com
 */

public interface PersonalInfoContract {

    interface View extends BaseContract.View {
        void refresh(List<DreamInfoBean> mStaffData, int pagination);

    }

    interface Presenter extends BaseContract.Presenter {
        void queryData(int pagination);
    }

}
