package com.dreamnote.ui.main.record;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dreamnote.R;
import com.dreamnote.utils.ToastUtils;

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

    @BindView(R.id.toolbar_record_cancel)
    TextView toolbarRecordCancel;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_record_release)
    TextView toolbarRecordRelease;
    @BindView(R.id.toolbar_toolbar)
    Toolbar toolbarToolbar;
    @BindView(R.id.record_text_content)
    EditText recordTextContent;
    @BindView(R.id.record_text_other_visible)
    TextView recordTextOtherVisible;
    @BindView(R.id.record_text_soft_keyboard)
    LinearLayout recordTextSoftKeyboard;

    int screenHeight = 0;
    //要发布的信息是不是公开的
    private Boolean isPublic = false;

    private OnSoftKeyboardStateChangedListener mKeyboardStateListener;      //软键盘状态监听列表
    private ViewTreeObserver.OnGlobalLayoutListener mLayoutChangeListener;
    private boolean mIsSoftKeyboardShowing;

    @NonNull
    @Override
    protected TextRecordContract.Presenter createPresenter() {
        return new TextRecordPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_text);
        ButterKnife.bind(this);

        initSoftKeyboardListener();
        initListener();

    }

    private void initListener() {
        recordTextOtherVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView)v;
                if(isPublic){
                    Drawable nav_up = getResources().getDrawable(R.drawable.ic_lock_outline_black_18dp);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    recordTextOtherVisible.setCompoundDrawables(nav_up, null, null, null);
                    tv.setText("私有");
                    isPublic = false;
                }else{
                    Drawable nav_up = getResources().getDrawable(R.drawable.ic_public_black_18dp);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    recordTextOtherVisible.setCompoundDrawables(nav_up, null, null, null);
                    tv.setText("公开");
                    isPublic = true;
                }
            }
        });

        toolbarRecordCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(getBaseContext(), "取消发布");
                finish();
            }
        });

        toolbarRecordRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(getBaseContext(), "发布");
//                finish();
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        ToastUtils.showToast(this, "aaaaaa");
//        switch(v.getId()){
//            case R.id.record_text_other_visible:
//                TextView tv = (TextView)v;
//                if(isPublic){
//                    Drawable nav_up = getResources().getDrawable(R.drawable.ic_lock_outline_black_18dp);
//                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//                    recordTextOtherVisible.setCompoundDrawables(nav_up, null, null, null);
//                    tv.setText("私有");
//                    isPublic = false;
//                }else{
//                    Drawable nav_up = getResources().getDrawable(R.drawable.ic_public_black_18dp);
//                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//                    recordTextOtherVisible.setCompoundDrawables(nav_up, null, null, null);
//                    tv.setText("公开");
//                    isPublic = true;
//                }
//                break;
//            case R.id.toolbar_personal_img:
//                ToastUtils.showToast(this, "取消发布");
//                finish();
//                break;
//            case R.id.toolbar_setting_img:
//                ToastUtils.showToast(this, "发布");
//                finish();
//                break;
//            default:
//                break;
//        }
//    }

    private void initSoftKeyboardListener() {
        //获取屏幕高度
        screenHeight = ScreenUtils.getScreenHeight(this);

        mIsSoftKeyboardShowing = false;

        //设置整个view树的监听
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

                //如果之前软键盘状态为显示，现在为关闭，或者之前为关闭，现在为显示，则表示软键盘的状态发生了改变
                if ((mIsSoftKeyboardShowing && !isKeyboardShowing) || (!mIsSoftKeyboardShowing && isKeyboardShowing)) {
                    mIsSoftKeyboardShowing = isKeyboardShowing;
                    mKeyboardStateListener.OnSoftKeyboardStateChanged(mIsSoftKeyboardShowing, heightDifference);
                }
            }
        };
        //注册布局变化监听
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(mLayoutChangeListener);

        addSoftKeyboardChangedListener(new OnSoftKeyboardStateChangedListener() {
            @Override
            public void OnSoftKeyboardStateChanged(boolean isKeyBoardShow, int keyboardHeight) {
                if(isKeyBoardShow){
                    ALog.e("TextRecordActivity","aaaaaaaa:"+keyboardHeight);
//                    recordTextSoftKeyboard.getLayoutParams().height = keyboardHeight;
                    recordTextSoftKeyboard.setLayoutParams(new LinearLayout.LayoutParams(0,keyboardHeight-60));
                }else{
                    ALog.e("TextRecordActivity","bbbbbbbb:"+keyboardHeight);
//                    recordTextSoftKeyboard.getLayoutParams().height = 0;
                    recordTextSoftKeyboard.setLayoutParams(new LinearLayout.LayoutParams(0,0));
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        removeSoftKeyboardChangedListener();
        super.onDestroy();
    }

    public interface OnSoftKeyboardStateChangedListener {
        public void OnSoftKeyboardStateChanged(boolean isKeyBoardShow, int keyboardHeight);
    }

    //注册软键盘状态变化监听
    public void addSoftKeyboardChangedListener(OnSoftKeyboardStateChangedListener listener) {
        mKeyboardStateListener = listener;
    }
    //取消软键盘状态变化监听
    public void removeSoftKeyboardChangedListener() {
        mKeyboardStateListener = null;
    }

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
