package com.martn.enjoytime.base;

import android.os.Bundle;
import android.view.View;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Title: Juyixia
 * Package: com.lefu.juyixia.base.activity
 * Description: ("让activity具有滑动返回的效果")
 * Date 2015/8/6 14:14
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class SwipeBackActivity extends BaseActivity implements SwipeBackActivityBase {

        private SwipeBackActivityHelper mHelper;

        @Override
        public View findViewById(int id) {

                View view = super.findViewById(id);
                if ((view == null) && (mHelper != null))
                        view = mHelper.findViewById(id);
                return view;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                mHelper = new SwipeBackActivityHelper(this);
                mHelper.onActivityCreate();
        }

        @Override
        protected void onPostCreate(Bundle savedInstanceState) {
                super.onPostCreate(savedInstanceState);
                mHelper.onPostCreate();
        }

        @Override
        public SwipeBackLayout getSwipeBackLayout() {
                return mHelper.getSwipeBackLayout();
        }

        @Override
        public void setSwipeBackEnable(boolean b) {
                getSwipeBackLayout().setEnableGesture(b);
        }

        @Override
        public void scrollToFinishActivity() {
                getSwipeBackLayout().scrollToFinishActivity();
        }
}
