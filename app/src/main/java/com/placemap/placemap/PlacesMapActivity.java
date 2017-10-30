package com.placemap.placemap;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlacesMapActivity extends FragmentActivity implements OnMapReadyCallback {
    PlacesList nearPlaces;
    double latitude;
    double longitude;
    String Name;
    String Vicinity;
    String user_latitude;
    String user_longitude;
    double la;
    double lo;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        Intent i = getIntent();

        // Users current geo location
         user_latitude = i.getStringExtra("user_latitude");
         user_longitude = i.getStringExtra("user_longitude");
         la=Double.parseDouble(user_latitude);
        lo=Double.parseDouble(user_longitude);


        // Nearplaces list
        nearPlaces = (PlacesList) i.getSerializableExtra("near_places");

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(la, lo))      // Sets the center of the map to location user
                .zoom(17)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        MarkerOptions markerOptions1 = new MarkerOptions();
        LatLng latLng1 = new LatLng(la, lo);
        markerOptions1.position(latLng1);
        markerOptions1.title("That is you,asshole");
        map.addMarker(markerOptions1
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        if (nearPlaces.results != null) {
            // loop through all the places
            for (com.placemap.placemap.place place : nearPlaces.results) {
                MarkerOptions markerOptions = new MarkerOptions();
                latitude = place.geometry.location.lat; // latitude
                longitude = place.geometry.location.lng; // longitude
                Name = place.name;
                Vicinity = place.vicinity;
                LatLng latLng = new LatLng(latitude, longitude);
                markerOptions.position(latLng);
                markerOptions.title(Name +":"+ Vicinity);
                map.addMarker(markerOptions);


            }
        }
    }
}
    // Nearest places
