package ru.arhlib.app.news;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

class DateFormatter {
    static final String WP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    static String format(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(WP_PATTERN, Locale.getDefault());

        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
        if (parsedDate == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentDay = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(parsedDate);
        int parsedYear = calendar.get(Calendar.YEAR);
        int parsedDay = calendar.get(Calendar.DAY_OF_YEAR);

        String pattern;
        if (currentYear == parsedYear) {
            if (currentDay == parsedDay) {
                pattern = "HH:mm";
            } else {
                pattern = "dd MMMM";
            }
        } else {
            pattern = "dd MMMM yyyy";
        }

        dateFormat.applyPattern(pattern);
        return dateFormat.format(parsedDate);
    }
}
