package com.bawei.www.day1dome.adpter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawei.www.day1dome.fragment.HomeFragment;
import com.bawei.www.day1dome.fragment.OtherFragment;

public class FragementAdpter extends FragmentPagerAdapter {

    private Context mcontext;
    private String[] tab = new String[]{
            "热销", "招牌", "主食", "小吃", "热门", "其他", "其他", "其他"
    };

    public FragementAdpter(FragmentManager fm, Context context) {
        super(fm);
        this.mcontext = context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new HomeFragment();
            case 1:
                return new OtherFragment();
            default:
                return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }

    @Override
    public int getCount() {
        return tab.length;
    }
}
