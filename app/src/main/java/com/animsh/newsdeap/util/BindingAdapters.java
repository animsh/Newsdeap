package com.animsh.newsdeap.util;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.palette.graphics.Palette;

import com.animsh.newsdeap.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Collections;
import java.util.Comparator;

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

    @BindingAdapter("set_background_to_image")
    public static void setBackground(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).asBitmap()
                .load(url)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        Palette p = Palette.from(resource).generate();
                        Palette.Swatch palette = getDominantSwatch(p);
                        imageView.setBackgroundColor(palette.getRgb());
                        return true;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    @BindingAdapter("set_checked")
    public static void toggleFavButton(ImageView imageView, boolean isFav) {
        if (isFav)
            imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.red));
        else
            imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.dark_icon_tint_color));
    }

    @BindingAdapter("set_webpage")
    public static void setWebPage(WebView webPage, String url) {
        webPage.getSettings().setDomStorageEnabled(true);
        webPage.getSettings().setJavaScriptEnabled(true);
        webPage.getSettings().setLoadsImagesAutomatically(true);
        webPage.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webPage.setWebViewClient(new WebViewClient());
        webPage.loadUrl(url);
    }

    public static Palette.Swatch getDominantSwatch(Palette palette) {
        // find most-represented swatch based on population
        return Collections.max(palette.getSwatches(), new Comparator<Palette.Swatch>() {
            @Override
            public int compare(Palette.Swatch sw1, Palette.Swatch sw2) {
                return Integer.compare(sw1.getPopulation(), sw2.getPopulation());
            }
        });
    }
}
