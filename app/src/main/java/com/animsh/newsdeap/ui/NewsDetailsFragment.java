package com.animsh.newsdeap.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.data.Article;
import com.animsh.newsdeap.databinding.FragmentNewsDetailsBinding;

public class NewsDetailsFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Article article = (Article) getIntent().getSerializableExtra("Article");
        FragmentNewsDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.fragment_news_details);
        binding.setNewsItemData(article);
    }
}