package com.randeng.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private DateUtils() {
    }

    public static String format(Date date) {
        return format.format(date);
    }

    public static Date startOfDay(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date endOfDay(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date startOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date endOfWeek(Date date) {
        Date result = startOfWeek(date);
        result.setTime(result.getTime() + 7 * 24 * 3600 * 1000L - 1000L);
        return result;
    }

    public static Date daysAgo(int days) {
        Date date = new Date();
        date.setTime(date.getTime() - ((long) days) * 24L * 3600L * 1000L - 1000L);
        return date;
    }

    public static Date startOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date endOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date startOfNextMonth(Date date) {
        return new Date(DateUtils.endOfMonth(date).getTime() + 1000);
    }

    /**
     * Gets the weekday of the given date.
     * The first day of a week is Sunday and the index starts with 1. So, Sunday = 1, Monday = 2, ..., Saturday = 7.
     * @param date the date
     * @return the integer indicates the weekday
     */
    public static int weekday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean isWeekday(String day) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int dayOfWeek = weekday(format.parse(day));
        if (dayOfWeek == 1 || dayOfWeek == 7) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(weekday(format.parse("2018-11-05")));
        System.out.println(weekday(format.parse("2018-11-08")));
        System.out.println(weekday(format.parse("2018-11-09")));
        System.out.println(weekday(format.parse("2018-11-10")));
        System.out.println(weekday(format.parse("2018-11-11")));
//        System.out.println(endOfMonth(format.parse("2018-08-31")));
//        System.out.println(new Date(endOfMonth(format.parse("2018-08-31")).getTime() + 1000));
        System.out.println(DateUtils.format.parse("2020-02-01 00:00:00").getTime());
    }

}
