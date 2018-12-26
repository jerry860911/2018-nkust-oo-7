package com.example.demo001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private static boolean Login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("病床查詢系統");
//        getSupportActionBar().setDisplayShowHomeEnabled(true); 狀態列顯示LOGO
//        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);

        if (Login==false)
        {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            Login =true;
        }
    }




    public void function001(View view)
    {
        Intent intent = new Intent(this,function01.class);
        startActivity(intent);
    }

    public void function002(View view)
    {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

    public void function004(View view)
    {
        Intent intent = new Intent(this,function04.class);
        startActivity(intent);
    }

}



