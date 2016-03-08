package com.martn.enjoytime.db.dao;

import android.content.Context;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.db.dao
 * Description: ("记录时间的操作")
 * Date 2016/3/8 11:15
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class ActionDao extends ModelDaoBase{
    public static final String TABLE_NAME = "action";
    public static final String USER_ID = "user_id";
    public static final String IMAGE = "image";
    public static final String COLOR = "color";
    public static final String ACTION_NAME = "action_name";
    public static final String TYPE = "type";
    public static final String START_TIME = "start_time";//目标的开始时间
    public static final String DEAD_TIME = "dead_time";//目标预定结束的时间---期限
    public static final String LEVEL = "level";//目标自定义的级别
    public static final String TIME_OF_EVERYDAY = "time_of_everyday";//平均每天的时间
    public static final String EXPECT_SPEND = "expect_spend";
    public static final String HAD_SPEND = "had_spend";
    public static final String HAD_WASTE = "had_waste";
    public static final String IS_FINISH = "is_finish";
    public static final String IS_DELETE = "is_delete";
    public static final String FINISH_TIME = "finish_time";
    public static final String DELETE_TIME = "delete_time";
    public static final String INTRUCTION = "intruction";
    public static final String POSITION = "position";
    public static final String SEVER_ID = "sever_id";
    public static final String IS_SUB_GOAL = "is_sub_goal";
    public static final String IS_MANU_SCRIPT = "is_manu_script";
    public static final String IS_DEFAULT = "is_default";
    public static final String IS_HIDED = "is_hided";
    public static final String RESET_COUNT = "reset_count";






    public ActionDao(Context context) {
        super(context);
    }
}
