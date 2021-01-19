package com.animsh.newsdeap.data;

import java.io.Serializable;

/**
 * Created by animsh on 1/18/2021.
 */
public class NewsSource implements Serializable {
    private String id;
    private String name;

    public NewsSource(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
