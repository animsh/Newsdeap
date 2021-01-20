package com.animsh.newsdeap.ui.news;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.data.Article;
import com.animsh.newsdeap.databinding.ItemNewsImageBinding;
import com.animsh.newsdeap.databinding.ItemNewsTextBinding;
import com.animsh.newsdeap.ui.NewsDetailsFragment;

/**
 * Created by animsh on 1/12/2021.
 */
public class NewsListAdapter extends ListAdapter<Article, BaseViewHolder> {

    public static final int VIEW_TYPE_NEWS_TEXT = 0;
    public static final int VIEW_TYPE_NEWS_IMAGE = 1;
    OnNewsItemClickEvent listener;

    public NewsListAdapter(@NonNull DiffUtil.ItemCallback<Article> diffCallback) {
        super(diffCallback);
    }

    public void setOnNewsItemClickListener(OnNewsItemClickEvent listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getUrlToImage() != null) {
            return 1;
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NEWS_TEXT:
                ItemNewsTextBinding itemNewsTextBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_news_text, parent, false
                );
                return new TextViewHolder(itemNewsTextBinding);
            case VIEW_TYPE_NEWS_IMAGE:
                ItemNewsImageBinding itemNewsImageBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_news_image, parent, false
                );
                return new ImageViewHolder(itemNewsImageBinding);
            default:
                ItemNewsTextBinding defaultItemNewsTextBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_news_text, parent, false
                );
                return new TextViewHolder(defaultItemNewsTextBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindData(getItem(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), NewsDetailsFragment.class);
                intent.putExtra("Article", getItem(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    public interface OnNewsItemClickEvent {
        void onItemTextClick(Article item);

        void onItemImageClick(Article item);

        void onItemLongClick();
    }
}
