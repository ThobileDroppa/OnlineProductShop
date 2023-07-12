package com.example.onlinestore.model;

import android.widget.TextView;

public class OnBoardingClass {

    private String textView;
    private int imageView;

    public OnBoardingClass() {
    }

    public OnBoardingClass(String linearLayout, int imageView) {
        this.textView = linearLayout;
        this.imageView = imageView;
    }

    public String getTextView() {
        return textView;
    }

    public void setTextView(String linearLayout) {
        this.textView = linearLayout;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }
}
