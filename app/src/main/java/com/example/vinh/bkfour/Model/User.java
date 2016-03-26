package com.example.vinh.bkfour.Model;

import com.google.gson.JsonObject;

import org.json.JSONObject;

/**
 * Created by VINH on 3/26/2016.
 */
public class User {
    public User(String userName, String password, String lastName, String firstName, String phoneNumber, String email,
                String address,String avatar, float longitude, float latitude) {
        this.userName = userName;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.avatar = avatar;
    }

    public User(int userID) {
        this.userID = userID;
    }

    public User() {

    }

    private int userID;
    private String userName;
    private String password;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;
    private String address;
    private float longitude ;
    private float latitude;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    private String avatar;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public static User convertFromJsonObject(JsonObject jsonObject) {
        User user = new User();
        user.setUserID(jsonObject.get("user_id").getAsInt());
        user.setUserName(jsonObject.get("user_name").getAsString());
        user.setAvatar(jsonObject.get("avatar").getAsString());
        user.setEmail(jsonObject.get("email").getAsString());
        user.setAddress(jsonObject.get("address").getAsString());
        user.setFirstName(jsonObject.get("first_name").getAsString());
        user.setLastName(jsonObject.get("last_name").getAsString());
        user.setPhoneNumber(jsonObject.get("telephone").getAsString());
        user.setLongitude(jsonObject.get("long_location").getAsFloat());
        user.setLatitude(jsonObject.get("lat_location").getAsFloat());

        return user;
    }
}
