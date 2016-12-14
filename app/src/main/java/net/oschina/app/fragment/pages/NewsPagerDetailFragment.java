package net.oschina.app.fragment.pages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import net.oschina.app.R;
import net.oschina.app.base.BaseFragment;
import net.oschina.app.base.BaseProtocol;
import net.oschina.app.bean.News;
import net.oschina.app.bean.NewsDetail;
import net.oschina.app.interf.OnWebViewImageListener;
import net.oschina.app.protocol.NewsPagerDetailProtocol;
import net.oschina.app.util.StringUtils;
import net.oschina.app.util.UIHelper;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/9 16:04
 * 描述：    TODO
 */
public class NewsPagerDetailFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView mWebview;

    private String mNewsId;//需要别人传递过来

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.framgent_newspagerdetail, null);
    }

    @Override
    public void initData() {
        mNewsId = mArgs; //父类统一接受的传递参数

        //加载数据
        NewsPagerDetailProtocol protocol = new NewsPagerDetailProtocol();
        protocol.setNewsId(mNewsId);

        protocol.loadDataByGet(new BaseProtocol.Callback<NewsDetail>() {
            @Override
            public void onError(Call call, Exception e, int id, int reqType) {

            }

            @Override
            public void onResponse(NewsDetail newsDetail, int id, int reqType) {
//                System.out.println(newsDetail);
                String url = newsDetail.getNews().getUrl();

                //1.mWebview基本的使用
//                mWebview.loadUrl(url);

                // System.out.println("url:" + url);

                //2.mWebview高级的使用
                executeOnLoadDataSuccess(newsDetail);
            }
        }, 0); //初次加载的类型

    }

    protected void executeOnLoadDataSuccess(NewsDetail newsDetail) {
        News news = newsDetail.getNews();
        //处理网页内容,添加数据,为图片设置点击事件
        String webViewBody = this.getWebViewBody(news);//添加内容

        //设置监听
        addWebImageShow(mContext, mWebview);//设置监听

        //开始加载
        mWebview.loadDataWithBaseURL("", webViewBody, "text/html", "UTF-8", "");
    }

    //动态添加,替换内容
    protected String getWebViewBody(News mDetail) {
        StringBuffer body = new StringBuffer();
        body.append(UIHelper.WEB_STYLE).append(UIHelper.WEB_LOAD_IMAGES);
        // 添加title
        body.append(String.format("<div class='title'>%s</div>", mDetail.getTitle()));
        // 添加作者和时间
        String time = StringUtils.friendly_time(mDetail.getPubDate());
        String author = String.format("<a class='author' href='http://my.oschina.net/u/%s'>%s</a>", mDetail.getAuthorId(), mDetail.getAuthor());
        body.append(String.format("<div class='authortime'>%s&nbsp;&nbsp;&nbsp;&nbsp;%s</div>", author, time));
        // 添加图片点击放大支持

        body.append(setHtmlCotentSupportImagePreview(mDetail.getBody()));


        // 更多关于***软件的信息
        String softwareName = mDetail.getSoftwareName();
        String softwareLink = mDetail.getSoftwareLink();
        if (!StringUtils.isEmpty(softwareName)
                && !StringUtils.isEmpty(softwareLink))
            body.append(String
                    .format("<div class='oschina_software' style='margin-top:8px;font-weight:bold'>更多关于:&nbsp;<a href='%s'>%s</a>&nbsp;的详细信息</div>",
                            softwareLink, softwareName));

        // 相关新闻
        if (mDetail != null && mDetail.getRelatives() != null
                && mDetail.getRelatives().size() > 0) {
            String strRelative = "";
            for (News.Relative relative : mDetail.getRelatives()) {
                strRelative += String.format(
                        "<li><a href='%s' style='text-decoration:none'>%s</a></li>",
                        relative.url, relative.title);
            }
            body.append("<p/><div style=\"height:1px;width:100%;background:#DADADA;margin-bottom:10px;\"/>"
                    + String.format("<br/> <b>相关资讯</b><ul class='about'>%s</ul>",
                    strRelative));
        }
        body.append("<br/>");
        // 封尾
        body.append("</div></body>");
        return body.toString();
    }

    //所有图片设置点击事件,调 用java本地代码
    public static String setHtmlCotentSupportImagePreview(String body) {
        // 读取用户设置：是否加载文章图片--默认有wifi下始终加载图片
        if (true) {
            // 过滤掉 img标签的width,height属性
            body = body.replaceAll("(<img[^>]*?)\\s+width\\s*=\\s*\\S+", "$1");
            body = body.replaceAll("(<img[^>]*?)\\s+height\\s*=\\s*\\S+", "$1");
            // 添加点击图片放大支持
            // 添加点击图片放大支持
            body = body.replaceAll("(<img[^>]+src=\")(\\S+)\"",
                    "$1$2\" onClick=\"javascript:mWebViewImageListener.showImagePreview('$2')\"");
        } else {
            // 过滤掉 img标签
            body = body.replaceAll("<\\s*img\\s+([^>]*)\\s*>", "");
        }
        return body;
    }


    //js中调用该方法后,启动新的页面展示图片
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    @JavascriptInterface
    public static void addWebImageShow(final Context cxt, WebView wv) {
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new OnWebViewImageListener() {
            @Override
            @JavascriptInterface
            public void showImagePreview(String bigImageUrl) {
                if (bigImageUrl != null && !StringUtils.isEmpty(bigImageUrl)) {
                    UIHelper.showImagePreview(cxt, new String[]{bigImageUrl});
                }
            }
        }, "mWebViewImageListener");
    }



}
