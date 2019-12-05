package com.example.smartroom.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.smartroom.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    ImageView img_temp, img_weather, img_people, img_light;

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

        img_temp.setOnClickListener(this);
        img_weather.setOnClickListener(this);
        img_people.setOnClickListener(this);
        img_light.setOnClickListener(this);
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
                showPepleFragment();
                break;
            case R.id.img_light:
                showLightFragment();
                break;
        }
    }

    public void showTempFragment(){
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        Fragment fragment=new TempFragment();
        transaction.replace(R.id.framMain,fragment);
        transaction.commit();
    }

    public void showWeatherFragment(){
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        Fragment fragment=new WeatherFragment();
        transaction.replace(R.id.framMain,fragment);
        transaction.commit();
    }

    public void showPepleFragment(){
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        Fragment fragment=new PeopleFragment();
        transaction.replace(R.id.framMain,fragment);
        transaction.commit();
    }

    public void showLightFragment(){
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        Fragment fragment=new LightFragment();
        transaction.replace(R.id.framMain,fragment);
        transaction.commit();
    }
}
