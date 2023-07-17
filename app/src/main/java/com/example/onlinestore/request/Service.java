package com.example.onlinestore.request;

import com.example.onlinestore.utils.Credentials;
import com.example.onlinestore.utils.UserApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    public static Retrofit.Builder retrofitBuilder =
             new Retrofit.Builder()
                     .baseUrl(Credentials.BASE_KEY)
                     .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static UserApi userApi = retrofit.create(UserApi.class);

    public UserApi getUserApi(){
        return userApi;
    }

}
