package com.example.user.eventsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import java.util.Locale;

/**
 * Created by User on 22-02-2016.
 */
public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    Button b1;
    int i;
    int j;
    private GoogleMap mMap;
    Event selectedEvent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity);
        Intent i = getIntent();
        selectedEvent = (Event) i.getSerializableExtra("event");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        b1 = (Button) findViewById(R.id.justclick);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LocationActivity.this, "Hope You enjoyed the info", Toast.LENGTH_LONG).show();
            }
        });
//        createDataSetForLatsandLongs(sampleEvents1,j);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng place = new LatLng(selectedEvent.getLatitudes(), selectedEvent.getLongtitudes());
        mMap.addMarker(new MarkerOptions().position(place).title("hey u are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
        CameraUpdate center = CameraUpdateFactory.newLatLng(place);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);


    }
}

