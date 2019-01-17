package com.example.dan.fragmentviewpagercommunication.view.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.dan.fragmentviewpagercommunication.view.fragment.FragmentFive;
import com.example.dan.fragmentviewpagercommunication.view.fragment.FragmentFour;
import com.example.dan.fragmentviewpagercommunication.view.fragment.FragmentOne;
import com.example.dan.fragmentviewpagercommunication.view.fragment.FragmentThree;
import com.example.dan.fragmentviewpagercommunication.view.fragment.FragmentTwo;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FSAdapter extends FragmentStatePagerAdapter {
    private final SparseArray<WeakReference<Fragment>> instantiatedFragment = new SparseArray<>();
    private ArrayList<String> mTabHeader;

    public FSAdapter(FragmentManager fm, ArrayList<String> mTabHeader) {
        super(fm);
        this.mTabHeader = mTabHeader;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0 :
                FragmentOne tab1 = new FragmentOne();
                return tab1;
            case 1 :
                FragmentTwo tab2 = new FragmentTwo();
                return tab2;
            case 2 :
                FragmentThree tab3 = new FragmentThree();
                return tab3;
            case 3 :
                FragmentFour tab4 = new FragmentFour();
                return tab4;
            case 4 :
                FragmentFive tab5 = new FragmentFive();
                return tab5;
            default :
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabHeader.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        instantiatedFragment.put(position, new WeakReference<Fragment>(fragment));
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        instantiatedFragment.remove(position);
        super.destroyItem(container, position, object);
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabHeader.get(position);
    }
}