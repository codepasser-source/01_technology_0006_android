package com.baishui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button receiverBT;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.receiverBT=  (Button) findViewById(R.id.receiverBT);
        this.receiverBT.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) { 
				
				//创建一个广播intent
				Intent intent = new Intent();
				//指定动作Action 匹配 AndroidManifest.xml中receiver的 intent-filter <action android:name="android.intent.action.EDIT" />
				intent.setAction(Intent.ACTION_EDIT); 
				//发送广播
				MainActivity.this.sendBroadcast(intent); 
			}
		});
        
    }
    
    
    
}