package com.ensun.esy.service;

 
import com.ensun.esy.MainActivity;
import com.ensun.esy.MediaPlayer;
import com.ensun.esy.R;
 
import com.ensun.esy.model.Word;
import com.ensun.esy.util.Appconstant;
import com.ensun.esy.util.HttpHelper;
import com.ensun.esy.util.LogHelper;

 
 
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
 

public class DownLoadService extends Service {

	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Word word =  (Word) intent.getExtras().get("word"); 
		LogHelper.sysoLog("DownLoadService","onStartCommand","word:"+word.getWord()+" uri:"+word.getVedioUri());
		
		DownLoadRunnable dlRun = new DownLoadRunnable(word);
		Thread dlThread = new Thread(dlRun);
		dlThread.start();
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	class DownLoadRunnable implements Runnable{
	    Word word = null;
		 NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE); 
		 
		public DownLoadRunnable(Word word){
			this.word = word;
		}
		
		@Override
		public void run() {
			
			showNotNotification("正在查找文件请稍候...");
			
			int  state = HttpHelper.downloadFile(word.getVedioUri(), Appconstant.SDCardDir.SDDIR, word.getVedioName());
			
			String nfMsg = "";
			
			switch (state) {
			case 0:
				nfMsg = "文件已经存在";
				MediaPlayer.DOWNLOAD_STATE = MediaPlayer.DOWNLOAD_EXIST;
				break; 
            case 1:
            	nfMsg = "文件下载成功";
            	MediaPlayer.DOWNLOAD_STATE = MediaPlayer.DOWNLOAD_SUCCESS;
				break;
            case -1:
            	nfMsg = "文件下载失败";
            	MediaPlayer.DOWNLOAD_STATE = MediaPlayer.DOWNLOAD_FAIL;
	            break;
			}
			showNotNotification(nfMsg);
			 try {
				  Thread.sleep(2000);
				 } catch (InterruptedException e) { 
					e.printStackTrace();
				  } 
			notificationManager.cancel(0);
		}
		
		/**
		 * 显示通知
		 * @param nfMsg
		 */
		private void showNotNotification(String nfMsg){
			// 定义Notification的各种属性  
              int icon  = R.drawable.logo;//通知图标   
			  CharSequence tickerText = nfMsg; //状态栏显示的通知文本提示   
		      long when = System.currentTimeMillis(); //通知产生的时间，会在通知信息里显示   
		 //用上面的属性初始化 Nofification   
			  Notification notification = new Notification(icon,tickerText,when);  
		 //设置通知的事件消息   
			   Context context = getApplicationContext(); //上下文   
			   CharSequence contentTitle = "E手语"; //通知栏标题   
			   CharSequence contentText = "视频文件查找中..."; //通知栏内容    
			   Intent notificationIntent = new Intent(DownLoadService.this,MainActivity.class); //点击该通知后要跳转的Activity  
               PendingIntent contentIntent = PendingIntent.getActivity(DownLoadService.this,0,notificationIntent,0);   
			   notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent); 
		 //把Notification传递给 NotificationManager  
			   notificationManager.notify(0,notification); 
		}
		
	} 

}
