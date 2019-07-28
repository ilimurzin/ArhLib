package ru.arhlib.app.news;

import android.text.Html;

import com.google.gson.annotations.SerializedName;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import ru.arhlib.app.actual.ActualItem;

@Entity
public class Post implements ActualItem {
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
        return DateParser.parse(date);
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
            try {
                return embed.featuredMedia[0].mediaDetails.sizes.mediumLarge.url;
            } catch (NullPointerException e) {
                return embed.featuredMedia[0].sourceUrl;
            }
        }
        return null;
    }

    @Override
    public int getType() {
        return ActualItem.TYPE_POST;
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
