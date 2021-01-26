package com.animsh.newsdeap.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.data.Article;
import com.animsh.newsdeap.databinding.FragmentNewsDetailsBinding;

public class NewsDetailsFragment extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Article article = (Article) getIntent().getSerializableExtra("Article");
        FragmentNewsDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.fragment_news_details);
        binding.setNewsItemData(article);

        swipeRefreshLayout = findViewById(R.id.newsLisSwipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.setNewsItemData(article);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);
            }
        });
    }

    public void onBackClick(View view) {
        finish();
    }
}