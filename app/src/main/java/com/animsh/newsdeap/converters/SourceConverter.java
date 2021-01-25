package com.animsh.newsdeap.converters;

import androidx.room.TypeConverter;

import com.animsh.newsdeap.data.NewsSource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by animsh on 1/25/2021.
 */
public class SourceConverter {

    @TypeConverter
    public static NewsSource fromString(String value) {
        Type sourceType = new TypeToken<NewsSource>() {
        }.getType();
        return new Gson().fromJson(value, sourceType);
    }

    @TypeConverter
    public static String fromSource(NewsSource source) {
        Gson gson = new Gson();
        String json = gson.toJson(source);
        return json;
    }
}
