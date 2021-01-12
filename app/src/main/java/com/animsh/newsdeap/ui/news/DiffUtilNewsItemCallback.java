package com.animsh.newsdeap.ui.news;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.animsh.newsdeap.data.NewsItem;

/**
 * Created by animsh on 1/12/2021.
 */
public class DiffUtilNewsItemCallback extends DiffUtil.ItemCallback<NewsItem> {

    @Override
    public boolean areItemsTheSame(@NonNull NewsItem oldItem, @NonNull NewsItem newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull NewsItem oldItem, @NonNull NewsItem newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
