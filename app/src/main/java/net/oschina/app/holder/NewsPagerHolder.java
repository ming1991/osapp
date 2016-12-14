package net.oschina.app.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import net.oschina.app.base.BaseHolder;
import net.oschina.app.bean.News;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/4 19:48
 * 描述：    TODO
 */
public class NewsPagerHolder extends BaseHolder<News> {


    private TextView mTextView;

    public NewsPagerHolder(Context context) {
        super(context);

    }

    @Override
    public View initHolderView() {
        mTextView = new TextView(mContext);
          mTextView.setGravity(Gravity.CENTER);
          mTextView.setTextColor(Color.BLUE);
          mTextView.setText("我是成功视图");
        return mTextView;
    }

    @Override
    public void refreshHolderView(News data) {
        String title = data.getTitle();
        mTextView.setText(title);

    }
}
