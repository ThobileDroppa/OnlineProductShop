package com.example.onlinestore.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.ProductList;
import com.example.onlinestore.utils.RecyclerViewInterface;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {



    private List<ProductList> listProducts;
    private Context context;

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }


    public static class CheckoutViewHolder extends RecyclerView.ViewHolder{

        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
