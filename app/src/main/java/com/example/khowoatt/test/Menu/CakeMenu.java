package com.example.khowoatt.test.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.khowoatt.test.MainActivity;
import com.example.khowoatt.test.R;

public class CakeMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_menu);
    }
    public void onClickBack (View view){
        new Intent(CakeMenu.this,MainActivity.class);
        finish();
    }
}
