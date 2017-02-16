package com.dreamnote.util;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;

/**
 * @author: Administrator
 * @time 2017-02-16 13:53
 * @email 770164810@qq.com
 */

public class DesignViewUtils {
    /**
     * AppBarLayout 完全显示 打开状态
     *
     * @param verticalOffset
     * @return
     */
    public static boolean isAppBarLayoutOpen(int verticalOffset) {
        return verticalOffset >= 0;
    }

    /**
     * AppBarLayout 关闭或折叠状态
     *
     * @param appBarLayout
     * @param verticalOffset
     * @return
     */
    public static boolean isAppBarLayoutClose(AppBarLayout appBarLayout, int verticalOffset) {
        return appBarLayout.getTotalScrollRange() == Math.abs(verticalOffset);
    }

    /**
     * RecyclerView 滚动到底部 最后一条完全显示
     *
     * @param recyclerView
     * @return
     */
    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    /**
     * RecyclerView 滚动到顶端
     *
     * @param recyclerView
     * @return
     */
    public static boolean isSlideToTop(RecyclerView recyclerView) {
        return recyclerView.computeVerticalScrollOffset() <= 0;
    }
}
