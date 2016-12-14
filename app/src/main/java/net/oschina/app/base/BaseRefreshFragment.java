package net.oschina.app.base;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.widget.SpringView;

import net.oschina.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/5 0:48
 * 描述：    TODO 包含上拉,下拉刷新的listView的fragment
 */

public abstract class BaseRefreshFragment<RESTYPE, ITEMBEANTYPE> extends BaseFragment implements BaseProtocol.Callback<RESTYPE>, AdapterView.OnItemClickListener {
    @BindView(R.id.listView)
   public  ListView mListView;
    @BindView(R.id.springView)
    public SpringView mSpringView;

    //网络请求回来的数据集
    public List<ITEMBEANTYPE> mDataList;

    private SuperBaseAdapter<ITEMBEANTYPE> mPageAdapter;

    //加载页的索引
    private int mCurrentPageIndex = 0;

    //三种方式加载数据的方式
    public static final int REQTYPE_INIT = 0;
    public static final int REQTYPE_REFRESH = 1;
    public static final int REQTYPE_LOADMORE = 2;

    private BaseRefreshProtocol<RESTYPE> mProtocol;

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
        mSpringView.setHeader(new DefaultHeader(mContext));
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

        mPageAdapter = getSpecialBaseAdapter();

        mListView.setAdapter(mPageAdapter);


        //网络请求数据
        //http://www.oschina.net/action/api/news_list?pageIndex=0&catalog=1&pageSize=20
        /*String url = Constants.URLS.BASEURL + "news_list";

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("pageIndex", 0 + "");
        paramsMap.put("catalog", 1 + "");
        paramsMap.put("pageSize", 20 + "");*/

        mProtocol = getSpecilProtocol();

        mProtocol.setPageIndex(mCurrentPageIndex);
        mProtocol.loadDataByGet(this, REQTYPE_INIT);

    }



    @Override
    protected void initListener() {

        mListView.setOnItemClickListener(this); //设置listView 的条目的点击事件

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
      /*  String url = Constants.URLS.BASEURL + "news_list";

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("pageIndex", mCurrentPageIndex + "");
        paramsMap.put("catalog", 1 + "");
        paramsMap.put("pageSize", 20 + "");*/

        mProtocol.setPageIndex(mCurrentPageIndex);
        mProtocol.loadDataByGet(this, REQTYPE_LOADMORE);


    }

    /**
     * 下拉刷新数据
     * 回到第一条数据加载
     */
    private void onRefreshData() {
        //网络请求数据
        //http://www.oschina.net/action/api/news_list?pageIndex=0&catalog=1&pageSize=20
      /*  String url = Constants.URLS.BASEURL + "news_list";

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("pageIndex", 0 + "");
        paramsMap.put("catalog", 1 + "");
        paramsMap.put("pageSize", 20 + "");
*/

        mCurrentPageIndex = 0;
        mProtocol.setPageIndex(mCurrentPageIndex);
        mProtocol.loadDataByGet(this, REQTYPE_REFRESH);


    }

    @Override
    public void onError(Call call, Exception e, int id, int reqType) {
        switch (reqType) {
            case REQTYPE_INIT:
                mSpringView.onFinishFreshAndLoad();

                Toast.makeText(mContext, "初始加载失败", Toast.LENGTH_SHORT).show();

                break;
            case REQTYPE_REFRESH:
                mSpringView.onFinishFreshAndLoad();

                Toast.makeText(mContext, "下拉刷新失败", Toast.LENGTH_SHORT).show();

                break;

            case REQTYPE_LOADMORE:
                mSpringView.onFinishFreshAndLoad();

                Toast.makeText(mContext, "上拉加载失败", Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }

    }



    @Override
    public void onResponse(RESTYPE restype, int id, int reqType) {
        mSpringView.onFinishFreshAndLoad();

        if (reqType==REQTYPE_INIT){
            Toast.makeText(mContext, "初始加载成功", Toast.LENGTH_SHORT).show();
        }else if (reqType==REQTYPE_REFRESH){
            Toast.makeText(mContext, "下拉刷新成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext, "上拉加载成功", Toast.LENGTH_SHORT).show();
        }

        if (restype != null) {

            List<ITEMBEANTYPE> list = getSpecialList(restype);

            if (list != null&&list.size()>0) {

                if (reqType==REQTYPE_REFRESH){
                    //清空集合的数据
                    mDataList.clear();
                    mCurrentPageIndex = 1; //页面的索引设置成1

                }else if (reqType == REQTYPE_INIT||reqType==REQTYPE_LOADMORE){

                    mCurrentPageIndex++;
                }

                mDataList.addAll(list);
                //通知更新
               mPageAdapter.notifyDataSetChanged();
            }

        }

    }

    protected abstract List<ITEMBEANTYPE> getSpecialList(RESTYPE restype);

    protected abstract SuperBaseAdapter<ITEMBEANTYPE> getSpecialBaseAdapter();

    public abstract BaseRefreshProtocol<RESTYPE> getSpecilProtocol();


    //子类覆写实现listView 的条目的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}


