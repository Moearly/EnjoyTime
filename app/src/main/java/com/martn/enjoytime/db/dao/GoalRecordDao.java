package com.martn.enjoytime.db.dao;

import android.content.Context;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.db.dao
 * Description: ("对目标时间的记录")
 * Date 2016/3/8 16:14
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class GoalRecordDao extends ModelDaoBase{

    public static final String TABLE_NAME = "goal_record";
    public static final String USER_ID = "user_id";
    public static final String GOAL_ID = "goal_id";
    public static final String GOAL_NAME = "goal_name";
    public static final String GOAL_TYPE = "goal_type";
    public static final String STATICS_TYPE = "statics_type";
    public static final String EXPECT_INVEST = "expect_invest";
    public static final String HAD_INVEST = "had_invest";
    public static final String TODAY_INVEST = "today_invest";
    public static final String SEVEN_INVEST = "seven_invest";
    public static final String START_TIME = "start_time";
    public static final String CREAT_TIME = "create_time";
    public static final String DEAD_TIME = "dead_time";


    public GoalRecordDao(Context context) {
        super(context);
    }
}
