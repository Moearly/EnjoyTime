package com.martn.enjoytime.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.martn.enjoytime.R;
import com.martn.enjoytime.bean.User;
import com.martn.enjoytime.utility.AppUtils;
import com.martn.enjoytime.utility.DateHelper;

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
    public static final String CREATE_TIME = "create_time";

    public void insertDefalutGoal() {
        UserDao dao = new UserDao(ctx);
        Cursor cursor = db.rawQuery("Select "+UID+" from "+TABLE_NAME+" where "+USER_ID+" is ? order by "+POSITION+" limit 1", new String[]{dao.getUserId()});
        if (cursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(USER_ID, User.getInstance().getUserId());
            values.put(IMAGE, "desklamp");
            values.put(COLOR, AppUtils.addColors[0]);
            values.put(ACTION_NAME, ctx.getResources().getString(R.string.str_invest));
            values.put(POSITION, 1);
            values.put(TYPE, 10);
            values.put(CREATE_TIME, DateHelper.getCurrentTimeString());
            values.put(INTRUCTION, ctx.getResources().getString(R.string.str_ins_invest));
            db.insert(TABLE_NAME, null, values);

            values = new ContentValues();
            values.put(USER_ID, User.getInstance().getUserId());
            values.put(IMAGE, "computer");
            values.put(COLOR, AppUtils.addColors[1]);
            values.put(ACTION_NAME, ctx.getResources().getString(R.string.str_routine));
            values.put(POSITION, 2);
            values.put(TYPE, 20);
            values.put(CREATE_TIME, DateHelper.getCurrentTimeString());
            values.put(INTRUCTION, ctx.getResources().getString(R.string.str_ins_routine));
            db.insert(TABLE_NAME, null, values);

            values = new ContentValues();
            values.put(USER_ID, User.getInstance().getUserId());
            values.put(IMAGE, "bed");
            values.put(COLOR, AppUtils.addColors[2]);
            values.put(ACTION_NAME, ctx.getResources().getString(R.string.str_sleep));
            values.put(POSITION, 3);
            values.put(TYPE, 30);
            values.put(CREATE_TIME, DateHelper.getCurrentTimeString());
            values.put(INTRUCTION, ctx.getResources().getString(R.string.str_ins_sleep));
            db.insert(TABLE_NAME, null, values);

            values = new ContentValues();
            values.put(USER_ID, User.getInstance().getUserId());
            values.put(IMAGE, "trash");
            values.put(COLOR, AppUtils.addColors[0]);
            values.put(ACTION_NAME, ctx.getResources().getString(R.string.str_waste));
            values.put(POSITION, 4);
            values.put(TYPE, 40);
            values.put(CREATE_TIME, DateHelper.getCurrentTimeString());
            values.put(INTRUCTION, ctx.getResources().getString(R.string.str_ins_waste));
            db.insert(TABLE_NAME, null, values);
        }
        close(cursor);
    }



    public ActionDao(Context context) {
        super(context);
    }
}
