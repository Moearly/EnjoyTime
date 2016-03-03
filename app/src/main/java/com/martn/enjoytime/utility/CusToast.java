package com.martn.enjoytime.utility;

import android.graphics.Color;

import com.github.johnpersano.supertoasts.SuperToast;
import com.martn.enjoytime.R;
import com.martn.enjoytime.base.BaseApplication;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.utility
 * Description: ("漂亮的自定义Toast")
 * Date 2016/3/1 17:36
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class CusToast {
    private static CusToast ourInstance = new CusToast();
    private CusToast() {
    }

    /**
     *
     * @param text
     * @param color 背景颜色
     */
    public static void showToast(int text, int color) {
        showToast(BaseApplication.resources().getString(text),color);
    }

    public static void showToast(int text) {
        showToast(text,R.color.default_toast_color);//默认使用系统主题颜色
    }

    public static void showToast(String text) {
        showToast(text,R.color.default_toast_color);//默认使用系统主题颜色
    }

    public static void showToast(String text, int color) {
        SuperToast.cancelAllSuperToasts();
        SuperToast superToast = new SuperToast(BaseApplication.context());
        superToast.setAnimations(AppUtils.TOAST_ANIMATION);
        superToast.setDuration(SuperToast.Duration.SHORT);
        superToast.setTextColor(Color.parseColor("#ffffff"));
        superToast.setTextSize(SuperToast.TextSize.SMALL);
        superToast.setText(text);
        superToast.setBackground(color);
        superToast.getTextView().setTypeface(AppUtils.typefaceLatoLight);
        superToast.show();
    }


}
