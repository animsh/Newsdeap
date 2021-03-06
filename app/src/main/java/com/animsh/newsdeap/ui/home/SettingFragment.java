package com.animsh.newsdeap.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.newsdeap.R;
import com.animsh.newsdeap.ui.news.CountryListAdapter;
import com.animsh.newsdeap.ui.news.DiffUtilCountryItemCallback;
import com.animsh.newsdeap.util.NetworkUtil;

import static com.animsh.newsdeap.ui.MainActivity.countryList;
import static com.animsh.newsdeap.ui.MainActivity.currentCountry;

public class SettingFragment extends Fragment implements View.OnClickListener {

    public static AlertDialog dialogMode, dialogCountry;
    @SuppressLint("StaticFieldLeak")
    public static TextView txtCurrentCountry;
    LinearLayout layoutCountry, layoutMode, layoutAbout;
    String TAG = "SETTING_TAG";
    String selectedMode = "default";
    SharedPreferences myPref;
    SharedPreferences.Editor editor;
    TextView txtSelectedMode;

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
        myPref = view.getContext().getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        selectedMode = myPref.getString("mode", "default");

        if (!NetworkUtil.hasNetwork(getContext())) {
            Toast.makeText(getContext(), "No Internet Connection!!", Toast.LENGTH_SHORT).show();
        }

        layoutCountry = view.findViewById(R.id.layout_country);
        layoutMode = view.findViewById(R.id.layout_mode);
        layoutAbout = view.findViewById(R.id.layout_about);
        txtSelectedMode = view.findViewById(R.id.selectedMode);
        txtCurrentCountry = view.findViewById(R.id.currentCountry);

        for (int i = 0; i < countryList.size(); i++) {
            if (countryList.get(i).getAbbreviation().toLowerCase().equals(currentCountry)) {
                txtCurrentCountry.setText(countryList.get(i).getCountry());
            }
        }

        layoutCountry.setTag("layoutCountry");
        layoutMode.setTag("layoutMode");
        layoutAbout.setTag("layoutAbout");

        layoutCountry.setOnClickListener(this);
        layoutMode.setOnClickListener(this);
        layoutAbout.setOnClickListener(this);
        Log.d(TAG, "onViewCreated: " + layoutCountry.getTag());

        switch (selectedMode) {
            case "default":
                txtSelectedMode.setText(R.string.system_default);
                break;
            case "dark":
                txtSelectedMode.setText(R.string.dark_mode);
                break;
            case "light":
                txtSelectedMode.setText(R.string.light_mode);
                break;
        }
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
                showCountryDialog(view.getContext());
                break;
            case "layoutMode":
                showModeDialog(view.getContext());
                break;
            case "layoutAbout":
                break;
            case "default":
                if (dialogMode != null)
                    if (dialogMode.isShowing())
                        dialogMode.dismiss();
                txtSelectedMode.setText(R.string.system_default);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                editor = myPref.edit();
                editor.putString("mode", "default");
                editor.apply();
                dialogCountry = null;
                dialogMode = null;
                break;
            case "dark":
                if (dialogMode != null)
                    if (dialogMode.isShowing())
                        dialogMode.dismiss();
                txtSelectedMode.setText(R.string.dark_mode);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor = myPref.edit();
                editor.putString("mode", "dark");
                editor.apply();
                dialogCountry = null;
                dialogMode = null;
                break;
            case "light":
                if (dialogMode != null)
                    if (dialogMode.isShowing())
                        dialogMode.dismiss();
                txtSelectedMode.setText(R.string.light_mode);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor = myPref.edit();
                editor.putString("mode", "light");
                editor.apply();
                dialogCountry = null;
                dialogMode = null;
                break;
        }

    }

    private void showModeDialog(Context context) {
        if (dialogMode == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(
                    R.layout.layout_mode,
                    ((Activity) context).findViewById(R.id.layout_mode_container)
            );
            builder.setView(view);

            dialogMode = builder.create();
            if (dialogMode.getWindow() != null) {
                dialogMode.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            RadioGroup modeGroup = view.findViewById(R.id.modeGroup);
            modeGroup.findViewById(R.id.modeDefault).setOnClickListener(this);
            modeGroup.findViewById(R.id.modeLight).setOnClickListener(this);
            modeGroup.findViewById(R.id.modeDark).setOnClickListener(this);

            switch (selectedMode) {
                case "default":
                    modeGroup.check(R.id.modeDefault);
                    break;
                case "dark":
                    modeGroup.check(R.id.modeDark);
                    break;
                case "light":
                    modeGroup.check(R.id.modeLight);
                    break;
            }
        }
        dialogMode.show();
    }

    private void showCountryDialog(Context context) {
        if (dialogCountry == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            View view = LayoutInflater.from(requireContext()).inflate(
                    R.layout.layout_country,
                    ((Activity) requireContext()).findViewById(R.id.layout_country_container)
            );
            builder.setView(view);

            dialogCountry = builder.create();
            if (dialogCountry.getWindow() != null) {
                dialogCountry.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }

            RecyclerView countryRecycler = view.findViewById(R.id.rv_country);
            countryRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
            CountryListAdapter countryListAdapter = new CountryListAdapter(new DiffUtilCountryItemCallback());
            countryRecycler.setAdapter(countryListAdapter);
            countryListAdapter.submitList(countryList);
        }
        dialogCountry.show();
    }

}