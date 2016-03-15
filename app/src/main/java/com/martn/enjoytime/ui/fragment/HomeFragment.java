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
import com.martn.enjoytime.bean.ActionItem;
import com.martn.enjoytime.bean.User;
import com.martn.enjoytime.db.dao.ActionDao;
import com.martn.enjoytime.db.dao.DistributionDao;
import com.martn.enjoytime.db.dao.UserDao;
import com.martn.enjoytime.utility.AppUtils;
import com.martn.enjoytime.utility.DateHelper;
import com.martn.enjoytime.utility.FormatUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    private List<ActionItem> goalsData = new ArrayList<>();
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
        initAfterLogin();
        isNewUser();
    }

    public void initAfterLogin() {
        UserDao dao = new UserDao(activity);
        dao.getLoginUser();
        if (User.getInstance() == null) {
            //当没有注册的账号--使用本地测试账号
            dao.initTestUser();//添加---或者使用测试账号
        }
        ActionDao actionDao = new ActionDao(activity);

        actionDao.insertDefalutGoal();//初始化用户插入默认的数据
        upDataUiList();
    }

    /**
     * 更新ui列表中的数据
     */
    private void upDataUiList() {

    }

    /**
     * 更新目标
     */
    private void initGoalUI() {

        goalsData.clear();

    }




    /**
     * 判断是否是新用户--提示
     */
    private void isNewUser() {
//        int CONFIGURE_SHOW_GUIDE = PreferUtils.getSP(context).getInt(Val.CONFIGURE_IS_SHOW_GUIDE, 0);
//        if (getGoalNumber() <= 0 && CONFIGURE_SHOW_GUIDE < 4) {
//            new Builder(context).setTitle(getString(R.string.str_prompt)).setMessage((CharSequence) "\u4f60\u662f\u65b0\u624b\u5417\uff1f").setPositiveButton((CharSequence) "\u6211\u662f\u65b0\u624b", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    TodayActivity.this.AddGoalActivity();
//                    dialog.cancel();
//                }
//            }).setNegativeButton((CharSequence) "\u6211\u5df2\u4f1a\u4f7f\u7528", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            }).create().show();
//        }
    }



    private void initView() {
        layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerViewTouchActionGuardManager = new RecyclerViewTouchActionGuardManager();
        recyclerViewTouchActionGuardManager.
                setInterceptVerticalScrollingWhileAnimationRunning(true);
        recyclerViewTouchActionGuardManager.setEnabled(true);

        recyclerViewSwipeManager = new RecyclerViewSwipeManager();

//        mAdapter = new HomeSwipeableItemAdapter(mContext, RecordManager.SELECTED_RECORDS, this, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Message msg = new Message();
        msg.what = 2;
        this.handler.sendMessage(msg);

    }
}
