package ru.arhlib.app.data;

import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Webservice sInstance;

    public static Webservice getInstance() {
        return getInstance(HttpUrl.get("https://arhlib.ru/wp-json/"));
    }

    public static Webservice getInstance(HttpUrl httpUrl) {
        if (sInstance == null) {
            synchronized (Api.class) {
                if (sInstance == null) {
                    sInstance = new Retrofit.Builder()
                            .baseUrl(httpUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(Webservice.class);
                }
            }
        }
        return sInstance;
    }
}
