package com.baishui.android;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class Widget_1000_VideoView extends Activity {
	
	private VideoView videoView ;
	private Button startBT;
	private String SDPATH; 
	private String  loaclfilePath;
	private String remotefilePath;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoview); 
        
        this.SDPATH = Environment.getExternalStorageDirectory() + "/";
        this.loaclfilePath = this.SDPATH +"testDir/esy1.mp4";
        
        this.remotefilePath = "http://192.168.1.81:8080/AndroidDownload/esy.mp4";
        
        System.out.println("[MainActivity] |onCreate| :loaclfilePath:"+loaclfilePath);
        System.out.println("[MainActivity] |onCreate| :remotefilePath:"+remotefilePath);
        
        this.videoView = (VideoView) this.findViewById(R.id.videoView);
        this.startBT = (Button) this.findViewById(R.id.videoStartBT);
        this.startBT.setOnClickListener(new OnStartListener());
        
        //videoView.setVideoPath(path);
        //this.videoView.setVideoURI(Uri.parse(loaclfilePath)); //播放本地文件
        this.videoView.setVideoURI(Uri.parse(remotefilePath)); //播放远程文件
        this.videoView.setMediaController(new MediaController(this)); 
    }
    
    class OnStartListener implements View.OnClickListener{
    	@Override
    	public void onClick(View v) {
    		
    		 System.out.println("[MainActivity] |OnStartListener|");
    		 videoView.requestFocus();
    		 videoView.start();
    	}
    }
    
}