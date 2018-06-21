package com.baishui.android.task;

import com.baishui.android.NetOperator;

import android.os.AsyncTask;

public class CustAsyncTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... args) {
        NetOperator netOperator = new NetOperator();
        netOperator.execute();
        return null;
    }

}
