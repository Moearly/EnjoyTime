package com.martn.enjoytime.db.dao;

import android.content.Context;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.db.dao
 * Description: ("请描述功能")
 * Date 2016/3/8 16:02
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class ActionLinkDao extends ModelDaoBase {
    //CREATE TABLE t_act_link (Id integer primary key autoincrement not null,
    // userId integer,bigGoalId integer,subGoalId integer,createTime varchar(20),isUpload integer);

    public static final String TABLE_NAME = "action_link";
    public static final String USER_ID = "user_id";
    public static final String BIG_GOAL_ID = "big_goal_id";
    public static final String SUB_GOAL_ID = "sub_goal_id";

    public ActionLinkDao(Context context) {
        super(context);
    }
}
