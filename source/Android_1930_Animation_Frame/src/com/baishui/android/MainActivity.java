package com.baishui.android;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private Button runBT;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        runBT = (Button) findViewById(R.id.runBT);
        imageView = (ImageView) findViewById(R.id.imageView);

        runBT.setOnClickListener(new AnimationOnClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private class AnimationOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            imageView.setBackgroundResource(R.drawable.animation_list);
            AnimationDrawable animationDrawable = (AnimationDrawable) imageView
                    .getBackground();
            animationDrawable.start();
        }
    }
}
