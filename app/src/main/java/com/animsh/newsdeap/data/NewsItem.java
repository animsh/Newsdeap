package com.animsh.newsdeap.data;

import java.util.Date;

/**
 * Created by animsh on 1/11/2021.
 */
public class NewsItem {
    int id;
    String title;
    String username;
    String newsImg;
    String userImg;
    String content;
    String bgColor;
    Date creationDate;
    int viewType = 0;
    String videoUrl;
    boolean isFav = false;

    public NewsItem() {
    }

    public NewsItem(int id, String title, String username, String newsImg, String userImg, String content, String bgColor, Date creationDate, int viewType, String videoUrl, boolean isFav) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.newsImg = newsImg;
        this.userImg = userImg;
        this.content = content;
        this.bgColor = bgColor;
        this.creationDate = creationDate;
        this.viewType = viewType;
        this.videoUrl = videoUrl;
        this.isFav = isFav;
    }

    public NewsItem(int id, String title, String username, String newsImg, String userImg, String content, String bgColor, Date creationDate, int viewType) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.newsImg = newsImg;
        this.userImg = userImg;
        this.content = content;
        this.bgColor = bgColor;
        this.creationDate = creationDate;
        this.viewType = viewType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", newsImg='" + newsImg + '\'' +
                ", userImg='" + userImg + '\'' +
                ", content='" + content + '\'' +
                ", bgColor='" + bgColor + '\'' +
                ", creationDate=" + creationDate +
                ", viewType=" + viewType +
                ", videoUrl='" + videoUrl + '\'' +
                ", isFav=" + isFav +
                '}';
    }
}
