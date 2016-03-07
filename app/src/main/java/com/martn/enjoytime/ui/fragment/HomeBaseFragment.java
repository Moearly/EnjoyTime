package com.martn.enjoytime.ui.fragment;

import android.os.Bundle;

import com.martn.enjoytime.base.BaseFragment;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.ui.fragment
 * Description: ("处理主界面--fragment的共同逻辑")
 * Date 2016/3/7 17:02
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public abstract class HomeBaseFragment extends BaseFragment{

    public abstract void refreshAllData();

    public static <T extends HomeBaseFragment> T newInstance(boolean isRefreshData, Class<T> clazz) {
        T fragment = null;
        try {
            fragment = (T) clazz.newInstance();
            Bundle args = new Bundle();
            args.putBoolean("isRefreshData", isRefreshData);
            fragment.setArguments(args);
            return fragment;
        } catch (Exception e) {
            e.printStackTrace();
            return fragment;
        }
    }

    protected boolean isRefreshData() {
        return getArguments().getBoolean("isRefreshData");
    }


    public boolean onBackPressed() {
        return false;
    }



}
