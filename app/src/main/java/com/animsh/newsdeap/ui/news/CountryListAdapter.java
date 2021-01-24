package com.animsh.newsdeap.ui.news;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.data.Country;
import com.animsh.newsdeap.databinding.ItemCountryBinding;

import static android.content.Context.MODE_PRIVATE;
import static com.animsh.newsdeap.ui.MainActivity.currentCountry;
import static com.animsh.newsdeap.ui.SettingFragment.dialogCountry;
import static com.animsh.newsdeap.ui.SettingFragment.txtCurrentCountry;

/**
 * Created by animsh on 1/22/2021.
 */
public class CountryListAdapter extends ListAdapter<Country, BaseCountryViewHolder> {

    public int mSelectedItem = -1;

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
        if (getItem(position).getAbbreviation().toLowerCase().equals(currentCountry)) {
            ((RadioButton) holder.itemView).setChecked(true);
        } else {
            ((RadioButton) holder.itemView).setChecked(position == mSelectedItem);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = holder.itemView.getContext().getSharedPreferences(String.valueOf(R.string.app_name), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                mSelectedItem = holder.getAdapterPosition();
                notifyItemRangeChanged(0, getItemCount());
                currentCountry = getItem(position).getAbbreviation().toLowerCase();
                editor.putString("c", currentCountry);
                editor.apply();
                if (dialogCountry != null) {
                    if (dialogCountry.isShowing())
                        dialogCountry.dismiss();
                }
                if (txtCurrentCountry != null) {
                    txtCurrentCountry.setText(getItem(position).getCountry());
                }
            }
        });
    }
}
