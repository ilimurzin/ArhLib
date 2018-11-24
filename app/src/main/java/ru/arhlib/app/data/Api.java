package ru.arhlib.app.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Webservice sInstance;

    public static Webservice getInstance() {
        if (sInstance == null) {
            synchronized (Api.class) {
                if (sInstance == null) {
                    sInstance = new Retrofit.Builder()
                            .baseUrl("https://arhlib.ru/wp-json/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(Webservice.class);
                }
            }
        }
        return sInstance;
    }
}
