package com.baishui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button startBT;
	private Button stopBT;
	
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	System.out.println("[MainActivity] : onCreate");
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.startBT = (Button) this.findViewById(R.id.startBT);
        this.stopBT = (Button) this.findViewById(R.id.stopBT);
        this.startBT.setOnClickListener(new OnStartListener());
        this.stopBT.setOnClickListener(new OnStopListener());
    }
    
    
    class OnStartListener implements View.OnClickListener{
    	@Override
    	public void onClick(View v) {
    		System.out.println("[MainActivity] : OnStartListener");
    		Intent intent = new Intent();
    		intent.setClass(MainActivity.this, MyService.class)  ;
    		
    		//启动service 方法  
    		MainActivity.this.startService(intent);
    	}
    }
    class OnStopListener implements View.OnClickListener{
    	@Override
    	public void onClick(View v) {
    		System.out.println("[MainActivity] : OnStopListener");
    		Intent intent = new Intent();
    		intent.setClass(MainActivity.this, MyService.class);
    		//停止service 方法  
    		stopService(intent); 
    	}
    }
    
    
}