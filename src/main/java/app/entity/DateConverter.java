package app.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String myDate = date + " " + time;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dated = sdf.parse(myDate);
        return dated.getTime();
    }

}