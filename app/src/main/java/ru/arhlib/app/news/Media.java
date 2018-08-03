package ru.arhlib.app.news;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class Media {
    @SerializedName("source_url")
    String sourceUrl;
    @SerializedName("media_details")
    @Embedded
    MediaDetails mediaDetails;
    public static class MediaDetails {
        @Embedded
        Sizes sizes;
        public static class Sizes {
            @SerializedName("medium_large")
            @Embedded(prefix = "medium_large")
            Size mediumLarge;
            @Embedded
            Size thumbnail;
            public static class Size {
                @SerializedName("source_url")
                String url;
            }
        }
    }
}
