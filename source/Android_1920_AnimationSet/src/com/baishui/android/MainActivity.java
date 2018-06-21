package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
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

            /*
             * Animation animation = AnimationUtils.loadAnimation(
             * MainActivity.this, R.anim.animation);
             * imageView.startAnimation(animation);
             */

            AnimationSet animationSet = new AnimationSet(true);// 创建动画集合对象，构造参数是否使用共享设置

            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0); // 创建一个淡入淡出动画对象

            RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_PARENT, 0.2f,
                    Animation.RELATIVE_TO_PARENT, 0.2f);// 创建一个旋转动画对象
            animationSet.addAnimation(alphaAnimation);// 添加动画
            animationSet.addAnimation(rotateAnimation); // 设置动画时间
            animationSet.setDuration(2000); // 设置动画结束后保持最后的状态
            animationSet.setFillAfter(true); // 设置动画结束后回到初始状态
            animationSet.setFillBefore(false); // 启动动画
            animationSet
                    .setInterpolator(new AccelerateDecelerateInterpolator()); // 设置动画速率
            imageView.startAnimation(animationSet);

        }
    }
}
