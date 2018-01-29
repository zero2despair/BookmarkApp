package com.example.bookmarkapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: 寛人
 * Date: 2018/01/22
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */

public class ADBHelper extends SQLiteOpenHelper{
    public ADBHelper(Context context){
        super(context,"URLdb",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase nio){
        String shite = "";
        shite += "create table site(";
        shite += "name text not null,";
        shite += "url text not null";
        shite += ")";
        nio.execSQL(shite);

    }
    @Override
    public void onUpgrade(SQLiteDatabase nio,int i,int j ){

    }


}   //test
