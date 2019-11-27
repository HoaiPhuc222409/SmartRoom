package com.example.smartroom.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartroom.Fragment.LoginFragment;
import com.example.smartroom.R;

import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener,EditText.OnTouchListener{

    private EditText edtDateOfBirth,edtUserName,edtPassword, edtConfirmPassword, edtFullName, edtEmail, edtPhone;
    private TextView tvLogin, tvHintDateOfBirth;
    private Button btnRegister;
    private Pattern pattern;
    private String dateOfBirth;



    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_register, container, false);

        initialize(view);

        return view;
    }
    public void initialize(View view){
        tvHintDateOfBirth=view.findViewById(R.id.tvHintDateOfBirth);
        btnRegister = view.findViewById(R.id.btnRegister);
        tvLogin = view.findViewById(R.id.tvLogin);
        edtDateOfBirth = view.findViewById(R.id.edtBirthday);
        edtUserName=view.findViewById(R.id.edtUsername);
        edtPassword=view.findViewById(R.id.edtPassword);
        edtConfirmPassword=view.findViewById(R.id.edtConfirmPassword);
        edtFullName=view.findViewById(R.id.edtFullName);
        edtEmail=view.findViewById(R.id.edtEmail);
        edtPhone=view.findViewById(R.id.edtPhone);

        //addOnClickListener
        tvLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        edtDateOfBirth.setOnTouchListener(this);
        edtConfirmPassword.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvLogin:{
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new LoginFragment();
                transaction.replace(R.id.frameMain, fragment);
                transaction.commit();
            }
            case R.id.btnRegister:{
                dateOfBirth=edtDateOfBirth.getText().toString();
                if (dateOfBirth.length()==8){
                    if (checkFill(edtDateOfBirth)&&checkFill(edtUserName)&&checkFill(edtPassword)&&checkFill(edtConfirmPassword)&&checkFill(edtFullName)&&checkFill(edtEmail)&&checkFill(edtPhone)){
                        Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "All field must be fill", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Please input date of birth include 8 characters", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    public boolean checkFill(EditText editText){
        if (TextUtils.isEmpty(editText.getText())){
            return false;
        }else return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.edtBirthday:{
                edtDateOfBirth.requestFocus();
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edtDateOfBirth, InputMethodManager.SHOW_IMPLICIT);
                tvHintDateOfBirth.setVisibility(View.VISIBLE);
            }
            case R.id.edtConfirmPassword:{
                edtConfirmPassword.requestFocus();
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edtConfirmPassword, InputMethodManager.SHOW_IMPLICIT);
                edtConfirmPassword.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())){
                            edtPassword.setBackgroundResource(R.drawable.boder_match);
                            edtConfirmPassword.setBackgroundResource(R.drawable.boder_match);
                        }else {
                            edtConfirmPassword.setBackgroundResource(R.drawable.boder_not_match);
                            edtPassword.setBackgroundResource(R.drawable.boder_not_match);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        }


        return true;
    }
}
