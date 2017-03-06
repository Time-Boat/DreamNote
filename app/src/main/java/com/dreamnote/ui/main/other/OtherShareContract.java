package com.dreamnote.ui.main.other;

import com.dreamnote.bean.DreamInfoBean;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;

/**
 * @author: Administrator
 * @time 2017-03-06 15:02
 * @email 770164810@qq.com
 */

public interface OtherShareContract {

    interface View extends BaseContract.View{
        void refresh(List<DreamInfoBean> mStaffData, int pagination);
    }

    interface Presenter extends BaseContract.Presenter {
        void queryData(int pagination);
    }

}
