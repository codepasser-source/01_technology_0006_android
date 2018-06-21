package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private Button alphaBT;
    private Button scaleBT;
    private Button rotateBT;
    private Button translateBT;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        alphaBT = (Button) findViewById(R.id.alphaBT);
        scaleBT = (Button) findViewById(R.id.scaleBT);
        rotateBT = (Button) findViewById(R.id.rotateBT);
        translateBT = (Button) findViewById(R.id.translateBT);
        imageView = (ImageView) findViewById(R.id.imageView);

        alphaBT.setOnClickListener(new AlphaOnClickListener());
        scaleBT.setOnClickListener(new ScaleOnClickListener());
        rotateBT.setOnClickListener(new RotateOnClickListener());
        translateBT.setOnClickListener(new TranslateOnClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private class AlphaOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(
                    MainActivity.this, R.anim.alpha_animation);
            imageView.startAnimation(animation);
        }
    }

    private class ScaleOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(
                    MainActivity.this, R.anim.scale_animation);
            imageView.startAnimation(animation);
        }
    }

    private class RotateOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(
                    MainActivity.this, R.anim.rotate_animation);
            imageView.startAnimation(animation);
        }
    }

    private class TranslateOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(
                    MainActivity.this, R.anim.translate_animation);
            imageView.startAnimation(animation);
        }
    }
}
