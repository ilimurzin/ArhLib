package ru.arhlib.app.news;

import com.google.gson.annotations.SerializedName;

import androidx.room.Embedded;

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

            public static class Size {
                @SerializedName("source_url")
                String url;
            }
        }
    }
}
