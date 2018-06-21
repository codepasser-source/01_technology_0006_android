package com.baishui.android;

import com.baishui.android.task.CustAsyncTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button executeBT = (Button) this.findViewById(R.id.executeBT);
        executeBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                /*阻塞主线程
                 * NetOperator netOperator = new NetOperator();
                netOperator.execute();*/
                //使用异步线程
                CustAsyncTask asyncTask = new CustAsyncTask();
                asyncTask.execute();
            }
        });

        Button operatorBT = (Button) this.findViewById(R.id.operatorBT);
        operatorBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "立即显示", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}