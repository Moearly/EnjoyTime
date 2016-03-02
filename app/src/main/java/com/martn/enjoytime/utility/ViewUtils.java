package com.martn.enjoytime.utility;

import android.util.DisplayMetrics;

import com.martn.enjoytime.base.BaseApplication;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.utility
 * Description: ("ui视图操作相关工具栏")
 * Date 2016/3/2 11:11
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class ViewUtils {

    /**
     * dp到px的转换
     * @param dp
     * @return
     */
    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = BaseApplication.context().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
