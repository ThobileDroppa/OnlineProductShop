package com.example.onlinestore.utils;

import com.example.onlinestore.ProductList;
import com.example.onlinestore.model.Product;
import com.example.onlinestore.response.LoginSuccess;
import com.example.onlinestore.user.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @POST("entity.useraccount/create")
    Call<UserModel> createUser(@Body UserModel userModel);

    @GET("entity.useraccount/login/{email}/{password}")
    Call<LoginSuccess> loginUser(@Path("email") String email, @Path("password") String password);

    @POST("entity.product/createProduct")
    Call<Product> createProduct(@Header("authentication")String token,@Body Product product);

    @GET("entity.product/availableToShop")
    Call<List<ProductList>> availableToShop(@Header("authentication")String token);

    @GET("entity.product/mySellingProducts")
    Call<List<ProductList>> mySellingProducts(@Header("authentication")String token);

    @POST("entity.product/addToCart")
    Call<ProductList> addToCart(@Header("authentication")String token,@Body ProductList product);

   /* @POST("entity.useraccount/create")
    Call<UserModel> createUser(@Body UserModel userModel);*/



}
