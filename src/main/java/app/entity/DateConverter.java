package app.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {

    private static SimpleDateFormat formatForDate = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat formatForTime = new SimpleDateFormat("HH:mm");

    public static String millsToDate(Long mills) {
        return formatForDate.format(mills);
    }

    public static String millsToTime(Long mills) {
        return formatForTime.format(mills);
    }

    public static long stringToMills(String time, String date) throws ParseException {
        try {
            return formatForDate.parse(time + " " + date).getTime();
        } catch (ParseException ex) {
            return 0;
        }
    }

    public static long hour(int hour) {
        return 1000 * 60 * 60 * hour;
    }

}