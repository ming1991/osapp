package net.oschina.app.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import net.oschina.app.R;
import net.oschina.app.fragment.pages.NewsPagerDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 创建者:   ming001
 * 创建时间: 2016/11/9 15:52
 * 描述：    TODO
 */
public class NewsPagerDetailActivity extends AppCompatActivity{

    @BindView(R.id.container)
    FrameLayout mContainer;
    @BindView(R.id.emoji_keyboard)
    FrameLayout  mEmojiKeyboard;
    @BindView(R.id.option)
    FrameLayout  mOption;
    @BindView(R.id.activity_root)
    LinearLayout mActivityRoot;

    private String mNewsId; //条目的id

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_pager_detail);
        ButterKnife.bind(this);
        init();
        initFraments();

    }

    private void init() {
        mNewsId = getIntent().getStringExtra("newsId");
    }

    private void initFraments() {  //还差底部按钮的fragment

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        Bundle args = new Bundle();
        args.putString("args", mNewsId); //条目的id
        fragmentTransaction.add(R.id.container, Fragment.instantiate(this, NewsPagerDetailFragment.class.getName(), args), "NewsPagerDetailFragment"); //tag标记
//        fragmentTransaction.add(R.id.container, new 表情的Fragment(), "NewsPagerDetailFragment");

        fragmentTransaction.commit();
    }
}
