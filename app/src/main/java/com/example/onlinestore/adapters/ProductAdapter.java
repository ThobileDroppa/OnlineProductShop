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
import com.example.onlinestore.R;
import com.example.onlinestore.model.Product;
import com.example.onlinestore.utils.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    private List<Product> listProducts;
    private Context context;

    /*public ProductAdapter(RecyclerViewInterface recyclerViewInterface) {
       // this.recyclerViewInterface = recyclerViewInterface;
        this.listProducts = listProducts;
        this.context = context;
    }*/

    public ProductAdapter(RecyclerViewInterface recyclerViewInterface, List<Product> listProducts, Context context) {
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
        //return listProducts.length;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        public ImageView productImage;
        private TextView price, title, quantity;
        private LinearLayout addToCart;

        public ProductViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            this.price = itemView.findViewById(R.id.product_price);
            this.quantity = itemView.findViewById(R.id.product_quantity);
            this.title = itemView.findViewById(R.id.product_title);
            this.productImage = itemView.findViewById(R.id.product_image);
            this.addToCart = itemView.findViewById(R.id.add_to_cart_layout);

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
        }



    }

    public List<Product> getAll(){ return  listProducts;}

    public  List<Product> getSelected() {
        List<Product> selected = new ArrayList<>();
        for (int i = 0; i < listProducts.size(); i++) {
            if (listProducts.get(i).isChecked()) {
                selected.add(listProducts.get(i));
            }
        }
        return selected;
    }


}
