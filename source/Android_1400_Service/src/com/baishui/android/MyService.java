package com.baishui.android;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("[MyService] :onBind");
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub 
		System.out.println("[MyService] :onCreate");
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		System.out.println("[MyService] :onStart");
		super.onStart(intent, startId);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("[MyService] :onStartCommand");
		System.out.println("[MyService] :onStartCommand flags："+flags);
		System.out.println("[MyService] :onStartCommand startId："+startId);
		return START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy() {
		System.out.println("[MyService] : onDestroy");
		super.onDestroy();
	}
	
	
}
