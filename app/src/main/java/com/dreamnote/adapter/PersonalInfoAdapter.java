package com.dreamnote.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dreamnote.R;
import com.dreamnote.bean.DreamInfo;

/**
 * @author: Administrator
 * @time 2017-02-15 10:23
 * @email 770164810@qq.com
 */

public class PersonalInfoAdapter extends BaseQuickAdapter<DreamInfo, BaseViewHolder> {

    public PersonalInfoAdapter() {
        super(R.layout.item_personal_fragment, null);
    }

    @Override
    protected void convert(BaseViewHolder holder, DreamInfo item) {
        holder.setText(R.id.item_personal_name, item.getName())
                .setText(R.id.item_personal_time, item.getReleaseTime())
                .setText(R.id.item_personal_content, item.getContent());
    }
}
