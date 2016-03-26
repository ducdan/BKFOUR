package com.example.vinh.bkfour;

/**
 * Created by danhk on 26-Mar-16.
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;

public class PostAPI extends AsyncTask<Void, Void, Boolean>{

    public interface ApiCallback{
        public void onSuccess(String value);
        public void onFail(HttpURLConnection cn);
    }

    String url;
    ApiCallback callback;

    HttpURLConnection cn;
    String value = "";

    String searchName;

    boolean running;

    public boolean isRunning() {
        return running;
    }


    public void setRunning(boolean running) {
        this.running = running;
    }



    public ApiCallback getCallback() {
        return callback;
    }


    public void setCallback(ApiCallback callback) {
        this.callback = callback;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        running = true;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        try {


            URL url = new URL("192.168.173.236/HuyAPI/user/login");

            cn = (HttpURLConnection) url.openConnection();

            cn.setConnectTimeout(30000);
            cn.setReadTimeout(30000);
            cn.setRequestMethod("POST");
            cn.setDoInput(true);
            cn.setDoOutput(true);
			/*cn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			cn.setRequestProperty("User-Agent","ie, firefox");*/

            String param = "user_name=abc&password=abc";
            OutputStream out = cn.getOutputStream();
            out.write(param.getBytes());

            cn.connect();

            int status = cn.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {

                InputStream in = cn.getInputStream();

                BufferedReader read = new BufferedReader( new InputStreamReader(in) );

                //
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = read.readLine())!=null) {
                    buffer.append(line);
                }

                in.close();

                value = buffer.toString();

                return true;

            }
            else{
                System.out.println("ket noi that bai:" + status);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        running = false;

        if (callback!=null) {
            if (result) {
                callback.onSuccess(value);
            }
            else{
                callback.onFail(cn);
            }
        }


    }

    private void sendparamPostMethod(){

    }

}
