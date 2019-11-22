package com.example.smartroom;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    TextView login;
    Button btnRegister;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_register, container, false);

        btnRegister=view.findViewById(R.id.btnRegister);
        login=view.findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(v->{
            Toast.makeText(getContext(), "Don't have server! Can't Register. Please wait for Server Team!", Toast.LENGTH_SHORT).show();
        });

        login.setOnClickListener(v->{
            FragmentManager manager=getFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            Fragment fragment=new LoginFragment();
            transaction.replace(R.id.frameMain,fragment);
            transaction.commit();
        });

        return view;
    }

}
