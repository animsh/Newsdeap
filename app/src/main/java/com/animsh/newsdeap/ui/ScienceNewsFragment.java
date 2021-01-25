package com.animsh.newsdeap.ui;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.data.NewsCollection;
import com.animsh.newsdeap.ui.news.DiffUtilNewsItemCallback;
import com.animsh.newsdeap.ui.news.NewsListAdapter;
import com.animsh.newsdeap.util.NewsApiCall;
import com.animsh.newsdeap.util.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.animsh.newsdeap.ui.MainActivity.currentCountry;

public class ScienceNewsFragment extends Fragment {

    RecyclerView newsRecyclerView;
    NewsListAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    NewsCollection newsCollection;
    String TAG = "S_NEWS";

    public ScienceNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        newsRecyclerView = view.findViewById(R.id.rv_news_list);
        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new NewsListAdapter(new DiffUtilNewsItemCallback());
        newsRecyclerView.setAdapter(adapter);

        ProgressBar progressBar = view.findViewById(R.id.progressBar);

        Retrofit retrofit = RetrofitClient.getClient();
        NewsApiCall newsApiCall = retrofit.create(NewsApiCall.class);
        Call<NewsCollection> topHeadlinesCall = newsApiCall.getTopHeadLines(currentCountry, "science", getString(R.string.api_key));

        topHeadlinesCall.enqueue(new Callback<NewsCollection>() {
            @Override
            public void onResponse(@NotNull Call<NewsCollection> call, @NotNull Response<NewsCollection> response) {
                newsCollection = response.body();
                adapter.submitList(newsCollection.getArticles());
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NotNull Call<NewsCollection> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.submitList(newsCollection.getArticles());
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_science_news, container, false);
    }
}