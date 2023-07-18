package com.example.onlinestore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinestore.model.Product;
import com.example.onlinestore.user.models.UserModel;
import com.example.onlinestore.utils.Credentials;
import com.example.onlinestore.utils.RealPathUtil;
import com.example.onlinestore.utils.UserApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddProduct extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final int GALLERY_CODE = 1;

    private ImageView addPhoto;

    private EditText name, description, quantity, price;
    private String uriString;

    private Button addProductBtn;

    private Uri imageUri;

    private ProductList productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        addPhoto = findViewById(R.id.add_photo_img);
        name = findViewById(R.id.name_fill_add_product_text);
        description = findViewById(R.id.description_fill_add_product_text);
        quantity = findViewById(R.id.quantity_add_product_EditText);
        price = findViewById(R.id.price_add_product_EditText);
        addProductBtn = findViewById(R.id.add_product_btn);

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
                galleryIntent.setType("image/*");

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                Intent chooser = new Intent(Intent.ACTION_CHOOSER);
                chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);
                chooser.putExtra(Intent.EXTRA_TITLE, "Select from:");

                Intent[] intentArray = {cameraIntent};
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooser, REQUEST_CODE);
                //onActivityResult();
            }
        });

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String productName = name.getText().toString();
                String desc = description.getText().toString();
                String productPrice = price.getText().toString();
                String productQuantity = quantity.getText().toString();


                    if(validateText(productName,desc,productPrice,productQuantity,uriString)){

                        //Product product = new Product(productName,desc,Integer.parseInt(productQuantity),Double.parseDouble(productPrice),"lets go");

                        createProduct(new Product(productName,desc,Integer.parseInt(productQuantity),Double.parseDouble(productPrice),uriString,new UserModel(null,null,null)));


                    }

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Products",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
            }
        });

    }

    private boolean validateText(String name,String desc,String price,String qty,String uri) {


        if(!TextUtils.isEmpty(name)||!TextUtils.isEmpty(desc)||
                !TextUtils.isEmpty(price)||!TextUtils.isEmpty(qty)
                   ||!TextUtils.isEmpty(uri)) {

            return true;

        }else {

            Toast.makeText(this, "Complete form!", Toast.LENGTH_SHORT).show();
            return  false;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                imageUri = data.getData();        // Getting the actual image path
                addPhoto.setImageURI(imageUri);  // Showing the image
               // uriString = imageUri.toString();
                Context context = AddProduct.this;
                uriString = RealPathUtil.getRealPath(context,imageUri);

                Bitmap bitmap = BitmapFactory.decodeFile(uriString);
            }
        }
    }


    private void createProduct(Product product) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Credentials.BASE_KEY)
                .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofit.create(UserApi.class);


        File file =  new File(uriString);
        //RequestBody requestFile = RequestBody.create(MediaType.parse(""))

        Call<Product> service = userApi.createProduct(Credentials.getToken() , product);

        service.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if (response.code() == 204) {
                    Log.d("Create Product", "Successful");
                    Log.d("Create Product", "Successful"+" product has been added");
                    Log.d("code response", "CODE: " + response.code());

                    startActivity(new Intent(AddProduct.this,AdminPage.class));
                    Toast.makeText(AddProduct.this, "Product Successfully Added To Market", Toast.LENGTH_SHORT).show();
                }else {

                    Log.d("code response", "CODE: " + response.code());
                    Log.d("code response", "Error: " + response.errorBody().toString());

                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

                Log.e("onFailure","ON FAILURE CALLED");

            }
        });

    }
}