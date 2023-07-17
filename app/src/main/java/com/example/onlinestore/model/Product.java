package com.example.onlinestore.model;

import com.example.onlinestore.user.models.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    /*//constants
    public static final String TABLE_NAME = "products";
    public static final  String COLUMN_ID = "product_id";
    public static final String COLUMN_OWNERID = "oid";*/

    private String nameofProduct;

    private boolean isChecked = false;

    private String description;
    private int quantity;
    private Double price;
    private String image;

    @SerializedName("userAccount")
    @Expose
    private UserModel userModel;

    public Product(String nameofProduct, String description, int quantity, Double price, String image, UserModel userModel) {
        this.nameofProduct = nameofProduct;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.userModel = userModel;
    }

    //Sql Query

    /*public static final String CREATE_TABLE =
            "CREATE TABLE" + TABLE_NAME + "("
            +COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN*/

    public Product() {
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getNameofProduct() {
        return nameofProduct;
    }

    public void setNameofProduct(String nameofProduct) {
        this.nameofProduct = nameofProduct;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
