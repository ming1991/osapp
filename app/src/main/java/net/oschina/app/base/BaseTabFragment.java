package net.oschina.app.base;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import net.oschina.app.R;
import net.oschina.app.adapter.NewsFragmentPagerAdapter;
import net.oschina.app.bean.PagerInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/2 21:03
 * 描述：    TODO  带指示器的fragment
 */

public abstract class BaseTabFragment extends BaseFragment {

    @BindView(R.id.viewpagertab)
    SmartTabLayout mViewpagertab;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private List<PagerInfo> mPagerInfoList;


    @Override
    public void init() {

    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_news, null);
        return view;
    }

    @Override
    protected void initData() {

        mPagerInfoList = new ArrayList<>();

        initSpecialPagerInfos(mPagerInfoList);


        NewsFragmentPagerAdapter newsFragmentPagerAdapter = new NewsFragmentPagerAdapter(getChildFragmentManager(),mPagerInfoList,mContext);
        mViewpager.setAdapter(newsFragmentPagerAdapter);

        mViewpagertab.setViewPager(mViewpager);

    }

    /**
     * 交给子类去封装页面的数据
     * */
    public abstract void initSpecialPagerInfos(List<PagerInfo> mPagerInfoList);

    @Override
    protected void initListener() {

    }

}
