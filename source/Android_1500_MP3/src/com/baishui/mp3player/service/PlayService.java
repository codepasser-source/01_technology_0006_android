package com.baishui.mp3player.service;

import com.baishui.download.FileUtiles;
import com.baishui.model.Mp3Info;
import com.baishui.util.AppConstant;
 

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

public class PlayService extends Service {

	
	//是否正在播放
	private boolean isPlaying = false; 
	//是否播放完成
	private boolean isReleased  = false;
	//是否暂停
	private boolean isPause = false; 
	
	private MediaPlayer mediaPlayer = null; 
	 
	private Mp3Info mp3Info=null;
	
	private String filePath = null;
	
	@Override
	public void onCreate() {
		System.out.println("[PlayService ] : onCreate");
		super.onCreate();
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		System.out.println("[PlayService ] : onBind");
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("[PlayService ] : onStartCommand");
		int playAction = intent.getIntExtra("PlayAction", 0); 
		System.out.println(" [PlayService ] :  [playAction]: " +playAction );
		
		if(playAction == AppConstant.PlayAction.PLAY_MSG){
			mp3Info = (Mp3Info) intent.getSerializableExtra("mp3Info"); 
			System.out.println("[PlayService ] :  [mp3Info]  name : "+ mp3Info.getMp3Name() +" size:"+mp3Info.getMp3Size());
		    filePath = FileUtiles.getMp3SDCARDPATH()+mp3Info.getMp3Name();
	    	System.out.println("[PlayService]  filePath :"+filePath);  
	    	play();
		}else if(playAction == AppConstant.PlayAction.PAUSE_MSG){
			pause();
		}else if(playAction == AppConstant.PlayAction.STOP_MSG){
			stop();
		}
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	private void play(){
		System.out.println("[PlayerService] StartButtonClickListener ： isPlaying :"+isPlaying +" isPause:" +isPause+" isReleased:" +isReleased );
		if(isPlaying ==  false){
			// TODO Auto-generated method stub
			mediaPlayer= MediaPlayer.create(this, Uri.parse(filePath));
			mediaPlayer.setLooping(false);
			mediaPlayer.start(); 
			isPlaying = true;
			isReleased = false;
			isPause = false; 
		} 
	}

	private void pause(){
		System.out.println("[PlayerService]  PauseButtonClickListener ：  isPlaying :"+isPlaying +" isPause:" +isPause+" isReleased:" +isReleased );
		if(mediaPlayer != null){//正在播放
			if(isReleased == false){//没有播完
				if(isPause==false){//当前没有暂停
					mediaPlayer.pause();
					isPause = true;
					isPlaying= false;
				}else{//当前已经暂停
					mediaPlayer.start();
					isPause = false;
					isPlaying= true;
				} 
			}
		} 
	}
	
	private void stop(){
		System.out.println("[PlayerService]  StopButtonClickListener ：  isPlaying :"+isPlaying +" isPause:" +isPause+" isReleased:" +isReleased );
		 if(mediaPlayer!=null){
			if(isPlaying==true){
				if(isReleased==false){
					mediaPlayer.stop();
					mediaPlayer.release();
					isReleased = true;
					isPlaying = false;
				}
			}
		 }
	}
	
 
	  
	  @Override
	public void onDestroy() {
		  System.out.println("[PlayService ] : onDestroy");
		super.onDestroy();
	}
	
}
