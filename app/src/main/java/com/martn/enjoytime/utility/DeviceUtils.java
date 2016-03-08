package com.martn.enjoytime.utility;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.utility
 * Description: ("请描述功能")
 * Date 2016/3/8 17:13
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class DeviceUtils {
    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }
}
