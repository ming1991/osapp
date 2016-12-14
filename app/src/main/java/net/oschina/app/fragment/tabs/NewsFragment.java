package net.oschina.app.fragment.tabs;

import net.oschina.app.base.BaseTabFragment;
import net.oschina.app.bean.PagerInfo;
import net.oschina.app.fragment.DefaultFramgent;
import net.oschina.app.fragment.pages.NewsPageFramgent;

import java.util.List;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/2 21:03
 * 描述：    TODO
 */

public class NewsFragment extends BaseTabFragment {

    @Override
    public void initSpecialPagerInfos(List<PagerInfo> mPagerInfoList) {

        mPagerInfoList.add(new PagerInfo("资讯",null,new NewsPageFramgent().getClass() ));
        mPagerInfoList.add(new PagerInfo("热点",null,new DefaultFramgent().getClass() ));
        mPagerInfoList.add(new PagerInfo("博客",null,new DefaultFramgent().getClass() ));
        mPagerInfoList.add(new PagerInfo("推荐",null,new DefaultFramgent().getClass() ));
    }

}
