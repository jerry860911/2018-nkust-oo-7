package com.example.demo001;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.demo001.LoginActivity.closedb;

public class RegisteActivity extends AppCompatActivity {


    Connection con;
    public static Statement stmt;
    EditText Resgistename,Resgistpassword,Resgistemali,Resgistphone,Resgistbirth;
    RadioButton Resgistgendermale,Resgistgenderfemale;
    RadioGroup radioGroup;
    String dbun,dbpass,db,ip;
    private PreparedStatement pStatement = null;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);
        setTitle("病床查詢系統");

        Resgistename = (EditText) findViewById(R.id.editText2);
        Resgistpassword = (EditText) findViewById(R.id.editText5);
        Resgistemali = (EditText) findViewById(R.id.editText4);
        Resgistphone = (EditText) findViewById(R.id.editText6);
        Resgistbirth = (EditText) findViewById(R.id.editText7);
        Resgistgendermale = findViewById(R.id.radioButton);
        Resgistgenderfemale = findViewById(R.id.radioButton2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        ip = "163.18.42.32";
        db = "Demo";
        dbun = "sa";
        dbpass = "12345";


    }

    public int chk(){


        String Rname =Resgistename.getText().toString();
        String Rpass =Resgistpassword.getText().toString();
        String Remail =Resgistemali.getText().toString();
        String Rphone =Resgistphone.getText().toString();
        String Rbirth =Resgistbirth.getText().toString();

        if(Rname.isEmpty()){
            new  AlertDialog.Builder(this)
                    .setMessage("請輸入帳號")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }else if (Rpass.isEmpty())
        {
            new  AlertDialog.Builder(this)
                    .setMessage("請輸入密碼")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }else if(Remail.isEmpty()){
            new  AlertDialog.Builder(this)
                    .setMessage("請輸入電子郵件")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        } else if (Rphone.isEmpty())
        {
            new  AlertDialog.Builder(this)
                    .setMessage("請輸入手機號碼")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }else if (Rbirth.isEmpty())
        {
            new  AlertDialog.Builder(this)
                    .setMessage("請輸入生日")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }else
        {
            return 0;
        }
        return 1;
    }

    public void insertTable(View view){

        int confirm = doInBackground();//先去資料表查使用者名稱有無重複
        int flag = 0;
        String Rname =Resgistename.getText().toString();
        String Rpass =Resgistpassword.getText().toString();
        String Remail =Resgistemali.getText().toString();
        String Rphone =Resgistphone.getText().toString();
        String Rbirth =Resgistbirth.getText().toString();

            switch (radioGroup.getCheckedRadioButtonId()){
                case R.id.radioButton:
                    gender = "女";
                case R.id.radioButton2:
                    gender = "男";
            }

            int chk = chk();

        String z = "fail";
        String insertdbSQL = "insert into [Demo].[dbo].[Memberprofile]([Id],[Password],[Email],[Phone],[Gender],[Birthday]) values('"+Rname+"','"+Rpass+"','"+Remail+"','"+Rphone+"','"+gender+"','"+Rbirth+"');";
        if (chk ==1){

        }else {
        if (confirm==0) {//沒有重複使用者
                con = connetionclass(dbun,dbpass,db,ip);  // connect to db

                if (con == null)
                {
                    z = "Check your internet";
                    Toast.makeText(RegisteActivity.this, z, Toast.LENGTH_LONG).show();

                } else {
                    try {
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(insertdbSQL);
                        Toast.makeText(RegisteActivity.this, "創建成功", Toast.LENGTH_LONG).show();
                        finish();
                    } catch (SQLException e) {
                        Toast.makeText(RegisteActivity.this, "創建失敗", Toast.LENGTH_LONG).show();
                    }


                }
            } else
        {
            Toast.makeText(RegisteActivity.this, "帳號重複", Toast.LENGTH_LONG).show();
        }



    }}

    public int doInBackground()
    {
        String Rname =Resgistename.getText().toString();
        int flag = 0;
            try {
                con = connetionclass(dbun,dbpass,db,ip);  // connect to db
                if (con == null)
                {
                    Toast.makeText(this,"Check your internet",Toast.LENGTH_LONG).show();
                } else
                {
                    //String sql = "select Id from [Demo].[dbo].[Memberprofile]";
                    String sql = "select Id from [Demo].[dbo].[Memberprofile] where Id ='" + Rname +"'" ;
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if(rs.next())
                    {
                        //Toast.makeText(RegisteActivity.this,"chk",Toast.LENGTH_LONG).show();
                        Log.i("thiss1","okok");
                        flag = 1;
                        Log.i("thiss0",Integer.valueOf(flag).toString());
                    }else
                    {
                        flag = 0;
                    }
                }
            }
            catch (Exception ex)
            {
            }
            finally {
                closedb();
            }
        return flag;
    }




    @SuppressLint("NewApi")
    public static  Connection connetionclass(String user , String password,String database, String server)
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
