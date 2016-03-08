package com.martn.enjoytime.db.dao;

import android.content.Context;

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

    public UserDao(Context context) {
        super(context);
    }
}
