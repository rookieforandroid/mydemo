package com.hts.mydemo.viewpagerdemo;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.hts.mydemo.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerDemo extends FragmentActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    /**
     * 页面集合
     */
    private List<Fragment> fragmentList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagerdemo);

        initView();
        initFragment();
        initTab();

//        mTabLayout.setOnClickListener(new MyClickListener());

        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager(),fragmentList,mTitleList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
//        mTabLayout.setOnTabSelectedListener(new MySelectedListener());
    }


    private void initFragment() {
        fragmentList= new ArrayList<>();

         Fragment1 fragment1= new Fragment1();
         Fragment2 fragment2= new Fragment2();
         Fragment3 fragment3= new Fragment3();
         Fragment4 fragment4= new Fragment4();
         Fragment5 fragment5= new Fragment5();

        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
    }

    private void initTab() {
        //添加页卡标题
        mTitleList.add("页面一");
        mTitleList.add("页面二");
        mTitleList.add("页面三");
        mTitleList.add("页面四");
        mTitleList.add("页面五");


        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)),0);//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)),1);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)),2);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(3)),3);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(4)),4);
    }


//    private class MySelectedListener implements TabLayout.OnTabSelectedListener{
//
//
//        @Override
//        public void onTabSelected(TabLayout.Tab tab) {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            hideAllFragment(transaction);
//            switch (tab.getPosition()){
//                case 0:
//                    if (fragment1 == null){
//                        fragment1 = new Fragment1();
//                        fragmentList.add(fragment1);
//                        transaction.add(fragment1,"fg1");
//                    }else {
//                        transaction.show(fragment1);
//                    }
//                case 1:
//                    if (fragment2 == null){
//                        fragment2 = new Fragment2();
//                        fragmentList.add(fragment2);
//                        transaction.add(fragment2,"fg2");
//                    }else {
//                        transaction.show(fragment2);
//                    }
//                case 2:
//                    if (fragment3 == null){
//                        fragment3 = new Fragment3();
//                        fragmentList.add(fragment3);
//                        transaction.add(fragment3,"fg3");
//                    }else {
//                        transaction.show(fragment3);
//                    }
//                case 3:
//                    if (fragment4 == null){
//                        fragment4 = new Fragment4();
//                        fragmentList.add(fragment4);
//                        transaction.add(fragment4,"fg4");
//                    }else {
//                        transaction.show(fragment4);
//                    }
//                case 4:
//                    if (fragment5 == null){
//                        fragment5 = new Fragment5();
//                        fragmentList.add(fragment5);
//                        transaction.add(fragment5,"fg5");
//                    }else {
//                        transaction.show(fragment5);
//                    }
//
//                    transaction.commit();
//            }
//        }
//
//        @Override
//        public void onTabUnselected(TabLayout.Tab tab) {
//
//        }
//
//        @Override
//        public void onTabReselected(TabLayout.Tab tab) {
//
//        }
//    }
//
//    //隐藏所有Fragment
//    private void hideAllFragment(FragmentTransaction fragmentTransaction){
//        if(fragment1 != null)fragmentTransaction.hide(fragment1);
//        if(fragment2 != null)fragmentTransaction.hide(fragment2);
//        if(fragment3 != null)fragmentTransaction.hide(fragment3);
//        if(fragment4 != null)fragmentTransaction.hide(fragment4);
//    }


}
