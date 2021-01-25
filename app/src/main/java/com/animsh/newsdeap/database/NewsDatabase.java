package com.animsh.newsdeap.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.animsh.newsdeap.converters.SourceConverter;
import com.animsh.newsdeap.dao.NewsDao;
import com.animsh.newsdeap.entities.Article;

/**
 * Created by animsh on 1/25/2021.
 */
@Database(entities = Article.class, version = 1, exportSchema = false)
@TypeConverters({SourceConverter.class})
public abstract class NewsDatabase extends RoomDatabase {

    public static NewsDatabase newsDatabase;

    public static synchronized NewsDatabase getNewsDatabase(Context context) {
        if (newsDatabase == null) {
            newsDatabase = Room.databaseBuilder(
                    context,
                    NewsDatabase.class,
                    "news_db"
            ).build();
        }
        return newsDatabase;
    }

    public abstract NewsDao newsDao();
}
