package com.martn.enjoytime.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martn.enjoytime.R;
import com.martn.enjoytime.base.SwipeBackActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.ui.activity
 * Description: ("添加目标界面")
 * Date 2014/10/5 21:02
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class AddGoalActivity extends SwipeBackActivity {


    @Bind(R.id.tv_tab_one)
    TextView tvTabOne;
    @Bind(R.id.tv_tab_two)
    TextView tvTabTwo;
    private View mCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //直接用layout就可以去除base顶部的toolbar
        setContentView(R.layout.activity_add_goal);
        ButterKnife.bind(this);
        initToolbar();

    }

    private void initToolbar() {
        mCustomView = LayoutInflater.from(this).inflate(R.layout.view_toolbar_tow_tag, null);
        ButterKnife.bind(mCustomView);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(this.mCustomView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

}
