package ru.arhlib.app.news;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static ru.arhlib.app.news.DateFormatter.WP_PATTERN;

public class DateFormatterTest {

    @Test
    public void testToday() {
        assertDateMatchFormat(new Date(), "HH:mm");
    }

    private void assertDateMatchFormat(Date date, String format) {
        String wpDate = (new SimpleDateFormat(WP_PATTERN)).format(date);
        String expectedResult = (new SimpleDateFormat(format)).format(date);

        assertEquals(expectedResult, DateFormatter.format(wpDate));
    }

    @Test
    public void testWeekAgo() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.WEEK_OF_YEAR, -1);

        assertDateMatchFormat(calendar.getTime(), "dd MMMM");
    }

    @Test
    public void testYearAgo() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.YEAR, -1);

        assertDateMatchFormat(calendar.getTime(), "dd MMMM yyyy");
    }
}
