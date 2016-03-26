package com.example.vinh.bkfour;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.vinh.bkfour.Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String SERVER_URL = "http://192.168.173.236/HuyAPI/user/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private class RequestServer extends AsyncTask<String, Void, Void> {

        private String jsonResponse;
        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);


        @Override
        protected Void doInBackground(String... urls) {
            try {
                // STEP1. Create a HttpURLConnection object releasing REQUEST to given site
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("User-Agent", "");
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.connect();

                // STEP2. wait for incoming RESPONSE stream, place data in a buffer
                InputStream isResponse = urlConnection.getInputStream();
                BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(isResponse));

                // STEP3. Arriving JSON fragments are concatenate into a StringBuilder
                String myLine = "";
                StringBuilder strBuilder = new StringBuilder();
                while ((myLine = responseBuffer.readLine()) != null) {
                    strBuilder.append(myLine);
                }

                // Show Response ( JSON ENcoded data)
                jsonResponse = strBuilder.toString();
                Log.e("RESPONSE", jsonResponse);

            } catch (Exception e) {
                Log.e("RESPONSE Error", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                dialog.dismiss();

                // update GUI

                // Step4. Convert JSON list into a Java collection of Person objects
                // prepare to decode JSON response and create Java list
                Gson gson = new Gson();
                Log.e("PostExecute", "content: " + jsonResponse);

                // set (host) Java type of encoded JSON response
                Type listType = new TypeToken<ArrayList<User>>(){}.getType();

                ArrayList<User> personList = gson.fromJson(jsonResponse, listType);
                Log.e("PostExecute", "OutputData: " + personList.toString());


                //Step5. Show results (update GUI with Java version of retrieved list)


            } catch (Exception e) {
            }
        }
    }
}
