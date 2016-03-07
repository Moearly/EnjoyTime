package com.martn.enjoytime.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.martn.enjoytime.R;
import com.martn.enjoytime.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.ui.fragment
 * Description: ("首页")
 * Date 2014/10/5 23:02
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.tv_today)
    TextView tvToday;
    @Bind(R.id.pb_today_invest)
    ProgressBar pbTodayInvest;
    @Bind(R.id.tv_invest)
    TextView tvInvest;
    @Bind(R.id.pb_today_waste)
    ProgressBar pbTodayWaste;
    @Bind(R.id.tv_waste)
    TextView tvWaste;
    @Bind(R.id.rl_home_head_tip)
    RelativeLayout rlHomeHeadTip;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.sl_refresh)
    SwipeRefreshLayout slRefresh;

    public static String getMyTag() {
        return "home";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
