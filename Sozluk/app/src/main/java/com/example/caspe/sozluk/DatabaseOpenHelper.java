package com.example.caspe.sozluk;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABSE_NAME="data.sqlite";
    private static final int DATABSE_VERSION=1;


    public DatabaseOpenHelper(Context context){
        super(context,DATABSE_NAME,null,DATABSE_VERSION);
    }
}
