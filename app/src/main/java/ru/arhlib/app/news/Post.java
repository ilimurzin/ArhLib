package ru.arhlib.app.news;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.text.Html;

import com.google.gson.annotations.SerializedName;

@Entity
public class Post {
    @PrimaryKey
    public int id;
    @SerializedName("date")
    public String date;
    @SerializedName("link")
    public String link;
    @SerializedName("title")
    @Embedded(prefix = "title")
    public Rendered title;
    @SerializedName("content")
    @Embedded(prefix = "content")
    public Rendered content;
    @SerializedName("excerpt")
    @Embedded(prefix = "excerpt")
    public Rendered excerpt;
    @SerializedName("_embedded")
    @Embedded
    public Embed embed;

    public Post(int id, String date, String link, Rendered title, Rendered content, Rendered excerpt, Embed embed) {
        this.id = id;
        this.date = date;
        this.link = link;
        this.title = title;
        this.content = content;
        this.excerpt = excerpt;
        this.embed = embed;
    }

    public String getDate() {
        return Converters.dateParse(date);
    }

    public String getTitle() {
        return Html.fromHtml(title.rendered).toString().trim();
    }

    public String getContent() {
        return content.rendered;
    }

    public String getExcerpt() {
        return Html.fromHtml(excerpt.rendered).toString().trim();
    }

    public String getImageUrl() {
        if (embed.featuredMedia[0] != null) {
            if (embed.featuredMedia[0].mediaDetails.sizes.mediumLarge != null) {
                return embed.featuredMedia[0].mediaDetails.sizes.mediumLarge.url;
            } else {
                return embed.featuredMedia[0].sourceUrl;
            }
        }
        return null;
    }

    public static class Rendered {
        String rendered;
    }

    @TypeConverters(Converters.class)
    public static class Embed {
        @SerializedName("wp:featuredmedia")
        Media[] featuredMedia;
    }
}
