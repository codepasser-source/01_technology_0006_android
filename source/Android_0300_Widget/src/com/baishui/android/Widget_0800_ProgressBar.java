package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Widget_0800_ProgressBar extends Activity {

    private ProgressBar firstBar;
    private ProgressBar secondBar;
    private Button button;

    private int progress = 0;

    public ProgressBar getFirstBar() {
        return firstBar;
    }

    public void setFirstBar(ProgressBar firstBar) {
        this.firstBar = firstBar;
    }

    public ProgressBar getSecondBar() {
        return secondBar;
    }

    public void setSecondBar(ProgressBar secondBar) {
        this.secondBar = secondBar;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);

        this.firstBar = (ProgressBar) this.findViewById(R.id.firstProgressBar);
        this.secondBar = (ProgressBar) this
                .findViewById(R.id.secondProgressBar);
        this.button = (Button) this.findViewById(R.id.sureButtonId);

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progress == 0) {
                    // 开始显示
                    firstBar.setVisibility(View.VISIBLE);
                    secondBar.setVisibility(View.VISIBLE);
                    firstBar.setMax(100);
                } else if (progress <= firstBar.getMax()) {
                    firstBar.setProgress(progress);
                    firstBar.setSecondaryProgress(progress + 10);
                    // 默认的进度条 是无法设置显示进度
                    // secondBar.setPressed(progress);
                    // secondBar.setSecondaryProgress(progress+10);
                } else {
                    // 设置消失
                    firstBar.setVisibility(View.GONE);
                    secondBar.setVisibility(View.GONE);
                }
                progress += 10;
            }
        });
    }
}