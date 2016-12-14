package net.oschina.app.protocol;

import android.support.annotation.NonNull;

import net.oschina.app.base.BaseProtocol;
import net.oschina.app.bean.NewsDetail;
import net.oschina.app.conf.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/9 15:52
 * 描述：    TODO
 */
public class NewsPagerDetailProtocol extends BaseProtocol<NewsDetail> {
    private String newsId;

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    @NonNull
    @Override
    public String getUrl() {
        return Constants.URLS.BASEURL + "news_detail";
    }

    @Override
    protected Map<String, String> getParmasMap() {
        Map<String, String> parmasMap = new HashMap<>();
        parmasMap.put("id", newsId);
        return parmasMap;
    }
    //http://www.oschina.net/action/api/news_detail?id=64198
}
