package ru.arhlib.app.news;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<Post> posts);

    @Query("SELECT * FROM post ORDER BY date DESC")
    LiveData<List<Post>> load();
}
