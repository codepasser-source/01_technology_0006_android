package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {
	
	
	
    /** 
     *比较两个 Activity 与handler 是否在同一个线程之中
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 写法一：与Activity使用同一个线程执行run方法
         */ 
        myHandler.post(r);
        
        /**写法二 ： 与Activity不使用同一个线程 执行run方法
         *  Thread myThread = new Thread(r);
         *  myThread.start();
         */
       
        setContentView(R.layout.main);
        //打印输出当前Activity线程的Id
        System.out.println("Activity ThreadId----> " + Thread.currentThread().getId());
		System.out.println("Activity ThreadName----> " + Thread.currentThread().getName());
        
    }
    
    Handler myHandler = new Handler();
	Runnable r = new Runnable() { 
		@Override
		public void run() {
			//打印输出当前调用run方法的线程的Id
			System.out.println("Handler ThreadId----> " + Thread.currentThread().getId());
			System.out.println("Handler ThreadName----> " + Thread.currentThread().getName());
		   try {
			   Thread.sleep(5000);
		   } catch (InterruptedException e) { 
			 e.printStackTrace();
		   }
		}
	};
    
}