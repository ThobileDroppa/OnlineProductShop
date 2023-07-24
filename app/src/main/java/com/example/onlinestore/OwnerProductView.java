package com.example.onlinestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

public class OwnerProductView extends AppCompatActivity {

    private ImageView image;

    private TextView title,desc,price,quantity;


    private Button removeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_product_view);

        image = findViewById(R.id.owner_view_product_image);
        title = findViewById(R.id.owner_view_product_title);
        desc = findViewById(R.id.owner_product_description_view);
        price = findViewById(R.id.owner_view_product_price);
        quantity = findViewById(R.id.owner_view_product_quantity);
        removeBtn = findViewById(R.id.delete_product_btn);

        Intent i = getIntent();


        ProductList product1 = i.getParcelableExtra("Product");


        title.setText(product1.getNameofProduct());
        desc.setText(product1.getDescription());
        price.setText(String.valueOf(product1.getPrice()));
        quantity.setText(String.valueOf(product1.getQuantity()));

        Picasso
                .get()
                .load(product1.getImage())
                .into(image);

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(product1.getId());
            }
        });
    }

    private void remove(Long id) {
        Retrofit retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(Credentials.BASE_KEY)
                        .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofitBuilder.create(UserApi.class);
        Call<ResponseBody> user = userApi.remove(Credentials.getToken(),id);

        user.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               if(response.code()==200){

                   Log.e("Successful", "=== " + response.toString());
               }else{
                   Log.e("ERROR", "CODE- " + response.code());
                   Log.e("ERROR", "CODE IS NOT 200");
                   System.out.println(response.errorBody().toString());

               }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.e("ON FAILURE", "ON FAILURE IS CALLED ");
                Log.e("TAG_string", "onResponse:_ " + t.getMessage(),t);

            }
        });
    }
}