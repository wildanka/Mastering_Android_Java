package com.wildanka.df_vp_f.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.wildanka.df_vp_f.Content1;
import com.wildanka.df_vp_f.Content2;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by DAN on 6/9/2018.
 */

public class PlaceOrderbookFSPAdapter extends FragmentStatePagerAdapter {
    private final SparseArray<WeakReference<Fragment>> instantiatedFragment = new SparseArray<>();
//    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private ArrayList<String> mFragmentTitleList;
    private Bundle marketActivity;

    public PlaceOrderbookFSPAdapter(FragmentManager fm, ArrayList<String> mFragmentTitleList, Bundle marketActivity) {
        super(fm);
        this.mFragmentTitleList = mFragmentTitleList;
        this.marketActivity = marketActivity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                Content1 tab1 = new Content1();
                tab1.setArguments(marketActivity);
                return tab1;
            case 1 :
                Content2 tab2 = new Content2();
                tab2.setArguments(marketActivity);
                return tab2;
            default :
                return null;
        }
    }

    @Override
    public int getCount() {
        return mFragmentTitleList.size();
    }


    //region modify it using instantiateItem so it can be refreshed from activity

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        instantiatedFragment.put(position, new WeakReference<Fragment>(fragment));
        return fragment;
    }

    @Nullable
    public Fragment getFragment(final int position){
        final WeakReference<Fragment> wr = instantiatedFragment.get(position);
        if (wr != null){
            return wr.get();
        }else{
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
