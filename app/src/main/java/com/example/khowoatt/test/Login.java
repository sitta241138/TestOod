package com.example.khowoatt.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.khowoatt.test.SQL.MemberTable;

public class Login extends AppCompatActivity {

    private MemberTable objMemberTable;
    private EditText userEditText,passEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void btnLogin(View view){
        userEditText = (EditText) findViewById(R.id.editUser);
        passEditText = (EditText) findViewById(R.id.editPass);

        String struser = userEditText.getText().toString().trim();
        String strpass = passEditText.getText().toString().trim();

        //Check char zero
        if(struser.equals("") || strpass.equals("")){
            errorDialog("มีช่องว่าง","กรุณากรอกให้ครบ ทุกช่อง");

        }else {
            checkUSERPASSWORD(struser,strpass);
        }
    }

    private void checkUSERPASSWORD(String struser, String strpass) {
        try{
            String[] strMyResult = objMemberTable.searchUSERPASSWORD(struser);
            if(strpass.equals(strMyResult[3])){
                //password True
                welcomDialog(struser);
            }else {
                //password False
                errorDialog("รหัสผ่านใหม่ถูกต้อง","กรุณาลองใหม่");
            }

        }catch(Exception e){
            errorDialog("ชื่อผู้ใช้ไม่ถูกต้อง","ไม่มี "+struser+" ในระบบ");
        }
    }

    private void welcomDialog(final String strname) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.welcome);
        objBuilder.setTitle("HomeBakery");
        objBuilder.setMessage("ยินดีต้อนรับคุณ "+strname+" เข้าสู่ระบบ กดปุ่ม OK เพื่อเข้าใช้งาน");
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("name",strname);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        objBuilder.show();
    } //Builder ERROR

    private void errorDialog(String strTitle,String strMessage) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.usererror);
        objBuilder.setTitle(strTitle);
        objBuilder.setMessage(strMessage);
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objBuilder.show();
    } //Builder ERROR
}
