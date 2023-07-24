package com.example.onlinestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinestore.model.CartItem;
import com.example.onlinestore.model.Product;
import com.example.onlinestore.model.ProductList;
import com.example.onlinestore.utils.Credentials;
import com.example.onlinestore.utils.UserApi;
import com.squareup.picasso.Picasso;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckoutPageView extends AppCompatActivity {

    private ImageView image,increaseBtn,decreaseBtn,removeBtn;

    private Integer maxQuantity;

    private TextView title,desc,price,quantity,inStock;

    private LinearLayout continueBtn;

    int num=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page_view);

        title = findViewById(R.id.view_product_title);
        desc = findViewById(R.id.product_description_view);
        price = findViewById(R.id.view_product_price);
        quantity = findViewById(R.id.qty_checkout_view);
        inStock = findViewById(R.id.stock_view_checkout_quantity);
        image = findViewById(R.id.img_checkout_product);
        decreaseBtn = findViewById(R.id.decrease_btn);
        increaseBtn = findViewById(R.id.increase_btn);
        removeBtn = findViewById(R.id.removeBtn);
        continueBtn = findViewById(R.id.continue_to_payment);

        Intent i = getIntent();

        CartItem cartItem = i.getParcelableExtra("cartItem");
        //cartItem.getProductList().getUserModel().

        title.setText(cartItem.getProductList().getNameofProduct());
        desc.setText(cartItem.getProductList().getDescription());
        price.setText(String.valueOf(cartItem.getProductList().getPrice()));
        inStock.setText(String.valueOf(cartItem.getProductList().getQuantity()));
        quantity.setText(String.valueOf(1));
        maxQuantity = cartItem.getProductList().getQuantity();

        Picasso
                .get()
                .load(cartItem.getProductList().getImage())
                .into(image);


            increaseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                 if(num<=(maxQuantity-1)){
                        num++;

                        quantity.setText(String.valueOf(num));
                        Double newPrice = cartItem.getProductList().getPrice()*(num);
                        price.setText(String.valueOf(newPrice));
                 //cartItem.getProductList().setPrice(newPrice);
                 }


                }
            });

            decreaseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(num!=0){
                        num--;

                        quantity.setText(String.valueOf(num));
                        price.setText(String.valueOf((cartItem.getProductList().getPrice())*num));
                    }



                }
            });

        System.out.println("---------------------ID "+cartItem.getProductList().getId()+" -----------");

            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeFromCart(cartItem.getProductList().getId());
                }
            });

            continueBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // System.out.println("=================================A"+cartItem);
                    cartItem.setNumItems(num);
                    updateCart(cartItem.getProductList().getId(), cartItem.getNumItems());
                }
            });


    }

    private void removeFromCart(Long id) {

        Retrofit retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(Credentials.BASE_KEY)
                        .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofitBuilder.create(UserApi.class);
        Call<ResponseBody> user = userApi.removeFromCart(Credentials.getToken(), id);

        user.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==204){
                    Toast.makeText(CheckoutPageView.this, "Removed From Cart", Toast.LENGTH_SHORT).show();

                    Log.d("Create User","Removed----Successful----------------");
                    Log.d("code response","CODE: "+response.code());

                    startActivity(new Intent(CheckoutPageView.this, CheckoutPage.class));

                }else{

                    Log.d("Create User","----ELSE----------------");
                    Log.d("code response","CODE: "+response.code());
                    Log.d("code response","CODE: "+response.errorBody().toString());

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.d("Create User","----ON FAILURE----------------");

            }
        });
    }

    private void updateCart(Long id,int numProducts) {

        //System.out.println("============================="+productList.getId());
        Retrofit retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(Credentials.BASE_KEY)
                        .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofitBuilder.create(UserApi.class);
        Call<ResponseBody> user = userApi.updateCart(Credentials.getToken(),id,numProducts);

        user.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 204) {

                    Log.d("Create User","UPDATED----Successful----------------");
                    Log.d("code response","CODE: "+response.code());





                    startActivity(new Intent(CheckoutPageView.this, CheckoutPage.class));

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Create User","----ON FAILURE----------------");

            }
        });

    }
}