package com.example.onlinestore.utils;

import android.database.Observable;

import com.example.onlinestore.model.CartItem;
import com.example.onlinestore.model.ProductList;
import com.example.onlinestore.model.Product;
import com.example.onlinestore.response.LoginSuccess;
import com.example.onlinestore.user.models.UserModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

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

    @GET("entity.product/viewCart")
    Call<List<CartItem>> viewCart(@Header("authentication")String token);

    /*@Multipart
    @POST("entity.product/createProduct")
    Observable<ResponseBody> updateProfile(@Part("product") Product product,
                                           @Part MultipartBody.Part image);*/

  /*  @POST
    Call<Product> createProduct(@Url String url, @Part MultipartBody.Part image,
                                @Body Product product);*/

    @POST("entity.product/removeFromCart/{id}")
    Call<ResponseBody> removeFromCart(@Header("authentication")String token,@Path("id") Long id);


    @GET("entity.product/getCartTotal")
    Call<Double> getCartTotal(@Header("authentication")String token);

    /*@POST
    @Path("updateCart")
    https://2557-41-76-201-165.ngrok-free.app/TryHere/resources/entity.product/updateCart
    public void updateFromCart(CartItem cartItem,@HeaderParam("authentication")String token)*/

    @POST("entity.product/updateCart/{id}/{numberoproduct}")
    Call<ResponseBody> updateCart(@Header("authentication")String token,@Path("id") Long productID,@Path("numberoproduct")int numberofProducts);

    @DELETE("remove/{id}")
    Call<ResponseBody> remove(@Header("authentication")String token,@Path("id") Long productID);



}
