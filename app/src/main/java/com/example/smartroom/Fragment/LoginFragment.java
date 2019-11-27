package com.example.smartroom.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartroom.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    TextView register;
    EditText username, password;
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
        username=view.findViewById(R.id.edtUsername);
        password=view.findViewById(R.id.edtPassword);
        btnLogin=view.findViewById(R.id.btnLogin);

        //addOnClickListener
        btnLogin.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:{
                if (username.getText().length()==0||password.getText().length()==0){
                    Toast.makeText(getContext(), "Not Invalid", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
            case R.id.tvCreateAccount:{
                FragmentManager manager=getFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                Fragment fragment=new RegisterFragment();
                transaction.replace(R.id.frameMain,fragment);
                transaction.commit();
            }
        }
    }
}
