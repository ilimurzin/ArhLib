package ru.arhlib.app.afisha;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface PageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Page page);

    @Query("SELECT * FROM page WHERE id = :pageId")
    LiveData<Page> load(int pageId);
}
