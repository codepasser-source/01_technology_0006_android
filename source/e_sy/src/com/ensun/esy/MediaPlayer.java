package com.ensun.esy;  

import com.ensun.esy.model.Word;
import com.ensun.esy.service.DownLoadService;
import com.ensun.esy.util.Appconstant;
import com.ensun.esy.util.LogHelper; 
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
 
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
 
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
 

public class MediaPlayer extends Activity {

	private static final int  OPTIONMENU_OPTION = 1;
	private static final int  OPTIONMENU_BACK = 2; 
	
	public static final int DOWNLOAD_INIT = 0; 
	public static final int DOWNLOAD_SUCCESS = 1; 
	public static final int DOWNLOAD_EXIST = 2;
	public static final int DOWNLOAD_FAIL = 3;
	
	public static int DOWNLOAD_STATE = DOWNLOAD_INIT;  
	
	private ProgressBar progressBar;
	private TextView waitLabel;
	private Word word = null;
	private VideoView videoView = null;
	private MediaController mediaController=null;
	
	private Intent intent = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {  
		LogHelper.sysoLog("MediaPlayer","onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mediaplayer);  
		this.videoView = (VideoView) this.findViewById(R.id.videoView); 
		this.progressBar=(ProgressBar) this.findViewById(R.id.progressBar);
		 this.waitLabel=(TextView) this.findViewById(R.id.waitLabel);
		
		intent = getIntent();
		word = (Word) intent.getExtras().get("word"); 
        LogHelper.sysoLog("MediaPlayer","onCreate","word:"+word.getWord()+" vedioName:"+word.getVedioName()+" vedioSize:"+word.getVedioSize()+" vedioUri:"+word.getVedioUri());
      //  LogHelper.sysoLog("MediaPlayer","filePath:",Appconstant.SDCardDir.SDDIR+"/"+word.getVedioName());
       
        
        downloadVedio(); //下载 
        handler.post(playerListener); //启动监听
        
	} 
	
	
	
	  //创建一个Handler对象 重写 handlerMessage方法，此方法在 sendMessage被调用时立即执行
    Handler handler = new Handler(){  
    	@Override
    	public void handleMessage(Message msg) {
    		//根据Message对象 msg传递 值来动态改变 状态进度条 显示  
    		//反复压入异步线程堆栈 
    		//LogHelper.sysoLog("handler", "handleMessage: get arg1:"+msg.arg1);
    		
    		if(msg.arg1 == MediaPlayer.DOWNLOAD_INIT){ //继续监听
    			handler.post(playerListener);
    		}else{
    			if(msg.arg1==MediaPlayer.DOWNLOAD_SUCCESS ||msg.arg1==MediaPlayer.DOWNLOAD_EXIST){
    			   	progressBar.setVisibility(View.GONE);
    				 waitLabel.setVisibility(View.GONE); 
    				play();
    			}else{
    				Toast.makeText(MediaPlayer.this, "文件连接失败", Toast.LENGTH_SHORT).show();
    			}
    			MediaPlayer.DOWNLOAD_STATE = MediaPlayer.DOWNLOAD_INIT;//重置状态
    		}
    		
    	} 
    };
	
    Runnable  playerListener = new  Runnable(){ 
		@Override
		public void run() {
			
			Message msg = handler.obtainMessage();
			msg.arg1 = MediaPlayer.DOWNLOAD_STATE;
			//LogHelper.sysoLog("ListenerPlayer", "state:"+MediaPlayer.DOWNLOAD_STATE);  
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			handler.sendMessage(msg);  
		} 
	}; 
	

		
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    	 
    	//横屏时
    	if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
    		 
         }else 
          //竖屏时 
          if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
        	 
    	}
    	super.onConfigurationChanged(newConfig);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, OPTIONMENU_OPTION, OPTIONMENU_OPTION,R.string.optionsMenu_Option);
		menu .add(1,OPTIONMENU_BACK, OPTIONMENU_BACK,R.string.optionsMenu_Back);
		return super.onCreateOptionsMenu(menu);
	}
     
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == OPTIONMENU_OPTION) { 
		} else if (item.getItemId() == OPTIONMENU_BACK) {
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	} 
	
	
	 private void play(){  
			// this.videoView.setVideoURI(Uri.parse(word.getVedioUri())); //播放远程文件
			this.videoView.setVideoURI(Uri.parse(Appconstant.SDCardDir.SDPATH
					+ Appconstant.SDCardDir.SDDIR + "/" + word.getVedioName())); // 播放本地文件
			mediaController = new MediaController(this); 
			
			this.videoView.setMediaController(mediaController);
			
			this.videoView.setOnCompletionListener(new OnMyCompletionListener());
			 
			
			videoView.pause();
			videoView.start();
		 } 
		
		private void downloadVedio(){
			 intent.setClass(MediaPlayer.this, DownLoadService.class);
			 MediaPlayer.this.startService(intent);
		}
	
		
		class OnMyCompletionListener implements android.media.MediaPlayer.OnCompletionListener{

			@Override
			public void onCompletion(android.media.MediaPlayer mp) {
				 MediaPlayer.this.finish();
			}
			
		}
	 
}
