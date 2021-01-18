package com.animsh.newsdeap.util;


import com.animsh.newsdeap.data.NewsCollection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiCall {
    @GET("top-headlines")
    Call<NewsCollection> getTopHeadLines(@Query("country") String country, @Query("apiKey") String apiKey);
}
