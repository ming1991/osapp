package net.oschina.app.fragment.pages;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import net.oschina.app.base.BaseHolder;
import net.oschina.app.base.BaseRefreshFragment;
import net.oschina.app.base.BaseRefreshProtocol;
import net.oschina.app.protocol.NewsPageProtocol;
import net.oschina.app.base.SuperBaseAdapter;
import net.oschina.app.bean.News;
import net.oschina.app.bean.NewsList;
import net.oschina.app.holder.NewsPagerHolder;
import net.oschina.app.ui.NewsPagerDetailActivity;

import java.util.List;


/**
 * 创建者:   ming001
 * 创建时间: 2016/11/2 23:39
 * 描述：    TODO
 */
public class NewsPageFramgent extends BaseRefreshFragment<NewsList, News> {

    @Override
    protected List<News> getSpecialList(NewsList list) {
        List<News> newsList =  list.getList();
        return newsList;
    }

    @Override
    protected SuperBaseAdapter<News> getSpecialBaseAdapter() {
        return new NewsPageAdapter(mDataList);
    }

    @Override
    public BaseRefreshProtocol<NewsList> getSpecilProtocol() {
        NewsPageProtocol newsPageProtocol = new NewsPageProtocol();
        return newsPageProtocol;
    }

    class NewsPageAdapter extends SuperBaseAdapter<News> {


        public NewsPageAdapter(List<News> dataSource) {
            super(dataSource);
        }

        @Override
        public BaseHolder getSpecialBaseHolder(int position) {

            return new NewsPagerHolder(mContext);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //跳转到咨询的详情页面
        News news = mDataList.get(position);

        Intent intent = new Intent(mContext, NewsPagerDetailActivity.class);

        intent.putExtra("newsId", news.getId()+""); //注意参数的类型 int 转化成的 string

        mContext.startActivity(intent);

    }
}
