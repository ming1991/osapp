package net.oschina.app.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.oschina.app.bean.PagerInfo;

import java.util.List;

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<PagerInfo> mPagerInfoList;
        private Context mContext;

        public NewsFragmentPagerAdapter(FragmentManager fm, List<PagerInfo> pagerInfoList, Context context) {
            super(fm);
            mPagerInfoList = pagerInfoList;
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            PagerInfo pagerInfo = mPagerInfoList.get(position);
            Fragment fragment = Fragment.instantiate(mContext,pagerInfo.clz.getName() , pagerInfo.bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return mPagerInfoList==null?0:mPagerInfoList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPagerInfoList.get(position).title;
        }
    }