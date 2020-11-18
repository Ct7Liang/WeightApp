package com.ct7liang.weight.utils;

import android.util.Log;

import com.ct7liang.weight.bean.MonthDays;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    /**
     * 获取当前日期
     * @return yyyy年MM月dd日 字符串
     */
    public static String getCurrentDateStr(){
        Date d = new Date(System.currentTimeMillis());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        return sf.format(d);
    }

    /**
     * 获取当前日期数组
     * @return [年, 月, 日] 该数组为int数组
     */
    public static int[] getCurrentDateArr(){
        Date d = new Date(System.currentTimeMillis());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = sf.format(d);
        String[] split = time.split("-");
        return new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])};
    }

    /**
     * 获取某个月的天数据
     * @return 天集合
     */
    public static MonthDays getMonthDays(MonthDays monthDays, int y, int m, int d) {
        int num = 0;
        if(m == 2){
            if(y % 4 == 0 && y % 100 != 0){
                num = 29;
            }else if(y % 400 == 0){
                num = 29;
            }else{
                num = 28;
            }
        }else if( m == 4 || m == 6 || m == 9 || m == 11 ){
            num = 30;
        }else{
            num = 31;
        }

        int week_index = 1;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date parse = null;
        try {
            parse = sf.parse(y + "-" + m + "-" + 1);
            Calendar cal = Calendar.getInstance();
            cal.setTime(parse);
            week_index = cal.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        monthDays.weekIndex = week_index - 1  == 0 ? 7 : week_index - 1 ;
        if(d > num){
            monthDays.currentIndex = num - 1;
        }else{
            monthDays.currentIndex = d - 1;
        }
        monthDays.list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            MonthDays.Day day = new MonthDays.Day(i+1, 0);
            monthDays.list.add(day);
        }
        return monthDays;
    }

}
