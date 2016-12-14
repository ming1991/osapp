package net.oschina.app.base;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/5 1:37
 * 描述：    TODO ,带刷新的fragment ,通过设置pageIndex分页加载
 */

public abstract  class BaseRefreshProtocol<RESTYPE> extends BaseProtocol<RESTYPE> {
    public int mPageIndex;

    /*
   *
   * listView分页加载设置加载页面的index
   * */
    public void setPageIndex(int pageIndex) {
        this.mPageIndex = pageIndex;
    }
}
