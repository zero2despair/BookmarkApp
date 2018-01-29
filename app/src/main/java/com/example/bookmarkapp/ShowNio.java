package com.example.bookmarkapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * Created with IntelliJ IDEA.
 * User: 寛人
 * Date: 2018/01/23
 * Time: 10:05
 * To change this template use File | Settings | File Templates.
 */

public class ShowNio extends Activity {
    int i=0;
    public CustomButton button[] =new CustomButton[200];
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_nio);


        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);  //横にな
        setContentView(layout);

        ADBHelper helper=new ADBHelper(this);
        SQLiteDatabase nio=helper.getReadableDatabase();

        Cursor c=nio.query("site",new String[]{"name","url"},null,null, null, null, null);

        boolean mov =c.moveToFirst();
        while (mov){
            i=i+1;
            button[i] = new CustomButton(this);
            button[i].setTag(String.valueOf(i));
            button[i].setText(String.format( "%s ", c.getString(0)));
            //button[i].setText(String.format( "%s %s", c.getString(0), c.getString(1))); //デバッグ用
            button[i].setUrl(c.getString(1));
            //以下textデバッグ
            //Button button=new Button(this);
            //button.setText(String.format("%s", c.getString(0)));
            //TextView textView=new TextView(this);
           // textView.setText(String.format("%s:%s",c.getString(0),c.getString(1)));
            button[i].setOnClickListener(new View.OnClickListener() {
                                             public void onClick(View v) {
                                                 Uri uri = Uri.parse(button[Integer.parseInt(String.valueOf(v.getTag()))].getUrl());
                                                 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                                 startActivity(intent);
                                            }
                                         });


            mov=c.moveToNext();
            //layout.addView(textView);
            layout.addView(button[i]);
        }
        c.close();
        nio.close();
    }
}
