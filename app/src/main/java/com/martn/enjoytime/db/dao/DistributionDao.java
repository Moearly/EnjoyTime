package com.martn.enjoytime.db.dao;

import android.content.Context;
import android.database.Cursor;

import com.martn.enjoytime.bean.User;
import com.martn.enjoytime.utility.DateHelper;
import com.martn.enjoytime.utility.FormatUtils;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.db.dao
 * Description: ("时间分配记录")
 * Date 2016/3/8 10:57
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class DistributionDao extends ModelDaoBase {

    public static final String TABLE_NAME = "distribution";
    public static final String USER_ID = "user_id";
    public static final String INVEST = "invest";
    public static final String WASTE = "waste";
    public static final String ROUTINE = "routine";//日常的
    public static final String SLEEP = "sleep";
    public static final String TIME = "time";
    public static final String REMARKS = "remarks";
    public static final String MORNING_VOICE = "morning_voice";



    public DistributionDao(Context context) {
        super(context);
    }

    /**
     * 获取今天分配的时间
     * @return
     */
    public String[] queryTodayDistribution() {
        String[] strArr = new String[5];
        String investStr = "";
        String wasteStr = "";
        Cursor cur = db.rawQuery("Select * from "+TABLE_NAME+" where "+USER_ID +"is " + User.getInstance().getUserId() + " and "+TIME+" is '" + DateHelper.getCurrentDateString() + "'", null);
        if (cur.getCount() > 0) {
            cur.moveToNext();
            int invest = cur.getInt(cur.getColumnIndex(INVEST));
            int waste = cur.getInt(cur.getColumnIndex(WASTE));
            if (waste < 0) {
                waste = 0;
            }
            if (invest < 0) {
                invest = 0;
            }
            int lar = Math.max(waste, invest);
            int max = 3600;
            if (lar < 3600) {
                max = invest + waste;
                investStr = (invest / 60) + "min " + FormatUtils.format_1fra((((double) invest) / ((double) max)) * 100.0d) + "%";
                wasteStr = (waste / 60) + "min " + FormatUtils.format_1fra((((double) waste) / ((double) max)) * 100.0d) + "%";
            } else {
                if (((lar < 72000 ? 1 : 0) & (lar >= 3600 ? 1 : 0)) != 0) {
                    max = invest + waste;
                    investStr = FormatUtils.format_1fra(((double) invest) / 3600.0d) + "h " + FormatUtils.format_1fra((((double) invest) / ((double) max)) * 100.0d) + "%";
                    wasteStr = FormatUtils.format_1fra(((double) waste) / 3600.0d) + "h " + FormatUtils.format_1fra((((double) waste) / ((double) max)) * 100.0d) + "%";
                } else if (lar >= 72000) {
                    if (waste > 86400) {
                        waste = 86400;
                    }
                    if (invest > 86400) {
                        invest = 86400;
                    }
                    max = invest + waste;
                    investStr = FormatUtils.format_1fra(((double) invest) / 3600.0d) + "h " + FormatUtils.format_1fra((((double) invest) / ((double) max)) * 100.0d) + "%";
                    wasteStr = FormatUtils.format_1fra(((double) waste) / 3600.0d) + "h " + FormatUtils.format_1fra((((double) waste) / ((double) max)) * 100.0d) + "%";
                }
            }
            strArr[2] = invest + "";
            strArr[3] = waste + "";
            strArr[4] = max + "";
        } else {
            investStr = "0min 0%";
            wasteStr = "0min 0%";
        }
        strArr[0] = investStr;
        strArr[1] = wasteStr;
        close(cur);
        return strArr;
    }

}
