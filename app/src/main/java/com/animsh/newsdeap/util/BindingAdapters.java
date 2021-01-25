package com.animsh.newsdeap.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.palette.graphics.Palette;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.data.Article;
import com.animsh.newsdeap.database.NewsDatabase;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import static com.animsh.newsdeap.ui.MainActivity.countryList;
import static com.animsh.newsdeap.ui.MainActivity.currentCountry;

/**
 * Created by animsh on 1/12/2021.
 */
public class BindingAdapters {
    @BindingAdapter("glide_url")
    public static void loadImage(ImageView imageView, String url) {
        if (url != null && !url.equals("") && !url.isEmpty())
            Glide.with(imageView.getContext()).load(url).into(imageView);
        else
            imageView.setVisibility(View.GONE);
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
    public static void toggleFavButton(ImageView imageView, Article article) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final com.animsh.newsdeap.entities.Article articleDB = new com.animsh.newsdeap.entities.Article();
                articleDB.setSource(article.getSource());
                articleDB.setAuthor(article.getAuthor());
                articleDB.setTitle(article.getTitle());
                articleDB.setDescription(article.getDescription());
                articleDB.setUrl(article.getUrl());
                articleDB.setUrlToImage(article.getUrlToImage());
                articleDB.setPublishedAt(article.getPublishedAt());
                articleDB.setContent(article.getContent());

                class SaveNewsTask extends AsyncTask<Void, Void, Void> {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        NewsDatabase.getNewsDatabase(imageView.getContext()).newsDao().insertArticle(articleDB);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.red));
                    }
                }
                new SaveNewsTask().execute();
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter("set_webpage")
    public static void setWebPage(WebView webPage, String url) {
        webPage.getSettings().setDomStorageEnabled(true);
        webPage.getSettings().setJavaScriptEnabled(true);
        webPage.getSettings().setLoadsImagesAutomatically(true);
        webPage.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webPage.setWebViewClient(new WebViewClient());
        webPage.loadUrl(url);
    }

    @BindingAdapter("set_flag")
    public static void setCountryFlag(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(imageView);

    }

    @BindingAdapter("set_time_date")
    public static void setDateTime(TextView textView, String t) {
        String locale = null;
        for (int i = 0; i < countryList.size(); i++) {
            if (countryList.get(i).getAbbreviation().toLowerCase().equals(currentCountry)) {
                locale = countryList.get(i).getCountry().toLowerCase();
            }
        }
        PrettyTime prettyTime = new PrettyTime(new Locale(locale));
        String time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:", Locale.ENGLISH);
            Date date = simpleDateFormat.parse(t);
            time = prettyTime.format(date);
            textView.setText(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
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
