package com.example.vinh.bkfour;


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

/**
 * Created by danhk on 26-Mar-16.
 */
public class ProductDetailFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback,GoogleMap.OnMapLoadedCallback {
    GoogleMap googleMap;
    Button btnContact;
    TextView txtName,txtDescription,txtAddress,txtQuantity,txtUnit;

    void test(){
        txtAddress.setText("268 No Trang Long, Binh Thanh Dict, HCM City");
        txtName.setText("Phạm Luân");
        txtDescription.setText("Old bike for children");
        txtQuantity.setText("1");
        txtUnit.setText("vihicle");

    }
    @Override

    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View v = null;
        v = inflater.inflate(R.layout.product_detail, container,false);
        btnContact = (Button) v.findViewById(R.id.btnContact);
        txtName = (TextView) v.findViewById(R.id.txtViewName);
        txtDescription = (TextView) v.findViewById(R.id.txtDescription);
        txtAddress = (TextView) v.findViewById(R.id.txtAddress);
        txtQuantity = (TextView) v.findViewById(R.id.txtQuantity);
        txtUnit = (TextView) v.findViewById(R.id.txtUnit);
        txtAddress.setVisibility(View.INVISIBLE);
        test();
        return v;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        MapFragment mapfg = new MapFragment();
        mapfg.getMapAsync(this);
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //
        ft.replace(R.id.mapLayout, mapfg);

        //
        ft.commit();

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapLoadedCallback(this);
    }
    @Override
    public void onMapLoaded() {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(10.408705, 106.140274)).title("Mái ấm xxx"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(new LatLng(10.408705, 106.140274));
        googleMap.animateCamera(cameraUpdate);
    }



}
