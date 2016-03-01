package com.martn.enjoytime.ui.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.martn.enjoytime.R;
import com.martn.enjoytime.base.BaseApplication;
import com.martn.enjoytime.utility.AppUtils;
import com.tencent.bugly.crashreport.CrashReport;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

    }

    /**
     * 启动数据初始化
     */
    public class InitData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Bmob.initialize(BaseApplication.context(), AppUtils.APPLICATION_ID);
            CrashReport.initCrashReport(BaseApplication.context(), "900018935", false);
//            RecordManager.getInstance(CoCoinApplication.getAppContext());
//            CoCoinUtil.init(CoCoinApplication.getAppContext());
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d("CoCoin", "Loading Data completed");
            loadingText.setText(BaseApplication.resources().getString(R.string.loaded));
//            loadDataCompleted = true;
//            if (loadDataCompleted && showAnimationCompleted && !activityStarted) {
//                activityStarted = true;
//                startActivity(new Intent(mContext, MainActivity.class));
//                finish();
//            }
        }
    }
}
