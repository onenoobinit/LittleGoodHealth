package com.mobile.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenliangzhi
 * @date 2018/5/24
 * @describe
 */

public class DateUtils {
    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param date1 the first date
     * @param date1 the second date
     * @return true <br/>false
     */
    public static boolean isDateOneBigger(Date date1, Date date2) {
        boolean isBigger = false;
        if ((date1.getTime() - date2.getTime()) > 800) {
            if (date1.getTime() > date2.getTime()) {
                isBigger = true;
            } else if (date1.getTime() <= date1.getTime()) {
                isBigger = false;
            }
        } else {
            return false;
        }
        return isBigger;
    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br/>false
     */
    public static boolean isDate2Bigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = false;
        } else if (dt1.getTime() <= dt2.getTime()) {
            isBigger = true;
        }
        return isBigger;
    }
}
