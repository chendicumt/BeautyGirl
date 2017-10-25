package com.panda.beautygirl.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by chendi on 2017/10/16.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

//    private List<Fragment> mList;
//    private String tittle[]=new String[]{"图片","聊天","发现","我的"};
    public FragmentAdapter(FragmentManager manager)
    {
        super(manager);
//        this.mList=mList;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new SecondFragment();
        } else if (position == 2) {
            return new ThirdFragment();
        }else if (position==3){
            return new ForthFragment();
        }
        return new FirstFragment();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "开始";
    }

    @Override
    public int getCount() {
        return 4;
    }


}
