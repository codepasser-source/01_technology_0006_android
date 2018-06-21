package com.baishui.mp3player;
 
import com.baishui.model.Mp3Info;
import com.baishui.mp3player.service.PlayService;
import com.baishui.util.AppConstant;

import android.app.Activity;
import android.content.Intent;
 
import android.os.Bundle;
import android.view.View;
import android.widget.Button; 

public class PlayerActivity extends Activity {  
	
	private Button startBT;
	private Button pauseBT;
	private Button stopBT;
 
    private Mp3Info mp3Info=null;
	
	
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		
		System.out.println("[PlayerActivity] onCreate ：this.toString:" +this.toString());
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		Intent intent = getIntent();
	    mp3Info = (Mp3Info)intent.getExtras().get("mp3Info"); 
	    
	    System.out.println(mp3Info.getMp3Name()+"--"+mp3Info.getMp3Size()+"--");
		
	    this.startBT = (Button) this.findViewById(R.id.startBT);	
	    this.pauseBT = (Button) this.findViewById(R.id.pauseBT);	
	    this.stopBT = (Button) this.findViewById(R.id.stopBT);	
	  
	  
		this.startBT.setOnClickListener(new StartButtonClickListener());
	    this.pauseBT.setOnClickListener(new PauseButtonClickListener());
		this.stopBT.setOnClickListener(new StopButtonClickListener());
	    
		
	}
	
	class StartButtonClickListener implements View.OnClickListener{ 
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.putExtra("PlayAction", AppConstant.PlayAction.PLAY_MSG);
			intent.putExtra("mp3Info", mp3Info);
			intent.setClass(PlayerActivity.this, PlayService.class); 
			PlayerActivity.this.startService(intent);
		}
	}
	
	class PauseButtonClickListener implements View.OnClickListener{ 
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.putExtra("PlayAction", AppConstant.PlayAction.PAUSE_MSG); 
			intent.setClass(PlayerActivity.this, PlayService.class); 
			PlayerActivity.this.startService(intent);
		}
	}
	class StopButtonClickListener implements View.OnClickListener{ 
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.putExtra("PlayAction", AppConstant.PlayAction.STOP_MSG); 
			intent.setClass(PlayerActivity.this, PlayService.class); 
			PlayerActivity.this.startService(intent);
		}
	}
	
	 
}
