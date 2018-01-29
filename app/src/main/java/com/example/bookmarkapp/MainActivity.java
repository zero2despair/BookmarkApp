package com.example.bookmarkapp;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ADBHelper helper=new ADBHelper(this);
        final SQLiteDatabase nio=helper.getWritableDatabase();

        final EditText nameText=(EditText)findViewById(R.id.editName);
        final EditText urlText=(EditText)findViewById(R.id.editUrl);

        Button RegistrationButton=(Button)findViewById(R.id.insert);
        RegistrationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameText.getText().toString();
                String url=urlText.getText().toString();

                if(name.equals("")) {
                    Toast.makeText(MainActivity.this, "URL名を入力して＞＜", Toast.LENGTH_SHORT).show();
                }else {
                    if(url.equals("")) {
                        Toast.makeText(MainActivity.this, "URLがないやんけ", Toast.LENGTH_SHORT).show();
                    }else {
                        ContentValues insertValues = new ContentValues();
                        insertValues.put("name", name);
                        insertValues.put("url", url);
                        long id = nio.insert("site", name, insertValues);
                        Toast.makeText(MainActivity.this, "登録しtaYO", Toast.LENGTH_SHORT).show();
                       // nameText.getEditableText().clear();
                       // urlText.getEditableText().clear();
                    }
                }
            }
        });

        Button deleteButton=(Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameText.getText().toString();
                String url=urlText.getText().toString();

                if(name.equals("")){
                    Toast.makeText(MainActivity.this,"URL名が入力されてないのでグラブルやって",Toast.LENGTH_SHORT).show();
                    Uri ur=Uri.parse("http://game.granbluefantasy.jp/");
                    Intent intent=new Intent(Intent.ACTION_VIEW,ur);
                    startActivity(intent);

                }else{
                    nio.delete("site","name=?",new String[]{name} );
                    nameText.getEditableText().clear();
                    urlText.getEditableText().clear();
                    Toast.makeText(MainActivity.this,"削除しったｯピ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button allDeleteButton=(Button)findViewById(R.id.superDelete);
        allDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("削除しますか？")
                        .setMessage("全部削除しちゃうけど本当によろしいか？？？")
                        .setNegativeButton("やめて、どうぞ", null)
                        .setPositiveButton("削除しちくり＾～", null)
                        .show();


                String name=nameText.getText().toString();
                String url=urlText.getText().toString();

                nio.delete("site",null,null);
            }
        });
        Button shownioButton=(Button)findViewById(R.id.show);
        shownioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nioIntent = new Intent(MainActivity.this,
                        ShowNio.class);
                startActivity(nioIntent);
            }
        });
    }
}
