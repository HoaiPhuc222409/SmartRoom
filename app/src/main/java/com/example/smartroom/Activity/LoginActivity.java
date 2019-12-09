package com.example.smartroom.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.smartroom.Fragment.LoginFragment;
import com.example.smartroom.R;
import com.example.smartroom.model.Token;

public class LoginActivity extends AppCompatActivity {

    Token token = new Token();



    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        showLoginFragment();

    }

    public void showLoginFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new LoginFragment();
        transaction.replace(R.id.frameLogin, fragment);
        transaction.commit();
    }
}
