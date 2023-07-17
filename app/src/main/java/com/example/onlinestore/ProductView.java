package com.example.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ProductView extends AppCompatActivity {

    private ImageView image;

    private TextView title,desc,price,quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        image = findViewById(R.id.view_product_image);
        title = findViewById(R.id.view_product_title);
        desc = findViewById(R.id.product_description_view);
        price = findViewById(R.id.view_product_price);
        quantity = findViewById(R.id.view_product_quantity);

        Intent i = getIntent();

        String titles = i.getStringExtra("NAME");
        String descs = i.getStringExtra("DESC");
        String prices = i.getStringExtra("PRICE");
        String qty = i.getStringExtra("QUANTITY");
        String imgs = i.getStringExtra("IMAGE");

        title.setText(titles);
        desc.setText(descs);
        price.setText(prices);
        quantity.setText(qty);

        Picasso
                .get()
                .load(imgs)
                .into(image);

        //image.



    }
}