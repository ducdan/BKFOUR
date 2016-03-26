package com.example.vinh.bkfour;


public class ServerHandler {
    private String url;

    /**
     * Post LOGIN infor to server
     * @param userName
     * @param password
     */
    public void postLogin(String userName, String password) {

    }

    /**
     * Post REGISTER info to server
     * @param userName
     * @param password
     * @param lastName
     * @param firstName
     * @param phoneNumber
     * @param email
     * @param address
     * @param longitude optional
     * @param latitude optional
     */
    public void postRegister(String userName, String password, String lastName, String firstName, String phoneNumber, String email, String address, float longitude , float latitude ) {

    }

    /**
     * List all the products in one category
     * @param categoryID 1. Food ; 2. Book ; 3. Health Aid ; 4. Others
     */
    public void getListProducts(int categoryID) {

    }

    /**
     * Get product detail
     * @param productID
     */
    public void getProductDetail(int productID) {

    }
}
