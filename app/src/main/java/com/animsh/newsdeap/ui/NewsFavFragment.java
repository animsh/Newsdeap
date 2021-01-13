package com.animsh.newsdeap.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.data.FakeDataSource;
import com.animsh.newsdeap.ui.news.DiffUtilNewsItemCallback;
import com.animsh.newsdeap.ui.news.NewsListAdapter;

public class NewsFavFragment extends Fragment {

    RecyclerView favNewsRecyclerView;
    NewsListAdapter adapter;

    public NewsFavFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favNewsRecyclerView = view.findViewById(R.id.rv_fav_news);
        favNewsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        favNewsRecyclerView.setHasFixedSize(true);
        adapter = new NewsListAdapter(new DiffUtilNewsItemCallback());
        favNewsRecyclerView.setAdapter(adapter);
        FakeDataSource fakeDataSource = new FakeDataSource();
        adapter.submitList(fakeDataSource.getFakeStaticListNews());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_fav, container, false);
    }
}