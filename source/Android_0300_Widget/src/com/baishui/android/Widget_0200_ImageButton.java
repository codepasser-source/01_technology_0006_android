package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;

public class Widget_0200_ImageButton extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imgbutton);
        ImageButton img_button1 = (ImageButton) this
                .findViewById(R.id.img_button1);
        img_button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
            }
        });
    }
}
