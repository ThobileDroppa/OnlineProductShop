package com.example.onlinestore;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.adapters.MySellingAdapter;
import com.example.onlinestore.adapters.ProductAdapter;
import com.example.onlinestore.model.Product;
import com.example.onlinestore.utils.Credentials;
import com.example.onlinestore.utils.RecyclerViewInterface;
import com.example.onlinestore.utils.UserApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MySellingProdcts extends AppCompatActivity implements RecyclerViewInterface{

    private LinearLayout homepage;
    private RecyclerView recyclerView;

    private LinearLayout addToCart;

    private ImageView addProductIcon;

    private ArrayList<ProductList> myList;

    private Menu menu;

    MySellingAdapter sellingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_selling_prodcts);

        myList = new ArrayList<>();
        recyclerView = findViewById(R.id.selling_products_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sellingAdapter);

        mySellingProducts();
    }

    private void mySellingProducts() {

        Retrofit retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(Credentials.BASE_KEY)
                        .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofitBuilder.create(UserApi.class);
        Call<List<ProductList>> user = userApi.mySellingProducts(Credentials.getToken());

        user.enqueue(new Callback<List<ProductList>>() {
            @Override
            public void onResponse(Call<List<ProductList>> call, Response<List<ProductList>> response) {
                if (response.code() == 200) {
                    System.out.println("----------------------------------HERE ---"+response.code());
                    myList = (ArrayList<ProductList>) response.body();
                    putDataIntoRecyclerView(myList);
                    Log.e("Successful", "=== " + response.toString());
                }else{


                    System.out.println("----------------------------------HERE "+response.code());
                    Log.e("ERROR", "CODE- " + response.code());
                    Log.e("ERROR", "CODE IS NOT 200");
                    System.out.println(response.errorBody().toString());

                }
            }

            @Override
            public void onFailure(Call<List<ProductList>> call, Throwable t) {

                Log.e("ON FAILURE", "ON FAILURE IS CALLED ");
                Log.e("TAG_string", "onResponse:_ " + t.getMessage(),t);

            }
        });
    }

                private void putDataIntoRecyclerView(List<ProductList> productList){

                    ProductAdapter productAdapter1 = new ProductAdapter( this, myList,this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(productAdapter1);
                }

    @Override
    public void onItemClick(int position) {

    }
}