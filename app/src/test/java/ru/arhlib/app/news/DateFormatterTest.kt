package ru.arhlib.app.news

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateFormatterTest {
    @Test
    fun testToday() {
        assertDateMatchFormat(Date(), "HH:mm")
    }

    private fun assertDateMatchFormat(date: Date, format: String) {
        val wpDate = SimpleDateFormat(DateFormatter.WP_PATTERN).format(date)
        val expectedResult = SimpleDateFormat(format).format(date)
        assertEquals(expectedResult, DateFormatter.format(wpDate))
    }

    @Test
    fun testWeekAgo() {
        val calendar: Calendar = GregorianCalendar()
        calendar.add(Calendar.WEEK_OF_YEAR, -1)
        assertDateMatchFormat(calendar.time, "dd MMMM")
    }

    @Test
    fun testYearAgo() {
        val calendar: Calendar = GregorianCalendar()
        calendar.add(Calendar.YEAR, -1)
        assertDateMatchFormat(calendar.time, "dd MMMM yyyy")
    }
}
