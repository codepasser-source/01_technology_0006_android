package com.baishui.android;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    
    static final String SMS_ACTION="android.provider.Telephony.SMS_RECEIVED";
	private SMSReceiver  smsReceiver;
	
	private Button registerBT;
	private Button removeBT;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
      this.registerBT= (Button) this.findViewById(R.id.registerBT);
      this.removeBT	= (Button) this.findViewById(R.id.removeBT);
      this.registerBT.setOnClickListener(new OnRegisterClickListener());
      this.removeBT.setOnClickListener(new OnRemoveClickListener());
    }
    
    class OnRegisterClickListener implements View.OnClickListener{ 
    	@Override
    	public void onClick(View v) {
    		//创建广播接收器对象
    		smsReceiver = new SMSReceiver(); 
    		//创建一个IntentFilter对象
    		IntentFilter intentFilter = new IntentFilter();
    		//设定intent过滤器 执行的 事件
    		intentFilter.addAction(SMS_ACTION);
    		//利用当前Activity注册  广播接收器 和 广播接收器拦截 的intent
    		MainActivity.this.registerReceiver(smsReceiver, intentFilter); 
    		System.out.println("Activity OnRegisterClickListener");
    	}
    }
    
    class OnRemoveClickListener implements View.OnClickListener{ 
    	@Override
    	public void onClick(View v) {
    		//利用当前Activity取消注册广播接收器
    		MainActivity.this.unregisterReceiver(smsReceiver);
    		System.out.println("Activity OnRemoveClickListener");
    	}
    }
    
  
    
    
}