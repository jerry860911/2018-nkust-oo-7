package com.example.demo001;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.view.KeyEvent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private static boolean rLocationGranted = false;
    private FusedLocationProviderClient mFusedLocationProvider;
    private FusedLocationProviderClient mFusedLocationClient;
    private EditText mSearchText;

    LatLng Changgung = new LatLng(22.650063,120.356838); // 加入地圖標記
    LatLng Eda = new LatLng(22.765907,120.364355);
    LatLng kumh = new LatLng(22.646002,120.309581);
    LatLng Tatung = new LatLng(22.627035,120.297326);
    LatLng Vghks = new LatLng(22.677537,120.322504);
    LatLng Yuanhos = new LatLng(22.615373,120.297852);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (checkPlayService() == true) {
            initalMap();
            if (rLocationGranted == true) {
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
            }
        }

        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        final String[] list = {"=====選擇醫院=====","高雄長庚紀念醫院","義大醫院","高雄醫學大學附設中和紀念醫院","高雄市立大同醫院","高雄榮民總醫院","阮綜合醫療社團法人阮綜合醫院"};
        ArrayAdapter<String> lunchList = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                list);
        spinner.setAdapter(lunchList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MapsActivity.this,list[position], Toast.LENGTH_SHORT).show();

                if(list[position] == list[0] )
                {

                }else if(list[position] == list[1] )
                {
                    LatLng Changgung = new LatLng(22.650063,120.356838);
                    moveMap(Changgung);
                }else if(list[position] == list[2] )
                {
                    LatLng Eda = new LatLng(22.765907,120.364355);
                    moveMap(Eda);
                }else if(list[position] == list[3] )
                {
                    LatLng kumh = new LatLng(22.646002,120.309581);
                    moveMap(kumh);
                }else if(list[position] == list[4] )
                {
                    LatLng Tatung = new LatLng(22.627035,120.297326);
                    moveMap(Tatung);
                }else if(list[position] == list[5] )
                {
                    LatLng Vghks = new LatLng(22.677537,120.322504);
                    moveMap(Vghks);
                }else if(list[position] == list[6] )
                {
                    LatLng Yuanhos = new LatLng(22.615373,120.297852);
                    moveMap(Yuanhos);
                }



            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void searchnearhos(View v)
    {
        mFusedLocationProvider = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (rLocationGranted == true) {
                Task location = mFusedLocationProvider.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            //find location
                            Location mLocation = (Location) task.getResult();
                            LatLng mLatLng = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());

                            float[] resultsChanggung = new float[1];
                            Location.distanceBetween(mLocation.getLatitude(),mLocation.getLongitude(),Changgung.latitude,Changgung.longitude,resultsChanggung);

                            float[] resultsEda = new float[1];
                            Location.distanceBetween(mLocation.getLatitude(),mLocation.getLongitude(),Eda.latitude,Eda.longitude,resultsEda);

                            float[] resultskumh = new float[1];
                            Location.distanceBetween(mLocation.getLatitude(),mLocation.getLongitude(),kumh.latitude,kumh.longitude,resultskumh);

                            float[] resultsTatung = new float[1];
                            Location.distanceBetween(mLocation.getLatitude(),mLocation.getLongitude(),Tatung.latitude,Tatung.longitude,resultsTatung);

                            float[] resultsVghks = new float[1];
                            Location.distanceBetween(mLocation.getLatitude(),mLocation.getLongitude(),Vghks.latitude,Vghks.longitude,resultsVghks);

                            float[] resultsYuanhos = new float[1];
                            Location.distanceBetween(mLocation.getLatitude(),mLocation.getLongitude(),Yuanhos.latitude,Yuanhos.longitude,resultsYuanhos);

                            float nearest[] = {resultsChanggung[0],resultsEda[0],resultskumh[0],resultsTatung[0],resultsVghks[0],resultsYuanhos[0]};
                            float max=nearest[0], min=nearest[0];
                            for(int x=0;x<nearest.length;x++)
                            {
                                if(nearest[x]>max) max=nearest[x];
                                if(nearest[x]<min) min=nearest[x];
                            }

                            if(min==resultsChanggung[0])
                            {
                                moveMap(Changgung);
                            }else if(min==resultsEda[0]){
                                moveMap(Eda);
                            }else if(min==resultskumh[0]){
                                moveMap(kumh);
                            }else if(min==resultsTatung[0]){
                                moveMap(Tatung);
                            }else if(min==resultsVghks[0]){
                                moveMap(Vghks);
                            }else if(min==resultsYuanhos[0]){
                                moveMap(Yuanhos);
                            }

                            Log.i("Search test Changgung", String.valueOf(resultsChanggung[0]));
                            Log.i("Search test Eda", String.valueOf(resultsEda[0]));
                            Log.i("Search test kumh", String.valueOf(resultskumh[0]));
                            Log.i("Search test Tatung", String.valueOf(resultsTatung[0]));
                            Log.i("Search test Vghks", String.valueOf(resultsVghks[0]));
                            Log.i("Search test Yuanhos", String.valueOf(resultsYuanhos[0]));
                            Log.i("Search test near", String.valueOf(min));

                        }
                    }
                });
            }
        } catch (SecurityException ex) {
            Log.e("location error", ex.getMessage());
        }


    }




    private void initalMap() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if ((ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0])
                == PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[1])
                        == PackageManager.PERMISSION_GRANTED)
                ) {
            //fine location available
            rLocationGranted = true;
        } else {
            ActivityCompat.requestPermissions(this, permissions,
                    LOCATION_PERMISSION_REQUEST_CODE
            );
        }
    }

    private void getDeviceLocation() {
        mFusedLocationProvider = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (rLocationGranted == true) {
                Task location = mFusedLocationProvider.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            //find location
                            Location mLocation = (Location) task.getResult();
                            LatLng mLatLng = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(mLatLng).title("Your last location"));
                            moveMap(mLatLng);
                            Log.i("mylocation", "(" + mLocation.getLongitude() + "," + mLocation.getLatitude() + ")");
                        }
                    }
                });
            }
        } catch (SecurityException ex) {
            Log.e("location error", ex.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        rLocationGranted = false;
        switch (requestCode) {
            case 1001: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            rLocationGranted = false;
                            return;
                        }
                    }
                    rLocationGranted = true;
                }
            }
        }

    }

    private boolean checkPlayService() //版本確認
    {
        int avai = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapsActivity.this);
        if (avai == ConnectionResult.SUCCESS) {
            Log.i("Map test", "Version OK");
            return true;
        } else {
            Toast.makeText(this, "版本不足", Toast.LENGTH_LONG).show();
            Log.i("Map test", "Version no OK");
            return false;
        }
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
        mMap.getUiSettings().setCompassEnabled(true);//Compass
        mMap.getUiSettings().setZoomControlsEnabled(true);  // 右下角的放大縮小功能
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);//  Change the map type 改變地圖樣式 需做layout選項選擇
        getDeviceLocation(); // last location marker

        // now location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;

        }
        mMap.setMyLocationEnabled(true);
        //now location end


        //地圖標記

        //Changgung
        //LatLng Changgung = new LatLng(22.650063,120.356838); // 加入地圖標記
        String Changunginfo = "put info here";
        addMarker(Changgung, "高雄長庚紀念醫院",Changunginfo);

        //eda
        //LatLng Eda = new LatLng(22.765907,120.364355);
        String Edainfo = "put info here";
        addMarker(Eda,"義大醫院",Edainfo);

        //kumh
        //LatLng kumh = new LatLng(22.646002,120.309581);
        String kumhinfo = "put info here";
        addMarker(kumh,"高雄醫學大學附設中和紀念醫院",kumhinfo);

        //Tatung
        //LatLng Tatung = new LatLng(22.627035,120.297326);
        String Tatunginfo = "put info here";
        addMarker(Tatung,"高雄市立大同醫院",Tatunginfo);

        //Vghks
        //LatLng Vghks = new LatLng(22.677537,120.322504);
        String Vghksinfo = "put info here";
        addMarker(Vghks,"高雄榮民總醫院",Vghksinfo);

        //Yuanhos
        //LatLng Yuanhos = new LatLng(22.615373,120.297852);
        String Yuanhosinfo = "put info here";
        addMarker(Yuanhos,"阮綜合醫療社團法人阮綜合醫院",Yuanhosinfo);

        //地圖標記 end

        }

        public void mapopen(){


            // Create a Uri from an intent string. Use the result to create an Intent.
            Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");

            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps");

            // Attempt to start an activity that can handle the Intent
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }

        }



    private void moveMap(LatLng place) {
        // 建立地圖攝影機的位置物件
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(16)
                        .build();
        // 使用動畫的效果移動地圖
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    // 在地圖加入指定位置與標題的標記
    private void addMarker(LatLng place, String title,String info) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(place).title(title);
        markerOptions.snippet(info);

        mMap.addMarker(markerOptions);
    }





}
