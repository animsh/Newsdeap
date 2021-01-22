package com.animsh.newsdeap.ui.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.data.Country;
import com.animsh.newsdeap.databinding.ItemCountryBinding;

/**
 * Created by animsh on 1/22/2021.
 */
public class CountryListAdapter extends ListAdapter<Country, BaseCountryViewHolder> {

    public CountryListAdapter(@NonNull DiffUtil.ItemCallback<Country> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public BaseCountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCountryBinding itemCountryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_country, parent, false
        );
        return new CountryViewHolder(itemCountryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseCountryViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }
}
