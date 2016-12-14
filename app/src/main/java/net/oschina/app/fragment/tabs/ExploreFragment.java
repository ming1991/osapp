package net.oschina.app.fragment.tabs;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import net.oschina.app.base.BaseFragment;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/2 23:03
 * 描述：    TODO
 */

public class ExploreFragment extends BaseFragment {
    @Override
    public View initView() {
         TextView textView = new TextView(mContext);
          textView.setGravity(Gravity.CENTER);
          textView.setTextColor(Color.BLUE);
          textView.setText("ExploreFragment");
        return textView;
    }
}
