package com.example.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    private LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        lottie = findViewById(R.id.lottie);

        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this, HomePageActivity.class));

            }
        }, 4000);

        //availableToShop();
    }

    /*private void availableToShop() {

        Retrofit retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(Credentials.BASE_KEY)
                        .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofitBuilder.create(UserApi.class);
        Call<List<Product>> user = userApi.availableToShop(Credentials.getToken());

        user.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.code() == 200) {

                    for (int i = 0; i < response.body().size(); i++) {

                        //myList.set(i,response.body().get(i));

                        Log.d("Successful", "Successful-" + response.body().get(i).getNameofProduct());
                        Log.d("Successful", "Successful-" + response.body().get(i).getDescription());
                        Log.d("Successful", "Successful-" + response.body().get(i).getQuantity());
                        Log.d("Successful", "Successful-" + response.body().get(i).getPrice());
                        Log.d("Successful", "Successful--" + response.body().get(i).getImage());

                    }

                    Credentials.setMyList(response.body());

                    Product[] listData = new Product[]{

                            new Product(Credentials.getMyList().get(1).getNameofProduct(), Credentials.getMyList().get(1).getDescription()
                                    , Credentials.getMyList().get(1).getQuantity()
                                    , Credentials.getMyList().get(1).getPrice()
                                    , Credentials.getMyList().get(1).getImage()),
                            new Product(Credentials.getMyList().get(0).getNameofProduct(), Credentials.getMyList().get(0).getDescription()
                                    , Credentials.getMyList().get(0).getQuantity()
                                    , Credentials.getMyList().get(0).getPrice()
                                    , Credentials.getMyList().get(0).getImage()),


                    };

                    // Log.d("Successful","Successful" + response.body());
                    Log.e("Successful", "=== " + response.toString());

                } else {
                    Log.e("ERROR", "CODE- " + response.code());
                    Log.e("ERROR", "CODE IS NOT 200");
                    System.out.println(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                Log.e("ON FAILURE", "ON FAILURE IS CALLED ");

            }
        });
    }*/

}