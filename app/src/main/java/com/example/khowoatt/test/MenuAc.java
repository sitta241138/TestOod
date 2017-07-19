package com.example.khowoatt.test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MenuAc extends AppCompatActivity {


    private MenuTable objMenutable;


    private ListView MenuListview;
    // DB // DB// DB// DB// DB// DB// DB// DB
    SQLiteDatabase sqliteMyDB ;
    MySQLiteOpenHelper mHelper;
    Cursor myDBCursor;
    // DB // DB// DB// DB// DB// DB// DB// DB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
        objMenutable = new MenuTable(this);
    } // เปิดใช้งาน sqlite

    private void createListView() {
        //MenuTable objCasephone = new MenuTable(this);
        //String[] strC_Name = objCasephone.readALLCasePhone(1);
        //String[] strC_Pic = objCasephone.readALLCasePhone(2);
        //String[] strC_Price = objCasephone.readALLCasePhone(3);

        String[] o1 = {"ht","hy","hu"};
        String[] o2 = {"123","456","789"};
        String[] o3 = {"http://bitmouse.96.lt/GUPER/pic/iphone7.png","http://bitmouse.96.lt/GUPER/pic/iphone7.png","http://bitmouse.96.lt/GUPER/pic/iphone7.png"};
        //MyAdapter objMyAdapter = new MyAdapter(OrderActivity.this, strC_Name,strC_Price,strC_Pic);
        AdapterPastry objMyAdapter = new AdapterPastry(MenuAc.this, o1,o3,o2);
        MenuListview.setAdapter(objMyAdapter);

    }

    private void BindWidget() {
        MenuListview = (ListView) findViewById(R.id.listviewAC);
    }

}

