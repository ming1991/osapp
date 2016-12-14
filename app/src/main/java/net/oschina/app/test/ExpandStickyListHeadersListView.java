package net.oschina.app.test;


import android.content.Context;
import android.util.AttributeSet;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/16 19:58
 * 描述：    TODO
 */

public class ExpandStickyListHeadersListView extends StickyListHeadersListView {
    public ExpandStickyListHeadersListView(Context context) {
        super(context);
    }

    public ExpandStickyListHeadersListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandStickyListHeadersListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2
                , MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
