package com.martn.enjoytime;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
import com.martn.enjoytime.base.FloatingButtonBaseActivity;
import com.martn.enjoytime.ui.fragment.CountInfoFragment;
import com.martn.enjoytime.ui.fragment.EveryDayFragment;
import com.martn.enjoytime.ui.fragment.HomeFragment;
import com.martn.enjoytime.ui.fragment.ManageTargetFragment;
import com.martn.enjoytime.ui.fragment.UserCenterFragment;
import com.martn.enjoytime.ui.fragment.ViewLogFragment;
import com.martn.enjoytime.utility.CusToast;
import com.martn.enjoytime.utility.ViewUtils;
import com.martn.enjoytime.view.DrawerRow;
import com.martn.enjoytime.view.WaveView.WaveView;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends FloatingButtonBaseActivity {

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
//    @Bind(R.id.wave_title_bg)
//    WaveView mWaveView;
    private ActionBarDrawerToggle mDrawerToggle;
    private BaseFragment currentFragment;
    private boolean isDrawerOpen = false;
    private ArrayList<String> tags;
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
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                // 打開 up button
                actionBar.setDisplayHomeAsUpEnabled(true);
                //actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
//                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }



        mDrawerToggle = new HomeActionBarToggle(this, dlRoot,toolbar, 0, 0);
        mDrawerToggle.syncState();
        dlRoot.setDrawerListener(mDrawerToggle);
        //初始化---homeMeun的组件
        tags = new ArrayList<>();
        drawerHome.addClick(new MeunOnclick(1, HomeFragment.getMyTag()));
        drawerEveryday.addClick(new MeunOnclick(2, EveryDayFragment.getMyTag()));
        drawerStatistics.addClick(new MeunOnclick(3, CountInfoFragment.getMyTag()));
        drawerManGoal.addClick(new MeunOnclick(4, ManageTargetFragment.getMyTag()));
        drawerViewLog.addClick(new MeunOnclick(5, ViewLogFragment.getMyTag()));
        drawerMy.addClick(new MeunOnclick(6, UserCenterFragment.getMyTag()));
        drawerHome.setSelected();

        //填充数据
        contentInit(new HomeFragment(), HomeFragment.getMyTag());

        initFloatingActionButton();
//        initWaveView();
    }

//    private void initWaveView() {
//        ViewGroup.LayoutParams params = mWaveView.getLayoutParams();
//        toolbar.measure(0, 0);
//        params.height = toolbar.getMeasuredHeight()+ViewUtils.dpToPx(48);
//        mWaveView.setLayoutParams(params);
//    }



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

    @Override
    protected void handleFAB() {

    }

    /**
     * 对menu菜单点击后的处理
     */
    class MeunOnclick implements View.OnClickListener {

        private int index;
        private String tag;

        public MeunOnclick(int index, String tag) {
            this.index = index;
            this.tag = tag;
            tags.add(tag);
        }

        public void onClick(View view) {
            switchFragment(index,tag);
        }
    }



    /**
     * 展示类容
     */
    public void showContent() {
        if ((dlRoot != null) && (dlRoot.isDrawerOpen(Gravity.LEFT)))
            dlRoot.closeDrawer(Gravity.LEFT);
    }

    /**
     * 初始化
     * @param fragment
     * @param tag
     */
    public void contentInit(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * 处理fragment切换会带来的其他逻辑
     * @param index
     */
    private void switchFragment(int index, String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        switch (index) {
            case 1:
                if (fragment == null) {
                    fragment = new HomeFragment();
                }
                break;
            case 2:
                if (fragment == null) {
                    fragment = new EveryDayFragment();
                }
                break;
            case 3:
                if (fragment == null) {
                    fragment = new CountInfoFragment();
                }
                break;
            case 4:
                if (fragment == null) {
                    fragment = new ManageTargetFragment();
                }
                break;
            case 5:
                if (fragment == null) {
                    fragment = new ViewLogFragment();
                }
                break;
            case 6:
                if (fragment == null) {
                    fragment = new UserCenterFragment();
                }
                break;
            default:
                if (fragment == null) {
                    fragment = new HomeFragment();
                }
        }
        switchFragment(fragment,tag);
    }

    private void switchFragment(final Fragment fragment, final String tag) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showContent();
            }
        }, 120L);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
                fragmentTransaction.addToBackStack(tag);
                for (int i = 0; i < tags.size(); i++) {
                    String curTag = tags.get(i);
                    Fragment curFragment = getSupportFragmentManager().findFragmentByTag(curTag);
                    if (curFragment != null) {
                        if (!tag.equals(curTag)) {
                            fragmentTransaction.hide(curFragment);
                        }
                    }
                }
                fragmentTransaction.show(fragment);
                fragmentTransaction.commit();
            }
        }, 50L);
    }

    class HomeActionBarToggle extends ActionBarDrawerToggle{
        private String fragmentName;
        private String title;

        public void setFragment(String title,String fragmentName) {
            this.fragmentName = fragmentName;
            this.title = title;
        }

        public void setFragment(String fragmentName) {
            this.fragmentName = fragmentName;

        }

        public HomeActionBarToggle(Activity activity, DrawerLayout drawerLayout,Toolbar toolbar ,@StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
            super(activity, drawerLayout, toolbar,openDrawerContentDescRes, closeDrawerContentDescRes);
        }



        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
            isDrawerOpen = false;

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
