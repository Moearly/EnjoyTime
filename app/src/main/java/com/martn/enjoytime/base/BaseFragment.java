package com.martn.enjoytime.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.martn.enjoytime.app.App;
import com.squareup.leakcanary.RefWatcher;

import java.lang.reflect.Field;

/**
 * Title: ZeaApp
 * Package: com.martn.zeaapp.base
 * Description: ("fragment的基本类")
 * Date 2014/10/5 16:03
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class BaseFragment extends Fragment {
    protected Activity activity;

    public void loadFirst() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            activity = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        if (refWatcher != null)
            refWatcher.watch(this);
        //RequestManager.cancelAll(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            //发射修改mChildFragmentManager--解决detch时引发的异常
            Field field = Fragment.class.getDeclaredField("mChildFragmentManager");
            field.setAccessible(true);
            field.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public void showLoading() {
        if ((activity != null) && ((activity instanceof BaseActivity)))
            ((BaseActivity) activity).showLoading();
    }

    public void dismissLoading() {
        if ((activity != null) && ((activity instanceof BaseActivity)))
            ((BaseActivity) activity).dismissLoading();
    }

}
