package com.animsh.newsdeap.ui.news;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.animsh.newsdeap.data.Country;

/**
 * Created by animsh on 1/12/2021.
 */
public class DiffUtilCountryItemCallback extends DiffUtil.ItemCallback<Country> {

    @Override
    public boolean areItemsTheSame(@NonNull Country oldItem, @NonNull Country newItem) {
        return oldItem.getCountry().equals(newItem.getCountry());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Country oldItem, @NonNull Country newItem) {
        return oldItem.getCountry().equals(newItem.getCountry());
    }
}
