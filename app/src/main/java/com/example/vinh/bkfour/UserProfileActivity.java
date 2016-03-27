package com.example.vinh.bkfour;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

/**
 * Created by danhk on 26-Mar-16.
 */
public class UserProfileActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMapLoadedCallback{
    ImageView avatarImgView;
    TextView firstNameTextView, lastnameTextView, telTextView, addressTextView;
    ListView helpedPeopleListView, pendingHelpListView;
    GoogleMap googleMap;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       setContentView(R.layout.user_profile_layout);
        avatarImgView = (ImageView) findViewById(R.id.avatarImgView);
        firstNameTextView = (TextView) findViewById(R.id.txtFirstName);
        lastnameTextView = (TextView) findViewById(R.id.txtLastName);
        telTextView = (TextView) findViewById(R.id.txtTelephone);
        addressTextView = (TextView) findViewById(R.id.txtAddress);
        helpedPeopleListView = (ListView) findViewById(R.id.helpedPeopleList);
        pendingHelpListView = (ListView) findViewById(R.id.pendingPeopleList);


        MapFragment mapfg = new MapFragment();
        mapfg.getMapAsync(this);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        ft.replace(R.id.mapLayout, mapfg);


        ft.commit();

    }



    @Override
    public void onMapLoaded() {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(10.408705, 106.140274)).title("Mái ấm xxx"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(new LatLng(10.408705, 106.140274));
        googleMap.animateCamera(cameraUpdate);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapLoadedCallback(this);
    }
}
