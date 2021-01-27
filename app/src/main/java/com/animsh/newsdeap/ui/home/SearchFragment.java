package com.animsh.newsdeap.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.animsh.newsdeap.R;
import com.animsh.newsdeap.data.NewsCollection;
import com.animsh.newsdeap.ui.news.DiffUtilNewsItemCallback;
import com.animsh.newsdeap.ui.news.NewsListAdapter;
import com.animsh.newsdeap.util.NetworkUtil;
import com.animsh.newsdeap.util.NewsApiCall;
import com.animsh.newsdeap.util.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchFragment extends Fragment {

    private static final String TAG = "SEARCH_TAG";
    RecyclerView newsRecyclerView;
    NewsListAdapter adapter;
    NewsCollection newsCollection;
    EditText searchEditText;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsRecyclerView = view.findViewById(R.id.rv_news_list);
        searchEditText = view.findViewById(R.id.searchEditext);
        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new NewsListAdapter(new DiffUtilNewsItemCallback());
        newsRecyclerView.setAdapter(adapter);
        LottieAnimationView lottieAnimationView = view.findViewById(R.id.animationView);
        if (NetworkUtil.hasNetwork(getContext())) {
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!editable.toString().isEmpty() || !editable.toString().equals("")) {
                                Retrofit retrofit = RetrofitClient.getClient();
                                NewsApiCall newsApiCall = retrofit.create(NewsApiCall.class);
                                Call<NewsCollection> topHeadlinesCall = newsApiCall.getSpecificData(editable.toString(), getString(R.string.api_key));

                                topHeadlinesCall.enqueue(new Callback<NewsCollection>() {
                                    @Override
                                    public void onResponse(@NotNull Call<NewsCollection> call, @NotNull Response<NewsCollection> response) {
                                        newsCollection = response.body();
                                        if (!newsCollection.getArticles().isEmpty() && newsCollection.getArticles().size() != 0 && newsCollection.getArticles() != null) {
                                            lottieAnimationView.setVisibility(View.GONE);
                                            adapter.submitList(newsCollection.getArticles());
                                            adapter.notifyDataSetChanged();
                                        } else {
                                            lottieAnimationView.setVisibility(View.VISIBLE);
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NotNull Call<NewsCollection> call, @NotNull Throwable t) {
                                        Log.e(TAG, "onFailure: " + t.getMessage());
                                    }
                                });
                            } else {
                                adapter.submitList(null);
                            }
                        }
                    }, 500);
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection!!", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}