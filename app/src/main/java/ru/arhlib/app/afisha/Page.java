package ru.arhlib.app.afisha;

import com.google.gson.annotations.SerializedName;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Page {
    @PrimaryKey
    public Integer id;
    @SerializedName("content")
    @Embedded
    public Rendered content;

    public Page(Integer id, Rendered content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content.rendered;
    }

    static class Rendered {
        String rendered;
    }
}
