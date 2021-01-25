package com.animsh.newsdeap.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.animsh.newsdeap.data.NewsSource;

import java.io.Serializable;

/**
 * Created by animsh on 1/25/2021.
 */
@Entity(tableName = "news")
public class Article implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "source")
    private NewsSource source; // News Source

    @ColumnInfo(name = "author")
    private String author; // News Author

    @ColumnInfo(name = "title")
    private String title; // News Title

    @ColumnInfo(name = "description")
    private String description; // News Description

    @ColumnInfo(name = "url")
    private String url; // News URL

    @ColumnInfo(name = "urlToImage")
    private String urlToImage; // News Image URL

    @ColumnInfo(name = "publishedAt")
    private String publishedAt; // News Published Date

    @ColumnInfo(name = "content")
    private String content; // News Content

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NewsSource getSource() {
        return source;
    }

    public void setSource(NewsSource source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }
}
