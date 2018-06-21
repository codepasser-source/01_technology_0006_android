package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
 
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ProgressBar progressBar;
	private Button startBT;  
	
	 
	
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.startBT = (Button) this.findViewById(R.id.startBT);
        this.progressBar=(ProgressBar) this.findViewById(R.id.progressBar); 
        
        startBT.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(MainActivity.this, "请稍候...", Toast.LENGTH_SHORT).show();
				//显示 进度条
				progressBar.setVisibility(View.VISIBLE);
				System.out.println("启动线程");
				//启动线程
				 handler.post(proThead);
				 
			}
		});
        
    }
    
    //创建一个Handler对象 重写 handlerMessage方法，此方法在 sendMessage被调用时立即执行
    Handler handler = new Handler(){  
    	@Override
    	public void handleMessage(Message msg) {
    		//根据Message对象 msg传递 值来动态改变 状态进度条 显示 
    		System.out.println("接受消息");
    		System.out.println("再次启动线程");
    		 progressBar.setProgress(msg.arg1);
    		//反复压入异步线程堆栈
    		 handler.post(proThead);
    	} 
    };
    
	Runnable proThead = new Runnable() { 
		int progress = 0;
		@Override
		public void run() { 
			
			Message msg = handler.obtainMessage();
			msg.arg1 = progress;
		 
			try {
				//发送消息 执行handleMessage方法
				System.out.println("睡眠1000毫秒");
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(progress <= 100){
				System.out.println("发送消息");
				handler.sendMessage(msg); 
			}
		    if(progress > 100){ 
				System.out.println("完成"); 
				//取消调用
				handler.removeCallbacks(proThead);
				//隐藏进度条
				progressBar.setVisibility(View.GONE); 
				Toast.makeText(MainActivity.this, "完成", Toast.LENGTH_SHORT).show();
				//重置
				progress = 0; 
				progressBar.setProgress(progress);
			}
			System.out.println("progress:"+progress);
			progress += 25;
		}
	};
    
    
    
    
	public Handler getHandler() {
		return handler;
	}  

	public void setHandler(Handler handler) {
		this.handler = handler;
	} 
	public Button getStartBT() {
		return startBT;
	} 
	public void setStartBT(Button startBT) {
		this.startBT = startBT;
	}  
	public ProgressBar getProgressBar() {
		return progressBar;
	}
	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}
}