package com.dreamnote.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.dreamnote.R;
import com.dreamnote.ui.main.addchoose.AddChooseFragment;
import com.dreamnote.ui.main.other.OtherFramgent;
import com.dreamnote.ui.main.personal.PersonalInfoFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.mvp.view.base.BaseActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.bottom_navigation_fragment)
    AHBottomNavigation bottomNavigation;

    private int bottomNavigationPreposition = 0;

    private SupportFragment[] mFragments = new SupportFragment[3];

    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            mFragments[0] = PersonalInfoFragment.newInstance();
            mFragments[1] = AddChooseFragment.newInstance();
            mFragments[2] = OtherFramgent.newInstance();
            loadMultipleRootFragment(R.id.fl_container_main_fragment, 0, mFragments[0], mFragments[1], mFragments[2]);
        } else {
            mFragments[0] = findFragment(PersonalInfoFragment.class);
            mFragments[1] = findFragment(AddChooseFragment.class);
            mFragments[2] = findFragment(OtherFramgent.class);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

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
                showHideFragment(mFragments[position], mFragments[bottomNavigationPreposition]);
                bottomNavigationPreposition = position;
            }
        });

        bottomNavigation.setCurrentItem(0);
    }

    @NonNull
    @Override
    protected BaseContract.Presenter createPresenter() {
        return null;
    }



}
