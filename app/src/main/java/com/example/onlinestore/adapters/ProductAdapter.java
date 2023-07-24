package com.example.onlinestore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlinestore.model.ProductList;
import com.example.onlinestore.R;
import com.example.onlinestore.utils.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    private List<ProductList> listProducts;
    private Context context;

    public ProductAdapter(RecyclerViewInterface recyclerViewInterface, ArrayList<ProductList> listProducts, Context context) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.listProducts = listProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.product_view_layout,parent,false);
        return new ProductViewHolder(v,recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

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

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        public ImageView productImage,cartImg,plusSign,greenCart;
        private TextView price, title, quantity;
        private LinearLayout addToCart;

        public ProductViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            this.price = itemView.findViewById(R.id.product_price);
            this.quantity = itemView.findViewById(R.id.product_quantity);
            this.title = itemView.findViewById(R.id.product_title);
            this.productImage = itemView.findViewById(R.id.product_image);
            this.addToCart = itemView.findViewById(R.id.add_to_cart_layout);
            this.cartImg = itemView.findViewById(R.id.add_to_cart_image);
            this.plusSign = itemView.findViewById(R.id.add_to_cart_plus_sign);
            this.greenCart = itemView.findViewById(R.id.green_cart);

            itemView.findViewById(R.id.green_cart).setVisibility(View.INVISIBLE);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null){
                        int pos = getAdapterPosition();

                        if (pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

            itemView.findViewById(R.id.add_to_cart_layout).
                    setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    itemView.findViewById(R.id.add_to_cart_plus_sign).setVisibility(View.INVISIBLE);
                    itemView.findViewById(R.id.add_to_cart_image).setVisibility(View.INVISIBLE);
                    itemView.findViewById(R.id.green_cart).setVisibility(View.VISIBLE);


                    if(recyclerViewInterface!=null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onCartClick(pos);
                        }
                    }
                }
            });

            itemView.findViewById(R.id.green_cart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.findViewById(R.id.add_to_cart_plus_sign).setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.add_to_cart_image).setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.green_cart).setVisibility(View.INVISIBLE);

                    if(recyclerViewInterface!=null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onRemoveCartClick(pos);
                        }
                    }
                }
            });
        }



        }

    public List<ProductList> getAll(){ return  listProducts;}




}
