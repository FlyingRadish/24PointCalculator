package org.houxg.monkeyhey.util;

import java.util.Calendar;

public class TimeTool {

    public static String getHHmmss(Calendar calendar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(calendar.get(Calendar.HOUR_OF_DAY)).append(":");
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        if (minute < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(minute).append(":");
        if(second < 10){
            stringBuilder.append("0");
        }
        stringBuilder.append(second);
        return stringBuilder.toString();
    }

    public static String getyyyyMMdd(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        StringBuilder builder = new StringBuilder();
        builder.append(calendar.get(Calendar.YEAR)).append("-");
        if (month < 10) {
            builder.append("0");
        }
        builder.append(month).append("-");
        if (day < 10) {
            builder.append("0");
        }
        builder.append(day);
        return builder.toString();
    }

    public static Calendar getCalendarFromInt(int yyyyMMdd) {
        int day = yyyyMMdd % 100;
        int month = (yyyyMMdd / 100) % 100;
        int year = yyyyMMdd / 10000;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day,0,0);
        return calendar;
    }
}
