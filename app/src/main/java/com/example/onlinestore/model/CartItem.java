package com.example.onlinestore.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CartItem implements Parcelable {

    @SerializedName("numberOfProducts")
    @Expose
    private int numItems;

    @SerializedName("product")
    @Expose
    private ProductList productList;

    public CartItem(int numItems, ProductList productList) {
        this.numItems = numItems;
        this.productList = productList;
    }

    protected CartItem(Parcel in) {
        numItems = in.readInt();
        productList = in.readParcelable(ProductList.class.getClassLoader());
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(numItems);
        parcel.writeParcelable(productList, i);
    }
}
