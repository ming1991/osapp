package net.oschina.app.fragment.pages;

import android.view.View;
import android.widget.AdapterView;

import net.oschina.app.base.BaseHolder;
import net.oschina.app.base.BaseRefreshFragment;
import net.oschina.app.base.BaseRefreshProtocol;
import net.oschina.app.base.SuperBaseAdapter;
import net.oschina.app.bean.Tweet;
import net.oschina.app.bean.TweetsList;
import net.oschina.app.holder.TweetPageHolder;
import net.oschina.app.protocol.TweetPageProtocol;

import java.util.List;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/3 15:54
 * 描述：    TODO 继承带下拉刷新的fragment
 *
 * TweetsList,Tweet ,大的bean 和 集合中bean
 * List<Tweet> tweetslist = new ArrayList<Tweet>();
 */

public class TweetPageFragment extends BaseRefreshFragment<TweetsList,Tweet> {

    @Override
    protected List<Tweet> getSpecialList(TweetsList list) {

        return list.getTweetslist();
    }

    @Override
    protected SuperBaseAdapter<Tweet> getSpecialBaseAdapter() {
        TweetBaseAdapter tweetBaseAdapter = new TweetBaseAdapter(mDataList);
        return tweetBaseAdapter;
    }

    @Override
    public BaseRefreshProtocol<TweetsList> getSpecilProtocol() {
        TweetPageProtocol tweetPageProtocol = new TweetPageProtocol();
        return tweetPageProtocol;
    }

    class TweetBaseAdapter extends SuperBaseAdapter<Tweet>{

        public TweetBaseAdapter(List<Tweet> dataSource) {
            super(dataSource);
        }

        @Override
        public BaseHolder getSpecialBaseHolder(int position) {
            TweetPageHolder tweetPageHolder = new TweetPageHolder(mContext);
            return tweetPageHolder;
        }
    }
    
    //

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(mContext, "我被点击了", Toast.LENGTH_SHORT).show();
        //跳转到详情页面


    }
}
