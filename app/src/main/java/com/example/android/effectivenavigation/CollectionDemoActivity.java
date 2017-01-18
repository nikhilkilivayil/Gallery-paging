

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



import java.util.ArrayList;
import java.util.List;



public class CollectionDemoActivity extends FragmentActivity {


    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;

    ViewPager mViewPager;

    TextView pageCountTextView;
    static int displayWidth,displayHeight;
    private List<Album> albumList;
    int columnCount=4;
    int rowCount=4;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_demo);
        pageCountTextView=(TextView)findViewById(R.id.pageCountTextView);
        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        displayWidth = metrics.widthPixels ;
        displayHeight = (metrics.heightPixels)-200;




        final ActionBar actionBar = getActionBar();
       // actionBar.hide();

       // actionBar.setDisplayHomeAsUpEnabled(true);

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
        List<ArrayList<Album>> splittedList;

        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);


            String type=getIntent().getStringExtra("type");
            if(type.equals("list")){
                albumList=getIntent().getParcelableArrayListExtra("value");
            }else if(type.equals("dir")){
                String path=getIntent().getStringExtra("value");
            }else{

            }
            splittedList= getChunkList(albumList,(columnCount*rowCount));
        }


        private <T> List<ArrayList<T>> getChunkList(List<T> largeList , int chunkSize) {
            List<ArrayList<T>> chunkList = new ArrayList<>();
            for (int i = 0 ; i <  largeList.size() ; i += chunkSize) {
                ArrayList<T> list=new ArrayList<T>(largeList.subList(i , i + chunkSize >= largeList.size() ? largeList.size() : i + chunkSize));
                chunkList.add(list);
            }
            return chunkList;
        }
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new DemoObjectFragment();
            Bundle args = new Bundle();
            args.putInt(DemoObjectFragment.ARG_ROW_COUNT,rowCount);
            args.putInt(DemoObjectFragment.ARG_COLUMN_COUNT,columnCount);
            args.putParcelableArrayList(DemoObjectFragment.ARG_LIST,splittedList.get(i));
            args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return splittedList.size();
        }
    }

    public static class DemoObjectFragment extends Fragment {

        public static final String ARG_OBJECT = "object";
        public static final String ARG_COLUMN_COUNT="column";
        public static final String ARG_ROW_COUNT="row";
        public static final String ARG_LIST="list";

        int columnCount;
        int rowCount;
        List<Album> albumList;

        GridView gridView;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_collection_object, container, false);
            Bundle args = getArguments();
            columnCount=args.getInt(ARG_COLUMN_COUNT);
            rowCount=args.getInt(ARG_ROW_COUNT);
            albumList=args.getParcelableArrayList("list");
            gridView=(GridView)rootView.findViewById(R.id.gridViewlayout);
            gridView.setNumColumns(columnCount);
            int column_width = displayWidth/ rowCount;
            int column_height = displayHeight/columnCount ;

            gridView.setAdapter(new AlbumAdapter(getContext(),albumList,column_width,column_height));




            /*((TextView) rootView.findViewById(android.R.id.text1)).setText(
                    Integer.toString(args.getInt(ARG_OBJECT)));*/
            return rootView;
        }
    }
}
