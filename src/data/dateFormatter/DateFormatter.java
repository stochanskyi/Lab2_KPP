package data.dateFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateFormatter {

    private static DateFormatter instance = null;

    public static DateFormatter getInstance() {
        if (instance == null) {
            instance = new DateFormatter();
        }
        return instance;
    }

    private static final String DATE_PATTERN = "dd.MM.yyyy";

    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

    public Date parseDate(String inputDate) throws ParseException {
        return dateFormat.parse(inputDate);
    }

    public String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public long daysBetweenTimestamps(long first, long second) {
        long diff = Math.abs(first - second);
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

}
