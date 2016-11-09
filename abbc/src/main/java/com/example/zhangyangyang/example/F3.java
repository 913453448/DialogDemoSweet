package com.example.zhangyangyang.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by zhangyangyang on 2016/10/24.
 */

public class F3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f3, container, false);
        final ListView listView = (ListView) v.findViewById(R.id.listview);
        final mAdapter adapter = new mAdapter();
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (null != adapter) {
                    if (0 == firstVisibleItem && null != listView.getChildAt(0) && listView.getChildAt(0).getTop() >= 0) {
                        // 第一项完全显示出来
                        MainActivity ac = (MainActivity) getActivity();
                    } else {
                        MainActivity ac = (MainActivity) getActivity();
                    }
                }
            }
        });
        return v;
    }

    private class mAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, null);
            return convertView;
        }
    }
}
