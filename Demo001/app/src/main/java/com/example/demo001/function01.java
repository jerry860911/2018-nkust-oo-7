package com.example.demo001;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class function01 extends AppCompatActivity {

    Connection con;
    private FusedLocationProviderClient mFusedLocationProvider;
    private static boolean rLocationGranted = false;
    String dbun,dbpass,db,ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function01);
        setTitle("病床查詢系統");

        WebView webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.requestFocus();
        webview.loadUrl("http://163.18.42.32/phoneDemo/searchgui.php");

        ip = "163.18.42.32";
        db = "Demo";
        dbun = "sa";
        dbpass = "12345";

        getDeviceLocation();

    }



    private void getDeviceLocation() {

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if ((ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0])
                == PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[1])
                        == PackageManager.PERMISSION_GRANTED)
                ) {
            //fine location available
            rLocationGranted = true;


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
                                Log.i("mylocation", "(" + mLocation.getLongitude() + "," + mLocation.getLatitude() + ")");

                                Date dnow = new Date();
                                SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

                                String insertdbSQL = "insert into [Demo].[dbo].[LngLat]([Time],[Longitude],[Latitude]) values('"+ ft.format(dnow) +"','" + String.valueOf(mLocation.getLongitude()) +"','"+ String.valueOf(mLocation.getLatitude()) +"');";
                                con = connetionclass(dbun,dbpass,db,ip);
                                try {
                                    Statement stmt = con.createStatement();
                                    stmt.executeUpdate(insertdbSQL);
                                    //Toast.makeText(function01.this, "經緯成功", Toast.LENGTH_LONG).show();
                                } catch (SQLException e) {
                                    //Toast.makeText(function01.this, "經緯失敗", Toast.LENGTH_LONG).show();
                                }


                            }
                        }
                    });
                }
            } catch (SecurityException ex) {
                Log.e("location error", ex.getMessage());
            }
        }
    }

    @SuppressLint("NewApi")
    public static Connection connetionclass(String user , String password, String database, String server)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnetionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            ConnetionURL ="jdbc:jtds:sqlserver://163.18.42.32;database=[Demo].[dbo].[Memberprofile];user=test;password=54321";
            connection = DriverManager.getConnection(ConnetionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 :", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2:",e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 :",e.getMessage());
        }
        return connection;
    }

}


