package com.example.vinh.bkfour;


import com.example.vinh.bkfour.Model.Product;
import com.example.vinh.bkfour.Model.Variable;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseUser;
/*
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
*/
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.DatabaseMetaData;

/**
 * Created by danhk on 26-Mar-16.
 */
//implements OnMapReadyCallback,GoogleMap.OnMapLoadedCallback
public class ProductDetailActivity extends AppCompatActivity  {
   // GoogleMap googleMap;
    Button btnContact;
    TextView txtName,txtDescription,txtAddress,txtQuantity,txtUnit,txtPrice;
    Product product;
    ImageView picture;
    public void setProduct(Product product){
        this.product = product;
        txtName.setText(product.getProductName());
        txtDescription.setText(product.getDescription());
        txtQuantity.setText(product.getQuantity());
        txtUnit.setText(product.getUnit());

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        btnContact = (Button) findViewById(R.id.btnContact);
        txtName = (TextView) findViewById(R.id.txtViewName);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtQuantity = (TextView) findViewById(R.id.txtQuantity);
        txtUnit = (TextView) findViewById(R.id.txtUnit);
        txtAddress.setVisibility(View.INVISIBLE);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        picture  = (ImageView) findViewById(R.id.pictureImageView);


        Bundle bun = getIntent().getExtras();
        String data=bun.getString(Variable.ITEM_DATA);
        Gson gson = new Gson();
       product = gson.fromJson(data, Product.class);


        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageOnLoading(R.drawable.ic_loading)
                .build();
        imageLoader.displayImage(product.getProductPicture(), picture, options);

        txtName.setText(product.getProductName());
        txtDescription.setText(product.getDescription());
        txtAddress.setText(product.getAddress());
        txtQuantity.setText(product.getQuantity());
        txtUnit.setText(product.getUnit());
        txtPrice.setText(product.getPrice());

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAlreadyLogin() == false){
                    LoginActivity ac = new LoginActivity();
                    ac.startActivity(null);
                }
                else
                {
                    txtAddress.setVisibility(View.VISIBLE);
                    v.setVisibility(View.INVISIBLE);
                }

            }
        });
                /*
        MapFragment mapfg = new MapFragment();
        mapfg.getMapAsync(this);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mapLayout, mapfg);
        ft.commit();
    */
    }



    public boolean isAlreadyLogin() {
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
    /*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapLoadedCallback(this);
    }
    @Override
    public void onMapLoaded() {

        //googleMap.addMarker(new MarkerOptions().position(new LatLng(product.getLatitude(), product.getLongitude())).title(product.getAddress()));
        // CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(new LatLng(product.getLatitude(), product.getLongitude()));
        //  googleMap.animateCamera(cameraUpdate);
    }
    */

}
