package com.example.vinh.bkfour.Model;

import com.google.gson.JsonObject;

/**
 * Created by VINH on 3/26/2016.
 */
public class Product {

    public Product(int productID, String productName, String productPicture, float longitude, float latitude, String description, String quantity, String unit, boolean isFinished) {
        this.productID = productID;
        this.productName = productName;
        this.productPicture = productPicture;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
        this.isFinished = isFinished;
    }

    public Product() {

    }
    int productID;
    String productName;
    String productPicture;
    float longitude;
    float latitude;
    String description;
    String quantity;
    String unit;
    String price = "free";
    public String phone;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String address;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    boolean isFinished;


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public static Product convertFromJsonObject(JsonObject jsonObject) {
        Product product = new Product();
        product.setProductID(jsonObject.get("product_id").getAsInt());
        product.setProductName(jsonObject.get("product_name").getAsString());
        product.setProductPicture(jsonObject.get("picture1").getAsString());
        product.setDescription(jsonObject.get("description").getAsString());
       // product.setQuantity(jsonObject.get("quantity").getAsInt());
        product.setUnit(jsonObject.get("unit").getAsString());
        product.setAddress(jsonObject.get("address").getAsString());
        product.setPrice(jsonObject.get("price").getAsString());

        return product;
    }
}
