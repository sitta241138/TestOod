package com.example.khowoatt.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.khowoatt.test.Menu.CakeMenu;
import com.example.khowoatt.test.Menu.PastryMenu;
import com.example.khowoatt.test.Menu.SnackMenu;
import com.example.khowoatt.test.SQL.MemberTable;
import com.example.khowoatt.test.SQL.MenuTable;
import com.example.khowoatt.test.SQL.OrderTable;
import com.example.khowoatt.test.SQL.OrderlistTable;
import com.example.khowoatt.test.SQL.SnackTable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private MemberTable objMemberTable;
    private MenuTable objMenuTable;
    private SnackTable objSnackTable;
    private OrderTable objOrderTable;
    private OrderlistTable objOrderListTable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cdatabase();

        synJAONtoSQLite();
    }

    private void synJAONtoSQLite() {
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        //Loop 2 Time
        int intTimes = 0;
        while(intTimes <= 2){

            //Variable and Constant
            InputStream objInputStream = null;
            String strJSON = null;
            String strMemberURL = "http://5711020660011.sci.dusit.ac.th/getAllDataMember.php";
            String strMenuURL = "http://5711020660011.sci.dusit.ac.th/getAllDataMenu.php";
            String strSnackURL = "http://5711020660011.sci.dusit.ac.th/getAllDataSnackbox.php";
            String strOrderURL = "http://5711020660011.sci.dusit.ac.th/getAllDataOrder.php";
            String strOrderListURL = "http://5711020660011.sci.dusit.ac.th/getAllDataOrderlist.php";
            HttpPost objHttpPost = null;

            //1.Create InputStream
            try{
                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTimes){
                    case 0:
                        objHttpPost = new HttpPost(strMemberURL);
                        break;
                    case 1:
                        objHttpPost = new HttpPost(strMenuURL);
                        break;
                    case 2:
                        objHttpPost = new HttpPost(strSnackURL);
                        break;
                    case 3:
                        objHttpPost = new HttpPost(strOrderURL);
                        break;
                    default:
                        objHttpPost = new HttpPost(strOrderListURL);
                        break;
                }
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();
            }catch (Exception e){
                Log.d("bakery","InputStream ==> "+e.toString());
            }
            //2.Create strJSON
            try{
                InputStreamReader objInputStreamReader = new InputStreamReader(objInputStream,"UTF-8");
                BufferedReader objBufferedReader = new BufferedReader(objInputStreamReader);
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;
                while((strLine = objBufferedReader.readLine()) != null){
                    objStringBuilder.append(strLine);
                }
                objInputStream.close();
                strJSON = objStringBuilder.toString();
            }catch (Exception e){
                Log.d("masterUNG","strJSON"+e.toString());
            }
            //3.Update to SQLite
            try{
                JSONArray objJsonArray = new JSONArray(strJSON);
                for(int i =0;i<objJsonArray.length();i++){
                    JSONObject jsonObject = objJsonArray.getJSONObject(i);
                    switch (intTimes){
                        case 0:
                            //update MemberTable

                            String struser = jsonObject.getString("user");
                            String strpass = jsonObject.getString("pass");
                            String stremail = jsonObject.getString("email");
                            String strphone = jsonObject.getString("phone");
                            String strtype = jsonObject.getString("type");
                            objMemberTable.AddNewMember(struser,strpass,stremail,strphone,strtype);
                            break;
                        case 1:
                            //update MenuTable
                            String name_menu = jsonObject.getString("name_menu");
                            String detail_menu = jsonObject.getString("detail_menu");
                            String price_menu = jsonObject.getString("price_menu");
                            String picture_menu = jsonObject.getString("picture_menu");
                            String type = jsonObject.getString("type");
                            objMenuTable.AddNewMenuTable(name_menu,detail_menu,price_menu,picture_menu,type);
                            break;
                        case 2:
                            //update SnackTable
                            String name_snack = jsonObject.getString("name_snack");
                            String detail_snack = jsonObject.getString("detail_snack");
                            String price_snack = jsonObject.getString("price_snack");
                            String picture_snack = jsonObject.getString("picture_menu");
                            objSnackTable.AddNewSnackTable(name_snack,detail_snack,price_snack,picture_snack);
                            break;
                        case 3:
                            //update OrderTable
                            String id_member = jsonObject.getString("id_member");
                            String id_menu = jsonObject.getString("id_menu");
                            String date_order = jsonObject.getString("date_order");
                            String price_order = jsonObject.getString("price_order");
                            String status = jsonObject.getString("status");
                            objOrderTable.AddNewOrderTable(id_member,id_menu,date_order,price_order,status);
                            break;

                        default:
                            //update OrderListTable
                            String id_order = jsonObject.getString("id_order");
                            String id_menulist = jsonObject.getString("id_menu");
                            String amount = jsonObject.getString("amount");
                            String total = jsonObject.getString("Price");
                            objOrderListTable.AddNewOrderListTable(id_order,id_menulist,amount,total);
                            break;
                    }
                }
            }catch (Exception e){
                Log.d("mbakery","strJSON"+e.toString());
            }
            //Increase intTimes
            intTimes += 1;
        }
    } // ซิ้งข้อมูลจาก mysql to sqlite


    private void Cdatabase() {//open database
        objMemberTable = new MemberTable(this);
        objMenuTable = new MenuTable(this);
        objSnackTable = new SnackTable(this);
        objOrderTable = new OrderTable(this);
        objOrderListTable = new OrderlistTable(this);
    }

    public void onClickPastry (View view){
        Intent intent = new Intent(MainActivity.this,PastryMenu.class);
        startActivity(intent);
    }//ลิ้งไปหน้า Menu pastry
    public void onClickCake (View view){
        Intent intent= new Intent(MainActivity.this,CakeMenu.class);
        startActivity(intent);
    }//ลิ้งไปหน้า Menu cake
    public void onClickSnack (View view){
        Intent intent= new Intent(MainActivity.this,SnackMenu.class);
        startActivity(intent);
    }//ลิ้งไปหน้า Menu snack
}
