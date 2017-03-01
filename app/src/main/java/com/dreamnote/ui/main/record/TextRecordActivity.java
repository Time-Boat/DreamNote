package com.dreamnote.ui.main.record;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;

import com.dreamnote.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseActivity;
import cn.itsite.abase.utils.DensityUtils;
import cn.itsite.abase.utils.ScreenUtils;

/**
 * @author: Administrator
 * @time 2017-02-24 11:27
 * @email 770164810@qq.com
 */

public class TextRecordActivity extends BaseActivity<TextRecordContract.Presenter> implements TextRecordContract.View {

    @BindView(R.id.toolbar_personal_img)
    TextView toolbarPersonalImg;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_setting_img)
    TextView toolbarSettingImg;
    @BindView(R.id.toolbar_toolbar)
    Toolbar toolbarToolbar;
    @BindView(R.id.record_text_content)
    EditText recordTextContent;
    @BindView(R.id.record_text_other_visible)
    TextView recordTextOtherVisible;
    @BindView(R.id.record_text_soft_keyboard)
    View recordTextSoftKeyboard;

    int screenHeight = 0;

    @NonNull
    @Override
    protected TextRecordContract.Presenter createPresenter() {
        return new TextRecordPresenter(this);
    }

    public interface OnSoftKeyboardStateChangedListener {
        public void OnSoftKeyboardStateChanged(boolean isKeyBoardShow, int keyboardHeight);
    }

    //注册软键盘状态变化监听
    public void addSoftKeyboardChangedListener(OnSoftKeyboardStateChangedListener listener) {
        if (listener != null) {
            mKeyboardStateListeners.add(listener);
        }
    }
    //取消软键盘状态变化监听
    public void removeSoftKeyboardChangedListener(OnSoftKeyboardStateChangedListener listener) {
        if (listener != null) {
            mKeyboardStateListeners.remove(listener);
        }
    }

    private ArrayList<OnSoftKeyboardStateChangedListener> mKeyboardStateListeners;      //软键盘状态监听列表
    private ViewTreeObserver.OnGlobalLayoutListener mLayoutChangeListener;
    private boolean mIsSoftKeyboardShowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_text);
        ButterKnife.bind(this);

        screenHeight = ScreenUtils.getScreenHeight(this);

        mIsSoftKeyboardShowing = false;
        mKeyboardStateListeners = new ArrayList<>();


        mLayoutChangeListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //判断窗口可见区域大小
                Rect r = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
                int heightDifference = screenHeight - r.height();
                boolean isKeyboardShowing = heightDifference > screenHeight/3;
//                ALog.e("TextRecordActivity","isKeyboardShowing:"+isKeyboardShowing);
//                recordTextSoftKeyboard.getLayoutParams().height = 863;
                ALog.e("TextRecordActivity","heightDifference:"+heightDifference);
                ALog.e("TextRecordActivity","screenHeight:"+screenHeight);
                ALog.e("TextRecordActivity","rheight:"+r.height());
                ALog.e("TextRecordActivity","px2dp:"+(int)DensityUtils.px2dp(getBaseContext(),heightDifference));
                if((mIsSoftKeyboardShowing && !isKeyboardShowing) || (!mIsSoftKeyboardShowing && isKeyboardShowing)){
                    ALog.e("TextRecordActivity","aaaaaaaa");
                    recordTextSoftKeyboard.getLayoutParams().height = heightDifference;
                }else{
                    recordTextSoftKeyboard.getLayoutParams().height = 0;
                    ALog.e("TextRecordActivity","bbbbbbbb");
                }

                //如果之前软键盘状态为显示，现在为关闭，或者之前为关闭，现在为显示，则表示软键盘的状态发生了改变
//                if ((mIsSoftKeyboardShowing && !isKeyboardShowing) || (!mIsSoftKeyboardShowing && isKeyboardShowing)) {
//                    mIsSoftKeyboardShowing = isKeyboardShowing;
//                    for (int i = 0; i < mKeyboardStateListeners.size(); i++) {
//                        OnSoftKeyboardStateChangedListener listener = mKeyboardStateListeners.get(i);
//                        listener.OnSoftKeyboardStateChanged(mIsSoftKeyboardShowing, heightDifference);
//                    }
//                }
            }
        };
        //注册布局变化监听
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(mLayoutChangeListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    @Override
    protected void onDestroy() {
        //移除布局变化监听
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(mLayoutChangeListener);
        } else {
            getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(mLayoutChangeListener);
        }
        super.onDestroy();
    };

    @Override
    public void end() {

    }

    @Override
    public void error(Throwable t) {

    }

    @Override
    public void start() {

    }
}
