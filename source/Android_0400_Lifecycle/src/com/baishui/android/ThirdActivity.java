package com.baishui.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThirdActivity extends Activity {
 
	private Button button;
	
	
	
	public Button getButton() {
		return button;
	}



	public void setButton(Button button) {
		this.button = button;
	}

	class BT_Click_Listeners implements OnClickListener{
		
		@Override
		public void onClick(View v) {
			/**Android 系统自带的 短息功能接口 **/
    		//设定发送短信的Uri  传送的数据
    		
    		Uri uri = Uri.parse("smsto:13840981342");
    		
    		//在Intent 创建时 指定动作 ACTION_SENDTO
    		Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
    		//短信功能接口 固定的 参数  填充页面
    		intent.putExtra("sms_body", "短信内容");
    		//启动发短信界面
    		startActivity(intent);
			 
		}
	}


	@Override
  protected void onCreate(Bundle savedInstanceState) {
	  System.out.println("third activity ->>>> onCreate");
	  super.onCreate(savedInstanceState);
	  this.setContentView(R.layout.third);
	  this.button= (Button) this.findViewById(R.id.thirdButton);
	  this.button.setOnClickListener(new BT_Click_Listeners());
  }
	
	 @Override
	    protected void onStart() {
	    	System.out.println("third activity ->>>> onStart");
	    	super.onStart();
	    }
	     
	    @Override
	    protected void onResume() {
	    	System.out.println("third activity ->>>> onResume");
	    	super.onResume();
	    }
	    
	    
	    @Override
	    protected void onPause() {
	    	System.out.println("third activity ->>>> onPause");
	    	super.onPause();
	    }
	    
	    @Override
	    protected void onStop() {
	    	System.out.println("third activity ->>>> onStop");
	    	super.onStop();
	    }
	    
	    @Override
	    protected void onRestart() {
	    	System.out.println("third activity ->>>> onRestart");
	    	super.onRestart();
	    }
	    
	    
	    @Override
	    protected void onDestroy() {
	    	System.out.println("third activity ->>>> onDestroy");
	    	super.onDestroy();
	    }
}
