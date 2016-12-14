package net.oschina.app.fragment.pages;

import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import net.oschina.app.R;
import net.oschina.app.base.BaseFragment;
import net.oschina.app.base.BaseHolder;
import net.oschina.app.base.BaseProtocol;
import net.oschina.app.protocol.NewsPageProtocol;
import net.oschina.app.base.SuperBaseAdapter;
import net.oschina.app.bean.News;
import net.oschina.app.bean.NewsList;
import net.oschina.app.conf.Constants;
import net.oschina.app.holder.NewsPagerHolder;
import net.oschina.app.util.XmlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;


/**
 * 创建者:   ming001
 * 创建时间: 2016/11/2 23:39
 * 描述：    TODO
 */
public class NewsPageFramgentBackup extends BaseFragment {
    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.springView)
    SpringView mSpringView;
    private List<News> mDataList;
    private Handler mHandler = new Handler();
    private NewsPageAdapter mNewsPageAdapter;

    //加载页的索引
    private int mCurrentPageIndex = 0;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_news_page, null);
        return view;
    }

    @Override
    protected void initData() {
        //添加头和尾
        //SpringView 为不想去自定义头/尾的懒人提供了7种默认的实现
        // （模仿了阿里，腾讯，美团等多种风格）如下，还会继续增加
        /*
        DefaultHeader (com.liaoinstan.springview.container)
        AcFunHeader (com.liaoinstan.springview.container)
        RotationHeader (com.liaoinstan.springview.container)
        MeituanHeader (com.liaoinstan.springview.container)
        AliHeader (com.liaoinstan.springview.container)
         */
        /*
        DefaultFooter (com.liaoinstan.springview.container)
        AcFunFooter (com.liaoinstan.springview.container)
        RotationFooter (com.liaoinstan.springview.container)
        MeituanFooter (com.liaoinstan.springview.container)
        AliFooter (com.liaoinstan.springview.container)

         */

        //添加下拉和上拉刷新 头尾
        mSpringView.setHeader(new MeituanHeader(mContext));
        mSpringView.setFooter(new MeituanFooter(mContext));

        //设置下拉刷新的模式
        //Type.OVERLAP 头固定不定
        //Type.FOLLOW  头跟随动
        mSpringView.setType(SpringView.Type.FOLLOW);

        onInitLoadData();

    }

    /**
     * 初始化加载数据
     */
    private void onInitLoadData() {

        //模拟数据
        mDataList = new ArrayList<>();

        mNewsPageAdapter = new NewsPageAdapter(mDataList);

        mListView.setAdapter(mNewsPageAdapter);


        //网络请求数据
        //http://www.oschina.net/action/api/news_list?pageIndex=0&catalog=1&pageSize=20
        /*String url = Constants.URLS.BASEURL + "news_list";

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("pageIndex", 0 + "");
        paramsMap.put("catalog", 1 + "");
        paramsMap.put("pageSize", 20 + "");*/

        NewsPageProtocol newsPageProtocol = new NewsPageProtocol();
        newsPageProtocol.setPageIndex(mCurrentPageIndex);
        newsPageProtocol.loadDataByGet(new BaseProtocol.Callback<NewsList>() {
            @Override
            public void onError(Call call, Exception e, int id, int reqType) {

            }

            @Override
            public void onResponse(NewsList list, int id, int reqType) {

            }
        },0);

        /*mSpringView.onFinishFreshAndLoad();

        Toast.makeText(mContext, "网络出错了", Toast.LENGTH_SHORT).show();*/


        /*
         mSpringView.onFinishFreshAndLoad();

                Toast.makeText(mContext, "初次请求成功", Toast.LENGTH_SHORT).show();
                // Log.d("onResponse", "onResponse: "+response);
                //解析数据,断点查看
                NewsList newsList = XmlUtils.toBean(NewsList.class, response.getBytes());
                if (newsList != null) {

                    List<News> list = newsList.getList();

                    if (list != null&&list.size()>0) {

                        mDataList.addAll(list);
                        mCurrentPageIndex++;

                        //通知更新
                        mNewsPageAdapter.notifyDataSetChanged();
                    }

                } */

    }

    @Override
    protected void initListener() {
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                onRefreshData();
            }

            @Override
            public void onLoadmore() {
                onLoadMoreData();
            }
        });
    }

    /**
     * 上拉刷新加载更多数据
     */
    private void onLoadMoreData() {


//网络请求数据
        //http://www.oschina.net/action/api/news_list?pageIndex=0&catalog=1&pageSize=20
        String url = Constants.URLS.BASEURL + "news_list";

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("pageIndex", mCurrentPageIndex + "");
        paramsMap.put("catalog", 1 + "");
        paramsMap.put("pageSize", 20 + "");

        OkHttpUtils
                .get()
                .url(url)
                .params(paramsMap)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mSpringView.onFinishFreshAndLoad();

                        Toast.makeText(mContext, "网络出错了", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mSpringView.onFinishFreshAndLoad();

                        Toast.makeText(mContext, "上拉请求成功", Toast.LENGTH_SHORT).show();
                        // Log.d("onResponse", "onResponse: "+response);
                        //解析数据,断点查看
                        NewsList newsList = XmlUtils.toBean(NewsList.class, response.getBytes());
                        if (newsList!=null){

                            List<News> list = newsList.getList();

                            if (list!=null&&list.size()>0){
                                mDataList.addAll(list);

                                mCurrentPageIndex++;

                                //通知更新
                                mNewsPageAdapter.notifyDataSetChanged();
                            }

                        }


                    }
                });

    }

    /**
     * 下拉刷新数据
     * 回到第一条数据加载
     */
    private void onRefreshData() {
        //网络请求数据
        //http://www.oschina.net/action/api/news_list?pageIndex=0&catalog=1&pageSize=20
        String url = Constants.URLS.BASEURL + "news_list";

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("pageIndex", 0 + "");
        paramsMap.put("catalog", 1 + "");
        paramsMap.put("pageSize", 20 + "");

        OkHttpUtils
                .get()
                .url(url)
                .params(paramsMap)
                .build()
                .execute(new StringCallback() {   //异步加载数据,在主线程中回调
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mSpringView.onFinishFreshAndLoad();

                        Toast.makeText(mContext, "网络出错了", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mSpringView.onFinishFreshAndLoad();

                        Toast.makeText(mContext, "下拉刷新成功", Toast.LENGTH_SHORT).show();
                        // Log.d("onResponse", "onResponse: "+response);
                        //解析数据,断点查看
                        NewsList newsList = XmlUtils.toBean(NewsList.class, response.getBytes());
                        if (newsList!=null){
                            List<News> list = newsList.getList();
                            if (list!=null&&list.size()>0){

                                //清空集合的数据
                                mDataList.clear();
                                mCurrentPageIndex = 1; //页面的索引设置成1

                                mDataList.addAll(list);
                                //通知更新
                                mNewsPageAdapter.notifyDataSetChanged();
                            }

                        }



                    }
                });

    }

    class NewsPageAdapter extends SuperBaseAdapter<News> {


        public NewsPageAdapter(List<News> dataSource) {
            super(dataSource);
        }

        @Override
        public BaseHolder getSpecialBaseHolder(int position) {

            return new NewsPagerHolder(mContext) ;
        }
    }


}
