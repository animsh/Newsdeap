package com.animsh.newsdeap.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.animsh.newsdeap.R;

public class SettingFragment extends Fragment implements View.OnClickListener {

    LinearLayout layoutCountry, layoutMode, layoutAbout;
    String TAG = "SETTING_TAG";

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutCountry = view.findViewById(R.id.layout_country);
        layoutMode = view.findViewById(R.id.layout_mode);
        layoutAbout = view.findViewById(R.id.layout_about);

        layoutCountry.setTag("layoutCountry");
        layoutMode.setTag("layoutMode");
        layoutAbout.setTag("layoutAbout");

        layoutCountry.setOnClickListener(this);
        layoutMode.setOnClickListener(this);
        layoutAbout.setOnClickListener(this);
        Log.d(TAG, "onViewCreated: " + layoutCountry.getTag());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getTag().toString()) {
            case "layoutCountry":
                Toast.makeText(getContext(), view.getTag().toString(), Toast.LENGTH_SHORT).show();
                break;
            case "layoutMode":
                Toast.makeText(getContext(), view.getTag().toString(), Toast.LENGTH_SHORT).show();
                break;
            case "layoutAbout":
                Toast.makeText(getContext(), view.getTag().toString(), Toast.LENGTH_SHORT).show();
                break;

        }

    }
}