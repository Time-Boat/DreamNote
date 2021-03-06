package com.dreamnote.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dreamnote.R;
import com.dreamnote.bean.DreamInfoBean;

import cn.itsite.abase.log.ALog;

/**
 * @author: Administrator
 * @time 2017-03-07 18:45
 * @email 770164810@qq.com
 */

public class OtherShareAdapter  extends BaseQuickAdapter<DreamInfoBean, BaseViewHolder> {


    public OtherShareAdapter() {
        super(R.layout.item_personal_fragment, null);
    }

    @Override
    protected void convert(BaseViewHolder holder, DreamInfoBean item) {
        ALog.e(holder.getLayoutPosition());
        holder.setText(R.id.item_personal_name, item.getName())
                .setText(R.id.item_personal_time, item.getReleaseTime())
                .setText(R.id.item_personal_content, item.getContent());

        holder.addOnClickListener(R.id.item_personal_like);

        holder.addOnClickListener(R.id.item_personal_more);

        holder.addOnClickListener(R.id.item_personal_share);

        holder.addOnClickListener(R.id.item_personal_name_img);

        holder.addOnClickListener(R.id.item_personal_content_img);

    }
}
