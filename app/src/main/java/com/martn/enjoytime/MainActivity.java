package com.martn.enjoytime;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.martn.enjoytime.base.BaseActivity;
import com.martn.enjoytime.base.BaseFragment;
import com.martn.enjoytime.utility.CusToast;
import com.martn.enjoytime.utility.ViewUtils;
import com.martn.enjoytime.view.DrawerRow;

import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    protected Context ctx;
    @Bind(R.id.status_bar_view)
    View statusBarView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
//    @Bind(R.id.fragment_container)
//    FrameLayout fragmentContainer;
    @Bind(R.id.slider)
    SliderLayout slider;
    @Bind(R.id.user_head)
    CircleImageView userHead;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.drawer_home)
    DrawerRow drawerHome;
    @Bind(R.id.drawer_everyday)
    DrawerRow drawerEveryday;
    @Bind(R.id.drawer_statistics)
    DrawerRow drawerStatistics;
    @Bind(R.id.drawer_man_goal)
    DrawerRow drawerManGoal;
    @Bind(R.id.drawer_view_log)
    DrawerRow drawerViewLog;
    @Bind(R.id.drawer_my)
    DrawerRow drawerMy;
    @Bind(R.id.drawer_list)
    ScrollView drawerList;
    @Bind(R.id.left_drawer)
    LinearLayout leftDrawer;
    @Bind(R.id.dl_root)
    DrawerLayout dlRoot;
    private ActionBarDrawerToggle mDrawerToggle;
    private BaseFragment currentFragment;
    private boolean isDrawerOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        ButterKnife.bind(this);
        initView();

        //TODO--产品更新检查处理

        //TODO--用户相关数据--处理
    }

    private void initView() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBar();
        } else {
            // do something for phones running an SDK before lollipop
            View statusBarView = (View)findViewById(R.id.status_bar_view);
            statusBarView.getLayoutParams().height = ViewUtils.getStatusBarHeight();
        }

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }

        mDrawerToggle = new HomeActionBarToggle(this, dlRoot, 0, 0);
        dlRoot.setDrawerListener(mDrawerToggle);

        //初始化---homeMeun的组件
        drawerHome.addClick(new MeunOnclick("home", "hha"));
        drawerHome.setSelected();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBar() {
        // Do something for lollipop and above versions
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(ctx, R.color.statusBarColor));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 对menu菜单点击后的处理
     */
    class MeunOnclick implements View.OnClickListener {

        private String fragmentName;
        private String title;
        private int d;

        MeunOnclick(String title, String fragmentName) {
            this.title = title;
            this.fragmentName = fragmentName;
        }

        public void onClick(View view) {
            CusToast.showToast("点击了m" + title);
//            MainActivity.b localb = MainActivity.this.p;
//            String str1 = this.c;
//            String str2 = this.b;
//            int i = this.d;
//            localb.a(str1, str2, i);
//            MainActivity.a(MainActivity.this, 0);
        }
    }



    class HomeActionBarToggle  extends ActionBarDrawerToggle{
        private String fragmentName;
        private String title;

        public void setFragment(String title,String fragmentName) {
            this.fragmentName = fragmentName;
            this.title = title;
        }

        public void setFragment(String fragmentName) {
            this.fragmentName = fragmentName;

        }

        public HomeActionBarToggle(Activity activity, DrawerLayout drawerLayout, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
            super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        private void switchFragment(String title) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            currentFragment = fragmentManager.findFragmentByTag(title);

            if(currentFragment == null) {
                currentFragment = ContentFragment.newInstance(title);
                ft.add(R.id.container, currentFragment, title);
            }
            if(lastFragment != null) {
                ft.hide(lastFragment);
            }
            if(currentFragment.isDetached()){
                ft.attach(currentFragment);
            }
            ft.show(currentFragment);
            lastFragment = currentFragment;
            ft.commit();
            onSectionAttached(title);

            Iterator iterator = mFragments.iterator();
            while (iterator.hasNext()) {
                BaseFragment fragment = (BaseFragment) iterator.next();
                if (fragment == baseFragment) {
                    if (!baseFragment.isAdded()) {
                        fragmentTransaction.add(R.id.fragment_container, baseFragment);
                        baseFragment.loadFirst();
                    }
                    fragmentTransaction.show(baseFragment);
                } else if (fragment.isAdded()) {
                    fragmentTransaction.hide(fragment);
                }
            }
            fragmentTransaction.commitAllowingStateLoss();
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
            isDrawerOpen = false;
            if (fragmentName != null) {
                //设置title---改变文字
                if (title != null) {
                    tvTitle.setText(title);
                }

            }
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            isDrawerOpen = true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
