package com.example.vinh.bkfour;


import android.os.AsyncTask;
import android.util.Log;

import com.example.vinh.bkfour.Model.Product;
import com.example.vinh.bkfour.Model.User;
import com.example.vinh.bkfour.Utility.Config;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

interface IOnServerResponse{
    void OnPostLoginRes(User user);

    void OnPostRegisterRes(User user);

    void OnGetListProductsRes(ArrayList<Product> lstProds);

    void OnGetProductDetailRes(Product product, ArrayList<User> lstUserNeed, ArrayList<User> lstUserTransport);

    void OnAddProduct(Product product);

    void OnGetUserDetail(User user);
}

public class ServerHandler {
    IOnServerResponse iOnServerResponse;
    public static final String LOGIN = "login";
    public static final String REGISTER = "reg";
    public static final String LIST_PRODUCT = "lprod";
    public static final String PRODUCT_DETAIL = "prodde";
    public static final String USER_DETAIL = "usrdetail";
    public static final String ADD_PRODUCT = "addprod";

    private HttpClient httpClient;
    private HttpPost httpPost;
    private String result;



    public ServerHandler(IOnServerResponse iOnServerResponse) {
        httpClient = new DefaultHttpClient();
        this.iOnServerResponse = iOnServerResponse;
    }

    /*public static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }*/

    private void initHttpPost(JSONObject jsonObject) throws UnsupportedEncodingException {
        // 4. convert JSONObject to JSON to String
        String json = jsonObject.toString();

        // 5. set json to StringEntity
        StringEntity se = new StringEntity(json);

        // 6. set httpPost Entity
        httpPost.setEntity(se);

        // 7. Set some headers to inform server about the type of the content
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
    }

    /**
     * Post LOGIN infor to server
     * @param userName
     * @param password
     */
    public void postLogin(String userName, String password) {
        try {

            // 1. create HttpClient
            //HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(Config.ServerDomain+Config.Login);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("user_name", userName);
            jsonObject.accumulate("password", password);

            initHttpPost(jsonObject);

            // 8. Execute POST request to the given URL
           new HttpAsyncTask().execute(LOGIN);
            //HttpResponse httpResponse = httpClient.execute(httpPost);



            //result = EntityUtils.toString(httpResponse.getEntity());

            /*// 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";*/

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
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
        try {
            // 1. make POST request to the given URL
            HttpPost httpPost = new HttpPost(Config.ServerDomain+Config.Register);

            // 2. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("first_name", firstName);
            jsonObject.accumulate("last_name", lastName);
            jsonObject.accumulate("password", password);
            jsonObject.accumulate("long_location", longitude);
            jsonObject.accumulate("lat_location", latitude);
            jsonObject.accumulate("email", email);
            jsonObject.accumulate("telephone", phoneNumber);
            jsonObject.accumulate("address", address);
            jsonObject.accumulate("user_name", userName);

            // 3. Init
            initHttpPost(jsonObject);

            // 4. Execute POST request to the given URL
            new HttpAsyncTask().execute(REGISTER);

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
    }

    /**
     * List all the products in one category
     * @param categoryID 1. Food ; 2. Book ; 3. Health Aid ; 4. Others
     */
    public void getListProducts(int categoryID) {
        try {
            // 1. make POST request to the given URL
            HttpPost httpPost = new HttpPost(Config.ServerDomain+Config.ListProduct);

            // 2. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("category_id", categoryID);

            // 3. Init
            initHttpPost(jsonObject);

            // 4. Execute POST request to the given URL
            new HttpAsyncTask().execute(LIST_PRODUCT);

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
    }

    /**
     * Get product detail
     * @param productID
     */
    public void getProductDetail(int productID) {
        try {
            // 1. make POST request to the given URL
            HttpPost httpPost = new HttpPost(Config.ServerDomain+Config.ProductDetail);

            // 2. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("product_id", productID);

            // 3. Init
            initHttpPost(jsonObject);

            // 4. Execute POST request to the given URL
            new HttpAsyncTask().execute(PRODUCT_DETAIL);

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
    }


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);
                result = EntityUtils.toString(httpResponse.getEntity());

                switch (urls[0]) {

                    case LOGIN:
                        parseLoginRes(result);
                        break;


                    case REGISTER:
                        parseRegisterRes(result);
                        break;


                    case LIST_PRODUCT:
                        parseListProductsRes(result);
                        break;


                    case PRODUCT_DETAIL:
                        parseProductRes(result);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }


        private void parseLoginRes(String result) {
            try {
                JsonObject obj = new JsonParser().parse(result).getAsJsonObject();
                User user = new User();
                user.setUserID(obj.get("data").getAsJsonObject().get("user_id").getAsInt());

                iOnServerResponse.OnPostLoginRes(user);
            } catch (Exception e) {
                Log.e("PARSING", e.getMessage());
            }
        }


        private void parseRegisterRes(String result){
            try {
                JsonObject obj = new JsonParser().parse(result).getAsJsonObject();
                User user = new User();
                user.setUserID(obj.get("data").getAsJsonObject().get("user_id").getAsInt());

                iOnServerResponse.OnPostLoginRes(user);
            } catch (Exception e) {
                Log.e("PARSING", e.getMessage());
            }
        }

        private void parseListProductsRes(String result) {
            try {
                JsonObject obj = new JsonParser().parse(result).getAsJsonObject().get("data").getAsJsonObject();
                JsonArray arr = obj.getAsJsonArray("lstProductOverview");

                ArrayList<Product> listProduct = new ArrayList<>();

                for (int i = 0; i < arr.size(); ++i) {
                    JsonObject jsonProd = arr.get(i).getAsJsonObject();
                    Product product = Product.convertFromJsonObject(jsonProd);
                    listProduct.add(product);
                }

                iOnServerResponse.OnGetListProductsRes(listProduct);
            } catch (Exception e) {
                Log.e("PARSING", e.getMessage());
            }
        }

        private void parseProductRes(String result) {
            try{
                JsonObject obj = new JsonParser().parse(result).getAsJsonObject().get("data").getAsJsonObject();

                JsonObject jsonObject = obj.getAsJsonObject("productObject");
                JsonArray jsonLstUserNeed = obj.getAsJsonArray("lstUserNeed");
                JsonArray jsonLstUserTransport = obj.getAsJsonArray("lstUserTransport");

                Product product = Product.convertFromJsonObject(jsonObject);

                ArrayList<User> lstUserNeed = new ArrayList<>();
                for (int i = 0; i < jsonLstUserNeed.size(); ++i) {
                    JsonObject jsonUserNeed = jsonLstUserNeed.get(i).getAsJsonObject();
                    User user = User.convertFromJsonObject(jsonUserNeed);
                    lstUserNeed.add(user);
                }

                ArrayList<User> lstUserTransport = new ArrayList<>();
                for (int i = 0; i < lstUserTransport.size(); ++i) {
                    JsonObject jsonUserTransport = jsonLstUserTransport.get(i).getAsJsonObject();
                    User user = User.convertFromJsonObject(jsonUserTransport);
                    lstUserTransport.add(user);
                }

                iOnServerResponse.OnGetProductDetailRes(product,lstUserNeed,lstUserTransport);

            } catch (Exception e) {
                Log.e("PARSING", e.getMessage());
            }
        }
    }


}
