package com.hts.mydemo.ViewPagerDemo;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitleList;
    private List<Fragment> fragmentList;

    public MyPagerAdapter(FragmentManager fm,List<Fragment> fragmentList,List<String> mTitleList ) {
        super(fm);
        this.fragmentList = fragmentList;
        this.mTitleList = mTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);//页卡标题
    }

}
