package com.baishui.mp3player;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TabHost tabHost = getTabHost(); 
		Resources res = getResources(); 
		
		
		Intent remoteIntent = new Intent();  
		remoteIntent.setClass(this, RemoteActivity.class);  
		
		TabHost.TabSpec remoteSpec =tabHost.newTabSpec("remote list");
		
		remoteSpec.setIndicator("Remote",res.getDrawable(R.drawable.icon));
		remoteSpec.setContent(remoteIntent); 
		tabHost.addTab(remoteSpec);
		
		Intent localIntent = new Intent();  
		localIntent.setClass(this, LocalActivity.class);  
		
		TabHost.TabSpec localSpec =tabHost.newTabSpec("local list"); 
		localSpec.setIndicator("Local",res.getDrawable(R.drawable.icon));
		
		localSpec.setContent(localIntent); 
		tabHost.addTab(localSpec);
		
	    
	}
}
