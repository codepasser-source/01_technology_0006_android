package com.baishui.mp3player.service;

import com.baishui.download.HttpDownLoader;
import com.baishui.model.Mp3Info;  
 
import com.baishui.mp3player.MainActivity;
import com.baishui.mp3player.R;  
import android.app.Notification; 
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
 

public class DownLoadService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		 System.out.println("{DownLoadService]:onStartCommand");
		 System.out.println("{DownLoadService]:onStartCommand this.toString"+this.toString());
		 Mp3Info mp3Info = (Mp3Info) intent.getExtras().get("mp3Info");
		// System.out.println(mp3Info.getId()+"-"+mp3Info.getMp3Name()+"-"+mp3Info.getMp3Size());
		 DownFileRunnable downloRunnable = new DownFileRunnable(mp3Info);
		  Thread downThread = new Thread(downloRunnable);
		  downThread.start();
		 
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	class DownFileRunnable implements Runnable{
		
		private Mp3Info mp3Info = null; 
		//创建一个NotificationManager的引用    
		 NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE); 
		 
		 
		public DownFileRunnable(Mp3Info mp3Info) { 
			this.mp3Info = mp3Info;
		} 
		
		@Override
		public void run() {  
			
			//显示 通知
			 showNotNotification("请稍候...");
			//线程下载 
			 System.out.println("[DownFileRunnable]:run ");
			 String uri = "http://192.168.1.102:8080/AndroidDownload/mp3/"+mp3Info.getMp3Name();
			 HttpDownLoader downLoader = new HttpDownLoader();
			 int downResult = downLoader.downloadFile(uri, "testDir/", mp3Info.getMp3Name());  
			 // 1 : 下载文件成功  -1 ：下载文件失败  0 ： 文件已存在 
			String resultMsg =null;
			 switch (downResult) {
			  case 1:
				 System.out.println("文件下载成功"); 
				 resultMsg="文件下载成功";
				 break;
             case -1:
            	 System.out.println("文件下载失败"); 
            	 resultMsg="文件下载失败";
				 break;
             case 0:
            	 System.out.println("文件已经存在"); 
            	 resultMsg="文件已经存在";
				 break;
			}
			
			 //显示线程下载结果
			 showNotNotification(resultMsg);
			   
			   try {
				Thread.sleep(3000);
			   } catch (InterruptedException e) { 
				e.printStackTrace();
			   }
			   
			   notificationManager.cancel(0);
		}
		
		/**
		 * 显示通知的方法
		 * @param nfMsg
		 */
		private void showNotNotification(String nfMsg){
			// 定义Notification的各种属性  
              int icon  =R.drawable.icon ;//通知图标   
			  CharSequence tickerText = nfMsg; //状态栏显示的通知文本提示   
		      long when = System.currentTimeMillis(); //通知产生的时间，会在通知信息里显示   
		 //用上面的属性初始化 Nofification   
			  Notification notification = new Notification(icon,tickerText,when);  
		 //设置通知的事件消息   
			   Context context = getApplicationContext(); //上下文   
			   CharSequence contentTitle = "Notification title"; //通知栏标题   
			   CharSequence contentText = "Hello World!"; //通知栏内容    
			   Intent notificationIntent = new Intent(DownLoadService.this,MainActivity.class); //点击该通知后要跳转的Activity  
             PendingIntent contentIntent = PendingIntent.getActivity(DownLoadService.this,0,notificationIntent,0);   
			   notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent); 
		 //把Notification传递给 NotificationManager  
			   notificationManager.notify(0,notification); 
		} 
	}
	
	@Override
	public void onCreate() {
		 System.out.println("{DownLoadService]:onCreate");
		super.onCreate();
	}
	@Override
	public void onStart(Intent intent, int startId) {
		System.out.println("{DownLoadService]:onStart");
		super.onStart(intent, startId);
	}
	@Override
	public void onDestroy() {
		System.out.println("{DownLoadService]:onDestroy");
		super.onDestroy();
	}
}
