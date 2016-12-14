package net.oschina.app.protocol;

import android.support.annotation.NonNull;

import net.oschina.app.base.BaseRefreshProtocol;
import net.oschina.app.bean.TweetsList;
import net.oschina.app.conf.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/5 15:34
 * 描述：    TODO
 */
public class TweetPageProtocol extends BaseRefreshProtocol<TweetsList> {
    @NonNull
    @Override
    public String getUrl() {
        String url = Constants.URLS.BASEURL+"tweet_list";
        return url;
    }

    @Override
    protected Map<String, String> getParmasMap() {
        //http://www.oschina.net/action/api/tweet_list?uid=0&pageIndex=0&pageSize=20 
        Map<String, String> parmasMap = new HashMap<>();

        parmasMap.put("uid","0");  //用户id
        parmasMap.put("pageIndex",mPageIndex+""); //根据reqType 请求方式,(初次,下拉,上拉),实时变动
        parmasMap.put("pageSize","20");

        return parmasMap;
    }


}
