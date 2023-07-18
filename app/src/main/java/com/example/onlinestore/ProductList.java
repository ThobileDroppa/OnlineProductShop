package com.example.onlinestore;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.onlinestore.model.Product;
import com.example.onlinestore.user.models.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductList implements Parcelable{

    private Long id;

    private String nameofProduct;

    private String description;

    private int quantity;
    private Double price;
    private String image;

    @SerializedName("userAccount")
    @Expose
    private UserModel userModel;

    public ProductList(Long id, String nameofProduct, String description, int quantity, Double price, String image, UserModel userModel) {
        this.id = id;
        this.nameofProduct = nameofProduct;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.userModel = userModel;
    }

    protected ProductList(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        nameofProduct = in.readString();
        description = in.readString();
        quantity = in.readInt();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        image = in.readString();
    }

    public static final Creator<ProductList> CREATOR = new Creator<ProductList>() {
        @Override
        public ProductList createFromParcel(Parcel in) {
            return new ProductList(in);
        }

        @Override
        public ProductList[] newArray(int size) {
            return new ProductList[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameofProduct() {
        return nameofProduct;
    }

    public void setNameofProduct(String nameofProduct) {
        this.nameofProduct = nameofProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
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

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(nameofProduct);
        parcel.writeString(description);
        parcel.writeInt(quantity);
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(price);
        }
        parcel.writeString(image);
    }
}
