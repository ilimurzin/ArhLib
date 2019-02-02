package ru.arhlib.app.news;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateParser {

    public static String parse(String dateToParse) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(dateToParse);
        } catch (ParseException e) {
            return null;
        }
        if (parsedDate != null) {
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            int currentDay = calendar.get(Calendar.DAY_OF_YEAR);
            calendar.setTime(parsedDate);
            int parsedYear = calendar.get(Calendar.YEAR);
            int parsedDay = calendar.get(Calendar.DAY_OF_YEAR);

            if (currentYear == parsedYear) {
                if (currentDay == parsedDay) {
                    dateFormat.applyPattern("HH:mm");
                    return dateFormat.format(parsedDate);
                } else {
                    dateFormat.applyPattern("dd MMMM");
                    return dateFormat.format(parsedDate);
                }
            } else {
                dateFormat.applyPattern("dd MMMM yyyy");
                return dateFormat.format(parsedDate);
            }
        } else {
            return null;
        }
    }
}
