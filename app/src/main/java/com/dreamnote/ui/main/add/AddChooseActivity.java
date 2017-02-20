package com.dreamnote.ui.main.add;


import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamnote.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.mvp.view.base.BaseActivity;

public class AddChooseActivity extends BaseActivity {


    private static final String TAG = AddChooseActivity.class.getSimpleName();
    @BindView(R.id.add_choose_img)
    ImageView imageView2;
    @BindView(R.id.add_choose_record_text)
    TextView addChooseRecordText;
    @BindView(R.id.add_choose_record_picture)
    TextView addChooseRecordPicture;
    @BindView(R.id.add_choose_record_recording)
    TextView addChooseRecordRecording;
    @BindView(R.id.add_choose_close)
    ImageButton addChooseClose;

    @Override
    protected BaseContract.Presenter createPresenter() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_choose);
        ButterKnife.bind(this);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        
    }
}
