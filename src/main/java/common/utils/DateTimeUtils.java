package common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTimeUtils {

    public static String getTodayDate() {
        return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    public static LocalDate getInWeeksDate(int weeks) {
        return LocalDate.now().plus(weeks, ChronoUnit.WEEKS);
    }

    public static String getSystemTime() {
        return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
    }
}
