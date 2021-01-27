package com.animsh.newsdeap.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.animsh.newsdeap.R;
import com.animsh.newsdeap.database.NewsDatabase;
import com.animsh.newsdeap.entities.Article;
import com.animsh.newsdeap.ui.news.DiffUtilNewsItemCallback;
import com.animsh.newsdeap.ui.news.NewsListAdapter;
import com.animsh.newsdeap.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

public class NewsFavFragment extends Fragment {

    RecyclerView newsRecyclerView;
    NewsListAdapter adapter;
    LottieAnimationView lottieAnimationView;
    List<Article> articleDBList;
    List<com.animsh.newsdeap.data.Article> articleList;

    public NewsFavFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        articleDBList = new ArrayList<>();
        articleList = new ArrayList<>();
        lottieAnimationView = view.findViewById(R.id.animationView);
        newsRecyclerView = view.findViewById(R.id.rv_news_list);
        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new NewsListAdapter(new DiffUtilNewsItemCallback());
        if (NetworkUtil.hasNetwork(getContext())) {
            getNews(lottieAnimationView);
        } else {
            Toast.makeText(getContext(), "No Internet Connection!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getNews(LottieAnimationView lottieAnimationView) {
        class GetNewsTask extends AsyncTask<Void, Void, List<Article>> {

            @Override
            protected List<Article> doInBackground(Void... voids) {
                return NewsDatabase
                        .getNewsDatabase(getContext()).newsDao().getAllArticles();
            }

            @Override
            protected void onPostExecute(List<Article> articles) {
                super.onPostExecute(articles);
                Log.d("MY_ARTICLE: ", articles.toString());
                articleDBList.clear();
                articleDBList.addAll(articles);
                articleList.clear();
                for (int i = 0; i < articleDBList.size(); i++) {
                    articleList.add(new com.animsh.newsdeap.data.Article(
                            articleDBList.get(i).getSource(),
                            articleDBList.get(i).getAuthor(),
                            articleDBList.get(i).getTitle(),
                            articleDBList.get(i).getDescription(),
                            articleDBList.get(i).getUrl(),
                            articleDBList.get(i).getUrlToImage(),
                            articleDBList.get(i).getPublishedAt(),
                            articleDBList.get(i).getContent()));
                }
                if (articleList.size() != 0 && articleList != null && !articleList.isEmpty()) {
                    newsRecyclerView.setAdapter(adapter);
                    adapter.submitList(articleList);
                    adapter.notifyDataSetChanged();
                    lottieAnimationView.setVisibility(View.GONE);
                } else {
                    adapter.notifyDataSetChanged();
                    lottieAnimationView.setVisibility(View.VISIBLE);
                }
            }
        }

        new GetNewsTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_fav, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!NetworkUtil.hasNetwork(getContext())) {
            Toast.makeText(getContext(), "No Internet Connection!!", Toast.LENGTH_SHORT).show();
        } else {
            getNews(lottieAnimationView);
        }
    }
}