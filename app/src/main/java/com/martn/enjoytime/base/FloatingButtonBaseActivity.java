package com.martn.enjoytime.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.martn.enjoytime.R;
import com.martn.enjoytime.utility.CusToast;
import com.martn.enjoytime.view.TimeFloatingActionButton;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.base
 * Description: ("处理与floating相关的逻辑")
 * Date 2016/3/7 15:12
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public abstract class FloatingButtonBaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected View mFABWhiteBackground;
    private Animation mWhitBackgroundEnterAlphaAnim;
    protected Animation mWhitBackgroundExitAlphaAnim;
    private FloatingActionsMenu mFloatingActionsMenu;


//    protected abstract boolean finishActionModeIfNeed();
    protected abstract void handleFAB();

    /**
     * 初始化floating
     */
    protected void initFloatingActionButton() {
        TimeFloatingActionButton addTimeButton = (TimeFloatingActionButton) findViewById(R.id.fab_add_time);
        TimeFloatingActionButton addGoalButton = (TimeFloatingActionButton) findViewById(R.id.fab_add_goal);
        TimeFloatingActionButton CheckHoisButton = (TimeFloatingActionButton) findViewById(R.id.fab_check_hois);
        TimeFloatingActionButton mangerGoalButton = (TimeFloatingActionButton) findViewById(R.id.fab_manger_goal);


        addTimeButton.setOnClickListener(this);
        addGoalButton.setOnClickListener(this);
        CheckHoisButton.setOnClickListener(this);
        mangerGoalButton.setOnClickListener(this);

        mFloatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.fab_multiple_actions);
        mFABWhiteBackground = findViewById(R.id.fab_white_background);
        mWhitBackgroundEnterAlphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha_enter_fab_white_background);
        mWhitBackgroundExitAlphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha_exit_fab_white_background);
        mFABWhiteBackground.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismissFAB();
            }
        });

        this.mWhitBackgroundEnterAlphaAnim.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                mFABWhiteBackground.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.mWhitBackgroundExitAlphaAnim.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                mFABWhiteBackground.setVisibility(8);
                onFabButtonClick(((Integer) mFABWhiteBackground.getTag()).intValue());
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.mFloatingActionsMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            public void onMenuExpanded() {
//                finishActionModeIfNeed();
                mFABWhiteBackground.startAnimation(mWhitBackgroundEnterAlphaAnim);
            }

            public void onMenuCollapsed() {
                dismissFAB();
            }
        });
    }

    protected void dismissFAB() {
        dismissFAB(-1);
    }

    protected boolean isFloatingActionMenuExpanded() {
        return mFloatingActionsMenu.isExpanded();
    }


    private void dismissFAB(int id) {
        mFloatingActionsMenu.collapse();
        mFABWhiteBackground.setTag(Integer.valueOf(id));
        mFABWhiteBackground.startAnimation(mWhitBackgroundExitAlphaAnim);
    }

    private void onFabButtonClick(int id) {
        switch (id) {
            case R.id.fab_add_time /*2131624452*/:
            case R.id.fab_add_goal /*2131624453*/:
            case R.id.fab_check_hois /*2131624454*/:
            case R.id.fab_manger_goal /*2131624455*/:
        }
        CusToast.showToast("点击");
    }

    @Override
    public void onClick(View v) {
        dismissFAB(v.getId());
    }




}
