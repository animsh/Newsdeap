package com.animsh.newsdeap.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.ui.home.BusinessNewsFragment;
import com.animsh.newsdeap.ui.home.EntertainmentNewsFragment;
import com.animsh.newsdeap.ui.home.GeneralNewsFragment;
import com.animsh.newsdeap.ui.home.HealthNewsFragment;
import com.animsh.newsdeap.ui.home.ScienceNewsFragment;
import com.animsh.newsdeap.ui.home.SportsNewsFragment;
import com.animsh.newsdeap.ui.home.TechnologyNewsFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class NewsListFragment extends Fragment {

    public NewsListFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), FragmentPagerItems.with(requireContext())
                .add("General", GeneralNewsFragment.class)
                .add("Business", BusinessNewsFragment.class)
                .add("Entertainment", EntertainmentNewsFragment.class)
                .add("Health", HealthNewsFragment.class)
                .add("Science", ScienceNewsFragment.class)
                .add("Sports", SportsNewsFragment.class)
                .add("Technology", TechnologyNewsFragment.class)
                .create());

        ViewPager viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = view.findViewById(R.id.newsTab);
        viewPagerTab.setViewPager(viewPager);

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