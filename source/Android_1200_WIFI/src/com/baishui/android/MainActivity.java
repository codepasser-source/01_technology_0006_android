package com.baishui.android;

import android.app.Activity;
import android.content.Context;
 
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button openBT;
	private Button closeBT;
	private Button stateBT;
	
	private WifiManager wifiManager = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.openBT = (Button) this.findViewById(R.id.openBT);
        this.closeBT = (Button) this.findViewById(R.id.closeBT);
        this.stateBT = (Button) this.findViewById(R.id.stateBT);
        
        this.openBT.setOnClickListener(new OnOpenClickListener());
        this.closeBT.setOnClickListener(new OnCloseClickListener());
        this.stateBT.setOnClickListener(new OnStateClickListener());
    }
   
    class OnOpenClickListener  implements View.OnClickListener{ 
		@Override
		public void onClick(View v) {
			//获得WIFI Manager 的方法
			wifiManager = (WifiManager) MainActivity.this.getSystemService(Context.WIFI_SERVICE);
			//设置开启
			wifiManager.setWifiEnabled(true);
			System.out.println("wifi state ---->" + wifiManager.getWifiState());
			Toast.makeText(MainActivity.this, "当前Wifi网卡状态为："+wifiManager.getWifiState(), Toast.LENGTH_SHORT).show();
		    
		} 
    }
    class OnCloseClickListener  implements View.OnClickListener{ 
		@Override
		public void onClick(View v) {
			//获得WIFI Manager 的方法
			wifiManager = (WifiManager) MainActivity.this.getSystemService(Context.WIFI_SERVICE);
			//设置停止
			wifiManager.setWifiEnabled(false);
			System.out.println("wifi state ---->" + wifiManager.getWifiState());
			Toast.makeText(MainActivity.this, "当前Wifi网卡状态为："+wifiManager.getWifiState(), Toast.LENGTH_SHORT).show();
		    
		} 
    }
    class OnStateClickListener  implements View.OnClickListener{ 
		@Override
		public void onClick(View v) {
			//获得WIFI Manager 的方法
			wifiManager = (WifiManager) MainActivity.this.getSystemService(Context.WIFI_SERVICE);
			System.out.println("wifi state ---->" + wifiManager.getWifiState());
			Toast.makeText(MainActivity.this, "当前Wifi网卡状态为："+wifiManager.getWifiState(), Toast.LENGTH_SHORT).show();
		    
		} 
    }
    
}