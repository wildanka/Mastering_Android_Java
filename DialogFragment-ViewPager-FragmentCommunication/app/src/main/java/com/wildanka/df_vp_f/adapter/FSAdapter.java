package com.wildanka.df_vp_f.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wildanka.df_vp_f.fragment.BlankFragment1;
import com.wildanka.df_vp_f.fragment.Fragment2;

import java.util.List;

public class FSAdapter extends FragmentStatePagerAdapter {
    private List<String> tabTitle;

    public FSAdapter(FragmentManager fm, List<String> tabTitle) {
        super(fm);
        this.tabTitle = tabTitle;
    }

    @Override
    public int getCount() {
        return tabTitle.size();
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0 :
                BlankFragment1 fragment1 = new BlankFragment1();
                return fragment1;
            case 1 :
                Fragment2 fragment2 = new Fragment2();
                return fragment2;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position);
    }
}
