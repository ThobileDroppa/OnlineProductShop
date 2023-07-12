package com.example.onlinestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.onlinestore.model.OnBoardingClass;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;
    private LinearLayout linearLayout;
    private TextView skip_button;
    private ImageView next_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_actvity);

        linearLayout = findViewById(R.id.layoutOnBoardingIndicators);
        skip_button = findViewById(R.id.skip_textView);
        next_img = findViewById(R.id.next_button);

        setOnBoardingData();

        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(pagerAdapter);

        setOnBoardingIndicator();

        setCurrentOnBoardingIndicators(0);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicators(position);
            }
        });

        next_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPager2.getCurrentItem()+1<pagerAdapter.getItemCount()){
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);

                }else{

                    skip_button.setVisibility(View.INVISIBLE);
                }
            }
        });

        skip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    @SuppressLint("SetTextI18n")
    private void setCurrentOnBoardingIndicators(int num) {

        int childCount = linearLayout.getChildCount();

        for (int i =0;i<childCount;i++){
           ImageView imageView = (ImageView) linearLayout.getChildAt(i);

           if(i==num){
               imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.start_gradient));
           }else{
               imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.next_arrow));
           }

        }
    }

    private void setOnBoardingIndicator() {

        ImageView[] indicators = new ImageView[pagerAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(8,0,8,0);
        for(int i=0;i<indicators.length;i++){

            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.next_arrow));
            indicators[i].setLayoutParams(layoutParams);
            linearLayout.addView(indicators[i]);


        }

    }

    private void setOnBoardingData() {
        List<OnBoardingClass> onBoardingClassList = new ArrayList<>();

        OnBoardingClass welcome = new OnBoardingClass();
        welcome.setImageView(R.drawable.welcome_img);
        welcome.setTextView("WELCOME TO ONLINESHOP"+"\n"+"Where selling and shopping is made easy");

            OnBoardingClass uploadItem = new OnBoardingClass();
            uploadItem.setImageView(R.drawable.shop_logo);
            uploadItem.setTextView("Upload a picture and description" +"\n"+  "and start attracting customers");

            onBoardingClassList.add(welcome);
            onBoardingClassList.add(uploadItem);

    }
}