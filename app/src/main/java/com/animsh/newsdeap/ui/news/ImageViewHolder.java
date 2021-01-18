package com.animsh.newsdeap.ui.news;

import androidx.annotation.NonNull;

import com.animsh.newsdeap.data.Article;
import com.animsh.newsdeap.databinding.ItemNewsImageBinding;
import com.animsh.newsdeap.databinding.ItemNewsTextBinding;

/**
 * Created by animsh on 1/11/2021.
 */
public class ImageViewHolder extends BaseViewHolder {

    ItemNewsImageBinding itemNewsImageBinding;

    public ImageViewHolder(@NonNull ItemNewsImageBinding itemNewsImageBinding) {
        super(itemNewsImageBinding.getRoot());
        this.itemNewsImageBinding = itemNewsImageBinding;
    }

    @Override
    public void bindData(Article item) {
        itemNewsImageBinding.setNewsItemImage(item);
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
