package com.example.weekend5homeassignment.view.activities.mapactivity;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.weekend5homeassignment.R;
import com.example.weekend5homeassignment.model.contact.Contact;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            contact = bundle.getParcelable("contact");

        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng = getLatLngFromAddress(contact.getLocation());
        mMap.addMarker(new MarkerOptions().position(latLng).title(contact.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.setMinZoomPreference(15);
    }

    public LatLng getLatLngFromAddress(String address) {
        Geocoder geocoder = new Geocoder(this);
        LatLng contactLatLng = new LatLng(-34, 151);
        try {
            List<Address> addressLatLng = geocoder.getFromLocationName(address, 1);
            double lat = addressLatLng.get(0).getLatitude();
            double lng = addressLatLng.get(0).getLongitude();
            contactLatLng = new LatLng(lat, lng);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contactLatLng;
    }

}
