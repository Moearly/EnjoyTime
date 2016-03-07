package com.martn.enjoytime.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.ui.fragment
 * Description: ("请描述功能")
 * Date 2016/3/7 17:10
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public abstract class HomeRefreshFragment extends HomeBaseFragment {
    protected SwipeRefreshLayout mRefreshLayout;
    protected View mHomeRootView;

    protected abstract View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mHomeRootView = onCreateView(inflater, container);
        init();
        return mHomeRootView;
    }

    private void initViews() {
        initRefreshLayout();
        //initMessagesView();
    }

    private void initRefreshLayout() {
//        mRefreshLayout = (SwipeRefreshLayout) mHomeRootView.findViewById(R.id.re_);
//        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            public void onRefresh() {
//            }
//        });

    }


    private void initData() {
        if (isRefreshData()) {
            refreshAllData();
        }
    }


    private void init() {
        initViews();
        initData();
    }

}
