package com.example.vinh.bkfour;


import com.example.vinh.bkfour.Model.Product;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
public class ProductDetailActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMapLoadedCallback {
    GoogleMap googleMap;
    Button btnContact;
    TextView txtName,txtDescription,txtAddress,txtQuantity,txtUnit;
    Product product;
    public void setProduct(Product product){
        this.product = product;
        txtName.setText(product.getProductName());
        txtDescription.setText(product.getDescription());
        txtQuantity.setText(product.getQuantity());
        txtUnit.setText(product.getUnit());

    }

    void test(){
        txtAddress.setText("268 No Trang Long, Binh Thanh Dict, HCM City");
        txtName.setText("Phạm Luân");
        txtDescription.setText("Old bike for children");
        txtQuantity.setText("1");
        txtUnit.setText("vihicle");
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
        MapFragment mapfg = new MapFragment();
        mapfg.getMapAsync(this);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mapLayout, mapfg);
        ft.commit();

    }

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



}
