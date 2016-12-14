package net.oschina.app.test;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import net.oschina.app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwipeRefreshLayoutActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView mListview;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<String> mList;
    private MyAdapteer mMyAdapteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        ButterKnife.bind(this);

        init();

        initData();

        initListener();




    }

    private void init() {
        //设置卷内的颜色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);


    }

    private void initListener() {

        //设置下拉刷新监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mList.add(0, "添加新的item:" + new Random().nextInt());
                        mMyAdapteer.notifyDataSetChanged();
                        //停止刷新动画
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mList.add(""+i);
        }

        mMyAdapteer = new MyAdapteer();
        mListview.setAdapter(mMyAdapteer);
    }

    class MyAdapteer extends BaseAdapter{

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
             TextView textView = new TextView(SwipeRefreshLayoutActivity.this);
              textView.setGravity(Gravity.CENTER);
              textView.setTextColor(Color.BLUE);
              textView.setText(mList.get(position));
            return textView;
        }
    }
}
