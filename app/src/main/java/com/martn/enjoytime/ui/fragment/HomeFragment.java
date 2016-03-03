package com.martn.enjoytime.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martn.enjoytime.base.BaseFragment;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.ui.fragment
 * Description: ("首页")
 * Date 2014/10/5 23:02
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class HomeFragment extends BaseFragment{

    public static String getMyTag() {
        return "home";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(activity);
        textView.setText("home");
        return textView;
    }
}
