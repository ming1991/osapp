package net.oschina.app.base;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * 创建者     伍碧林
 * 创建时间   2016/7/6 15:34
 * 描述	     针对BaseAdapter中3个方法进行封装
 *
 * 更新者     $Author: admin $
 * 更新时间   $Date: 2016-07-06 16:51:54 +0800 (星期三, 06 七月 2016) $
 * 更新描述   ${TODO}
 */
public abstract class MyBaseAdapter<ITEMBEANTYPE> extends BaseAdapter {
    public List<ITEMBEANTYPE> mDataSource = null;

    public void setDataSource(List<ITEMBEANTYPE> dataSource) {
        mDataSource = dataSource;
        notifyDataSetChanged();
    }

    public MyBaseAdapter(List<ITEMBEANTYPE> dataSource) {
        mDataSource = dataSource;
    }

    @Override
    public int getCount() {
        if (mDataSource != null) {
            return mDataSource.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mDataSource != null) {
            return mDataSource.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
