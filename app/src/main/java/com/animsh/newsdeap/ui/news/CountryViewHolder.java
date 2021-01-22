package com.animsh.newsdeap.ui.news;

import androidx.annotation.NonNull;

import com.animsh.newsdeap.data.Country;
import com.animsh.newsdeap.databinding.ItemCountryBinding;

/**
 * Created by animsh on 1/22/2021.
 */
public class CountryViewHolder extends BaseCountryViewHolder {
    ItemCountryBinding itemCountryBinding;

    public CountryViewHolder(@NonNull ItemCountryBinding itemCountryBinding) {
        super(itemCountryBinding.getRoot());
        this.itemCountryBinding = itemCountryBinding;
    }

    @Override
    public void bindData(Country item) {
        itemCountryBinding.setCountryItem(item);
    }
}
