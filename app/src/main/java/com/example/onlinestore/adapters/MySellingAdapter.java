package com.example.onlinestore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlinestore.R;
import com.example.onlinestore.model.Product;
import com.example.onlinestore.utils.RecyclerViewInterface;

import java.util.List;

public class MySellingAdapter extends RecyclerView.Adapter<MySellingAdapter.SellingViewHolder>{

    private final RecyclerViewInterface recyclerViewInterface;

    private List<Product> listProducts;
    private Context context;

            public MySellingAdapter(RecyclerViewInterface recyclerViewInterface, List<Product> listProducts, Context context) {
                this.recyclerViewInterface = recyclerViewInterface;
                this.listProducts = listProducts;
                this.context = context;
            }

            @NonNull
            @Override
            public MySellingAdapter.SellingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v;
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                v = layoutInflater.inflate(R.layout.selling_products_layout,parent,false);
                return new MySellingAdapter.SellingViewHolder(v,recyclerViewInterface);
            }

            @Override
            public void onBindViewHolder(@NonNull MySellingAdapter.SellingViewHolder holder, int position) {

                holder.price.setText(String.valueOf(listProducts.get(position).getPrice()));
                holder.quantity.setText(String.valueOf(listProducts.get(position).getQuantity()));
                holder.title.setText(listProducts.get(position).getNameofProduct());

                Glide.with(context)
                        .load(listProducts.get(position).getImage())
                        //.placeholder()
                        .fitCenter()
                        .into(holder.productImage);

            }

            @Override
            public int getItemCount() {
                return listProducts.size();
            }

    public static class SellingViewHolder extends RecyclerView.ViewHolder {

        public ImageView productImage;
        private TextView price, title, quantity;

        public SellingViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            this.price = itemView.findViewById(R.id.sell_product_price);
            this.quantity = itemView.findViewById(R.id.sell_product_quantity);
            this.title = itemView.findViewById(R.id.selling_product_title);
            this.productImage = itemView.findViewById(R.id.sell_product_image);

        }
    }
        }
