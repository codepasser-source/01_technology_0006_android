package com.baishui.android;

import com.baishui.android.socket.SocketClient;

import android.app.Activity;
import android.os.Bundle;
 
import android.view.View;
import android.widget.Button;
 

public class MainActivity extends Activity {
	
	
	private SocketClient socketClient =null;
	
	private Button showResponseBT;
	
	public static String SERVER_RESPONSE = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
    	System.out.println("[MainActivity]: onCreate()"); 
    	System.out.println("[MainActivity]: request befor SERVER_RESPONSE:"+SERVER_RESPONSE);
    	//创建客户端连接对象
    	socketClient = new SocketClient();
    	//取得连接，需要tyr catch
    	socketClient.createConnect(); 
    	socketClient.sendMessageToServer("123456"); 
    	
    	System.out.println("[MainActivity]: MainActivity ThreadId----> " + Thread.currentThread().getId());
		System.out.println("[MainActivity]: MainActivity ThreadName----> " + Thread.currentThread().getName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.showResponseBT = (Button) this.findViewById(R.id.showResponseBT);
        this.showResponseBT.setOnClickListener(new OnShowResponseListener());
        
        
        
    }
    
    //发送消息
    class OnShowResponseListener implements View.OnClickListener{ 
		@Override
		public void onClick(View v) {
			System.out.println("[MainActivity]: request end SERVER_RESPONSE:"+SERVER_RESPONSE);
		} 
    }
    
   
     
}