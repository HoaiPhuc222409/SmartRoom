package com.example.smartroom.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartroom.Activity.LoginActivity;
import com.example.smartroom.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */


public class MainFragment extends Fragment implements View.OnClickListener {

    ImageView img_temp, img_weather, img_people, img_light;
    Button btn_logout;

    private final String SharedReferencesFile = "sharedReferencesFile";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_main, container, false);
        initialize(view);
        return view;
    }


    private void initialize(View view) {
        img_temp = view.findViewById(R.id.img_temp);
        img_weather = view.findViewById(R.id.img_weather);
        img_people = view.findViewById(R.id.img_people);
        img_light = view.findViewById(R.id.img_light);
        btn_logout = view.findViewById(R.id.btn_logOut);

        img_temp.setOnClickListener(this);
        img_weather.setOnClickListener(this);
        img_people.setOnClickListener(this);
        img_light.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_temp:
                showTempFragment();
                break;
            case R.id.img_weather:
                showWeatherFragment();
                break;
            case R.id.img_people:
                showPeopleFragment();
                break;
            case R.id.img_light:
                showLightFragment();
                break;
            case R.id.btn_logOut:
                onLogOut(SharedReferencesFile);
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
        }
    }

    public void showTempFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction().addToBackStack("TempFragment");
        Fragment fragment = new TempFragment();
        transaction.replace(R.id.framMain, fragment);
        transaction.commit();
    }

    public void showWeatherFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction().addToBackStack("WeatherFragment");
        Fragment fragment = new WeatherFragment();
        transaction.replace(R.id.framMain, fragment);
        transaction.commit();
    }

    public void showPeopleFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction().addToBackStack("PeopleFragment");
        Fragment fragment = new PeopleFragment();
        transaction.replace(R.id.framMain, fragment);
        transaction.commit();
    }

    public void showLightFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction().addToBackStack("LightFragment");
        Fragment fragment = new LightFragment();
        transaction.replace(R.id.framMain, fragment);
        transaction.commit();
    }

    private void onLogOut(String nameFile) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(nameFile, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
