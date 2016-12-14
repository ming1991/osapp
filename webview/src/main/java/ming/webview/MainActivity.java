package ming.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initWebView();


    }

    private void initWebView() {
        //影藏progressbar
        mProgressBar.setVisibility(View.GONE);

        //       ###webview初级
//                //1. 加载本地的html文件  //资产目录下的文件
      // mWebView.loadUrl("file:///android_asset/hm32.html");
       mWebView.loadUrl("file:///android_asset/hm33.html");

//                //2. 加载网络的html文件
        //mWebView.loadUrl("http://www.baidu.com");
      //  mWebView.loadUrl("http://m.jd.com");
//                //3.双击放大缩小
        WebSettings settings = mWebView.getSettings();
        settings.setUseWideViewPort(true);
//                /4.显示内建的放大缩小按钮
        settings.setBuiltInZoomControls(true);
//                //5.监听加载进度
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) { //newProgress取值0-100
               mProgressBar.setProgress(newProgress);
            }
        });
//                //6.监听加载完成,开始
        //可以控制所有的网页都是跳内置浏览器
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);

                //7.拿到webview正在请求的地址
//                String url = mWebview.getUrl();
//                Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();

                //13.1当页面执行完之后动态的执行js脚本
                //动态执行js脚本
                executeJsScript(view, jsScript);


                super.onPageFinished(view, url);
            }

            //控制页面内的子链接是否可以点击
            //8.拦截webview内部跳转
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //return super.shouldOverrideUrlLoading(view, url);
                return false; //默认还回false
            }
        });
//                //7.拿到webview正在请求的地址
//                /8.拦截webview内部跳转
//        //9.webview的回退操作

//        //10.允许js
        settings.setJavaScriptEnabled(true);


        /**----------------------webView 的高级使用*/

        //11.java代码可以动态的执行js
//        String jsScript = "alert('我是定义在java本地代码里面的脚本')";
//        mWebView.loadUrl("javascript:"+jsScript);

        //12.在js里面可以调用java本地的方法
       // object:任一一个类的一个对象,这个类里面定义的方法,都是供给js调用的方法,这个类可以叫"桥梁类"
//        name:别名,是在js里面调用java本地代码的时候需要用到的一个别名

       // mWebView.addJavascriptInterface(new MyJsBinder(),"hm32");

        mWebView.addJavascriptInterface(new IMyJsBinder() {
            @JavascriptInterface
            @Override
            public void sayHello2Java(String content) {
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
            }

            @JavascriptInterface
            @Override
            public void passCurImgUrl(String curImgUrl) {
                //TODO 跳转Activity,使用ViewPager展示所有的图片,跳转的时候需要传递当前图片地址,以及所有的图片地址

                Toast.makeText(MainActivity.this, curImgUrl, Toast.LENGTH_SHORT).show();

            }

            @JavascriptInterface
            @Override
            public void passAllImgUrl(String allImgUrl) {

                Toast.makeText(MainActivity.this, allImgUrl, Toast.LENGTH_SHORT).show();

            }
        },"hm31");  //别名别搞错了


        //


    }

    //13.1 动态执行js脚本

    /**
     * 动态执行js脚本
     *
     * @param webView
     * @param jsScript
     */
    public void executeJsScript(WebView webView, String jsScript) {
        webView.loadUrl("javascript:" + jsScript);
    }


    private String jsScript = "(function() {\n" +
            "        var imageList = \"\";\n" +
            "        var imgs = document.getElementsByTagName(\"img\");\n" +
            "        for (var i = 0; i < imgs.length; i++) {\n" +
            "            var img = imgs[i];\n" +
            "            imageList = imageList + img.src + \";\";\n" +
            "            img.onclick = function() {\n" +
            "                window.hm31.passCurImgUrl(this.src);\n" +
            "            }\n" +
            "        }\n" +
            "        window.hm31.passAllImgUrl(imageList);\n" +
            "    })()";



    //9.webview的回退操作 ,onPressBack方法也可以实现
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mWebView = (WebView) findViewById(R.id.webview);
    }
}
