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
 * Description: ("目标管理")
 * Date 2014/10/5 23:09
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class ManageTargetFragment extends BaseFragment{
    public static String getMyTag() {
        return "mange_target";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(activity);
        textView.setText("mange_target");
        return textView;
    }
}
