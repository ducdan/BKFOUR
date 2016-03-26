package com.example.vinh.bkfour;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinh.bkfour.Model.Product;
import com.example.vinh.bkfour.Model.User;
import com.example.vinh.bkfour.Utility.Config;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IOnServerResponse {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //editText = (EditText) findViewById(R.id.editText);

    }


    @Override
    public void OnPostLoginRes(User user) {

    }

    @Override
    public void OnPostRegisterRes(User user) {

    }

    @Override
    public void OnGetListProductsRes(ArrayList<Product> lstProds) {

    }

    @Override
    public void OnGetProductDetailRes(Product product, ArrayList<User> lstUserNeed, ArrayList<User> lstUserTransport) {

    }

    @Override
    public void OnAddProduct(Product product) {

    }

    @Override
    public void OnGetUserDetail(User user) {

    }
}