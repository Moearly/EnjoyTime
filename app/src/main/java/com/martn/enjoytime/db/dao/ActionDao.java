package com.martn.enjoytime.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.RelativeLayout;

import com.martn.enjoytime.R;
import com.martn.enjoytime.bean.User;
import com.martn.enjoytime.db.DBHelper;
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
    //public static final String LEVEL = "level";//目标自定义的级别
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
//    public static final String IS_SUB_GOAL = "is_sub_goal";
//    public static final String IS_MANU_SCRIPT = "is_manu_script";
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


    public void getGoals() {
        UserDao dao = new UserDao(ctx);
//        Cursor cur = db.rawQuery("Select * from (Select * from "+TABLE_NAME+" where "+USER_ID+" is "+dao.getUserId()+" and "+IS_DELETE+" is not 1 and "+IS_FINISH+" is not 1 and "+IS_MANU_SCRIPT+" is not 1 ) where "+IS_SUB_GOAL+" is null or "+IS_SUB_GOAL+" is 0 ORDER BY "+POSITION, null);
//        if (cur.getCount() > 0) {
//            while (cur.moveToNext()) {
//                String id = cur.getString(cur.getColumnIndex(UID));
//                int type = cur.getInt(cur.getColumnIndex(TYPE));
//                if (cur.getCount() <= 4 || type != 10) {
//                    String actName = cur.getString(cur.getColumnIndex(ACTION_NAME));
//                    String image = cur.getString(cur.getColumnIndex(IMAGE));
//                    String color = cur.getString(cur.getColumnIndex(COLOR));
//                    String intruction = cur.getString(cur.getColumnIndex(INTRUCTION));
//                    int isSubGoal = cur.getInt(cur.getColumnIndex(IS_SUB_GOAL));
//                    int isHided = cur.getInt(cur.getColumnIndex(IS_HIDED));
//                    int hadSpend = 0;
//                    if (type == 11) {
//                        try {
//
//                            hadSpend = (int) DbUtils.queryStaticsHadInvestByGoalId(context, Integer.parseInt(id));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (type == 10) {
//                        intruction = ctx.getResources().getString(R.string.str_help_time);
//                    } else if (type == 20) {
//                        intruction = ctx.getResources().getString(R.string.str_oblige_helpless_time);
//                    } else if (type == 30) {
//                        intruction = ctx.getResources().getString(R.string.str_sleep_time);
//                    } else if (type == 40) {
//                        intruction = ctx.getResources().getString(R.string.str_helpless_time);
//                    }
//                    String deadtime = cur.getString(cur.getColumnIndex(DEAD_TIME));
//                    String[] strArr = new String[]{id, actName, color, image, intruction, deadtime};
//                    int isShowConnerLabel = 0;
//
//                    if (isSubGoal > 0 && type == 11) {
//                        isShowConnerLabel = 2;
//                    } else if (type == 11) {
//                        isShowConnerLabel = 1;
//                    }
//
//
//                    String[] strArr2;
//                    int i;
//                    int i2;
//                    if (type == 11) {
//                        Cursor cursor2 = DbUtils.getDb(context).rawQuery(Sql.getSubGoals(context, id), null);
//                        if (cursor2.getCount() > 0) {
//                            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
//                            layoutParams.addRule(3, rlId - 1);
//                            layoutParams.topMargin = 5;
//                            View rl = (RelativeLayout) this.inflater.inflate(R.layout.tem_rl_big_goal, null);
//                            rl.setLayoutParams(layoutParams);
//                            rl.setId(rlId);
//                            rl.addView(getGoalItems(Status.NETWORK_FAIL, strArr, isShowConnerLabel, isHided, (double) hadSpend, dbType));
//                            int bigGoalRlId = Status.NETWORK_FAIL + 1;
//                            while (cursor2.moveToNext()) {
//                                String id2 = cursor2.getString(cursor2.getColumnIndex(f.bu));
//                                String actName2 = cursor2.getString(cursor2.getColumnIndex("actName"));
//                                String image2 = cursor2.getString(cursor2.getColumnIndex("image"));
//                                String color2 = cursor2.getString(cursor2.getColumnIndex("color"));
//                                String intruction2 = cursor2.getString(cursor2.getColumnIndex("intruction"));
//                                int dbType2 = cursor2.getInt(cursor2.getColumnIndex(com.umeng.update.a.c));
//                                int isSubGoal2 = cursor2.getInt(cursor2.getColumnIndex("isSubGoal"));
//                                int isHided2 = cursor2.getInt(cursor2.getColumnIndex("isHided"));
//                                int hadSpend2 = cursor2.getInt(cursor2.getColumnIndex("hadSpend"));
//                                String startTime2 = cursor2.getString(cursor2.getColumnIndex("startTime"));
//                                String deadline2 = cursor2.getString(cursor2.getColumnIndex("deadline"));
//                                if (dbType2 == 11) {
//                                    hadSpend2 = (int) DbUtils.queryStaticsHadInvestByGoalId(context, Integer.parseInt(id2));
//                                }
//                                String[] strArr22 = new String[]{id2, actName2, color2, image2, intruction2, deadline2};
//                                isShowConnerLabel = 0;
//                                if (isSubGoal2 > 0 && dbType2 == 11) {
//                                    isShowConnerLabel = 2;
//                                } else if (dbType2 == 11) {
//                                    isShowConnerLabel = 1;
//                                }
//                                int i3 = bigGoalRlId;
//                                int i4 = isShowConnerLabel;
//                                RelativeLayout relativeLayout = rl;
//                                relativeLayout.addView(getGoalItems(i3, strArr22, i4, isHided2, (double) hadSpend2, dbType2));
//                                bigGoalRlId++;
//                            }
//                            this.rl_today_items.addView(rl);
//                        } else {
//                            strArr2 = strArr;
//                            i = 4;
//                            i2 = isHided;
//                            this.rl_today_items.addView(getGoalItems(rlId, strArr2, i, i2, (double) hadSpend, dbType));
//                        }
//                        DbUtils.close(cursor2);
//                    } else {
//                        strArr2 = strArr;
//                        i = isShowConnerLabel;
//                        i2 = isHided;
//                        this.rl_today_items.addView(getGoalItems(rlId, strArr2, i, i2, (double) hadSpend, dbType));
//                    }
//                    rlId++;
//                    if (cur.isLast()) {
//                        this.rl_today_items.addView(getAddView(rlId));
//                    }
//                }
//            }
//        }
//        DbUtils.close(cur);
    }



    public ActionDao(Context context) {
        super(context);
    }

    /**
     * 更新item
     * @param values
     * @param actId
     */
    public void updateActionItem(ContentValues values, String actId) {
        db.update(TABLE_NAME, values,UID+" is ?", new String[]{actId});
        closeDB();
    }

    public void addActionItem(ContentValues values) {
        db.insert(TABLE_NAME, null, values);
        closeDB();
    }
}
