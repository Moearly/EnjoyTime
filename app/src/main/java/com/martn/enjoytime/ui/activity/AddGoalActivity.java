package com.martn.enjoytime.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.martn.enjoytime.R;
import com.martn.enjoytime.base.BaseApplication;
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




    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_des)
    EditText etDes;
    @Bind(R.id.tv_icon_reset)
    TextView tvIconReset;
    @Bind(R.id.gv_icon)
    GridView gvIcon;
    @Bind(R.id.tv_color_reset)
    TextView tvColorReset;
    @Bind(R.id.gv_colors)
    GridView gvColors;
    @Bind(R.id.rt_cancel)
    RoundTextView rtCancel;
    @Bind(R.id.rt_save)
    RoundTextView rtSave;

    private View mCustomView;
    private TextView tvTabOne;
    private TextView tvTabTwo;
    private View btnBack;

    private boolean tabSelectedOne = true;

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

        tvTabOne = (TextView) mCustomView.findViewById(R.id.tv_tab_one);
        tvTabTwo = (TextView) mCustomView.findViewById(R.id.tv_tab_two);
        tvTabOne.setText(getString(R.string.add_habit));
        tvTabTwo.setText(getString(R.string.add_goal));

        tvTabOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tabSelectedOne) {
                    tvTabTwo.setTextColor(getResources().getColor(R.color.theme_main_color));
                    tvTabOne.setTextColor(getResources().getColor(R.color.white));
                    tvTabOne.setBackgroundResource(R.drawable.head_tab_bg_shape_left_selected);
                    tvTabTwo.setBackgroundResource(R.drawable.head_tab_bg_shape_right);
                    tabSelectedOne = true;
                }
            }
        });

        tvTabTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tabSelectedOne) {
                    tvTabTwo.setTextColor(getResources().getColor(R.color.white));
                    tvTabOne.setTextColor(getResources().getColor(R.color.theme_main_color));
                    tvTabOne.setBackgroundResource(R.drawable.head_tab_bg_shape_left);
                    tvTabTwo.setBackgroundResource(R.drawable.head_tab_bg_shape_right_selected);
                    tabSelectedOne = false;
                }
            }
        });

        btnBack = mCustomView.findViewById(R.id.fl_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);//程序图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//去掉返回
        getSupportActionBar().setCustomView(this.mCustomView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

}
