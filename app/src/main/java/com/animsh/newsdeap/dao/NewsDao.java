package com.animsh.newsdeap.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.animsh.newsdeap.entities.Article;

import java.util.List;

/**
 * Created by animsh on 1/25/2021.
 */
@Dao
public interface NewsDao {

    @Query("SELECT * FROM news ORDER BY id DESC")
    List<Article> getAllArticles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticle(Article note);

    @Delete
    void deleteArticle(Article note);
}
