package sg.edu.rp.c346.p08_locatingaplace;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;
    Spinner spn;

    LatLng poi_singapore = new LatLng(1.352083, 103.819839);
    LatLng poi_north = new LatLng(1.424450, 103.829849);
    LatLng poi_central = new LatLng(1.3353769, 103.8125753);
    LatLng poi_east = new LatLng(1.3521954, 103.929085);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_singapore,
                        11));
                UiSettings ui = map.getUiSettings();

                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                Marker marker_north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_north)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm, Tel:65433456")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.star)));

                Marker marker_central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542, Operating hours: 11am-8pm, Tel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


                Marker marker_east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788, Operating hours: 9am-5pm, Tel:66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getBaseContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

            }
        });

//        btn1 = findViewById(R.id.btn1);
//        btn2 = findViewById(R.id.btn2);
//        btn3 = findViewById(R.id.btn3);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null){
//                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
//                            15));
//
//                }
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null){
//                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
//                            15));
//                }
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null){
//                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
//                            15));
//                }
//            }
//        });

        spn = findViewById(R.id.spn);


        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if (map != null){
                            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_singapore, 11));
                        }
                        break;
                    case 1:
                        if (map != null){
                            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north, 15));
                        }
                        break;
                    case 2:
                        if (map != null){
                            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central, 15));
                        }
                        break;
                    case 3:
                        if (map != null){
                            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east, 15));
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay!
                    Toast.makeText(MainActivity.this, "Permission granted",
                            Toast.LENGTH_SHORT).show();

                } else {
                    // permission denied... notify user
                    Toast.makeText(MainActivity.this, "Permission not granted",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}


