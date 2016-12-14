package net.oschina.app.protocol;

import android.support.annotation.NonNull;

import net.oschina.app.base.BaseRefreshProtocol;
import net.oschina.app.bean.NewsList;
import net.oschina.app.conf.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 创 建 者:  XQW
 * 创建时间:  2016/10/24 9:54
 * 描    述：
 */

public  class NewsPageProtocol extends BaseRefreshProtocol<NewsList> {



    //http://www.oschina.net/action/api/news_list?pageIndex=0&catalog=1&pageSize=20
        /*String url = Constants.URLS.BASEURL + "news_list";

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("pageIndex", 0 + "");
        paramsMap.put("catalog", 1 + "");
        paramsMap.put("pageSize", 20 + "");*/
    @NonNull
    @Override
    public String getUrl() {
        String url = Constants.URLS.BASEURL + "news_list";
        return url;
    }

    @Override
    protected Map<String, String> getParmasMap() {
        Map<String, String> map = new HashMap<>();

        map.put("pageIndex",mPageIndex+""); //根据reqType 请求方式,(初次,下拉,上拉),实时变动
        map.put("catalog","1");
        map.put("pageSize","20");

        return map;
    }


}