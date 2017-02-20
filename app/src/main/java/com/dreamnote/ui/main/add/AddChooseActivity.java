package com.dreamnote.ui.main.add;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dreamnote.R;
import com.dreamnote.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.mvp.view.base.BaseActivity;

public class AddChooseActivity extends BaseActivity implements View.OnClickListener{


    private static final String TAG = AddChooseActivity.class.getSimpleName();

    @BindView(R.id.add_choose_img)
    ImageView addChooseImg;
    @BindView(R.id.add_choose_record_text)
    TextView addChooseRecordText;
    @BindView(R.id.add_choose_record_picture)
    TextView addChooseRecordPicture;
    @BindView(R.id.add_choose_record_voice)
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

        addChooseClose.setOnClickListener(this);
        addChooseRecordText.setOnClickListener(this);
        addChooseRecordRecording.setOnClickListener(this);
        addChooseRecordPicture.setOnClickListener(this);

        initData();
    }

    private void initData() {
        Glide.with(this).
                load(R.drawable.ic_add_choose_img).
                asBitmap().
                into(addChooseImg);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_choose_record_text:
                ToastUtils.showToast(this, "添加文字");
                break;
            case R.id.add_choose_record_picture:
                ToastUtils.showToast(this, "添加图片");
                break;
            case R.id.add_choose_record_voice:
                ToastUtils.showToast(this, "添加录音");
                break;
            case R.id.add_choose_close:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
