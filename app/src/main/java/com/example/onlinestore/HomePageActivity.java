package com.example.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.adapters.ProductAdapter;
import com.example.onlinestore.model.Product;
import com.example.onlinestore.utils.Credentials;
import com.example.onlinestore.utils.UserApi;
import com.example.onlinestore.utils.RecyclerViewInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePageActivity extends AppCompatActivity implements RecyclerViewInterface {

    private LinearLayout homepage;
    private RecyclerView recyclerView;

    private LinearLayout addToCart;

    private ImageView addProductIcon;

    private ArrayList<ProductList> myList;

    private Menu menu;

    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_actvity);

        homepage = findViewById(R.id.home_layout);
        addProductIcon = findViewById(R.id.add_product_icon);
        myList = new ArrayList<>();
        recyclerView = findViewById(R.id.products_recyclerView);
        /*addToCart = findViewById(R.id.add_to_cart_layout);*/


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        addProductIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, AdminPage.class));
            }
        });

        availableToShop();

        createListOfData();



    }

    private void createListOfData(){

        ArrayList<Product> cartList =  new ArrayList<>();

        for (int i = 0; i < myList.size() ; i++){
            Product product= new Product();

            product.setNameofProduct(myList.get(i).getNameofProduct());
            product.setPrice(myList.get(i).getPrice());
            product.setDescription(myList.get(i).getDescription());

            if (i == 0 ){
                product.setChecked(true);
            }

           cartList.add(product);

            System.out.println("CART LIST" + cartList.get(i).getNameofProduct());
        }

        /*productAdapter.setProducts(myList);*/

    }



    private void availableToShop() {

        Retrofit retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(Credentials.BASE_KEY)
                        .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofitBuilder.create(UserApi.class);
        Call<List<ProductList>> user = userApi.availableToShop(Credentials.getToken());

        user.enqueue(new Callback<List<ProductList>>() {
            @Override
            public void onResponse(Call<List<ProductList>> call, Response<List<ProductList>> response) {
                if (response.code() == 200) {

                    myList = (ArrayList<ProductList>) response.body();
                    putDataIntoRecyclerView(myList);

                   /* myList = new ArrayList<>(productList.get());
                    putDataIntoRecyclerView(myList);*/

                    // Log.d("Successful","Successful" + response.body());
                    Log.e("Successful", "=== " + response.toString());

                } else {
                    Log.e("ERROR", "CODE- " + response.code());
                    Log.e("ERROR", "CODE IS NOT 200");
                    System.out.println(response.errorBody().toString());
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

    private void putDataIntoRecyclerView(ArrayList<ProductList> productList){

        ProductAdapter productAdapter1 = new ProductAdapter(this, myList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter1);
    }

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(HomePageActivity.this,ProductView.class);

        i.putExtra("Product",myList.get(position));


        startActivity(i);

    }
}

