package com.dreamnote.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author: Administrator
 * @time 2017-02-17 16:39
 * @email 770164810@qq.com
 */

public class ToastUtils {

    private static final String TAG = ToastUtils.class.getSimpleName();

    private static Toast mToast;

    public static void showToast(Context mContext, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

//    public static void showSuccessToast(Context mContext, String msg) {
//        TastyToast.makeText(mContext, msg, TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
//    }
//
//    public static void showInfoToast(Context mContext, String msg) {
//        TastyToast.makeText(mContext, msg, TastyToast.LENGTH_SHORT, TastyToast.INFO);
//    }
//
//    public static void showDefaultToast(Context mContext, String msg) {
//        TastyToast.makeText(mContext, msg, TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);
//    }
//
//    public static void showWarningToast(Context mContext, String msg) {
//        TastyToast.makeText(mContext, msg, TastyToast.LENGTH_SHORT, TastyToast.WARNING);
//    }
//
//    public static void showErrorToast(Context mContext, String msg) {
//        TastyToast.makeText(mContext, msg, TastyToast.LENGTH_SHORT, TastyToast.ERROR);
//    }
}
