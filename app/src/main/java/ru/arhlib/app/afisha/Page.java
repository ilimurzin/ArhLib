package ru.arhlib.app.afisha;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

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
