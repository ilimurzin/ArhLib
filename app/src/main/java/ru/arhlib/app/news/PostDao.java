package ru.arhlib.app.news;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PostDao {
    @Insert
    void save(List<Post> posts);

    @Query("SELECT * FROM post ORDER BY date DESC") //
    LiveData<List<Post>> load();

    @Query("DELETE FROM post")
    void delete();
}
