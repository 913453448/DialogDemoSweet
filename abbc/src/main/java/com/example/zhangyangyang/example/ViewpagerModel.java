package com.example.zhangyangyang.example;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.mingle.pulltonextlayout.model.PullToNextModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyangyang on 2016/10/26.
 */
public class ViewpagerModel extends PullToNextModel {
    private int index;
    private ViewPager vp;
    private FragmentActivity ac;
    private Button b1, b2, b3;

    public ViewpagerModel(int index, FragmentActivity ac) {
        this.index = index;
        this.ac = ac;
    }

    @Override
    public int getLayoutViewId() {
        return R.layout.activity_main2;
    }

    @Override
    public void onBindView(View view) {
        b1 = (Button) view.findViewById(R.id.b1);
        b2 = (Button) view.findViewById(R.id.b2);
        b3 = (Button) view.findViewById(R.id.b3);
        vp = (ViewPager) view.findViewById(R.id.viewpager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new F1());
        fragments.add(new F2());
        fragments.add(new F3());
        MyAdapter adapter = new MyAdapter(ac.getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0, true);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1, true);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(2, true);
            }
        });
    }

    private class MyAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments;

        MyAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
