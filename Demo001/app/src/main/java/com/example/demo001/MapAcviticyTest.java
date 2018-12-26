package com.example.demo001;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class MapAcviticyTest extends FragmentActivity implements OnMapReadyCallback {

    private static int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProvider;
    private static boolean rLocationGranted = false;
    private FusedLocationProviderClient mFusedLocationClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_acviticy_test);
        setContentView(R.layout.activity_maps);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (checkPlayService() == true) {
            initalMap();
            if (rLocationGranted == true) {
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(MapAcviticyTest.this);
            }
        }
    }

    char i ;

    private boolean checkPlayService() //版本確認
    {
        int avai = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapAcviticyTest.this);
        if (avai == ConnectionResult.SUCCESS) {
            Log.i("Map test", "Version OK");
            return true;
        } else {
            Toast.makeText(this, "版本不足", Toast.LENGTH_LONG).show();
            Log.i("Map test", "Version no OK");
            return false;
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
        LatLng Changgung = new LatLng(22.650072, 120.356837); // 加入地圖標記
        addMarker(Changgung, "高雄長庚紀念醫院");

        //eda
        LatLng Eda = new LatLng(22.764952, 120.364000);
        addMarker(Eda,"義大醫院");

        //kumh
        LatLng kumh = new LatLng(22.646005, 120.309581);
        addMarker(kumh,"高雄醫學大學附設中和紀念醫院");

        //Tatung
        LatLng Tatung = new LatLng(22.627053, 120.297326);
        addMarker(Tatung,"高雄市立大同醫院");

        //Vghks
        LatLng Vghks = new LatLng(22.677548, 120.322504);
        addMarker(Vghks,"高雄榮民總醫院");

        //Yuanhos
        LatLng Yuanhos = new LatLng(22.615380, 120.297850);
        addMarker(Yuanhos,"阮綜合醫療社團法人阮綜合醫院");

        //地圖標記 end

    }

    // 在地圖加入指定位置與標題的標記
    private void addMarker(LatLng place, String title) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(place).title(title);
        mMap.addMarker(markerOptions);
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


}
