package com.animsh.newsdeap.util;

import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.animsh.newsdeap.R;
import com.bumptech.glide.Glide;

/**
 * Created by animsh on 1/12/2021.
 */
public class BindingAdapters {

    @BindingAdapter("glide_url")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter("glide_circular_url")
    public static void loadUserImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).circleCrop().into(imageView);
    }

    @BindingAdapter("set_background")
    public static void setBackground(ImageView imageView, String color) {
        switch (color) {
            case "RED":
                imageView.setBackgroundColor(imageView.getContext().getResources().getColor(R.color.red));
                break;
            case "BLACK":
                imageView.setBackgroundColor(imageView.getContext().getResources().getColor(R.color.material_black));
                break;
            case "YELLOW":
                imageView.setBackgroundColor(imageView.getContext().getResources().getColor(R.color.yellow));
                break;
            case "BLUE":
                imageView.setBackgroundColor(imageView.getContext().getResources().getColor(R.color.blue));
                break;
            case "PURPLE":
                imageView.setBackgroundColor(imageView.getContext().getResources().getColor(R.color.purple));
                break;
            default:
                imageView.setBackgroundColor(imageView.getContext().getResources().getColor(R.color.red));

        }
    }

    @BindingAdapter("set_checked")
    public static void toggleFavButton(ImageView imageView, boolean isFav) {
        if (isFav)
            imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.red));
        else
            imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.dark_icon_tint_color));
    }
}
