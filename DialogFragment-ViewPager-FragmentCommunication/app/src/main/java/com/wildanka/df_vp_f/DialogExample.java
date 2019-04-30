package com.wildanka.df_vp_f;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.wildanka.df_vp_f.adapter.AdapterFragmentPager;

public class DialogExample extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment,container,false);
        TabLayout tabLayout = rootView.findViewById(R.id.tl);
        ViewPager viewPager = rootView.findViewById(R.id.vp);

        //create the adapter
        AdapterFragmentPager adapter = new AdapterFragmentPager(getChildFragmentManager());

        Bundle marketEnv = new Bundle();
        marketEnv.putString("firstCurrency", "btc");
        marketEnv.putString("secondCurrency", "idr");
        marketEnv.putString("lastPrice", "68000000");
        marketEnv.putString("selectedPrice", "68000000");

        //instance the fragment
        Content1 bidFragment = new Content1();
        bidFragment.setArguments(marketEnv);
        Content2 askFragment = new Content2();
        askFragment.setArguments(marketEnv);

        //adding fragment
        adapter.addFragment(bidFragment, "BID");
        adapter.addFragment(askFragment, "ASK");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Log.e("Width", "" + width);
        Log.e("height", "" + height);

        Window window = getDialog().getWindow();
        window.setLayout(width, (int) (height * 0.65));
        window.setGravity(Gravity.CENTER);
    }

}
