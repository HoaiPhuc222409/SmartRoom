package com.example.smartroom.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.smartroom.Fragment.MainFragment;
import com.example.smartroom.R;
import com.example.smartroom.service.LogOut;

public class MainActivity extends AppCompatActivity implements LogOut{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showMainFragment();
    }

    public void showMainFragment(){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction().addToBackStack("MainFragment");
        Fragment fragment=new MainFragment();
        transaction.replace(R.id.framMain,fragment);
        transaction.commit();
    }

    public void clearData(String nameFile) {
        SharedPreferences sharedPreferences = getSharedPreferences(nameFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    @Override
    public void onLogOut(String nameFile) {
        SharedPreferences sharedPreferences=getSharedPreferences(nameFile,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
