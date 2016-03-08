package com.martn.enjoytime.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.martn.enjoytime.db.DBHelper;
import com.martn.enjoytime.utility.AppUtils;
import com.martn.enjoytime.utility.DateHelper;
import com.martn.enjoytime.utility.DeviceUtils;
import com.socks.library.KLog;

/**
 * Title: Juyixia
 * Package: com.lefu.juyixia.database.modeldao
 * Description: ("请描述功能")
 * Date 2015/8/25 17:47
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class ModelDaoBase {
    static final String TAG = ModelDaoBase.class.getName();

    public static final String UID = "_id";
    public static final String CREATED_AT = "created_at";
//    public static final String REMOTE_UPDATED_AT = "updated_at";
    public static final String UPDATED_AT = "updated_at";


    public static final String END_UPDATE_TIME ="end_update_time";
    public static final String UPLOAD_TIME="upload_time";
    public static final String IS_UPLOAD ="is_upload";
    protected Context ctx = null;

    protected SQLiteDatabase db;

    protected ModelDaoBase(Context context) {
        ctx = context;
        if ((db == null) || (!db.isOpen())) {
            db = DBHelper.getInstance(context).getWritableDatabase();
        }
    }

    protected void closeDB() {

        if ((this.db != null) && (this.db.isOpen())) {
            KLog.w(TAG, "close db");
            this.db.close();
        }
    }

    protected void close(Cursor cursor) {
        if (cursor != null) {
            try {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            } catch (Exception e) {
                exceptionHandler(e);
            }
        }
    }


    protected void exceptionHandler(Exception e) {
        try {
            if (db != null) {
                db.insert("error_data", null, getErrorValues(e));
            } else {
                KLog.e("Override DbBase", "db is null");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    protected ContentValues getErrorValues(Exception e) {
        return getErrorValues(e, "");
    }


    protected ContentValues getErrorValues(Exception e, String paramsStr) {
        e.printStackTrace();
        String errorString = AppUtils.getExceptionString(e);
        ContentValues values = new ContentValues();
        String str = "ErrorData";
        StringBuilder append = new StringBuilder().append(errorString);
        String str2 = (paramsStr == null || paramsStr.length() <= 0) ? "" : "\n\n" + paramsStr;
        values.put(str, append.append(str2).toString());
        values.put("CreateDate", DateHelper.getCurrentDateTimeString());
        String VERSION_NAME = DeviceUtils.getVersionName(ctx);
        if (!TextUtils.isEmpty(VERSION_NAME)) {
            values.put("Version", VERSION_NAME);
        }
        return values;
    }




}
