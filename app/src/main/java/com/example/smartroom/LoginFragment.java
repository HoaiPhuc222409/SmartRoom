package com.example.smartroom;


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

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

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

        //
        register=view.findViewById(R.id.tvCreateAccount);
        username=view.findViewById(R.id.edtUsername);
        password=view.findViewById(R.id.edtPassword);
        btnLogin=view.findViewById(R.id.btnLogin);


        register.setOnClickListener(v->{
            FragmentManager manager=getFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            Fragment fragment=new RegisterFragment();
            transaction.replace(R.id.frameMain,fragment);
            transaction.commit();
        });

        btnLogin.setOnClickListener(v->{
            if (username.getText().length()==0||password.getText().length()==0){
                Toast.makeText(getContext(), "Not Invalid", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Don't have Sever", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
