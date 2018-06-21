package com.baishui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends Activity {

    public static final int NORMAL_MODE = 0;

    public static final int UPLOAD_MODE = 1;

    public static final String START_MODE = "com.baishui.android.start_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int startMode = NORMAL_MODE;
        Intent startIntent = getIntent();
        String action = startIntent.getAction();
        if (Intent.ACTION_MAIN.equals(action)) {
            startMode = NORMAL_MODE;
        } else if (Intent.ACTION_SEND.equals(action)) {
            startMode = UPLOAD_MODE;
        }

        Intent intent = new Intent();
        intent.putExtra(START_MODE, startMode);
        intent.setClass(this, ListActivity.class);
        startActivity(intent);

        finish();
    }

}
