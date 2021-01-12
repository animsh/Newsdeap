package com.animsh.newsdeap.ui.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.data.NewsItem;
import com.animsh.newsdeap.databinding.ItemNewsImageBinding;
import com.animsh.newsdeap.databinding.ItemNewsTextBinding;

/**
 * Created by animsh on 1/12/2021.
 */
public class NewsListAdapter extends ListAdapter<NewsItem, BaseViewHolder> {

    public static final int VIEW_TYPE_NEWS_TEXT = 0;
    public static final int VIEW_TYPE_NEWS_IMAGE = 1;
    public static final int VIEW_TYPE_NEWS_VIDEO = 2;
    OnNewsItemClickEvent listener;

    public NewsListAdapter(@NonNull DiffUtil.ItemCallback<NewsItem> diffCallback) {
        super(diffCallback);
    }

    public void setOnNewsItemClickListener(OnNewsItemClickEvent listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
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
    }

    public interface OnNewsItemClickEvent {
        void onItemTextClick();

        void onItemImageClick();

        void onItemLongClick();
    }
}
