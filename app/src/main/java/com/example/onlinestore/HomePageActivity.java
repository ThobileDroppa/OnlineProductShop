package com.example.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.adapters.ProductAdapter;
import com.example.onlinestore.model.ProductList;
import com.example.onlinestore.user.models.UserModel;
import com.example.onlinestore.utils.Credentials;
import com.example.onlinestore.utils.UserApi;
import com.example.onlinestore.utils.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
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

    private Button viewCart;

    private ArrayList<ProductList> myList;

    private ArrayList myList1;

    private LoaderDialog loaderDialog;

    private Menu menu;

    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_actvity);

        homepage = findViewById(R.id.home_layout);
        addProductIcon = findViewById(R.id.add_product_icon);
        myList = new ArrayList<>();
        myList1 = new ArrayList<>();
        recyclerView = findViewById(R.id.products_recyclerView);
        viewCart =findViewById(R.id.home_view_cart);
        /*addToCart = findViewById(R.id.add_to_cart_layout);*/

        loaderDialog = new LoaderDialog(this);
        viewCart.setVisibility(View.INVISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);
        loaderDialog.show();

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                loaderDialog.cancel();



            }
        };
        handler.postDelayed(runnable,3000);

        addProductIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, AdminPage.class));
            }
        });

        availableToShop();

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
                    if(myList.size()>=1){

                        loaderDialog.cancel();

                    }
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
                    Toast.makeText(HomePageActivity.this, "ADDED TO CART", Toast.LENGTH_SHORT).show();

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

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(HomePageActivity.this,ProductView.class);

        i.putExtra("Product",myList.get(position));


        startActivity(i);

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
                    Toast.makeText(HomePageActivity.this, "Removed From Cart", Toast.LENGTH_SHORT).show();

                    Log.d("Create User","Removed----Successful----------------");
                    Log.d("code response","CODE: "+response.code());



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

    @Override
    public void onCartClick(int pos) {

        addToCart(myList.get(pos));

        viewCart.setVisibility(View.VISIBLE);

        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this,CheckoutPage.class));
            }
        });

    }

    @Override
    public void onRemoveCartClick(int pos) {
        removeFromCart(myList.get(pos).getId());

    }
}

