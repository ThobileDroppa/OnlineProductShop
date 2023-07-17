package com.example.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class AdminPage extends AppCompatActivity {

    private LinearLayout addProductBtn ,viewMyProducts ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        addProductBtn = findViewById(R.id.add_admin_product_btn);
        viewMyProducts = findViewById(R.id.view_admin_product_btn);

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPage.this, AddProduct.class));
            }
        });

        viewMyProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPage.this,MySellingProdcts.class));
            }
        });
    }
}