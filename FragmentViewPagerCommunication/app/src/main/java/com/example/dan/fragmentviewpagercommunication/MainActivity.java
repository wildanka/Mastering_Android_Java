package com.example.dan.fragmentviewpagercommunication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dan.fragmentviewpagercommunication.view.adapter.FSAdapter;
import com.example.dan.fragmentviewpagercommunication.view.fragment.FragmentFive;
import com.example.dan.fragmentviewpagercommunication.view.fragment.FragmentFour;
import com.example.dan.fragmentviewpagercommunication.view.fragment.FragmentOne;
import com.example.dan.fragmentviewpagercommunication.view.fragment.FragmentThree;
import com.example.dan.fragmentviewpagercommunication.view.fragment.FragmentTwo;
import com.example.dan.fragmentviewpagercommunication.view.interactor.ITest;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FSAdapter adapter;
    private TabLayout tabLayout;

    //region set Interface Listener
    public void setTestListener(ITest testListener){
        this.mITestListener = testListener;
    }
    private ITest mITestListener;
    //endregion set Interface Listener trigger


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //bindview
        tabLayout = (TabLayout) findViewById(R.id.tl_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mITestListener.showToast();

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                //TODO : do communicate using interface
                int position = tabLayout.getSelectedTabPosition();
                Log.d(TAG, "Active Fragment onClick: "+position);
                Fragment fragment = adapter.getFragment(tabLayout.getSelectedTabPosition());
                Fragment fragment0 = adapter.getFragment(0);
                Fragment fragment1 = adapter.getFragment(1);
                Fragment fragment2 = adapter.getFragment(2);
                Fragment fragment3 = adapter.getFragment(3);
                Fragment fragment4 = adapter.getFragment(4);
                if (fragment != null) {
                    switch (position){
                        case 0 :
                            mITestListener.showToast();
                            //region fragment interactor
                            if (fragment0 != null) {
                                ((FragmentOne) fragment0).onRefresh();
                            }
                            if (fragment1 != null) {
                                ((FragmentTwo) fragment1).onRefresh();
                            }
                            if (fragment2 != null) {
                                ((FragmentThree) fragment2).onRefresh();
                            }
                            if (fragment3 != null) {
                                ((FragmentFour) fragment3).onRefresh();
                            }
                            if (fragment4 != null) {
                                ((FragmentFive) fragment4).onRefresh();
                            }
                            break;
                        //endregion fragment interactor
                        case 1 :
                            //region fragment interactor
                            if (fragment0 != null) {
                                ((FragmentOne) fragment0).onRefresh();
                            }
                            if (fragment1 != null) {
                                ((FragmentTwo) fragment1).onRefresh();
                            }
                            if (fragment2 != null) {
                                ((FragmentThree) fragment2).onRefresh();
                            }
                            if (fragment3 != null) {
                                ((FragmentFour) fragment3).onRefresh();
                            }
                            if (fragment4 != null) {
                                ((FragmentFive) fragment4).onRefresh();
                            }
                            break;
                        //endregion fragment interactor
                        case 2 :
                            //region fragment interactor
                            if (fragment0 != null) {
                                ((FragmentOne) fragment0).onRefresh();
                            }
                            if (fragment1 != null) {
                                ((FragmentTwo) fragment1).onRefresh();
                            }
                            if (fragment2 != null) {
                                ((FragmentThree) fragment2).onRefresh();
                            }
                            if (fragment3 != null) {
                                ((FragmentFour) fragment3).onRefresh();
                            }
                            if (fragment4 != null) {
                                ((FragmentFive) fragment4).onRefresh();
                            }
                            break;
                        //endregion fragment interactor
                        case 3 :
                            //region fragment interactor
                            if (fragment0 != null) {
                                ((FragmentOne) fragment0).onRefresh();
                            }
                            if (fragment1 != null) {
                                ((FragmentTwo) fragment1).onRefresh();
                            }
                            if (fragment2 != null) {
                                ((FragmentThree) fragment2).onRefresh();
                            }
                            if (fragment3 != null) {
                                ((FragmentFour) fragment3).onRefresh();
                            }
                            if (fragment4 != null) {
                                ((FragmentFive) fragment4).onRefresh();
                            }
                            break;
                        //endregion fragment interactor
                        case 4 :
                            //region fragment interactor
                            if (fragment0 != null) {
                                ((FragmentOne) fragment0).onRefresh();
                            }
                            if (fragment1 != null) {
                                ((FragmentTwo) fragment1).onRefresh();
                            }
                            if (fragment2 != null) {
                                ((FragmentThree) fragment2).onRefresh();
                            }
                            if (fragment3 != null) {
                                ((FragmentFour) fragment3).onRefresh();
                            }
                            if (fragment4 != null) {
                                ((FragmentFive) fragment4).onRefresh();
                            }
                            break;
                        //endregion fragment interactor
                    }
                }
            }
        });

        //tabs arrayList
        ArrayList<String> tabs = new ArrayList<>();
        tabs.add("Overview");
        tabs.add("Orderbook");
        tabs.add("Market Activity");
        tabs.add("Open Order");
        tabs.add("History");

        //viewpager
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_main);
        adapter = new FSAdapter(getSupportFragmentManager(), tabs);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}