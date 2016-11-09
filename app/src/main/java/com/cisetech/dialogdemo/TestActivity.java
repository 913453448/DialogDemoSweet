package com.cisetech.dialogdemo;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class TestActivity extends AppCompatActivity {
    private ListView mListView;
    private int[] colors = {
            R.color.colorAccent,
            R.color.colorPrimary,
            R.color.colorPrimaryDark,
            R.color.colorAccent3,
            R.color.colorAccent4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mListView = (ListView) findViewById(R.id.id_lv);
        initData();
    }

    private void initData() {
       mListView.setAdapter(new BaseAdapter() {
           @Override
           public int getCount() {
               return 100;
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
               if(convertView==null){
                   convertView=View.inflate(getApplicationContext(),R.layout.item_layout,null);
               }
               View circleView = convertView.findViewById(R.id.id_rl_circle);
               GradientDrawable drawable= (GradientDrawable) circleView.getBackground();
               Log.e("TAG", "getView: drawable--->"+drawable);
               drawable.setColor(getResources().getColor(colors[position%colors.length]));
               return convertView;
           }
       });
    }
}
