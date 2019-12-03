package com.example.smartroom.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartroom.Activity.LoginActivity;
import com.example.smartroom.Activity.MainActivity;
import com.example.smartroom.api.APIRetrofit;
import com.example.smartroom.api.APISignIn;
import com.example.smartroom.service.LoginRequest;
import com.example.smartroom.R;
import com.example.smartroom.model.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{
    APISignIn apiSignIn;
    TextView register;
    EditText edtUsername, edtPassword;
    Button btnLogin;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_login, container, false);

        initialize(view);

        return view;
    }

    public void initialize(View view){
        register=view.findViewById(R.id.tvCreateAccount);
        edtUsername =view.findViewById(R.id.edtUsername);
        edtPassword =view.findViewById(R.id.edtPassword);
        btnLogin=view.findViewById(R.id.btnLogin);

        //addOnClickListener
        btnLogin.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:{
                if (edtUsername.getText().length()==0|| edtPassword.getText().length()==0){
                    Toast.makeText(getContext(), "Not Invalid", Toast.LENGTH_SHORT).show();
                };
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                getToken(username,password);

                break;
            }
            case R.id.tvCreateAccount:{
                FragmentManager manager=getFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                Fragment fragment=new RegisterFragment();
                transaction.replace(R.id.frameLogin,fragment);
                transaction.commit();
                break;
            }
        }
    }

    public void getToken(String username, String password) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIRetrofit.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiSignIn = retrofit.create(APISignIn.class);

        Call<Token> call = apiSignIn.getToken(new LoginRequest(username, password));
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token token = response.body();
                if (token != null) {
                    Intent intent=new Intent(getContext(),MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Login Fail!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Token> call, Throwable t) {

                Log.i("tag","login fail");

            }
        });
    }
}
