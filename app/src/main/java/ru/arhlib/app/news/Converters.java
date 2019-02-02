package ru.arhlib.app.news;

import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public static String toJson(Media[] media) {
        return new Gson().toJson(media);
    }

    @TypeConverter
    public static Media[] fromJson(String url) {
        return new Gson().fromJson(url, Media[].class);
    }
}
