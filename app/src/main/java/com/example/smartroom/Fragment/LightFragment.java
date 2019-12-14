package com.example.smartroom.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.smartroom.Adapter.AdapterData;
import com.example.smartroom.R;
import com.example.smartroom.api.APIRetrofit;
import com.example.smartroom.model.Product;
import com.example.smartroom.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class LightFragment extends Fragment implements View.OnClickListener{

    ImageView back;
    Button btn_getData;
    EditText edtProductNumber;

    ProductService productService;
    RecyclerView lightRecyclerView;
    AdapterData adapterData;

    public final String valueName="BULB_STATE";
    public static final String SharedReferencesFile = "sharedReferencesFile";
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
        edtProductNumber=view.findViewById(R.id.edtNumberOfProduct);
        lightRecyclerView=view.findViewById(R.id.rclProduct);

        adapterData=new AdapterData(new ArrayList<Product>(),getContext());
        lightRecyclerView.setAdapter(adapterData);
        lightRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        back.setOnClickListener(this);
        btn_getData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBackLight:{
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                fragmentManager.popBackStack();
                transaction.commit();
                break;
            }
            case R.id.btnGetLight:{
                String numberProduct = edtProductNumber.getText().toString();
                SharedPreferences sharedPreferences=getContext().getSharedPreferences(SharedReferencesFile, Context.MODE_PRIVATE);
                String token=sharedPreferences.getString("token","");
                getDataLight(token,valueName,numberProduct);
                break;
            }
        }
    }

    public void getDataLight(String token, String valueName, String numberProduct) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIRetrofit.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        productService = retrofit.create(ProductService.class);

        Call<ArrayList<Product>> call = productService.getProducts(token, valueName, numberProduct);
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                ArrayList<Product> products=new ArrayList<>();
                products = response.body();
                if (products!=null){
                    adapterData.updateList(products);
                    adapterData.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
            }
        });
    }
}
