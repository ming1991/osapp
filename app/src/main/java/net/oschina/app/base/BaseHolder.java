package net.oschina.app.base;

import android.content.Context;
import android.view.View;

import butterknife.ButterKnife;

/**
 * 创建者     伍碧林
 * 创建时间   2016/7/6 16:25
 * 描述	      1.提供视图
 * 描述	      2.接收数据
 * 描述	      3.数据和视图的绑定
 * <p>
 * 更新者     $Author: admin $
 * 更新时间   $Date: 2016-07-06 16:40:55 +0800 (星期三, 06 七月 2016) $
 * 更新描述   ${TODO}
 */
public abstract class BaseHolder<HOLDERBEANTYPE> {
    public View           mHolderView;//可以提供的视图-->view
    public HOLDERBEANTYPE mData;//-->data
    public Context        mContext; //提供context

    public BaseHolder(Context context) {
        mContext = context;

        //初始化所能提供的视图
        mHolderView = initHolderView();

        ButterKnife.bind(this, mHolderView);

        //mHolderView<找>一个对象,然后绑定在自己身上
        mHolderView.setTag(this);


    }


    /**
     * @param data
     * @des 1.接收数据
     * @des 2.进行数据和视图的绑定操作
     */
    public void setDataAndRefreshHolderView(HOLDERBEANTYPE data) {
        //保存数据到成员变量
        mData = data;

        refreshHolderView(data);
    }


    /**
     * @return
     * @des 决定根视图长什么样子
     * @des 找出具体根视图里面的孩子对象, 转换成成员变量
     * @des 在BaseHolder中不知道根视图具体长什么样子, 交给子类
     * @des 子类必须实现, 但是不知道具体实现, 定义成为抽象方法
     */
    public abstract View initHolderView();

    /**
     * @param data
     * @des 进行数据和视图的绑定操作
     * @des 在BaseHolder中不知道如何进行数据和视图的绑定, 交给子类
     * @des 子类是必须实现, 所以定义成为抽象方法, 交给子类具体实现
     */
    public abstract void refreshHolderView(HOLDERBEANTYPE data);
}
