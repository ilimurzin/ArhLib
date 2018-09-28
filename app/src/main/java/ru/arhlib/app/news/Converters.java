package ru.arhlib.app.news;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.room.TypeConverter;

public class Converters {
    private static DateFormat simpleDateFormat;
    private static DateFormat dateFormat;

    public static String dateParse(String dateToParse) {
        if (simpleDateFormat == null || dateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            dateFormat = DateFormat.getDateInstance();
        }
        try {
            return dateFormat.format(simpleDateFormat.parse(dateToParse));
        } catch (ParseException e) {
            return null;
        }
    }

    @TypeConverter
    public static String toJson(Media[] media) {
        return new Gson().toJson(media);
    }

    @TypeConverter
    public static Media[] fromJson(String url) {
        return new Gson().fromJson(url, Media[].class);
    }
}
