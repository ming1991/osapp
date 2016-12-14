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

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class StickylistheadersActivity extends AppCompatActivity {

    private StickyListHeadersListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stickylistheaders);

        initView();
        initData();

        //ImageSwitcher
        //Gallery
    }

    private void initData() {
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
    }

    private void initView() {
        mListView = (StickyListHeadersListView) findViewById(R.id.listView);
    }

    class MyAdapter extends BaseAdapter implements StickyListHeadersAdapter{

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
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(StickylistheadersActivity.this);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLUE);
            textView.setText("我是成功视图");
            return textView;
        }

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
             TextView textView = new TextView(StickylistheadersActivity.this);
              textView.setGravity(Gravity.CENTER);
              textView.setTextColor(Color.BLUE);
              textView.setText("我是标题"+position);
            return textView;
        }

        @Override
        public long getHeaderId(int position) {
            return position;
        }
    }
}
