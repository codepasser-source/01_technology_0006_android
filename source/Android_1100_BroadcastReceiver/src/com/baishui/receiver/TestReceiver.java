package com.baishui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TestReceiver extends BroadcastReceiver {

	
	public TestReceiver() {
		System.out.println("TestReceiver control");
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		 
		System.out.println("TestReceiver onReceive(Context context, Intent intent) ");
		
	}

}
