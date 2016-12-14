package net.oschina.app.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.AbsListView;
import android.widget.ListView;

import java.lang.reflect.Field;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/16 18:35
 * 描述：    TODO
 * Android ListView实现阻尼回弹效果 并去除边缘阴影
 *
 * 使用ListView将位置拖到顶部和底部默认是没有回弹效果的，为了增加这个效果，方法如下：
 1、开启overScrollMode为always
 在布局中 android:overScrollMode="always"
 或在代码中 setOverScrollMode(View.OVER_SCROLL_ALWAYS);
 2、继承listview 覆盖overScrollBy方法，并且利用反射机制修改阴影效果为透明


 默认 maxOverScrollX = 0;   maxOverScrollY = 0;


 */

public class PullListview extends ListView {

    private static final int MAX_Y_OVERSCROLL_DISTANCE = 100;

    private Context mContext;
    private int mMaxYOverscrollDistance;


    public PullListview(Context context) {
        super(context);
        mContext = context;
        initPullListview();
    }

    public PullListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPullListview();
    }

    public PullListview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initPullListview();
    }

    private void initPullListview() {
        // get the density of the screen and do some maths with it on the max
        // overscroll distance
        // variable so that you get similar behaviors no matter what the screen
        // size

        final DisplayMetrics metrics = mContext.getResources()
                .getDisplayMetrics();
        final float density = metrics.density;
        mMaxYOverscrollDistance = (int)(density * MAX_Y_OVERSCROLL_DISTANCE);

        // this.setOverScrollMode(View.OVER_SCROLL_ALWAYS);

        try {
            Class < ? > c = (Class < ? > ) Class.forName(AbsListView.class.getName());
            Field egtField = c.getDeclaredField("mEdgeGlowTop");
            Field egbBottom = c.getDeclaredField("mEdgeGlowBottom");
            egtField.setAccessible(true);
            egbBottom.setAccessible(true);
            Object egtObject = egtField.get(this); // this 指的是ListiVew实例
            Object egbObject = egbBottom.get(this);

            // egtObject.getClass() 实际上是一个 EdgeEffect 其中有两个重要属性 mGlow mEdge
            // 并且这两个属性都是Drawable类型
            Class < ? > cc = (Class < ? > ) Class.forName(egtObject.getClass()
                    .getName());
            Field mGlow = cc.getDeclaredField("mGlow");
            mGlow.setAccessible(true);
            mGlow.set(egtObject, new ColorDrawable(Color.TRANSPARENT));
            mGlow.set(egbObject, new ColorDrawable(Color.TRANSPARENT));

            Field mEdge = cc.getDeclaredField("mEdge");
            mEdge.setAccessible(true);
            mEdge.set(egtObject, new ColorDrawable(Color.TRANSPARENT));
            mEdge.set(egbObject, new ColorDrawable(Color.TRANSPARENT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        // This is where the magic happens, we have replaced the incoming
        // maxOverScrollY with our own custom variable mMaxYOverscrollDistance;
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX,
                mMaxYOverscrollDistance, isTouchEvent);
    }
}
