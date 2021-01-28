package ru.arhlib.app.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.arhlib.app.news.Post;
import ru.arhlib.app.news.PostDao;

@Database(entities = {Post.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return sInstance;
    }

    public abstract PostDao postDao();
}
