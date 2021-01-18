package com.animsh.newsdeap.data;

/**
 * Created by animsh on 1/18/2021.
 */
public class Article {
    private NewsSource source; // News Source
    private String author; // News Author
    private String title; // News Title
    private String description; // News Description
    private String url; // News URL
    private String urlToImage; // News Image URL
    private String publishedAt; // News Published Date
    private String content; // News Content

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
}
