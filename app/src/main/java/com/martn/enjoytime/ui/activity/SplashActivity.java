package com.martn.enjoytime.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.martn.enjoytime.MainActivity;
import com.martn.enjoytime.R;
import com.martn.enjoytime.base.BaseApplication;
import com.martn.enjoytime.utility.AppUtils;
import com.martn.enjoytime.utility.ViewUtils;
import com.socks.library.KLog;
import com.tencent.bugly.crashreport.CrashReport;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.widget.RevealFrameLayout;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.ui.activity
 * Description: ("启动界面")
 * Date 2016/3/1 17:42
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class SplashActivity extends Activity {

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.app_name)
    TextView appName;
    @Bind(R.id.loading_text)
    TextView loadingText;
    @Bind(R.id.ll_bg)
    LinearLayout llBg;
    @Bind(R.id.rfl_root)
    RevealFrameLayout rflRoot;

    private boolean loadDataCompleted = false;
    private boolean showAnimationCompleted = false;
    private boolean activityStarted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        new InitData().execute();
    }

    private boolean hasAnimationStarted;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !hasAnimationStarted) {
            startCircularReveal();
        }
    }

    private void startCircularReveal() {
        // get the center for the clipping circle
        int[] location = new int[2];
        image.getLocationOnScreen(location);//获取image图片在屏幕中的位置---开始发生渐变的位置
        int cx = location[0] + ViewUtils.dpToPx(24);
        int cy = location[1] + ViewUtils.dpToPx(24);

        // get the final radius for the clipping circle
        int dx = Math.max(cx, llBg.getWidth() - cx);
        int dy = Math.max(cy, llBg.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        SupportAnimator animator =
                ViewAnimationUtils.createCircularReveal(llBg, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(3000);
        animator.start();
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {
                KLog.d("Showing animation completed");
                showAnimationCompleted = true;
                if (loadDataCompleted && showAnimationCompleted && !activityStarted) {
                    activityStarted = true;//已经进入了主界面
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationCancel() {

            }

            @Override
            public void onAnimationRepeat() {

            }
        });
        hasAnimationStarted = true;
    }

    /**
     * 启动数据初始化
     */
    public class InitData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Bmob.initialize(BaseApplication.context(), AppUtils.APPLICATION_ID);
            CrashReport.initCrashReport(BaseApplication.context(), AppUtils.BUGLY_APP_ID, false);
//            RecordManager.getInstance(CoCoinApplication.getAppContext());
            AppUtils.init(BaseApplication.context());
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d("CoCoin", "Loading Data completed");
            loadingText.setText(BaseApplication.resources().getString(R.string.loaded));
            loadDataCompleted = true;
            if (loadDataCompleted && showAnimationCompleted && !activityStarted) {
                activityStarted = true;
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }
    }
}
