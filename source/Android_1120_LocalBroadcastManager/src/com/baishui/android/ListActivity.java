package com.baishui.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListActivity extends SuperActivity {

    private Button sendBT;

    private Button detailBT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        sendBT = (Button) this.findViewById(R.id.sendBroadcastBT);
        sendBT.setOnClickListener(new SendOnClickListener());

        detailBT = (Button) this.findViewById(R.id.detailBT);
        detailBT.setOnClickListener(new DetailOnClickListener());
    }

    private class SendOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent finishIntent = new Intent();
            finishIntent.setAction(ACTION_FINISH_SELF);
            finishIntent.putExtra(StartActivity.START_MODE, getStartMode());
            getLocalBroadcastManager().sendBroadcast(finishIntent);
        }
    }

    private class DetailOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent detailIntent = new Intent();
            detailIntent.setClass(ListActivity.this, DetailActivity.class);
            startActivity(detailIntent);
        }
    }

    @Override
    public void finish() {
        System.out.println("trigger finish receiver ListActivity finish()..."
                + this);
        super.finish();
    }
}
