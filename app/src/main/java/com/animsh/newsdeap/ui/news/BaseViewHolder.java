package com.animsh.newsdeap.ui.news;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.newsdeap.data.Article;
import com.animsh.newsdeap.databinding.ItemNewsImageBinding;
import com.animsh.newsdeap.databinding.ItemNewsTextBinding;

/**
 * Created by animsh on 1/11/2021.
 */
abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindData(Article item);

    // for shared view animation
    public abstract ItemNewsTextBinding getItemNewsTextBinding();

    public abstract ItemNewsImageBinding getItemNewsImageBinding();
}
