package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

public class Widget_1400_SeekBar extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbar);

        SeekBar seekBar = (SeekBar) this.findViewById(R.id.seekbarId);

        // 设置最大值
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBarListener());
    }

    // 坚挺器类，监听进度条状态改变
    private class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

        // 当进度条进度发生变化时调用该方法
        // 参数：progress 进度值
        // 参数：fromUser 是否是用户滑动
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // TODO Auto-generated method stub
            if(fromUser){
                System.out.println("progress-> "+seekBar.getProgress());
            }
        }

        // 当用户开始滑动
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
            System.out.println("start---> "+seekBar.getProgress());
        }

        // 当用户结束滑动
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
            System.out.println("stop---> "+seekBar.getProgress());
        }

    }
}