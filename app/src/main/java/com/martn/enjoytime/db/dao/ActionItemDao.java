package com.martn.enjoytime.db.dao;

import android.content.Context;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.db.dao
 * Description: ("请描述功能")
 * Date 2016/3/8 15:43
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class ActionItemDao extends ModelDaoBase {
    public static final String TABLE_NAME = "action_item";
    public static final String USER_ID = "user_id";

    public static final String ACTION_ID = "action_id";
    public static final String ACTION_TYPE = "action_type";
    public static final String START_TIME = "start_time";
    public static final String TAKE = "take";
    public static final String STOP_TIME = "stop_time";
    public static final String IS_END = "is_end";
    public static final String IS_RECORD = "is_record";
    public static final String REMARKS = "remarks";
    public static final String S_GOAL_ITEM_ID = "s_goal_item_id";
    public static final String ID_DELETE = "is_delete";
    public static final String DELETE_TIME = "delete_time";

    public ActionItemDao(Context context) {
        super(context);
    }
}
