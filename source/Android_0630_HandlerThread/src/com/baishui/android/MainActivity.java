package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;


/**
 * 
 * @author think
 *  不与Acivity使用同一个线程的第三种写法
 *  创建一个自定义的 HandlerThread 线程 与  Handler绑定到一起
 */
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
      //创建一个HandlerThread 并给定名字
        HandlerThread handlerThread = new HandlerThread("handler thread"); 
      //启动线程,不启动线程 时 当前线程的looper 为null
        handlerThread.start();
      //把当前线程与 自定义Handler绑定在一起
        MyHandler myHandler = new MyHandler(handlerThread.getLooper());
      //打印输出当前Activity Thread id 
        
        System.out.println("Activity Thread id:"+Thread.currentThread().getId());
        System.out.println("Activity Thread name:"+Thread.currentThread().getId());
        
      //创建消息对象
        Message msg =  myHandler.obtainMessage();
        //1 利用 arg1 arg2 传递数据 
        //2 利用obj成员属性传递数据
         msg.obj = "abc";
        //3 利用 Bundle 传递大量的数据
        Bundle data = new Bundle();
        data.putInt("id", 1);
        data.putString("name", "张三");
        msg.setData(data);
        //第一种发送消息的方法 ： myHandler.sendMessage(msg);
        //第二种发送消息的方法，像拥有当前的handler 发送数据
        msg.sendToTarget();
    }
    
    
    class MyHandler extends Handler{
    	
    	public MyHandler(){ 
    	}
    	public MyHandler(Looper looper){
    		super(looper);
    	}
    	
    	@Override
    	public void handleMessage(Message msg) {
    		
    		//打印输出当前Activity Thread id  
            System.out.println("MyHandler Thread id:"+Thread.currentThread().getId());
            System.out.println("MyHandler Thread name:"+Thread.currentThread().getId());
    		
    		//获取 obj 
    		String obj = (String) msg.obj;
    		System.out.println("msg.obj:" + obj);
    		//获取 Bundle  data
    		Bundle data = msg.getData(); 
    	    System.out.println("Bundle data  id + "+data.getInt("id")+"Bundle data  name + "+data.getString("name"));
    	} 
    }
    
   //添加菜单功能
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	 menu.add(0, 1, 1, "退出");
    	 menu.add(0, 2, 2, "关于");
    	return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(item.getItemId()==1){
    		finish();
    	}else if(item.getItemId() == 2){
    		
    	}
    	return super.onOptionsItemSelected(item);
    }
}