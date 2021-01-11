package com.animsh.newsdeap.ui.news;

import android.view.View;

import androidx.annotation.NonNull;

import com.animsh.newsdeap.data.NewsItem;
import com.animsh.newsdeap.databinding.ItemNewsImageBinding;
import com.animsh.newsdeap.databinding.ItemNewsTextBinding;

/**
 * Created by animsh on 1/11/2021.
 */
public class ImageViewHolder extends BaseViewHolder {
    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(NewsItem item) {

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
