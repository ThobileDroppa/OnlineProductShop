package com.example.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinestore.user.models.UserModel;
import com.example.onlinestore.utils.Credentials;
import com.example.onlinestore.utils.UserApi;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    private EditText name,email,password;

    private TextInputLayout text1;
    private List<String> emailList;

    private Button createUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name_create_text);
        email = findViewById(R.id.email_create_text);
        password = findViewById(R.id.password_create_text);
        createUserBtn = findViewById(R.id.create_user_btn);

        String names = name.getText().toString();
        String passwords = password.getText().toString();
        String emails = email.getText().toString();



         emailList = new ArrayList<>();

        System.out.println(name.getText().toString());
        System.out.println(email.getText().toString());
        System.out.println(password.getText().toString());


        //UserModel userModel = new UserModel(names,emails,passwords);

        //email.

        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String names = name.getText().toString();
                String passwords = password.getText().toString();
                String emails = email.getText().toString().trim();

                if (TextUtils.isEmpty(names)||names.length()<2) {
                    name.setError("Name is compulsory");

                    return;
                }

                if (TextUtils.isEmpty(emails)) {
                    email.setError("Email is compulsory");
                    return;
                }

                if (TextUtils.isEmpty(passwords)) {
                    password.setError("Password is compulsory");
                    return;
                }

               if(validateText(names,emails,passwords)
                       &&validateEmail(emails)) {
                        //&&checkEmailList(emails)

                   //startActivity(new Intent(SignUpActivity.this, HomePageActivity.class));

                   createUser(new UserModel(names, emails, passwords));

                   //startActivity(new Intent(SignUpActivity.this, SplashWelcome.class));

               }

                System.out.println(name.getText().toString());
                System.out.println(email.getText().toString());
                System.out.println(password.getText().toString());
            }
        });
    }

    private boolean checkEmailList(String emails) {

        boolean check = false;

        for(int i=0;i<emails.length();i++) {

            if(emails.equalsIgnoreCase(emailList.get(i))){

                check = false;
                Toast.makeText(this, "User Already Exists!", Toast.LENGTH_SHORT).show();
            }else{
                check = true;
            }
        }

        return check;
    }

    private boolean validateEmail(String emails) {

        boolean check = true;

        if(Patterns.EMAIL_ADDRESS.matcher(emails).matches()){


           check = true;
        }else{
            Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
            check = false;
        }

        return check;
    }

    private boolean validateText(String names,String emails,String passwords) {


        if(!TextUtils.isEmpty(names)&&!TextUtils.isEmpty(emails)&&!TextUtils.isEmpty(passwords)) {

            return true;

        }else {

            Toast.makeText(this, "Fill in Text! ", Toast.LENGTH_SHORT).show();
           return  false;
        }

    }

    private void createUser(UserModel userModel){

         Retrofit retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(Credentials.BASE_KEY)
                        .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofitBuilder.create(UserApi.class);
        Call<UserModel> user = userApi.createUser(userModel);

        user.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(response.code()==204){
                   //createUser(userModel);
                   Log.d("Create User","Successful");
                   Log.d("code response","CODE: "+response.code());

                   emailList.add(userModel.getEmail());
                    startActivity(new Intent(SignUpActivity.this, SplashWelcome.class));

                }else{
                    Log.e("ERROR","FAILED");
                    Log.e("code",String.valueOf(response.code())+response.errorBody().toString());
                    System.out.println("ELSE IS TRIGGERED");
                    System.out.println("CODE RETURNED: "+String.valueOf(response.code()));
                }

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

                Log.e("ERROR","FAILED");
                Log.e("code",t.toString());
                System.out.println("ON FAILURE METHOD IS CALLED");
                System.out.println(t.getCause());
                System.out.println(t.getCause());

            }
        });
    }
}