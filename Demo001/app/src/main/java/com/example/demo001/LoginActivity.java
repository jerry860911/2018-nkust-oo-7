package com.example.demo001;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText username,password;
    ProgressBar progressBar;

    Connection con;
    String dbun,dbpass,db,ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("病床查詢系統");

        login = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText3);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        ip = "163.18.42.32";
        db = "Demo";
        dbun = "test";
        dbpass = "54321";



    }

    public void login(View view)
    {
        CheckLogin checkLogin = new CheckLogin();
        checkLogin.execute("");

//        EditText Userid = (EditText) findViewById(R.id.userid);
//        EditText Passwd = (EditText) findViewById(R.id.password);

//        String uid = Userid.getText().toString();
//        String pw = Passwd.getText().toString();
//        if (uid.equals("test") && pw.equals("1234")){ //登入成功
//            Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show();
//            finish();
//        }else { //登入失敗
//            new AlertDialog.Builder(this)
//                    .setTitle("Alart")
//                    .setMessage("登入失敗")
//                    .setPositiveButton("OK", null)
//                    .show();
//              }  //test

    }

    public void clear(View view)
    {
        username.setText("");
        password.setText("");

    }

    public void Registe(View view)
    {
        Intent intent = new Intent(this,RegisteActivity.class);
        startActivity(intent);
    }


    public class CheckLogin extends AsyncTask<String,String,String>
    {

        String z = "";
        Boolean isSuccess = false;


        @Override
        protected  void onPreExecute()
        {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params)
        {

            String usernam = username.getText().toString();
            String passwordd = password.getText().toString();

            if(usernam.trim().equals("") || passwordd.trim().equals(""))
            {
                z = "Please enter username and password";
            } else
            {
                try {
                    con = connetionclass(dbun,dbpass,db,ip);  // connect to db
                    if (con == null)
                    {
                        z = "Check your internet";
                    } else
                    {
                        String sql = "select [Id],[Password] from [Demo].[dbo].[Memberprofile] where Id='" + usernam.toString() + "'and Password='" + passwordd.toString() + "'";
                        //String quary = "select * from [Demo].[dbo].[Memberprofile] where Id='" + username.toString() + "'and Password='" + password.toString() + "'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        if(rs.next())
                        {
                            z = "登入成功";
                            isSuccess = true;
                            con.close();
                            finish();

                        } else
                        {
                            z = "帳號或密碼有誤";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.getMessage();
                }
                finally {
                    closedb();
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String r)
        {
//            progressBar.setVisibility(View.GONE);
//            Toast.makeText(LoginActivity.this, "Login successfull",Toast.LENGTH_LONG).show();
//            //finish          //version1

            progressBar.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this,r,Toast.LENGTH_LONG).show();
            if(isSuccess)
            {
                //Toast.makeText(LoginActivity.this,"Login Successfull",Toast.LENGTH_LONG).show();
                //finish();   //version2
            }

        }




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
            ConnetionURL ="jdbc:jtds:sqlserver://163.18.42.32;user=test;password=54321"; //;database=[Demo].[dbo].[Memberprofile]
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

    public static void closedb(){
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        Statement statement = null;
        try {
            if (resultSet!=null){
                resultSet.close();
                resultSet = null;
            }
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (pStatement != null) {
                pStatement.close();
                pStatement = null;
            }
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }




}
