package com.example.bookmarkapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created with IntelliJ IDEA.
 * User: 寛人
 * Date: 2018/01/25
 * Time: 12:04
 * To change this template use File | Settings | File Templates.
 */

public class CustomButton extends Button {
    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CustomButton(Context context) {
        super(context);

    }
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
