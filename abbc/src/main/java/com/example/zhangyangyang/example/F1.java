package com.example.zhangyangyang.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by zhangyangyang on 2016/10/24.
 */

public class F1 extends Fragment {
    private ScrollView scrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f1, container, false);
        scrollView = (ScrollView) v.findViewById(R.id.scrollview);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (scrollView != null) {
            scrollView.pageScroll(ScrollView.FOCUS_UP);
        }
    }
}
