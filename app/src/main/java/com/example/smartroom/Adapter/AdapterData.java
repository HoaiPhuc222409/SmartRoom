package com.example.smartroom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroom.R;
import com.example.smartroom.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.ViewHolder> {

    ArrayList<Product> productList;
    Context context;

    public AdapterData(ArrayList<Product> productList,Context context) {
        this.productList = productList;
        this.context=context;
    }
public void updateList(ArrayList<Product> productList){
        this.productList=productList;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product=productList.get(position);
        holder.tvProductId.setText(product.getProductId());
        holder.tvValueName.setText(product.getValueName());
        holder.tvData.setText(product.getData());
        holder.tvTime.setText(product.getTime());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
    TextView tvProductId;
    TextView tvValueName;
    TextView tvData;
    TextView tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductId=itemView.findViewById(R.id.tvProductId);
            tvValueName=itemView.findViewById(R.id.tvValueName);
            tvData=itemView.findViewById(R.id.tvData);
            tvTime=itemView.findViewById(R.id.tvTime);
        }
    }
}
