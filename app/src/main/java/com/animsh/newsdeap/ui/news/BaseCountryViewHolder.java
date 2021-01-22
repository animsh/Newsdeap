package com.animsh.newsdeap.ui.news;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.newsdeap.data.Country;

/**
 * Created by animsh on 1/22/2021.
 */
abstract class BaseCountryViewHolder extends RecyclerView.ViewHolder {
    public BaseCountryViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindData(Country item);
}
