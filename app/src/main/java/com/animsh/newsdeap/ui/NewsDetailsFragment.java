package com.animsh.newsdeap.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.databinding.FragmentNewsDetailsBinding;

public class NewsDetailsFragment extends Fragment {

    public NewsDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentNewsDetailsBinding fragmentNewsDetailsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()),
                R.layout.fragment_news_details,
                container,
                false
        );

//        FakeDataSource fakeDataSource = new FakeDataSource();
//        NewsItem item = fakeDataSource.generateRandomNewsItem();
//
//        fragmentNewsDetailsBinding.setNewsItemData(item);
        return fragmentNewsDetailsBinding.getRoot();
    }
}