package com.animsh.newsdeap.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import com.animsh.newsdeap.ui.NewsDetailsFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.skydoves.transformationlayout.TransformationLayout;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.animsh.newsdeap.ui.MainActivity.countryList;
import static com.animsh.newsdeap.ui.MainActivity.currentCountry;

/**
 * Created by animsh on 1/12/2021.
 */
public class BindingAdapters {

    static List<Article> articleList = new ArrayList<>();
    static List<com.animsh.newsdeap.entities.Article> articleDBList = new ArrayList<>();

    @BindingAdapter("open_article")
    public static void openArticle(TransformationLayout transformationLayout, Article article) {
        transformationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = transformationLayout.withContext(transformationLayout.getContext(), "myTransitionName");
                Intent intent = new Intent(transformationLayout.getContext(), NewsDetailsFragment.class);
                intent.putExtra("Article", article);
                intent.putExtra("TransformationParams", transformationLayout.getParcelableParams());
                transformationLayout.getContext().startActivity(intent, bundle);
            }
        });
    }

    @BindingAdapter("share_link")
    public static void shareLink(ImageView imageView, String url) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT, url);
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                share.setType("text/plain");
                imageView.getContext().startActivity(Intent.createChooser(share, "Share Article"));
            }
        });
    }

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
        class GetNewsTask extends AsyncTask<Void, Void, List<com.animsh.newsdeap.entities.Article>> {
            int id = -1;

            @Override
            protected List<com.animsh.newsdeap.entities.Article> doInBackground(Void... voids) {
                return NewsDatabase
                        .getNewsDatabase(imageView.getContext()).newsDao().getAllArticles();
            }

            @Override
            protected void onPostExecute(List<com.animsh.newsdeap.entities.Article> articles) {
                super.onPostExecute(articles);
                Log.d("MY_ARTICLE: ", articles.toString());
                articleDBList.addAll(articles);
                for (int i = 0; i < articleDBList.size(); i++) {
                    Article article1 = new com.animsh.newsdeap.data.Article(
                            articleDBList.get(i).getSource(),
                            articleDBList.get(i).getAuthor(),
                            articleDBList.get(i).getTitle(),
                            articleDBList.get(i).getDescription(),
                            articleDBList.get(i).getUrl(),
                            articleDBList.get(i).getUrlToImage(),
                            articleDBList.get(i).getPublishedAt(),
                            articleDBList.get(i).getContent());
                    articleList.add(article1);

                    if (article1.getTitle().equals(article.getTitle())) {
                        id = articleDBList.get(i).getId();
                    }

                }
                if (id != -1) {
                    imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.red));
                } else {
                    imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.dark_icon_tint_color));
                }
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        performTask(imageView.getContext(), article, imageView, id);
                    }
                });
            }
        }
        new GetNewsTask().execute();
    }

    private static void performTask(Context context, Article article, ImageView imageView, int id) {
        final com.animsh.newsdeap.entities.Article articleDB = new com.animsh.newsdeap.entities.Article();
        if (id != -1) {
            articleDB.setId(id);
            articleDB.setSource(article.getSource());
            articleDB.setAuthor(article.getAuthor());
            articleDB.setTitle(article.getTitle());
            articleDB.setDescription(article.getDescription());
            articleDB.setUrl(article.getUrl());
            articleDB.setUrlToImage(article.getUrlToImage());
            articleDB.setPublishedAt(article.getPublishedAt());
            articleDB.setContent(article.getContent());
            class DeleteNewsTask extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    NewsDatabase.getNewsDatabase(context).newsDao().deleteArticle(articleDB);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    imageView.setColorFilter(ContextCompat.getColor(context, R.color.dark_icon_tint_color));
                }
            }
            new DeleteNewsTask().execute();

        } else {
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
                    NewsDatabase.getNewsDatabase(context).newsDao().insertArticle(articleDB);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    imageView.setColorFilter(ContextCompat.getColor(context, R.color.red));
                }
            }
            new SaveNewsTask().execute();
        }
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
