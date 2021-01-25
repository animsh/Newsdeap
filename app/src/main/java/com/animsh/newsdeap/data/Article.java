package com.animsh.newsdeap.data;

import java.io.Serializable;

/**
 * Created by animsh on 1/18/2021.
 */
public class Article implements Serializable {
    private final NewsSource source; // News Source
    private final String author; // News Author
    private final String title; // News Title
    private final String description; // News Description
    private final String url; // News URL
    private final String urlToImage; // News Image URL
    private final String publishedAt; // News Published Date
    private final String content; // News Content

    public Article(NewsSource source, String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public NewsSource getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public boolean getFav() {
        return false;
    }
}
