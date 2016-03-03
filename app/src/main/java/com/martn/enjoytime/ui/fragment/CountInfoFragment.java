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
 * Description: ("统计信息")
 * Date 2014/10/5 23:07
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class CountInfoFragment extends BaseFragment {
    public static String getMyTag() {
        return "count_info";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(activity);
        textView.setText("count_info");
        return textView;
    }
}
