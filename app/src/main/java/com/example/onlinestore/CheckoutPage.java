package com.example.onlinestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.onlinestore.adapters.CheckoutAdapter;
import com.example.onlinestore.model.CartItem;
import com.example.onlinestore.utils.Credentials;
import com.example.onlinestore.utils.RecyclerViewInterface;
import com.example.onlinestore.utils.UserApi;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckoutPage extends AppCompatActivity implements RecyclerViewInterface{

    private ArrayList<CartItem> cartList;
    private int maxQ;

    int numItems;

    CheckoutAdapter checkoutAdapter;

    private RecyclerView recyclerView;
    Double cartTotal;

    private LinearLayout proceed;

    private TextView cartTotalText,numItemsText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page);

        cartList = new ArrayList<>();
        recyclerView = findViewById(R.id.cart_recyclerView);
        cartTotalText = findViewById(R.id.cart_total);
        //numItemsText = findViewById(R.id.cart_num_items);
        proceed = findViewById(R.id.proceed_Layout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(checkoutAdapter);
        viewCart();
        getCartTotal();

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CheckoutPage.this,PaymentActvity.class);
                startActivity(i);
            }
        });

    }

    private void getCartTotal(){

        Retrofit retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(Credentials.BASE_KEY)
                        .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofitBuilder.create(UserApi.class);

        Call<Double> user = userApi.getCartTotal(Credentials.getToken());

        user.enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {

                if(response.code()==200){

                    cartTotal = response.body();
                    Log.e("Successful", "=== " + response.toString());
                    System.out.println("-------Total-----"+cartTotal);
                    cartTotalText.setText(String.valueOf(cartTotal));

                }else{

                    System.out.println("----------------------------------HERE "+response.code());
                    Log.e("ERROR", "CODE- " + response.code());
                    Log.e("ERROR", "CODE IS NOT 200");

                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {

                Log.e("ON FAILURE", "ON FAILURE IS CALLED ");
                Log.e("TAG_string", "onResponse:_ " + t.getMessage(),t);

            }
        });
    }

    private void viewCart(){

        Retrofit retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(Credentials.BASE_KEY)
                        .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofitBuilder.create(UserApi.class);

        Call<List<CartItem>> user = userApi.viewCart(Credentials.getToken());

        user.enqueue(new Callback<List<CartItem>>() {
            @Override
            public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {

                if (response.code() == 200) {
                    System.out.println("--------------VIEW CART--------------------HERE ---"+response.code());


                    cartList = (ArrayList<CartItem>) response.body();

                    if(cartList.size()>0){
                        putDataIntoRecyclerView(cartList);

                    }else{

                    }



                    Log.e("Successful", "=== " + response.toString());
                }else{
                    System.out.println("----------------------------------HERE "+response.code());
                    Log.e("ERROR", "CODE- " + response.code());
                    Log.e("ERROR", "CODE IS NOT 200");
                    //System.out.println(response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<List<CartItem>> call, Throwable t) {

                Log.e("ON FAILURE", "ON FAILURE IS CALLED ");
                Log.e("TAG_string", "onResponse:_ " + t.getMessage(),t);

            }
        });
    }

    private void putDataIntoRecyclerView(List<CartItem> productList){

        CheckoutAdapter checkoutAdapter= new CheckoutAdapter((RecyclerViewInterface) this, cartList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(checkoutAdapter);
    }

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(CheckoutPage.this,CheckoutPageView.class);

        i.putExtra("cartItem",cartList.get(position));


        startActivity(i);

    }

    @Override
    public void onCartClick(int pos) {

    }

    @Override
    public void onRemoveCartClick(int pos) {

    }
}