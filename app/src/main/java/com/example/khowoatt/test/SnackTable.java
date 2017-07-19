package com.example.khowoatt.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by khowoatt on 17/7/2560.
 */

public class SnackTable {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;

    public static final String Snack_TABLE = "snackboxtable";
    public static final String Snack_ID = "id_snack";
    public static final String Snack_NAME = "name_snack";
    public static final String Snack_DETAIL = "detail_snack";
    public static final String Snack_PRICE = "price_snack";
    public static final String Snack_PICTURE = "picture_snack";

    public SnackTable(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long AddNewSnackTable(String name_snack, String detail_snack, String price_snack,String picture_snack) {
        ContentValues objContentValues = new ContentValues();

        objContentValues.put(objMySQLiteOpenHelper.Snack_NAME, name_snack);
        objContentValues.put(objMySQLiteOpenHelper.Snack_DETAIL, detail_snack);
        objContentValues.put(objMySQLiteOpenHelper.Snack_PRICE, price_snack);
        objContentValues.put(objMySQLiteOpenHelper.Snack_PICTURE, picture_snack);

        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.Snack_TABLE, null, objContentValues);
    }

    public String[] readALLSnackTable(int intColume){
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(Snack_TABLE, new String[]{Snack_ID,Snack_NAME,Snack_DETAIL,Snack_PRICE,Snack_PICTURE},null,null,null,null,null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    strResult = new String[10];
                    // strResult[0] = objCursor.getString(0);
                    // strResult[1] = objCursor.getString(1);
                    // strResult[2] = objCursor.getString(2);
                    // strResult[3] = objCursor.getString(3);
                    for(int i =0;i<10;i++){
                        strResult[i] = objCursor.getString(intColume);
                        objCursor.moveToNext();
                    }
                }
            }
            objCursor.close();
            return strResult;
        }catch (Exception e){
            return null;
        }
        //return new String[0];
    }

}
