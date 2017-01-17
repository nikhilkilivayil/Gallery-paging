

package com.example.android.effectivenavigation;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;


public class CollectionDemoActivity extends FragmentActivity {


    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;

    ViewPager mViewPager;

    TextView pageCountTextView;
    static int displayWidth,displayHeight;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_demo);
        pageCountTextView=(TextView)findViewById(R.id.pageCountTextView);
        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        displayWidth = metrics.widthPixels ;
        displayHeight = metrics.heightPixels;


        final ActionBar actionBar = getActionBar();
        actionBar.hide();

        actionBar.setDisplayHomeAsUpEnabled(true);

        // Set up the ViewPager, attaching the adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        pageCountTextView.setText((mViewPager.getCurrentItem()+1)+"/"+mDemoCollectionPagerAdapter.getCount());
    }


    public void nextClick(View v){
        int itemPos=mViewPager.getCurrentItem()+1;
        if(itemPos<mDemoCollectionPagerAdapter.getCount()) {
            mViewPager.setCurrentItem(itemPos);
            pageCountTextView.setText((itemPos+1) + "/" + mDemoCollectionPagerAdapter.getCount());
        }
    }
    public void prevClick(View v){
        int itemPos=(mViewPager.getCurrentItem()-1);
        if(itemPos>=0) {
            mViewPager.setCurrentItem(itemPos);
            pageCountTextView.setText(itemPos+1 + "/" + mDemoCollectionPagerAdapter.getCount());
        }
    }


    public  class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new DemoObjectFragment();
            Bundle args = new Bundle();
            args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    public static class DemoObjectFragment extends Fragment {

        public static final String ARG_OBJECT = "object";
        int column=4;

        GridView gridView;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_collection_object, container, false);
            Bundle args = getArguments();
            gridView=(GridView)rootView.findViewById(R.id.gridViewlayout);
            gridView.setNumColumns(column);
            int column_width = displayWidth/column ;
            int column_height = displayHeight/column ;



            /*((TextView) rootView.findViewById(android.R.id.text1)).setText(
                    Integer.toString(args.getInt(ARG_OBJECT)));*/
            return rootView;
        }
    }
}
