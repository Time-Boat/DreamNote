package com.dreamnote.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.dreamnote.R;
import com.dreamnote.ui.main.add.AddChooseActivity;
import com.dreamnote.ui.main.other.OtherFramgent;
import com.dreamnote.ui.main.personal.PersonalInfoFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author: Administrator
 * @time 2017-02-21 10:05
 * @email 770164810@qq.com
 */

public class MainFragment extends BaseFragment {

    private final String TAG = this.getClass().getSimpleName();

    @NonNull
    @Override
    protected BaseContract.Presenter createPresenter() {
        return null;
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @BindView(R.id.bottom_navigation_fragment)
    AHBottomNavigation bottomNavigation;

    private int bottomNavigationPreposition = 0;

    private SupportFragment[] mFragments = new SupportFragment[3];

    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    //记录启动activity之前是哪一个activity，在activity被销毁，回到这里之后哪一个AHButton按钮被选中
    private int fragmentPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);


        if (savedInstanceState == null) {
            mFragments[0] = PersonalInfoFragment.newInstance();
            mFragments[2] = OtherFramgent.newInstance();
            loadMultipleRootFragment(R.id.fl_container_main_fragment, 0, mFragments[0], mFragments[2]);
        } else {
            mFragments[0] = findFragment(PersonalInfoFragment.class);
            mFragments[2] = findFragment(OtherFramgent.class);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBottomNavigation();

    }

    private void initBottomNavigation() {

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_menu_camera, R.color.white);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_menu_camera, R.color.white);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_menu_camera, R.color.white);
        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigation.addItems(bottomNavigationItems);
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.white));
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setColored(true);
        bottomNavigation.setForceTint(false);
        bottomNavigation.setAccentColor(getResources().getColor(R.color.base_color));
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.gray));
        bottomNavigation.setForceTitlesDisplay(true);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, final boolean wasSelected) {
                if (position == 1) {
                    startActivity(new Intent(_mActivity, AddChooseActivity.class));
                } else {
                    fragmentPosition = position;
                    showHideFragment(mFragments[position], mFragments[bottomNavigationPreposition]);
                    bottomNavigationPreposition = position;
                }

            }
        });

        bottomNavigation.setCurrentItem(0);
    }

    @Override
    public void onResume() {
        ALog.e(TAG, "onResume");
        bottomNavigation.setCurrentItem(fragmentPosition);
        super.onResume();
    }

    @Override
    public void onStart() {
        ALog.e(TAG, "onStart");
        super.onStart();
    }

}
