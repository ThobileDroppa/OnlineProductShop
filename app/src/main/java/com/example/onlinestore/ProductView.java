package com.example.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinestore.model.ProductList;
import com.example.onlinestore.utils.Credentials;
import com.example.onlinestore.utils.UserApi;
import com.squareup.picasso.Picasso;

import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ProductView extends AppCompatActivity {

    private ImageView image;

    private TextView title,desc,price,quantity;

    private  Timer timer;

    private Button addToCartBtn,proceedToCheckout,continueShoppingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        image = findViewById(R.id.view_product_image);
        title = findViewById(R.id.view_product_title);
        desc = findViewById(R.id.product_description_view);
        price = findViewById(R.id.view_product_price);
        quantity = findViewById(R.id.view_product_quantity);

        //Buttons
        addToCartBtn = findViewById(R.id.add_product_to_cart_btn);
        proceedToCheckout = findViewById(R.id.proceed_to_checkout_btn);
        continueShoppingBtn = findViewById(R.id.continue_shopping_btn);

        continueShoppingBtn.setVisibility(View.INVISIBLE);
        proceedToCheckout.setVisibility(View.INVISIBLE);

        Intent i = getIntent();

        /*String titles = i.getStringExtra("NAME");
        String descs = i.getStringExtra("DESC");
        String prices = i.getStringExtra("PRICE");
        String qty = i.getStringExtra("QUANTITY");
        String imgs = i.getStringExtra("IMAGE");
        String jObject = i.getStringExtra("OBJECT");
*/
        ProductList product1 = i.getParcelableExtra("Product");


        title.setText(product1.getNameofProduct());
        desc.setText(product1.getDescription());
        price.setText(String.valueOf(product1.getPrice()));
        quantity.setText(String.valueOf(product1.getQuantity()));

        Picasso
                .get()
                .load(product1.getImage())
                .into(image);


        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(ProductView.this, "ADDED TO CART", Toast.LENGTH_SHORT).show();
                addToCart(product1);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        continueShoppingBtn.setVisibility(View.VISIBLE);
                        proceedToCheckout.setVisibility(View.VISIBLE);
                    }
                }, 3000);

            }
        });

        continueShoppingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductView.this,HomePageActivity.class));
            }
        });

        proceedToCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductView.this,CheckoutPage.class));
            }
        });

    }

    private void addToCart(ProductList product) {

        Retrofit retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(Credentials.BASE_KEY)
                        .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofitBuilder.create(UserApi.class);
        Call<ProductList> user = userApi.addToCart(Credentials.getToken(), product);

        user.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {

                if(response.code() == 204){
                    Toast.makeText(ProductView.this, "ADDED TO CART", Toast.LENGTH_SHORT).show();

                    Log.d("Create User","Successful");
                    Log.d("code response","CODE: "+response.code());

                }else {

                    Log.e("ERROR", "CODE- " + response.code());
                    Log.e("ERROR", "CODE IS NOT 200");
                    System.out.println(response.errorBody().toString());
                    System.out.println(response.errorBody().toString());

                }

            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {

            }
        });

    }
}