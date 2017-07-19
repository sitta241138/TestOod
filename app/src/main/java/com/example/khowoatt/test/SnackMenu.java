package com.example.khowoatt.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SnackMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_menu);
    }
    public void onClickBack (View view){
        new Intent(SnackMenu.this,MainActivity.class);
        finish();
    }


}
