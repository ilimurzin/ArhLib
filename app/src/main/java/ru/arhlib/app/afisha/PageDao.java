package ru.arhlib.app.afisha;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Page page);

    @Query("SELECT * FROM page WHERE id = :pageId")
    LiveData<Page> load(int pageId);
}
