package com.baishui.android;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class Widget_1100_SurfaceView extends Activity {
	
	//private String localhost = "/mnt/sdcard/testDir/esy.mp4";
	private String remoteFileUri ="http://192.168.1.81:8080/AndroidDownload/esy.mp4";
 
	private SurfaceView surfaceView;
	private Button setBT1;
	private Button setBT2;
	private Button startBT;
	
	private  SurfaceHolder surfaceHolder;
	//
	private MediaPlayer mediaPlayer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surfaceview);
        
        this.surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView); 
        this.setBT1 = (Button) this.findViewById(R.id.surfaceSetBT1);
        this.setBT2 = (Button) this.findViewById(R.id.surfaceSetBT2);
        this.startBT = (Button) this.findViewById(R.id.surfaceStartBT);
        
        this.setBT1.setOnClickListener(new OnSet1Listener());
        this.setBT2.setOnClickListener(new OnSet2Listener());
        this.startBT.setOnClickListener(new OnStartListener());
        
        mediaPlayer = new MediaPlayer();
        //设置音频流 类型
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setFixedSize(320, 240);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        
        //设置播放的容器
        mediaPlayer.setDisplay(surfaceHolder);
       
    }
    
    class OnSet1Listener implements View.OnClickListener{ 
    	 @Override
    	public void onClick(View v) {
    		  surfaceHolder.setFixedSize(320, 240);
    	        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    	} 
    }
    class OnSet2Listener implements View.OnClickListener{ 
   	 @Override
   	public void onClick(View v) {
   	     surfaceHolder.setFixedSize(176, 144);
         surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
   	} 
   }
    class OnStartListener implements View.OnClickListener{ 
   	 @Override
   	public void onClick(View v) {
   		 //设置文件路径 可以是本地 也可以是 http uri
         try {
 			mediaPlayer.setDataSource(remoteFileUri);
 			mediaPlayer.prepare();
 			mediaPlayer.start();
 		} catch (IllegalArgumentException e) { 
 			e.printStackTrace();
 		} catch (IllegalStateException e) { 
 			e.printStackTrace();
 		} catch (IOException e) { 
 			e.printStackTrace();
 		} 
   	} 
   }
}