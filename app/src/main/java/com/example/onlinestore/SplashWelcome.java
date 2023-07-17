package com.example.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashWelcome extends AppCompatActivity {

    private LottieAnimationView lottieWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_welcome);

        lottieWelcome = findViewById(R.id.lottieWelcome);

        lottieWelcome.animate().translationX(2000).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashWelcome.this, LoginActivity.class));

            }
        },4000);
    }
}