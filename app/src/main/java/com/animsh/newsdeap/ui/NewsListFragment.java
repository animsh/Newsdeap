package com.animsh.newsdeap.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.data.FakeDataSource;
import com.animsh.newsdeap.ui.news.DiffUtilNewsItemCallback;
import com.animsh.newsdeap.ui.news.NewsListAdapter;

public class NewsListFragment extends Fragment {

    RecyclerView newsRecyclerView;
    NewsListAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    public NewsListFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsRecyclerView = view.findViewById(R.id.rv_news_list);
        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new NewsListAdapter(new DiffUtilNewsItemCallback());
        newsRecyclerView.setAdapter(adapter);

        FakeDataSource fakeDataSource = new FakeDataSource();
        adapter.submitList(fakeDataSource.getFakeListNews());

        swipeRefreshLayout = view.findViewById(R.id.newsLisSwipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.submitList(fakeDataSource.getFakeUpdatedStaticListNews());
                        swipeRefreshLayout.setRefreshing(false);

                        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                            @Override
                            public void onItemRangeInserted(int positionStart, int itemCount) {
                                super.onItemRangeInserted(positionStart, itemCount);
                                newsRecyclerView.smoothScrollToPosition(positionStart);
                            }
                        });
                    }
                }, 500);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }
}