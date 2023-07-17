    package com.example.onlinestore;

    import android.content.Intent;
    import android.os.Bundle;
    import android.text.TextUtils;
    import android.util.Log;
    import android.util.Patterns;
    import android.view.View;
    import android.widget.Button;
    import android.widget.LinearLayout;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;

    import com.example.onlinestore.response.LoginSuccess;
    import com.example.onlinestore.utils.Credentials;
    import com.example.onlinestore.utils.UserApi;
    import com.google.android.material.textfield.TextInputEditText;
    import com.google.gson.Gson;
    import com.google.gson.GsonBuilder;

    import java.io.IOException;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;
    import retrofit2.Retrofit;
    import retrofit2.converter.gson.GsonConverterFactory;

    public class LoginActivity extends AppCompatActivity {

    private LinearLayout signUpText;

    private TextInputEditText email,password;

    private Button loginBtn;

    private String access = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvity);

        signUpText= findViewById(R.id.sign_up_texView);
        email =findViewById(R.id.email_login_text);
        password = findViewById(R.id.password_login_text);
        loginBtn = findViewById(R.id.login_btn);


              loginBtn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      String emails = email.getText().toString();
                      String passwords  = password.getText().toString();

                      if(TextUtils.isEmpty(emails) ){
                          email.setError("Email is compulsory");

                      }

                      if(TextUtils.isEmpty(passwords)){

                          password.setError("Password is compulsory");
                      }


                      if(validateText(emails,passwords)&&validateEmail(emails)){

                          loginUser(emails,passwords);
                         // startActivity(new Intent(LoginActivity.this,SplashScreen.class));
                      }

                  }
              });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
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

        private boolean validateText(String emails,String passwords) {


            if(!TextUtils.isEmpty(emails)&&!TextUtils.isEmpty(passwords)) {

                return true;

            }else {

                Toast.makeText(this, "Fill in Text! ", Toast.LENGTH_SHORT).show();
                return  false;
            }

        }

    private void loginUser(String email,String password){


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Credentials.BASE_KEY)
                .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofit.create(UserApi.class);

        Call<LoginSuccess> service = userApi.loginUser(email,password);


        service.enqueue(new Callback<LoginSuccess>() {
            @Override
            public void onResponse(Call<LoginSuccess> call, Response<LoginSuccess> response) {

                //System.out.println("OUTCOME"+response.code()+" ----"+response.body().toString());

                if(response.code()==200){
                    //loginUser(email, password);
                    access = "correct";
                    Log.d("Successful","Successful" + response.body().getEmail());
                    Log.d("Successful","Successful" + response.body().getToken());
                    Log.e("Successful", "=== " + response.toString());

                    Credentials.setToken(response.body().getToken());

                    //String result = response.body()
                    System.out.println("Response Body Result= "+response.body().getClass().toString());


                    //startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
                    startActivity(new Intent(LoginActivity.this, SplashScreen.class));

                }else {
                    Log.e("Error",String.valueOf(response.code()));


                    try {
                        Log.e("TAG_string", "onResponse:_ " + response.errorBody().string());
                    } catch (IOException e) {
                        Log.e("TAG_string", "onResponse: __" + e.getMessage());
                    }

                }

            }

            @Override
            public void onFailure(Call<LoginSuccess> call, Throwable t) {

                System.out.println("ON FAILURE CALLED");
                Log.e("TAG_string", "onResponse:_ " + t.getMessage(),t);
                //System.out.println("CODE RETURNED: "+String.valueOf(response.code()));

            }




        });




    }
}