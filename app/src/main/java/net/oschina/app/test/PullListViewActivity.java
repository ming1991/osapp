package net.oschina.app.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.oschina.app.R;

public class PullListViewActivity extends AppCompatActivity {

    private PullListview mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_list_view);

        initView();


    }

    private void initView() {
        mListView = (PullListview) findViewById(R.id.listView);
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
             TextView textView = new TextView(PullListViewActivity.this);
              textView.setGravity(Gravity.CENTER);
              textView.setTextColor(Color.BLUE);
              textView.setText("我是成功视图");
            return textView;
        }
    }
}
