package com.animsh.newsdeap.ui.news;

import androidx.annotation.NonNull;

import com.animsh.newsdeap.data.NewsItem;
import com.animsh.newsdeap.databinding.ItemNewsImageBinding;
import com.animsh.newsdeap.databinding.ItemNewsTextBinding;

/**
 * Created by animsh on 1/11/2021.
 */
public class TextViewHolder extends BaseViewHolder {

    ItemNewsTextBinding itemNewsTextBinding;

    public TextViewHolder(@NonNull ItemNewsTextBinding itemNewsTextBinding) {
        super(itemNewsTextBinding.getRoot());
        this.itemNewsTextBinding = itemNewsTextBinding;
    }

    @Override
    public void bindData(NewsItem item) {
        itemNewsTextBinding.setNewsItemText(item);
    }

    @Override
    public ItemNewsTextBinding getItemNewsTextBinding() {
        return null;
    }

    @Override
    public ItemNewsImageBinding getItemNewsImageBinding() {
        return null;
    }
}
