package net.oschina.app.ui;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import net.oschina.app.R;
import net.oschina.app.fragment.DefaultFramgent;
import net.oschina.app.fragment.tabs.NewsFragment;
import net.oschina.app.fragment.tabs.TweetFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ------------------------------------------
 * Created by weisongli on 2015/10/22.
 * <p/>
 * 描    述:
 * <p/>
 * 修订历史:
 * ------------------------------------------
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.tabHost)
    FragmentTabHost mTabHost;
    @BindView(R.id.ibQuickOption)
    ImageButton mIbQuickOption;
    private List<TabInfo> mTabInfoDataList;
    private MAINTABINFO[] mValues;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //初始化底部的菜单
        initFragmentTabHost();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initFragmentTabHost() {
        //初始化数据
        initTabInfoData();

        //初始化FragmentTabHost
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);


        //1.采用集合循环添加选项卡
        // for (int i = 0; i < mTabInfoDataList.size(); i++) {

        //2.mValues
        for (int i = 0; i < mValues.length; i++) {

            //TabInfo tabInfo = mTabInfoDataList.get(i);

            MAINTABINFO tabInfo = mValues[i];

            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tabInfo.title); //标记

            //添加一个复杂的视图
            View view = View.inflate(this, R.layout.inflate_indicatorview, null);
            TextView tab_title = (TextView) view.findViewById(R.id.tab_title);
            tab_title.setText(tabInfo.title);
            tab_title.setCompoundDrawablesWithIntrinsicBounds(0, tabInfo.topResId, 0, 0); //设置textview上面的图片

            tabSpec.setIndicator(view);

            Bundle args = new Bundle();
            args.putString("args", i + "");

            mTabHost.addTab(tabSpec, tabInfo.clz, args);

            if (i == 2) {
                view.setVisibility(View.INVISIBLE);
            }

        }

        // 去除分割线
        if (Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

    }

    private void initTabInfoData() {

        //1.采用集合封装底部菜单的数据
        mTabInfoDataList = new ArrayList<>();
        mTabInfoDataList.add(new TabInfo("综合", R.drawable.tab_icon_new, new NewsFragment().getClass()));
        mTabInfoDataList.add(new TabInfo("动弹", R.drawable.tab_icon_tweet, new TweetFragment().getClass()));
        mTabInfoDataList.add(new TabInfo("空", R.drawable.tab_icon_new, null));
        mTabInfoDataList.add(new TabInfo("发现", R.drawable.tab_icon_explore, new DefaultFramgent().getClass()));
        mTabInfoDataList.add(new TabInfo("我", R.drawable.tab_icon_me, new DefaultFramgent().getClass()));

        //2.采用枚举封装底部菜单的数据
        mValues = MAINTABINFO.values();

    }

    @OnClick(R.id.ibQuickOption)
    public void onClick() {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        AppIndex.AppIndexApi.start(mClient, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mClient, getIndexApiAction());
        mClient.disconnect();
    }

    class TabInfo {
        public String title;
        public int topResId;
        public Class clz;

        public TabInfo(String title, int topResId, Class clz) {
            this.title = title;
            this.topResId = topResId;
            this.clz = clz;
        }

    }


    enum MAINTABINFO {
        NEW("综合", R.drawable.tab_icon_new, new NewsFragment().getClass()),
        TWEET("动弹", R.drawable.tab_icon_tweet, new TweetFragment().getClass()),
        QUICK("空", R.drawable.tab_icon_new, null),
        EXPLORE("发现", R.drawable.tab_icon_explore, new DefaultFramgent().getClass()),
        MW("我", R.drawable.tab_icon_me, new DefaultFramgent().getClass());

        public String title;
        public int topResId;
        public Class clz;

        MAINTABINFO(String title, int topResId, Class clz) {
            this.title = title;
            this.topResId = topResId;
            this.clz = clz;
        }
    }
}
