package com.ensun.esy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;
 
 
public class StartActivity extends Activity { 
	
	public static boolean isConnected = false;
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		
		ConnectivityManager cwjManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo info = cwjManager.getActiveNetworkInfo(); 
		 if (info != null && info.isAvailable()){ 
		       //do something 
		       //能联网 
			  isConnected = true; 
			  //Toast.makeText(StartActivity.this, "能连网", Toast.LENGTH_SHORT).show()	;
		  }else{ 
		       //do something 
		       //不能联网 
			  isConnected = false; 
			  Toast.makeText(StartActivity.this, "请检查当前网络连接", Toast.LENGTH_SHORT).show()	;
		  } 
		/* if (cwjManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) { 
			 Toast.makeText(StartActivity.this, "WIFI", Toast.LENGTH_SHORT).show()	;
		 }  
	     if (cwjManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED) {
	    	 Toast.makeText(StartActivity.this, "GPRS", Toast.LENGTH_SHORT).show(); 
	     }*/
			 
		 
		ReadyRunable readyRunable = new ReadyRunable();
		Thread readyThread = new Thread(readyRunable);
		readyThread.start();
		
	}
	
	 class ReadyRunable implements Runnable{
		 @Override
		public void run() {
			 try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Intent intent = new Intent();
			intent.setClass(StartActivity.this, MainActivity.class);
			StartActivity.this.finish();
			StartActivity.this.startActivity(intent);  
		}
	 }
    
	 
}
