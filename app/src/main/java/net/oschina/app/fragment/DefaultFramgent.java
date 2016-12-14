package net.oschina.app.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/2 19:52
 * 描述：    TODO
 */
public class DefaultFramgent extends Fragment{

    private String mArgs;

    //获取传递的参数
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        //防止空指针
        if (arguments!=null){

            mArgs = arguments.getString("args");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         TextView textView = new TextView(getActivity());
          textView.setGravity(Gravity.CENTER);
          textView.setTextColor(Color.BLUE);
          textView.setText(mArgs);


        return textView;
    }
}
