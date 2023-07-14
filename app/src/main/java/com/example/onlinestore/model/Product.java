package com.example.onlinestore;

public class Product {

    private String title;
    private String description;
    private Double price;
    private int productImage;


    public Product(String title, String description, Double price, int productImage) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.productImage = productImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }
}
