package com.example.dietapp5;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.dietapp5.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




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

        LatLng dongguk = new LatLng(37.558127, 126.998425);


        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(dongguk);
        markerOptions.title("???????????????");
        markerOptions.snippet("?????? ?????? ???");
        mMap.addMarker(markerOptions);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dongguk));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions mOptions = new MarkerOptions();
                // ?????? ?????????
                mOptions.title("????????? ??????");
                Double latitude = point.latitude; // ??????
                Double longitude = point.longitude; // ??????
                // ????????? ?????????(????????? ?????????) ??????
//                mOptions.snippet(latitude.toString() + ", " + longitude.toString());
                // LatLng: ?????? ?????? ?????? ?????????
                mOptions.position(new LatLng(latitude, longitude));

                // ??????(???) ??????
                mMap.clear();
                Marker marker = mMap.addMarker(mOptions);
                marker.showInfoWindow();

            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
            @Override
            public void onInfoWindowClick(Marker marker){
                Intent intent = new Intent(MapsActivity.this, AddActivity.class);

                LatLng point = marker.getPosition();
                intent.putExtra("lat", Double.toString(point.latitude));
                intent.putExtra("long", Double.toString(point.longitude));
                setResult(RESULT_OK, intent);
                finish();

            }
        });



    }


}