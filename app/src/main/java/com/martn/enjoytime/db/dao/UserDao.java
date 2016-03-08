package com.martn.enjoytime.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.martn.enjoytime.bean.User;
import com.martn.enjoytime.utility.MD5;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.db.dao
 * Description: ("请描述功能")
 * Date 2016/3/8 10:34
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class UserDao extends ModelDaoBase {

    public static final String TABLE_NAME = "users";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String NICK_NAME = "nick_name";
    public static final String IS_LOGIN = "is_login";
    public static final String GENDER ="gender";
    public static final String LOGIN_TIME ="login_time";
    public static final String PHONE = "phone";

    public static final String TEST_USER_NAME = "superman";
    public static final String TEST_PASS_WD = "superman";

    public UserDao(Context context) {
        super(context);
    }

    /**
     * 获得当前登录用户
     */
    public void getLoginUser() {
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where "+IS_LOGIN+" = 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            int id = cursor.getInt(cursor.getColumnIndex(UID));
            String userName = cursor.getString(cursor.getColumnIndex(USER_NAME));
            String nickname = cursor.getString(cursor.getColumnIndex(NICK_NAME));
            int integral = cursor.getInt(cursor.getColumnIndex("integral"));
            String uid = cursor.getString(cursor.getColumnIndex("uuid"));
            User user = User.getInstance();
            user.setUserId(id);
            user.setUserName(userName);
            user.setIntegral(integral);
            user.setNickname(nickname);
            user.setUid(uid);
        }
        close(cursor);
    }

    /**
     * 初始化测试用户
     */
    public void initTestUser() {
        chearLoginUser();
        Cursor cursor = db.rawQuery("select "+UID+" from " + TABLE_NAME + " where "+USER_NAME+" is '"+TEST_USER_NAME+"'", null);
        ContentValues values;
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            int id = cursor.getInt(cursor.getColumnIndex(UID));
            values = new ContentValues();
            values.put(IS_LOGIN, Integer.valueOf(1));
            db.update(TABLE_NAME, values, " id = " + id, null);
        } else {
            values = new ContentValues();
            values.put(USER_NAME, TEST_USER_NAME);
            values.put(PASSWORD, MD5.md5(TEST_PASS_WD));
            values.put(IS_LOGIN, Integer.valueOf(1));
            db.insert(TABLE_NAME, null, values);
        }
        close(cursor);
        getLoginUser();
    }

    /**
     * 清空登录状态
     */
    public void chearLoginUser() {
        ContentValues values = new ContentValues();
        values.put(IS_LOGIN, Integer.valueOf(0));
        db.update(TABLE_NAME, values, TABLE_NAME+" is ?", new String[]{"1"});
    }

    /**
     * 获取用户user_id
     * @return
     */
    public String getUserId() {
        if (User.getInstance() == null) {
            getLoginUser();
        }
        int id = User.getInstance().getUserId();
        if (id == 0) {
            getLoginUser();
            id = User.getInstance().getUserId();
        }
        return String.valueOf(id);
    }


}
