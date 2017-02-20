package com.dreamnote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dreamnote.R;
import com.dreamnote.bean.DreamInfo;
import com.dreamnote.utils.ToastUtils;

import cn.itsite.abase.log.ALog;

/**
 * @author: Administrator
 * @time 2017-02-15 10:23
 * @email 770164810@qq.com
 */

public class PersonalInfoAdapter extends BaseQuickAdapter<DreamInfo, BaseViewHolder> {

    Context context;

    public PersonalInfoAdapter(Context context) {
        super(R.layout.item_personal_fragment, null);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, DreamInfo item) {
        ALog.e(holder.getLayoutPosition());
        holder.setText(R.id.item_personal_name, item.getName())
                .setText(R.id.item_personal_time, item.getReleaseTime())
                .setText(R.id.item_personal_content, item.getContent());

        holder.getView(R.id.item_personal_like).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(context,"click like");
            }
        });

        holder.getView(R.id.item_personal_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(context,"click more");
            }
        });

        holder.getView(R.id.item_personal_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(context,"click share");
            }
        });

        holder.getView(R.id.item_personal_name_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(context,"click item_name");
            }
        });

    }

}
