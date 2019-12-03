package com.example.smartroom.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartroom.Activity.MainActivity;
import com.example.smartroom.R;
import com.example.smartroom.api.APIRetrofit;
import com.example.smartroom.api.APISignIn;
import com.example.smartroom.api.APISignUp;
import com.example.smartroom.model.ResponseSignUpBody;
import com.example.smartroom.model.Token;
import com.example.smartroom.service.LoginRequest;
import com.example.smartroom.service.SignupRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener, EditText.OnTouchListener {

    private EditText edtBirthday, edtUserName, edtPassword, edtConfirmPassword, edtFullName, edtEmail, edtPhone;
    private TextView tvLogin, tvHintDateOfBirth;
    private Button btnRegister;
    private Pattern pattern;
    private String birthday, fullName, username, password, email, phone;
    private APISignUp apiSignUp;


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

    public void initialize(View view) {
        tvHintDateOfBirth = view.findViewById(R.id.tvHintDateOfBirth);
        btnRegister = view.findViewById(R.id.btnRegister);
        tvLogin = view.findViewById(R.id.tvLogin);
        edtBirthday = view.findViewById(R.id.edtBirthday);
        edtUserName = view.findViewById(R.id.edtUsername);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtConfirmPassword = view.findViewById(R.id.edtConfirmPassword);
        edtFullName = view.findViewById(R.id.edtFullName);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPhone = view.findViewById(R.id.edtPhone);

        //addOnClickListener
        tvLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        edtBirthday.setOnTouchListener(this);
        edtConfirmPassword.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLogin: {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new LoginFragment();
                transaction.replace(R.id.frameLogin, fragment);
                transaction.commit();
            }
            case R.id.btnRegister: {
                fullName = edtFullName.getText().toString();
                username = edtUserName.getText().toString();
                password = edtPassword.getText().toString();
                birthday = edtBirthday.getText().toString();
                email = edtEmail.getText().toString();
                phone = edtPhone.getText().toString();
                if (birthday.length() == 8) {
                    if (edtPassword.getText().toString().length() >= 6) {
                        if (edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
                            if (checkFill(edtBirthday) && checkFill(edtUserName) && checkFill(edtPassword) && checkFill(edtConfirmPassword) && checkFill(edtFullName) && checkFill(edtEmail) && checkFill(edtPhone)) {
                                signUp(fullName, username, password, birthday, phone, email);
                            } else {
                                Toast.makeText(getContext(), "All field must be fill", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Password must be matched", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Password least is 6 characters", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Please input date of birth include 8 characters", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    public boolean checkFill(EditText editText) {
        if (TextUtils.isEmpty(editText.getText())) {
            return false;
        } else return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.edtBirthday: {
                edtBirthday.requestFocus();
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edtBirthday, InputMethodManager.SHOW_IMPLICIT);
                tvHintDateOfBirth.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.edtConfirmPassword: {
                edtConfirmPassword.requestFocus();
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edtConfirmPassword, InputMethodManager.SHOW_IMPLICIT);
                edtConfirmPassword.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
                            edtPassword.setBackgroundResource(R.drawable.boder_match);
                            edtConfirmPassword.setBackgroundResource(R.drawable.boder_match);
                        } else {
                            edtConfirmPassword.setBackgroundResource(R.drawable.boder_not_match);
                            edtPassword.setBackgroundResource(R.drawable.boder_not_match);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
            }
        }
        return true;
    }

    public void signUp(String fullName, String username, String password, String birthday, String phone, String email) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIRetrofit.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiSignUp = retrofit.create(APISignUp.class);

        Call<ResponseSignUpBody> call = apiSignUp.signUp(new SignupRequest(fullName, username, password, birthday, phone, email));
        call.enqueue(new Callback<ResponseSignUpBody>() {
            @Override
            public void onResponse(Call<ResponseSignUpBody> call, Response<ResponseSignUpBody> response) {
                ResponseSignUpBody token = response.body();
                if (token != null) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Login Fail!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSignUpBody> call, Throwable t) {

                Log.i("tag", "login fail");

            }
        });
    }

}
