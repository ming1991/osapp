package net.oschina.app.base;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 创建者     伍碧林
 * 创建时间   2016/7/6 16:42
 * 描述	      SuperBaseAdapter针对的就是MyBaseAdapter中未处理的getView方法,没有加载更多
 *
 * 更新者     $Author: admin $
 * 更新时间   $Date: 2016-07-11 17:26:28 +0800 (星期一, 11 七月 2016) $
 * 更新描述   ${TODO}
 */
public abstract class SuperBaseAdapter<ITEMBEANTYPE> extends MyBaseAdapter<ITEMBEANTYPE> {

    public SuperBaseAdapter(List<ITEMBEANTYPE> dataSource) {
        super(dataSource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*--------------- 决定根视图长什么样子 ---------------*/
        BaseHolder baseHolder = null;
        if (convertView == null) {
            baseHolder = getSpecialBaseHolder(position);
        } else {
            baseHolder = (BaseHolder) convertView.getTag();
        }

        /*--------------- 得到数据,绑定数据 ---------------*/
        //data
        ITEMBEANTYPE data = mDataSource.get(position);

        //data+view
        baseHolder.setDataAndRefreshHolderView(data);

        View holderView = baseHolder.mHolderView;
        /*//针对holderView执行动画
        holderView.setScaleX(0.6f);
        holderView.setScaleY(0.5f);


        ViewCompat.animate(holderView).scaleX(1).scaleY(1).setDuration(400).
                setInterpolator(new OvershootInterpolator(4)).start();*/

        return holderView;
    }


    /**
     * @param position
     * @return
     * @des 创建一个BaseHolder的具体的子类对象
     * @des 在SuperBaseAdapter中不知道如何创建一个BaseHolder的具体的子类对象, 只能交给子类
     * @des 子类必须实现, 定义成为抽象方法, 交给子类具体实现
     */
    public abstract BaseHolder getSpecialBaseHolder(int position);
}
