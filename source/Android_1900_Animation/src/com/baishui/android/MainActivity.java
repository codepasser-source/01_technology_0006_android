package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
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
            // 创建动画集合对象
            AnimationSet animationSet = new AnimationSet(true);
            // 创建一个淡入淡出动画对象
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
            // 设置动画时间
            alphaAnimation.setDuration(2000);
            // 添加动画
            animationSet.addAnimation(alphaAnimation);
            // 设置动画结束后保持最后的状态
            animationSet.setFillAfter(false);
            // 设置动画结束后回到初始状态
            animationSet.setFillBefore(true);
            //设置动画开始等待时间间隔
            animationSet.setStartOffset(2000);
            //设置动画重复次数
            animationSet.setRepeatCount(2);
            // 启动动画
            imageView.startAnimation(animationSet);
        }
    }

    private class ScaleOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 创建动画集合对象
            AnimationSet animationSet = new AnimationSet(true);
            // 创建一个缩放动画对象
            ScaleAnimation ScaleAnimation = new ScaleAnimation(1, 0.1f, 1,
                    0.1f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            // 设置动画时间
            ScaleAnimation.setDuration(2000);
            // 添加动画
            animationSet.addAnimation(ScaleAnimation);
            // 设置动画结束后保持最后的状态
            animationSet.setFillAfter(true);
            // 设置动画结束后回到初始状态
            animationSet.setFillBefore(false);
            // 启动动画
            imageView.startAnimation(animationSet);
        }
    }

    private class RotateOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 创建动画集合对象
            AnimationSet animationSet = new AnimationSet(true);
            // 创建一个旋转动画对象
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_PARENT, 0.5f,
                    Animation.RELATIVE_TO_PARENT, 0.5f);
            // 设置动画时间
            rotateAnimation.setDuration(2000);
            // 添加动画
            animationSet.addAnimation(rotateAnimation);
            // 设置动画结束后保持最后的状态
            animationSet.setFillAfter(true);
            // 设置动画结束后回到初始状态
            animationSet.setFillBefore(false);
            // 启动动画
            imageView.startAnimation(animationSet);
        }
    }

    private class TranslateOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 创建动画集合对象
            AnimationSet animationSet = new AnimationSet(true);
            // 创建一个位移动画对象
            TranslateAnimation translateAnimation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0f,
                    Animation.RELATIVE_TO_PARENT, 0.5f,
                    Animation.RELATIVE_TO_PARENT, 0f,
                    Animation.RELATIVE_TO_PARENT, 0.5f);
            // 设置动画时间
            translateAnimation.setDuration(2000);
            // 添加动画
            animationSet.addAnimation(translateAnimation);
            // 设置动画结束后保持最后的状态
            animationSet.setFillAfter(true);
            // 设置动画结束后回到初始状态
            animationSet.setFillBefore(false);
            // 启动动画
            imageView.startAnimation(animationSet);
        }
    }
}
