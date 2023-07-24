package com.example.onlinestore.utils;

import com.example.onlinestore.model.Product;

import java.util.List;

public class Credentials {

    //public static final String BASE_KEY = "https://9f6b-41-76-201-165.ngrok-free.app/TryHere/resources/";

    public static final String BASE_KEY = "https://66ca-41-76-201-165.ngrok-free.app/TryHere/resources/";

    /*public static final String BASE_KEY_PRODUCTS = "https://6470-41-150-224-190.ngrok-free.app/TryHere/resources/";*/

    public static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Credentials.token = token;
    }

    public static List<Product> myList;

    public static List<Product> getMyList() {
        return myList;
    }

    public static void setMyList(List<Product> myList) {
        Credentials.myList = myList;
    }

    //Google drive link - https://drive.google.com/drive/folders/1E8n-1EZ_wleS_diStL271V-SFtfmUNgt?usp=sharing
}
