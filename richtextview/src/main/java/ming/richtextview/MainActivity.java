package ming.richtextview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.ClipboardManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        EditText editText = (EditText) findViewById(R.id.editText);

        //1.显示html标签
//        //Spanned类采用fromHtml方法解析 html标签
//        String html = "我是普通文本"+ red("我是红色文本")+ green("我是绿色文本");
//        Spanned spanned = Html.fromHtml(html);
//        textView.setText(spanned);


        //2.显示链接(文本链接,网页链接)
//        String html1 = "\t我已经在活动“<a href=\"http://www.oschina.net/question/574485_242756\" \n" +
//                "\ttarget=\"_blank\">OSC长沙开源行 技术交流峰会</a>”签到！";
//
//        String html2 = "\t领极客学院30天VIP  http://e.jikexueyuan.com/invite/index.html?\n" +
//                "\tZnJvbV9jb2RlPUk3QUY2WCZ1bmFtZT1qaWtlXzUxNTM0MjQmY2hhbm5lbD1pbnZpdGVf\n" +
//                "\tMTAwd19yZXNoYXJlYnV0dG9uX2NvcHkx";
//        Spanned spanned1 = Html.fromHtml(html1);
//        Spanned spanned2 = Html.fromHtml(html2);
//
//        textView1.setText(spanned1);
//        textView2.setText(spanned2);



        //3.###方式二:使用Spannable接口对象设置富文本 extends Spanned

        //创建一个 SpannableString对象
//        SpannableString msp = new SpannableString("字体测试字体大小一半两倍前景色背景色正常粗体斜体粗斜体下划线删除线x1x2电话邮件网站短信彩信地图X轴综合/bot");
//
//        //设置字体(default,default-bold,monospace,serif,sans-serif)
//        msp.setSpan(new TypefaceSpan("monospace"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        msp.setSpan(new TypefaceSpan("serif"), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        //设置字体大小（绝对值,单位：像素）
//        msp.setSpan(new AbsoluteSizeSpan(20), 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        msp.setSpan(new AbsoluteSizeSpan(20, true), 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。
//
//        //设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍
//        msp.setSpan(new RelativeSizeSpan(0.5f), 8, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //0.5f表示默认字体大小的一半
//        msp.setSpan(new RelativeSizeSpan(2.0f), 10, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //2.0f表示默认字体大小的两倍
//
//        //设置字体前景色
//        msp.setSpan(new ForegroundColorSpan(Color.MAGENTA), 12, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色
//
//        //设置字体背景色
//        msp.setSpan(new BackgroundColorSpan(Color.CYAN), 15, 18, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);  //设置背景色为青色
//
//        //设置字体样式正常，粗体，斜体，粗斜体
//        msp.setSpan(new StyleSpan(android.graphics.Typeface.NORMAL), 18, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //正常
//        msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 20, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
//        msp.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 22, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //斜体
//        msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 24, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗斜体
//
//        //设置下划线
//        msp.setSpan(new UnderlineSpan(), 27, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        //设置删除线
//        msp.setSpan(new StrikethroughSpan(), 30, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        //设置上下标
//        msp.setSpan(new SubscriptSpan(), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //下标
//        msp.setSpan(new SuperscriptSpan(), 36, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //上标
//
//        //超级链接（需要添加setMovementMethod方法附加点击响应）
//        msp.setSpan(new URLSpan("tel:4155551212"), 37, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //电话
//        msp.setSpan(new URLSpan("mailto:webmaster@google.com"), 39, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //邮件
//        msp.setSpan(new URLSpan("http://www.baidu.com"), 41, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //网络
//        msp.setSpan(new URLSpan("sms:4155551212"), 43, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //短信   使用sms:或者smsto:
//        msp.setSpan(new URLSpan("mms:4155551212"), 45, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //彩信   使用mms:或者mmsto:
//        msp.setSpan(new URLSpan("geo:38.899533,-77.036476"), 47, 49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //地图
//
//        //设置字体大小（相对值,单位：像素） 参数表示为默认字体宽度的多少倍
//        msp.setSpan(new ScaleXSpan(2.0f), 49, 51, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //2.0f表示默认字体宽度的两倍，即X轴方向放大为默认字体的两倍，而高度不变
//
//        //设置字体（依次包括字体名称，字体大小，字体样式，字体颜色，链接颜色）
//        ColorStateList csllink = getResources().getColorStateList(R.color.linkcolor); //状体选择器
//        ColorStateList csl =getResources().getColorStateList(R.color.color);  //状体选择器
//        msp.setSpan(new TextAppearanceSpan("monospace", android.graphics.Typeface.BOLD_ITALIC, 30, csl, csllink), 37, 49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        //设置项目符号
//        msp.setSpan(new BulletSpan(android.text.style.BulletSpan.STANDARD_GAP_WIDTH, Color.GREEN), 0, msp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //第一个参数表示项目符号占用的宽度，第二个参数为项目符号的颜色
//
//        //设置图片  /bot处用图片占位
//        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        msp.setSpan(new ImageSpan(drawable), 53, 57, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        textView1.setText(msp);
//
//        //设置链接可点击
//        textView1.setMovementMethod(LinkMovementMethod.getInstance());
//
//        //edi
//        //设置字体背景色  Spanned.SPAN_EXCLUSIVE_INCLUSIVE 前面插入不包括当前样式,后面插入包括当前的样式
//        //msp.setSpan(new BackgroundColorSpan(Color.CYAN), 15, 18, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);  //设置背景色为青色
//        editText.setText(msp);



        //4.##表情添加
       // 4.1.方式一:Html 使用fromHtml重载方法,配合<img>标签,添加表情
//        String html = "我<img src='dog'/>已经在活动“<a href=\"http://www.oschina.net/" +
//                "question/574485_242756\" target=\"_blank\">OSC长沙开源行 技术交流峰会</a>”签到！";
//        Spanned spanned = Html.fromHtml(html, new Html.ImageGetter() {
//            @Override
//            public Drawable getDrawable(String source) {
//                int resourceId = getResourceId(source);
//
//                System.out.println("source:" + source);  //dog
//                System.out.println("sourceId:" + resourceId);
//
//                //对图片进行压缩（此处我采用原图）
//                Drawable drawable = getResources().getDrawable(resourceId);
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//                return drawable;
//            }
//        }, null);

        //textView1.setText(spanned);
        //设置链接可点击
        //textView1.setMovementMethod(LinkMovementMethod.getInstance());

        //4.2.使用spannable里面的setSpan设置表情  ImageSpan 图片包裹，可以在一段文字中添加一个图片
        //占位符
//        String html = "[pig]我<img src='pig'/>已经在活动“<a href=\\\"" +
//                "http://www.oschina.net/question/574485_242756\\\" target=\\\"_blank\\\">OSC长沙开源行 " +
//                "技术交流峰会</a>”签到！";
//        SpannableString spannableString = new SpannableString(html);
//        Drawable drawable = getResources().getDrawable(R.drawable.dog);
//        //设置图片的大小
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//
//        spannableString.setSpan(new ImageSpan(drawable),0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        textView1.setText(spannableString);


        //4.33.ImageSpan和Html里面的fromHtml方法混合使用,处理占位符,以及img标签类型的图片展示
//        String html = "[frog]我<img src='dog'/>已经在活动“<a href=\\\"http://www.oschina.net" +
//                "/question/574485_242756\\\" target=\\\"_blank\\\">OSC长沙开源行 技术交流峰会</a>”签到！";
//
//        //先处理img标签里面的图片
//        Spanned spanned = Html.fromHtml(html, new Html.ImageGetter() {
//            @Override
//            public Drawable getDrawable(String source) {
//                int resourceId = getResourceId(source);
//
//                System.out.println("source:" + source);
//                System.out.println("sourceId:" + resourceId);
//
//                //对图片进行压缩（此处我采用原图）
//                Drawable drawable = getResources().getDrawable(resourceId);
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//                return drawable;
//            }
//        }, null);
//
//        //强制转换
//        Spannable spannable = (Spannable) spanned;
//
//
//        //用spannable 处理占位符的情况
//        Drawable drawable = getResources().getDrawable(R.drawable.dog);
//        //设置图片的大小
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//
//        spannable.setSpan(new ImageSpan(drawable),0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        //显示文本内容
//        textView1.setText(spannable);
//        //设置链接可点击
//        textView1.setMovementMethod(LinkMovementMethod.getInstance());


        //5设置链接可被点击

        //5.11.使用URLSpan  动态替换 , 变成链接效果

        //设置属性  //文本超链接
//        SpannableString msp = new SpannableString("百度");
//       msp.setSpan(new URLSpan("http://www.baidu.com"),0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textView1.setText(msp);
//        textView1.setMovementMethod(LinkMovementMethod.getInstance());
//
//        //5.22.带有a标签的情况 ,html 动态替换
//        String html = new String("<a href=\"http://www.baidu.com\" target=\"_blank\">百度</a>哈哈哈哈哈哈");
//        Spanned spanned = Html.fromHtml(html);
//        textView2.setText(spanned);
//        textView2.setMovementMethod(LinkMovementMethod.getInstance());


        //5.33.http地址,但是没有a标签 ,显示链接效果 处理方式 ,自定义的第三方工具包
//
//        String html = new String("黑马程序员  http://www.itheima.com");
//        Spanned spanned = Html.fromHtml(html);
//        textView1.setText(spanned);
//        textView1.setMovementMethod(LinkMovementMethod.getInstance());
//        //底层采用 循环遍历 动态替换 UILSpan 的方法 设置链接
//        MyURLSpan.parseLinkText(textView1,spanned);




        //6.点赞效果,参考 textview的局部可点击
        //Android中实现为TextView添加多个可点击的文本

        // 构造多个超链接的html, 通过选中的位置来获取用户名
//        StringBuilder sbBuilder = new StringBuilder();
//        for (int i = 0; i < 10; i++) {
//            sbBuilder.append("username-" + i + "、");
//        }
//
//        String likeUsers = sbBuilder.substring(0, sbBuilder.lastIndexOf("、")).toString();
//        textView1.setMovementMethod(LinkMovementMethod.getInstance());
//        textView1.setText(addClickablePart(likeUsers), TextView.BufferType.SPANNABLE);


        //7.长按复制  textview
        textView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                copyTextToBoard(MainActivity.this, textView1.getText().toString());
                return false;
            }
        });

    }


    //7.长按复制  textview
    @SuppressWarnings("deprecation")
    public static void copyTextToBoard(Context context, String string) {
        if (TextUtils.isEmpty(string))
            return;
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clip.setText(string);  //保存到剪切板
        Toast.makeText(context, "拷贝成功", Toast.LENGTH_SHORT).show();
    }


    //6.Android中实现为TextView添加多个可点击的文本
    private SpannableStringBuilder addClickablePart(String str) {
        // 第一个赞图标
        ImageSpan span = new ImageSpan(this, R.drawable.dog);
        SpannableString spanStr = new SpannableString("p.");
        spanStr.setSpan(span, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
        ssb.append(str) ;

        String[] likeUsers = str.split("、");

        if (likeUsers.length > 0) {
            // 最后一个
            for (int i = 0; i < likeUsers.length; i++) {
                final String name = likeUsers[i];
                final int start = str.indexOf(name) + spanStr.length();

                ssb.setSpan(new ClickableSpan() {  //核心方法,设置局部可被点击效果

                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(MainActivity.this, name,
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);

                         ds.setColor(Color.RED); // 设置文本颜色
                        // 去掉下划线
                        ds.setUnderlineText(false);
                    }

                }, start, start + name.length(), 0);
            }
        }
        return ssb.append("等"
                + likeUsers.length + "个人赞了您.");
    } // end of addClickablePart




    //利用Java反射机制根据资源名称获取R资源Id
    public int getResourceId(String name) {
        try {
            //利用Java反射机制获取R资源Id
            Field field = R.drawable.class.getField(name);
            return Integer.parseInt(field.get(null).toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return 0;
    }



    /*
    * 封装方法,字符串转化成html标签
    * */
    private String green(String content) {
        String format = String.format("<font color='green'>%s</font>", content); // %s 占位符
        return format;
    }

    private String red(String content) {
        String format = String.format("<font color='red'>%s</font>", content);  // %s 占位符
        return format;
    }
}
