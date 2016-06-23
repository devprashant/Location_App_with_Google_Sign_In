package com.example.probook.locationappwithgooglesignin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng kalka = new LatLng(30.8390873,76.9295389);
        mMap.addMarker(new MarkerOptions().position(kalka).title("Marker is Somewhere").flat(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kalka));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));

        String vID = "gedFd0LRXsYHJ918doD831oGNzA2"; // Vehicle id

        mDatabase.getReference().child("vehicles").child(vID).child("location")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        VehicleLocation vehicleLocation = dataSnapshot.getValue(VehicleLocation.class);

                        LatLng latLng = new LatLng(Float.valueOf(vehicleLocation.getLatitude()), Float.valueOf(vehicleLocation.getLongitude()));
                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().position(latLng).title("Current Position"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        ((TextView)findViewById(R.id.tvUpdateStatus)).setText(databaseError.toString());
                    }
                });
    }
}
