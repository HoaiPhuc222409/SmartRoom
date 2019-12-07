package com.example.smartroom.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.smartroom.Fragment.LightFragment;
import com.example.smartroom.Fragment.MainFragment;
import com.example.smartroom.Fragment.PeopleFragment;
import com.example.smartroom.Fragment.TempFragment;
import com.example.smartroom.Fragment.WeatherFragment;
import com.example.smartroom.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showMainFragment();

    }

    public void showMainFragment(){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        Fragment fragment=new MainFragment();
        transaction.replace(R.id.framMain,fragment);
        transaction.commit();
    }

}
