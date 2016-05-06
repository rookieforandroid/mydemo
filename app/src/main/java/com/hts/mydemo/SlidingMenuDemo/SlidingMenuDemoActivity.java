package com.hts.mydemo.slidingmenudemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;

import com.hts.mydemo.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * SlidingMenu使用
 * Created by Chao on 2016/5/4.
 */
public class SlidingMenuDemoActivity extends SlidingFragmentActivity {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingmenudemo);
        // 初始化SlideMenu
        initRightMenu();
        // 初始化ViewPager
        initViewPager();
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        MainTab01 tab01 = new MainTab01();
        MainTab02 tab02 = new MainTab02();
        MainTab03 tab03 = new MainTab03();
        mFragments.add(tab01);
        mFragments.add(tab02);
        mFragments.add(tab03);
        /**
         * 初始化Adapter
         */
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
    }

    private void initRightMenu() {
        Fragment leftMenuFragment = new MenuLeftFragment();
        //设置左侧边栏的布局
        setBehindContentView(R.layout.slidingdemo_left_frame);
        //左侧边栏是一个framlayout布局，用menuLeftFragment的布局替换
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_left_menu, leftMenuFragment).commit();
        SlidingMenu menu = getSlidingMenu();
        //侧边栏模式，左侧+右侧
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式，在屏幕侧边才能滑动出侧边栏
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //根据dimension资源文件的ID来设置阴影的宽度
        menu.setShadowWidthRes(R.dimen.shadow_width);
        //根据资源文件ID来设置滑动菜单的阴影效果
        menu.setShadowDrawable(R.drawable.slidingdemo_shadow);
        // 设置滑动菜单视图的宽度
        //获取屏幕宽度，设置菜单宽度为屏幕像素宽度的4/5
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int menuWidth = dm.widthPixels * 4 / 5;
        menu.setBehindWidth(menuWidth);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);

        //设置右边（二级）侧滑菜单
        menu.setSecondaryMenu(R.layout.slidingdemo_right_frame);
        //根据资源文件ID来设置滑动菜单的阴影效果
        menu.setSecondaryShadowDrawable(R.drawable.slidingdemo_shadow);
        //右侧边栏是一个framlayout布局，用menuRightFragment的布局替换
        Fragment rightMenuFragment = new MenuRightFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_right_menu, rightMenuFragment).commit();
    }
    //layout中的左上方imagebutton点击事件
    public void showLeftMenu(View view) {
        getSlidingMenu().showMenu();
    }

    //layout中的右上方imagebutton点击事件
    public void showRightMenu(View view) {
        getSlidingMenu().showSecondaryMenu();
    }

}
