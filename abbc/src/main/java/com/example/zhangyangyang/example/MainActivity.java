package com.example.zhangyangyang.example;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.mingle.pulltonextlayout.OnItemSelectListener;
import com.mingle.pulltonextlayout.PullToNextLayout;
import com.mingle.pulltonextlayout.adapter.PullToNextModelAdapter;
import com.mingle.pulltonextlayout.anim.PullToNextAnimationI;
import com.mingle.pulltonextlayout.model.PullToNextModel;
import com.nineoldandroids.animation.Animator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    public PullToNextLayout pullToNextLayout;
    private List<PullToNextModel> list;
    private int currentIndex = 0;
    private PullToNextModelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullToNextLayout = (PullToNextLayout) findViewById(R.id.pull);
        list = new ArrayList<>();
        list.add(new ScrollModel(currentIndex++));
        list.add(new ViewpagerModel(currentIndex++, this));
        adapter = new PullToNextModelAdapter(this, list);
        pullToNextLayout.setAdapter(adapter);
        pullToNextLayout.setOnItemSelectListener(new OnItemSelectListener() {
            @Override
            public void onSelectItem(int position, View view) {
                setTitle(position + 1 + ".0 谷歌仍是毕业生心目中的最佳雇主");
            }
        });
        setTitle(1 + ".0 谷歌仍是毕业生心目中的最佳雇主");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
