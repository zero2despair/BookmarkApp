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

/**
 * Created with IntelliJ IDEA.
 * User: 寛人
 * Date: 2018/01/??
 * Time: ??:??
 * To change this template use File | Settings | File Templates.
 */

public class MainActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ADBHelper helper=new ADBHelper(this);
        final SQLiteDatabase nio=helper.getWritableDatabase();

        final EditText nameText=(EditText)findViewById(R.id.editName);
        final EditText urlText=(EditText)findViewById(R.id.editUrl);

        ContentValues insertValues = new ContentValues();                               ////最初に追加してあるのでここから
        insertValues.put("name", "グラブル");
        insertValues.put("url", "http://game.granbluefantasy.jp/#mypage");
        long id = nio.insert("site", "グラブル", insertValues);                     ////ここまでは正式に使う場合は削除

        Button registrationButton=(Button)findViewById(R.id.insert);   ////////登録ぼたんおしたとき！！！名前とurlをひろっちゃうよ
        registrationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameText.getText().toString();
                String url=urlText.getText().toString();



                if(name.equals("")) {               /////さいと名をにゅうりょくしてないとき！！！！サイト名を入力して＞＜ってでるよ！！！
                    Toast.makeText(MainActivity.this, "サイト名を入力して＞＜", Toast.LENGTH_SHORT).show();
                }else {
                    if(url.equals("")) {            ///////URLをにゅうりょくしてないとき！！！URLがないやんけってでるかも！！！！
                        Toast.makeText(MainActivity.this, "URLがないやんけ", Toast.LENGTH_SHORT).show();
                    }else {
                        if(url.startsWith("http:")) {            ///URLの先頭がhttp:ではじまってるとサイト名とURLが入力されてると登録しtaYO、ってでてでーたーべーすに内容が保存されりょ！！！！！
                            ContentValues insertValues = new ContentValues();
                            insertValues.put("name", name);
                            insertValues.put("url", url);
                            long id = nio.insert("site", name, insertValues);
                            Toast.makeText(MainActivity.this, "登録しtaYO", Toast.LENGTH_SHORT).show();
                            // nameText.getEditableText().clear();
                            // urlText.getEditableText().clear();
                        }else{
                            if(url.startsWith("https:")) {   ///URLの先頭がhttp:ではじまってるとサイト名とURLが入力されてると登録しtaYO、ってでてでーたーべーすに内容が保存されりょ！！！！！
                                ContentValues insertValues = new ContentValues();
                                insertValues.put("name", name);
                                insertValues.put("url", url);
                                long id = nio.insert("site", name, insertValues);
                                Toast.makeText(MainActivity.this, "登録しtaYO", Toast.LENGTH_SHORT).show();
                            }else {                                         ///////http:かhttps:が先頭にないと以下文章がでてくるにょ！！！
                                Toast.makeText(MainActivity.this, "ウラルの術式の電都ラバハキアの”魔人”セントゥ・ウ（属性：メタル）にHypertext Transfer Protocol又は" +
                                        "Hypertext Transfer Protocol Secureを口述伝承してみよ。", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }
            }
        });

        Button deleteButton=(Button) findViewById(R.id.delete);         ///////削除ぼたんおしたとき！！！！textboxの名前とurlもってくよ！
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameText.getText().toString();
                String url=urlText.getText().toString();

                if(name.equals("")){            /////サイト名がにゅうりょくされてないときっ！！サイト名が入力されてないのでグラブルやってってでてグラブルのHPにとばされちゃうよ！！こわーーい！！
                    Toast.makeText(MainActivity.this,"サイト名が入力されてないのでグラブルやって",Toast.LENGTH_SHORT).show();
                    Uri ur=Uri.parse("http://game.granbluefantasy.jp/");
                    Intent intent=new Intent(Intent.ACTION_VIEW,ur);
                    startActivity(intent);

                }else{                          ////////サイト名が入力されてると削除したｯピってでて該当サイト名のデータが全部きえるよ！！
                    nio.delete("site","name=?",new String[]{name} );
                    nameText.getEditableText().clear();
                    urlText.getEditableText().clear();
                    Toast.makeText(MainActivity.this,"削除したｯピ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button allDeleteButton=(Button)findViewById(R.id.superDelete);          //////全削除ぼたんおしたとき！！！！
        allDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)                      //////確認ダイアログがでるよ
                        .setTitle("削除しますか？")
                        .setMessage("全部削除しちゃうけど本当によろしいか？？？")
                        .setNegativeButton("やめて、どうぞ", null)             ////所謂Noなにもおきないよ
                        .setPositiveButton("やめろォ(建前) ナイスゥ(本音)", new DialogInterface.OnClickListener() {     ////所謂Yws全部きえちゃうよ
                            @Override
                            public void onClick(DialogInterface dialog, int w) {    //きえちゃう
                                String name=nameText.getText().toString();
                                String url=urlText.getText().toString();
                                nio.delete("site",null,null);       //siteの中身をnullにｓるおで実質全削除
                            }
                        })
                        .show();



            }
        });
        Button shownioButton=(Button)findViewById(R.id.show);       //////保存したURLみるとこにとぶよ！
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
