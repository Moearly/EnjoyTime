package com.martn.enjoytime.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.martn.enjoytime.db.dao.ActionDao;
import com.martn.enjoytime.db.dao.ActionItemDao;
import com.martn.enjoytime.db.dao.ActionLinkDao;
import com.martn.enjoytime.db.dao.DistributionDao;
import com.martn.enjoytime.db.dao.GoalRecordDao;
import com.martn.enjoytime.db.dao.UserDao;
import com.socks.library.KLog;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.db
 * Description: ("请描述功能")
 * Date 2016/3/8 10:28
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class DBHelper extends SQLiteOpenHelper {

    private final String CREATE_USERS = "CREATE TABLE if not exists "+UserDao.TABLE_NAME+"("+UserDao.UID+" integer primary key autoincrement not null,"
            + UserDao.USER_NAME+ " varchar(10),"
            + UserDao.PASSWORD+" varchar(10),"
            + UserDao.NICK_NAME+" varchar(10),"
            + UserDao.IS_LOGIN+" integer,"
            + UserDao.GENDER+" integer,"
            + UserDao.LOGIN_TIME+" varchar(20),"
            + UserDao.PHONE+" varchar(20),"
            + UserDao.END_UPDATE_TIME+" varchar(20),"
            + UserDao.UPLOAD_TIME+" varchar(20),"
            + UserDao.IS_UPLOAD+" integer,"
            + UserDao.UPDATED_AT+" datetime default CURRENT_TIMESTAMP);";

    private final String CREATE_ALLOCATION = "CREATE TABLE if not exists "+ DistributionDao.TABLE_NAME+"("+DistributionDao.UID+" integer primary key autoincrement not null,"
            + DistributionDao.USER_ID+" varchar(10),"
            + DistributionDao.INVEST+" integer,"
            + DistributionDao.WASTE+" integer,"
            + DistributionDao.ROUTINE+" integer,"
            + DistributionDao.SLEEP+" integer,"
            + DistributionDao.TIME+" varchar(20),"
            + DistributionDao.REMARKS+" varchar(20),"
            + DistributionDao.MORNING_VOICE+" varchar(20),"
            + DistributionDao.END_UPDATE_TIME+" varchar(20),"
            + DistributionDao.UPLOAD_TIME+" varchar(20),"
            + DistributionDao.IS_UPLOAD+" integer,"
            + DistributionDao.UPDATED_AT+" datetime default CURRENT_TIMESTAMP);";

    private final String CREATE_ACTION = "CREATE TABLE if not exists "+ ActionDao.TABLE_NAME+"("+ActionDao.UID+" integer primary key autoincrement not null,"
            + ActionDao.USER_ID+" integer,"
            + ActionDao.IMAGE+" varchar(10),"
            + ActionDao.COLOR+" varchar(10),"
            + ActionDao.ACTION_NAME+" varchar(10),"
            + ActionDao.TYPE+" integer,"
            + ActionDao.START_TIME+" varchar(20),"
            + ActionDao.DEAD_TIME+" varchar(20),"
            + ActionDao.TIME_OF_EVERYDAY+" integer,"
            + ActionDao.EXPECT_SPEND+" integer,"
            + ActionDao.HAD_SPEND+" integer,"
            + ActionDao.HAD_WASTE+" integer,"
            + ActionDao.IS_FINISH+" integer,"
            + ActionDao.IS_DELETE+" integer,"
            + ActionDao.FINISH_TIME+" varchar(20),"
            + ActionDao.DELETE_TIME+" varchar(20),"
            + ActionDao.INTRUCTION+" varchar(20),"
            + ActionDao.POSITION+" integer,"
            + ActionDao.SEVER_ID+" integer,"
            + ActionDao.IS_SUB_GOAL+" integer,"
            + ActionDao.IS_MANU_SCRIPT+" integer,"
            + ActionDao.IS_DEFAULT+" integer,"
            + ActionDao.IS_HIDED+" integer,"
            + ActionDao.RESET_COUNT+" integer,"
            + ActionDao.END_UPDATE_TIME+" varchar(20),"
            + ActionDao.UPLOAD_TIME+" varchar(20),"
            + ActionDao.IS_UPLOAD+" integer,"
            + ActionDao.UPDATED_AT+" datetime default CURRENT_TIMESTAMP);";

    private final String CREATE_ACTION_ITEM = "CREATE TABLE if not exists "+ ActionItemDao.TABLE_NAME+"("+ActionItemDao.UID+" integer primary key autoincrement not null,"
            + ActionItemDao.USER_ID+" integer,"
            + ActionItemDao.ACTION_ID+" integer,"
            + ActionItemDao.ACTION_TYPE+" integer,"
            + ActionItemDao.START_TIME+" varchar(10),"
            + ActionItemDao.TAKE+" integer,"
            + ActionItemDao.STOP_TIME+" varchar(10),"
            + ActionItemDao.IS_END+" integer,"
            + ActionItemDao.IS_RECORD+" integer,"
            + ActionItemDao.REMARKS+" varchar(200),"
            + ActionItemDao.S_GOAL_ITEM_ID+" double,"
            + ActionItemDao.ID_DELETE+" integer,"
            + ActionItemDao.DELETE_TIME+" varchar(20),"
            + ActionItemDao.END_UPDATE_TIME+" varchar(20),"
            + ActionItemDao.UPLOAD_TIME+" varchar(20),"
            + ActionItemDao.IS_UPLOAD+" integer,"
            + ActionItemDao.UPDATED_AT+" datetime default CURRENT_TIMESTAMP);";

    private final String CREATE_ACTION_LINK = "CREATE TABLE if not exists "+ ActionLinkDao.TABLE_NAME+"("+ActionLinkDao.UID+" integer primary key autoincrement not null,"
            + ActionLinkDao.USER_ID+" integer,"
            + ActionLinkDao.BIG_GOAL_ID+" integer,"
            + ActionLinkDao.SUB_GOAL_ID+" integer,"
            + ActionLinkDao.IS_UPLOAD+" integer,"
            + ActionLinkDao.CREATED_AT+" datetime default CURRENT_TIMESTAMP,"
            + ActionLinkDao.UPDATED_AT+" datetime default CURRENT_TIMESTAMP);";

    private final String CREATE_GOAL_RECORD = "CREATE TABLE if not exists "+ GoalRecordDao.TABLE_NAME+"("+GoalRecordDao.UID+" integer primary key autoincrement not null,"
            + GoalRecordDao.USER_ID+" integer,"
            + GoalRecordDao.GOAL_ID+" integer,"
            + GoalRecordDao.GOAL_NAME+" varchar(20),"
            + GoalRecordDao.GOAL_TYPE+" integer,"
            + GoalRecordDao.STATICS_TYPE+" integer,"
            + GoalRecordDao.EXPECT_INVEST+" double,"
            + GoalRecordDao.HAD_INVEST+" double,"
            + GoalRecordDao.TODAY_INVEST+" double,"
            + GoalRecordDao.SEVEN_INVEST+" double,"
            + GoalRecordDao.START_TIME+" String,"
            + GoalRecordDao.CREAT_TIME+" String,"
            + GoalRecordDao.DEAD_TIME+" String,"
            + GoalRecordDao.IS_UPLOAD+" integer,"
            + GoalRecordDao.CREATED_AT+" datetime default CURRENT_TIMESTAMP,"
            + GoalRecordDao.UPDATED_AT+" datetime default CURRENT_TIMESTAMP);";

    private final String CREATE_ERROR_DATA = "CREATE TABLE  if not exists error_data (Id integer primary key autoincrement not null,ErrorData varchar(200),CreateDate varchar(50),Version varchar(20),IsUpload integer);";




    private Context mContext;
    private static DBHelper helper;

    private static final String DB_NAME = "enjoy_time.db";
    public static final int DB_VERSION = 1;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    public static DBHelper getInstance(Context context) {
        if (helper == null)
            helper = new DBHelper(context);
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(CREATE_USERS);
            db.execSQL(CREATE_ALLOCATION);
            db.execSQL(CREATE_ACTION);
            db.execSQL(CREATE_ACTION_ITEM);
            db.execSQL(CREATE_ACTION_LINK);
            db.execSQL(CREATE_GOAL_RECORD);
            db.execSQL(CREATE_ERROR_DATA);


            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        KLog.w("onUpgrade start");
        KLog.w("oldVersion:" + oldVersion + "\t" + "newVersion:" + newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        KLog.w("onDowngrade");
        if (oldVersion < newVersion) {

        }
        KLog.w("onUpgrade end");
    }
}
