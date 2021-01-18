package com.animsh.newsdeap.ui.news;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.animsh.newsdeap.data.Article;

/**
 * Created by animsh on 1/12/2021.
 */
public class DiffUtilNewsItemCallback extends DiffUtil.ItemCallback<Article> {

    @Override
    public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.getTitle().equals(newItem.getTitle());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.getTitle().equals(newItem.getTitle());
    }
}
