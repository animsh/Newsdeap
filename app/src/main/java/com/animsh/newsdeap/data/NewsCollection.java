package com.animsh.newsdeap.data;

import java.util.List;

/**
 * Created by animsh on 1/18/2021.
 */
public class NewsCollection {
    private String status;
    private int totalResults;
    private List<Article> articles;

    public NewsCollection(String status, int totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
