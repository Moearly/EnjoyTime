package com.martn.enjoytime.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.martn.enjoytime.R;
import com.martn.enjoytime.base.BaseFragment;
import com.martn.enjoytime.db.dao.DistributionDao;
import com.martn.enjoytime.ui.adapter.HomeSwipeableItemAdapter;
import com.martn.enjoytime.utility.AppUtils;
import com.martn.enjoytime.utility.DateHelper;
import com.martn.enjoytime.utility.FormatUtils;

import java.util.Calendar;

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
    private LinearLayoutManager layoutManager;
    private RecyclerViewTouchActionGuardManager recyclerViewTouchActionGuardManager;
    private RecyclerViewSwipeManager recyclerViewSwipeManager;

    public static String getMyTag() {
        return "home";
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2) {
                initHomeBar();
            }
        }
    };

    /**
     * 初始化--更新home--顶部bar--ui
     */
    private void initHomeBar() {
        DistributionDao dao = new DistributionDao(activity);//数据库中获取数据
        String[] todayDis = dao.queryTodayDistribution();
        tvInvest.setText(todayDis[0] + " " + getResources().getString(R.string.str_invest));
        tvWaste.setText(todayDis[1] + " " + getResources().getString(R.string.str_waste));
        setHomeTodayData();
    }

    /**
     * 设置顶部今天时间显示
     */
    private void setHomeTodayData() {
        Calendar c = Calendar.getInstance();
        long now = c.getTime().getTime();
        tvToday.setText(DateHelper.format(c.getTime(),"yyyy.MM.dd"));
        tvToday.setTypeface(AppUtils.typefaceLatoLight);
//        c.set(11, 23);
//        c.set(12, 59);
//        c.set(13, 59);
        //设置今天剩余的时间
        //tv.setText(FormatUtils.format_1fra((((double) (c.getTime().getTime() - now)) / 1000.0d) / 3600.0d) + "h");
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        initView();


    }

    private void initView() {
        layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerViewTouchActionGuardManager = new RecyclerViewTouchActionGuardManager();
        recyclerViewTouchActionGuardManager.
                setInterceptVerticalScrollingWhileAnimationRunning(true);
        recyclerViewTouchActionGuardManager.setEnabled(true);

        recyclerViewSwipeManager = new RecyclerViewSwipeManager();

        mAdapter = new HomeSwipeableItemAdapter(mContext, RecordManager.SELECTED_RECORDS, this, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Message msg = new Message();
        msg.what = 2;
        this.handler.sendMessage(msg);

    }
}
