package net.oschina.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * 创建者:   ming001
 * 创建时间: 2016/11/2 20:37
 * 描述：    TODO
 */

public abstract class BaseFragment extends Fragment {

    public Context mContext;
    public String mArgs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化子类需要的的一些参数
        mContext = getActivity();

        //父类统一接受传递过来的参数
        Bundle arguments = getArguments();
        if (arguments!=null){
            mArgs = arguments.getString("args");
        }

      init();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //找到控件
        ButterKnife.bind(this,view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initListener();

    }

    /**
     * 初始化监听
     */
    protected  void initListener(){

    }

    /**
     * 初始化数据,网络请求数据
     */
    protected void initData(){

    }

    /**
     * 初始化视图
     * */
    public abstract View initView();

    /**
     * 初始化
     */
    public void init() {

    }


}
