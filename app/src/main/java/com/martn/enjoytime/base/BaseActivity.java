package com.martn.enjoytime.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.martn.enjoytime.MainActivity;
import com.martn.enjoytime.R;
import com.umeng.analytics.MobclickAgent;

/**
 * Title: ZeaApp
 * Package: com.martn.zeaapp.base
 * Description: ("Activity基础类")
 * Date 2014/10/5 14:58
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class BaseActivity extends AppCompatActivity {

    //全局上下文
    protected Context ctx;
    protected BaseActivity activity;
    //自定义的进度加载器
    //private LoadingFragment mLoading;

    private LinearLayout rootLayout;
    protected Toolbar toolbar;

    /**
     * 初始化appbar显示
     */
    private void initToolbar() {

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        //BlackTech.enableApiSwitch(this.toolbar, this);
    }

    public void comeOnBaby(Class pClass) {
        startActivity(new Intent(this, pClass));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.setContentView(R.layout.activity_base);
        //bug监听上传
//        AppManager.getAppManager().addActivity(this);
        ctx = this;
        activity = this;
        //理解这段代码作用
        if (Build.VERSION.SDK_INT >= 19) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.flags = (0x4000000 | attributes.flags);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        rootLayout = ((LinearLayout) findViewById(R.id.root_layout));
        if (rootLayout != null) {
            View view = View.inflate(this, layoutResID, null);
            rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            initToolbar();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //RequestManager.cancelAll(this);
    }

    @Override
    protected void onPause() {

        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {

        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onBackPressed() {
        //MainAc还开在---则启动mainAc
        if (!((BaseApplication) getApplication()).getIsMainOpened()) {

            startActivity(new Intent(this.ctx, MainActivity.class));
            finish();
        }
        super.onBackPressed();
    }


//    /**
//     * 显示加载
//     */
//    public void showLoading() {
//
//        try {
//            if (mLoading == null) {
//                mLoading = LoadingFragment.newInstance();
//            }
//
//            if (!mLoading.isAdded()) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.remove(mLoading).commitAllowingStateLoss();
//                mLoading.show(getSupportFragmentManager(), "loading");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


//    /**
//     * 关闭加载
//     */
//    public void dismissLoading() {
//
//        try {
//            if ((mLoading != null) && (mLoading.isAdded()))
//                mLoading.dismiss();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    protected boolean getBooleanExtra(String key) {

        return getIntent().getBooleanExtra(key, false);
    }

    protected int getIntExtra(String key) {

        return getIntent().getIntExtra(key, -1);
    }

    protected String getStringExtra(String key) {

        return getIntent().getStringExtra(key);
    }

}
