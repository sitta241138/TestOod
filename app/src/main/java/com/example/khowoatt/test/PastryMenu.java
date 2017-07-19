package com.example.khowoatt.test;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class PastryMenu extends AppCompatActivity {

    private MenuTable objMenuTable;
    private TextView NameTextView;
    private TextView PriceTextView;
    private ListView PastryListView;

    // DB // DB// DB// DB// DB// DB// DB// DB
    SQLiteDatabase sqliteMyDB ;
    MySQLiteOpenHelper mHelper;
    Cursor myDBCursor;
    // DB // DB// DB// DB// DB// DB// DB// DB
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastry_menu);

        BindWidget();
        Cdatabase();
        createListView();

    }
    @Override
    public void onPause() {
        super.onPause();
        mHelper.close();
        sqliteMyDB.close();
    }

    public void Cdatabase(){ // Open DataBase

        objMenuTable = new MenuTable(this);
    } // เปิดใช้งาน sqlite

    private void createListView() {
        MenuTable objMenuTable = new MenuTable(this);
        String[] name_menu = objMenuTable.readALLMenuTable(1);
        String[] picture_menu = objMenuTable.readALLMenuTable(4);
        String[] price_menu = objMenuTable.readALLMenuTable(3);

        //String[] o1 = {"ht","hy","hu"};
        //String[] o2 = {"123","456","789"};
        //String[] o3 = {"http://bitmouse.96.lt/GUPER/pic/iphone7.png","http://bitmouse.96.lt/GUPER/pic/iphone7.png","http://bitmouse.96.lt/GUPER/pic/iphone7.png"};
        AdapterPastry objAdapterPastry = new AdapterPastry(PastryMenu.this,name_menu,picture_menu,price_menu);
        //MyAdapter objMyAdapter = new MyAdapter(OrderActivity.this, o1,o3,o2);
        PastryListView.setAdapter(objAdapterPastry);

    }

    private void BindWidget() {
        NameTextView = (TextView) findViewById(R.id.texttext);
        PriceTextView = (TextView) findViewById(R.id.texd);
        PastryListView = (ListView) findViewById(R.id.livpastry);

    }
    public void onClickBack (View view){
        new Intent(PastryMenu.this,MainActivity.class);
        finish();
    }
}
