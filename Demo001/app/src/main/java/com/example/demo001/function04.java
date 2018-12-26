package com.example.demo001;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.MapTableData;
import com.bin.david.form.utils.DensityUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class function04 extends AppCompatActivity {

    Connection con;
    public SmartTable table;
    String dbun,dbpass,db,ip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function04);
        setTitle("病床查詢系統");

        FontStyle.setDefaultTextSize(DensityUtils.sp2px(this, 15));
        table = (SmartTable) findViewById(R.id.table);


    }

    public void getData(String json){

        MapTableData tableData = MapTableData.create("查詢結果",JsonHelper.jsonToMapList(json));
        table.setTableData(tableData);


    }
    String search_result = "";


    public void search(View view) {

        String z = "fail";
        Boolean isSuccess = false;

        try {
            con = connetionclass(dbun,dbpass,db,ip);  // connect to db
            if (con == null)
            {
                z = "Check your internet";
            } else
            {
                String sql = "select * from [test].[dbo].[Edah] where Datetimes = '2018-12-19' for json path";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()) {
                    search_result = rs.toString();
                    Toast.makeText(function04.this,search_result,Toast.LENGTH_LONG).show();
                }
            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            z = ex.getMessage();
            Toast.makeText(function04.this,z,Toast.LENGTH_LONG).show();
        }
        finally {
            closedb();
        }


        String json="[{\"Bed\":\"健保病床 急性一般病床                   \",\"TotBed\":605,\"BedUs\":414,\"BedUnUs\":191,\"TotBedUsp\":6.843000000000001e+001,\"Type\":\"T006\",\"Hos\":\"義大醫院           \",\"Datetimes\":\"2018-12-09\"},{\"Bed\":\"健保病床 急性精神病床                   \",\"TotBed\":27,\"BedUs\":27,\"BedUnUs\":0,\"TotBedUsp\":1.000000000000000e+002,\"Type\":\"T007\",\"Hos\":\"義大醫院           \",\"Datetimes\":\"2018-12-09\"},{\"Bed\":\"差額病床 急性一般病床                   \",\"TotBed\":293,\"BedUs\":178,\"BedUnUs\":115,\"TotBedUsp\":6.075000000000000e+001,\"Type\":\"T006\",\"Hos\":\"義大醫院           \",\"Datetimes\":\"2018-12-09\"},{\"Bed\":\"差額病床 急性精神病床                   \",\"TotBed\":3,\"BedUs\":2,\"BedUnUs\":1,\"TotBedUsp\":6.667000000000000e+001,\"Type\":\"T007\",\"Hos\":\"義大醫院           \",\"Datetimes\":\"2018-12-09\"},{\"Bed\":\"外科加護病床                        \",\"TotBed\":40,\"BedUs\":37,\"BedUnUs\":3,\"TotBedUsp\":9.250000000000000e+001,\"Type\":\"T013\",\"Hos\":\"義大醫院           \",\"Datetimes\":\"2018-12-09\"},{\"Bed\":\"兒科加護病床                        \",\"TotBed\":15,\"BedUs\":10,\"BedUnUs\":5,\"TotBedUsp\":6.667000000000000e+001,\"Type\":\"T013\",\"Hos\":\"義大醫院           \",\"Datetimes\":\"2018-12-09\"},{\"Bed\":\"內科加護病床                        \",\"TotBed\":26,\"BedUs\":26,\"BedUnUs\":0,\"TotBedUsp\":1.000000000000000e+002,\"Type\":\"T013\",\"Hos\":\"義大醫院           \",\"Datetimes\":\"2018-12-09\"},{\"Bed\":\"燒傷加護病床                        \",\"TotBed\":4,\"BedUs\":0,\"BedUnUs\":4,\"TotBedUsp\":0.000000000000000e+000,\"Type\":\"T015\",\"Hos\":\"義大醫院           \",\"Datetimes\":\"2018-12-09\"},{\"Bed\":\"新生兒中重度病床                      \",\"TotBed\":26,\"BedUs\":18,\"BedUnUs\":8,\"TotBedUsp\":6.923000000000000e+001,\"Type\":\"T035\",\"Hos\":\"義大醫院           \",\"Datetimes\":\"2018-12-09\"},{\"Bed\":\"其他特殊病床                        \",\"TotBed\":27,\"BedUs\":13,\"BedUnUs\":14,\"TotBedUsp\":4.815000000000000e+001,\"Type\":\"T035\",\"Hos\":\"義大醫院           \",\"Datetimes\":\"2018-12-09\"}]";

        getData(json);
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
