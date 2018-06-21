package com.baishui.android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

public class SuperActivity extends Activity {

    /** android-support-v4.jar local broadcast manager **/
    private LocalBroadcastManager localBroadcastManager;

    private FinishSelfReceiver finishSelfReceiver;

    protected static final String ACTION_FINISH_SELF = "com.baishui.android.action_finish_self";

    private int startMode;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finishSelfReceiver = new FinishSelfReceiver();
        IntentFilter finishFilter = new IntentFilter();
        finishFilter.addAction(ACTION_FINISH_SELF);
        // create subclass local broadcast manager
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager
                .registerReceiver(finishSelfReceiver, finishFilter);

        Intent intent = getIntent();
        if (intent.hasExtra(StartActivity.START_MODE)) {
            startMode = intent.getIntExtra(StartActivity.START_MODE,
                    StartActivity.NORMAL_MODE);
            System.out.println("onCreate:" + startMode);
        }
    }

    private class FinishSelfReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(StartActivity.START_MODE)) {
                System.out.println(startMode
                        + " - "
                        + intent.getIntExtra(StartActivity.START_MODE,
                                StartActivity.NORMAL_MODE));
                if (startMode != intent.getIntExtra(StartActivity.START_MODE,
                        StartActivity.NORMAL_MODE)) {
                    System.out.println("not one mode");
                    return;
                }
            }
            onFinishByBroadcast();
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(finishSelfReceiver);
        super.onDestroy();
    }

    protected void onFinishByBroadcast() {
    }

    protected LocalBroadcastManager getLocalBroadcastManager() {
        return localBroadcastManager;
    }

    protected int getStartMode() {
        return startMode;
    }

    @Override
    public void startActivity(Intent intent) {
        intent.putExtra(StartActivity.START_MODE, startMode);
        super.startActivity(intent);
    }
}