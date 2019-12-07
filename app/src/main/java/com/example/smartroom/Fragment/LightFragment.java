package com.example.smartroom.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartroom.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LightFragment extends Fragment implements View.OnClickListener{

    ImageView back;
    Button btn_getData;
    public LightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_light, container, false);
        initialize(view);
        return view;
    }

    public void initialize(View view){
        back=view.findViewById(R.id.btnBackLight);
        btn_getData=view.findViewById(R.id.btnGetLight);

//        back.setOnClickListener(this);
//        btn_getData.setOnClickListener(this);
    }

    public void showMainFragment(){
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        Fragment fragment=new MainFragment();
        transaction.replace(R.id.framMain,fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBackLight:{
//                showMainFragment();
                break;
            }
            case R.id.btnGetLight:{
                break;
            }
        }
    }
}
