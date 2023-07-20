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
import com.example.onlinestore.model.CartItem;
import com.example.onlinestore.R;
import com.example.onlinestore.utils.RecyclerViewInterface;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {


    private final RecyclerViewInterface recyclerViewInterface;
    private List<CartItem> cartItems;
    private Context context;

    int maxQ;

    public CheckoutAdapter(RecyclerViewInterface recyclerViewInterface, List<CartItem> cartItems, Context context) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.cartItems = cartItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.checkout_view,parent,false);
        return new CheckoutAdapter.CheckoutViewHolder(v,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutAdapter.CheckoutViewHolder holder, int position) {

        holder.price.setText(String.valueOf(cartItems.get(position).getProductList().getPrice()));
        holder.name.setText(cartItems.get(position).getProductList().getNameofProduct());

        Glide.with(context)
                .load(cartItems.get(position).getProductList().getImage())
                //.placeholder()
                .fitCenter()
                .into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }


    public static class CheckoutViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage,editImg;

        private TextView price,name,quantity,maxQ;


        int qty = 0;


        //private

        public CheckoutViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            this.price = itemView.findViewById(R.id.price_checkout_product_TextView);
            this.name = itemView.findViewById(R.id.name_checkout_product_TextView);
            this.editImg = itemView.findViewById(R.id.edit_pencil_checkout);
            this.productImage = itemView.findViewById(R.id.checkout_product_image);


            itemView.findViewById(R.id.edit_pencil_checkout)
                    .setOnClickListener(new View.OnClickListener() {
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
}
