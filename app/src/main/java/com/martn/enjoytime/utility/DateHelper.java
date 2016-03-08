package com.martn.enjoytime.utility;

import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Title: Juyixia
 * Package: com.lefu.juyixia.utils
 * Description: ("日期处理")
 * Date 2015/8/21 17:12
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class DateHelper {


    /**
     * 获取时间----时间搓
     * @param times
     * @return
     */
    public static String timestampToStr(long times) {

        Timestamp timestamp = new Timestamp(times);
        PrintStream printStream = System.out;
        String str = "dateline-" + times;
        printStream.println(str);
        return new SimpleDateFormat("yyyy-MM-dd E HH:mm").format(timestamp);
    }

    public static String timestampToStr_ne(long times) {

        Timestamp timestamp = new Timestamp(times);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(timestamp);
    }


    /**
     * 获取 时间---文字
     * @param times
     * @return
     */
    public static String getShortTime(long times) {

        String desTimeS = null;

        Date date = parseString(timestampToStr(times));
        if (date == null) {
            return null;
        }
        long nowTime = Calendar.getInstance().getTimeInMillis();
        long comparisonTime = date.getTime();

        long disTime = (nowTime - comparisonTime) / 1000L;
        if (disTime > 31536000L) {
            desTimeS = String.valueOf((int) (disTime / 31536000L)) + "年前";
        }else if (disTime > 86400L) {
            desTimeS = String.valueOf((int) (disTime / 86400L)) + "天前";
        }else if (disTime > 3600L) {
            desTimeS = String.valueOf((int) (disTime / 3600L)) + "小时前";

        }else if (disTime > 60L) {
            desTimeS = String.valueOf((int) (disTime / 3600L)) + "分前";

        }else if (disTime > 1L) {
            desTimeS = String.valueOf(disTime) + "秒前";

        } else {
            desTimeS = "刚刚";
        }
        return desTimeS;
    }


    /**
     * 获得时区
     * @return
     */
    public static String getTimeZone() {

        return TimeZone.getDefault().getID();
    }

    /**
     * 获取某个时间的状态
     * @param times
     * @return
     */
    public static String getTopJZTime(long times) {

        long nowTime = Calendar.getInstance().getTimeInMillis();
        long disTime = (times - nowTime) / 1000L;

        String desTimeS;

        if (disTime <= 0L) {
            desTimeS = "已截止";
        } else if (disTime < 60L) {
            desTimeS = "即将截止";
        } else if (disTime < 3600L) {
            long fen = disTime / 60L;
            long dis = 60L * fen;
            long miao = disTime - dis;
            desTimeS = "剩余" + fen + "分" + miao + "秒";
        } else if (disTime < 86400L) {
            long hour = disTime / 60L / 60L;
            long dis = 60L * hour * 60L;
            long fen = (disTime - dis) / 60L;
            desTimeS = "剩余" + hour + "小时" + fen + "分";
        } else if (disTime < 2592000L) {
            long day = disTime / 60L / 60L / 24L;
            long dis = 60L * day * 60L * 24L;
            long hour = (disTime - dis) / 60L / 60L;
            desTimeS = "剩余" + day + "天零" + hour + "小时";
        } else if (disTime < 31104000L) {
            long month = disTime / 60L / 60L / 24L / 30L;
            long dis = 60L * month * 60L * 24L * 30L;
            long day = (disTime - dis) / 60L / 60L / 24L;
            desTimeS = "剩余" + month + "月" + day + "天";
        } else {
            desTimeS = "还有很久";
        }

        return desTimeS;
    }


    /**
     * 转换miao
     * @param second
     * @return
     */
    public static String formatSecond(Object second){
        String  html="0秒";
        if(second!=null){
            Double s=(Double) second;
            String format;
            Object[] array;
            Integer hours =(int) (s/(60*60));
            Integer minutes = (int) (s/60-hours*60);
            Integer seconds = (int) (s-minutes*60-hours*60*60);
            if(hours>0){
                format="%1$,d时%2$,d分%3$,d秒";
                array=new Object[]{hours,minutes,seconds};
            }else if(minutes>0){
                format="%1$,d分%2$,d秒";
                array=new Object[]{minutes,seconds};
            }else{
                format="%1$,d秒";
                array=new Object[]{seconds};
            }
            html= String.format(format, array);
        }

        return html;

    }
    public static String formatSecond02(Object second){
        String  html="";
        if(second!=null){
            Integer s=(Integer) second;
            String format;
            Object[] array;
            Integer hours =(int) (s/(60*60));
            Integer minutes = (int) (s/60-hours*60);
            if(hours>0){
                format="%d小时%d分钟";
                array=new Object[]{hours,minutes};
            }else{
                format="%d分钟";
                array=new Object[]{minutes};
            }
            html= String.format(format, array);
        }

        return html;

    }

    /**
     * 添加日期---计算加多少天后得日期
     * @param date
     * @param count
     * @return
     */
    public static Date addDays(Date date, int count) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, count);
        return calendar.getTime();
    }


    public static int getDay(Date Date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date);
        return calendar.get(Calendar.DATE);
    }
    /**
     * 添加月份
     * @param date
     * @param count
     * @return
     */
    public static Date addMonths(Date date, int count) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, count);
        return calendar.getTime();
    }

    /**
     * 计算距离今天时间--天
     * @param date
     * @return
     */
    public static int between(Date date) {

        return (int) ((new Date().getTime() - date.getTime()) / 86400000L);
    }

    public static int between(Date date1, Date date2) {

        return (int) ((date1.getTime() - date2.getTime()) / 86400000L);
    }

    public static boolean bigger(Date date1, Date date2) {

        if (date1.getTime() > date2.getTime()) {
            return true;
        } else {
            return false;
        }
    }


//    public static Date defaultTargetDate() {
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
//        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
//        return calendar.getTime();
//    }


    public static String today() {

        return nextDay(new Date(), 0);
    }

    public static String tomorrow() {

        return nextDay(new Date(), 1);
    }

    public static String yesterday() {

        return previousDay(new Date(), 1);
    }

    public static String nextDay(String time, int count) {

        return nextDay(parseString(time), count);
    }

    public static String nextDay(Date date, int count) {

        return format(addDays(date, count));
    }

    public static String previousDay(String time, int count) {

        return previousDay(parseString(time), count);
    }

    public static String previousDay(Date date, int count) {

        return format(addDays(date, count));
    }

    /**
     * 获取年龄
     * @param date
     * @return
     * @throws Exception
     */
    public static int getAge(Date date)
            throws Exception {

        Calendar calendar = Calendar.getInstance();
        if (calendar.before(date))
            throw new IllegalArgumentException("date cannot after today");
        int year = calendar.get(Calendar.YEAR);
        int mouth = 1 + calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.setTime(date);
        int ageY = calendar.get(Calendar.YEAR);
        int ageM = calendar.get(Calendar.MONTH);
        int ageD = calendar.get(Calendar.DATE);
        int disY = year - ageY;
        if (mouth <= ageM) {
            if (mouth != ageM) {
                disY--;
            }else if (day < ageD) {
                disY--;
            }
        }
        return disY;
    }

    /**
     * 获得当前的时间
     * @return
     */
    public static String getCurrentDateTimeString() {

        return format(Calendar.getInstance().getTime(), "yy-MM-dd HH:mm");
    }

    /**
     * 当前时间字符串--到秒
     * @return
     */
    public static String getCurrentTimeString() {
        return format(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentDateString() {
        return format(Calendar.getInstance().getTime(), "yyyy-MM-dd");
    }


    public static String getCurrentTimeMills() {

        return format(Calendar.getInstance().getTime(), "yy-MM-dd HH:mm:ss.SSS");
    }

    public static Date getData() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 基本---年-月-日
     * @param date
     * @return
     */
    public static String format(Date date) {

        return format(date, "yyyy-MM-dd");
    }

    /**
     * 格式化日期
     * @param date
     * @param partnString
     * @return
     */
    public static String format(Date date, String partnString) {

        SimpleDateFormat format = new SimpleDateFormat(partnString, Locale.getDefault());
        String str;
        try {
            str = format.format(date);
        } catch (Exception e) {
            str = format.format(new Date());
        }
        return str;
    }

    public static String formatString(String date, String partnString) {

        Date time = parseFromString(date, "yyyy-MM-dd");
        return new SimpleDateFormat(partnString, Locale.getDefault()).format(time);
    }

    /**
     * 字符串解析成--日期date
     * @param timeS
     * @param partnString
     * @return
     */
    public static Date parseFromString(String timeS, String partnString) {

        SimpleDateFormat format = new SimpleDateFormat(partnString, Locale.getDefault());
        Date date;
        try {
            date = format.parse(timeS);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date;
    }

    public static Date parseString(String timeS) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date;
        try {
            date = format.parse(timeS);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date;
    }

}
