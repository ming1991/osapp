package net.oschina.app.fragment.tabs;

import net.oschina.app.base.BaseTabFragment;
import net.oschina.app.bean.PagerInfo;
import net.oschina.app.fragment.pages.TweetPageFragment;

import java.util.List;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/2 23:03
 * 描述：    TODO
 */

public class TweetFragment extends BaseTabFragment {

    @Override
    public void initSpecialPagerInfos(List<PagerInfo> mPagerInfoList) {
        mPagerInfoList.add(new PagerInfo("最新动弹",null,new TweetPageFragment().getClass() ));
        mPagerInfoList.add(new PagerInfo("热门动弹",null,new TweetPageFragment().getClass() ));
        mPagerInfoList.add(new PagerInfo("我的动弹",null,new TweetPageFragment().getClass() ));

    }
}
