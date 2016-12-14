package net.oschina.app.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Typeface工具类 用于设置字体图标
 * <p/>
 * TypefaceUtil.java
 *
 * @author 火蚁(http://my.oschina.net/u/253900)
 * @data 2015-3-4 下午3:05:43
 */
public class TypefaceUtils {

    private static Typeface getTypeface(Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(),
                "fontawesome-webfont.ttf");
        return font;
    }

    public static void setTypeface(Context context,TextView tv) {
        tv.setTypeface(getTypeface(context));
    }
}
