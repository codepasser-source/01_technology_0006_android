package com.baishui.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SMS extends Activity {
    /** Called when the activity is first created. */
	
	private Button button;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.button = (Button) this.findViewById(R.id.myButton);
        this.button.setOnClickListener(new MyListeners());
        
    }
    
    class MyListeners  implements OnClickListener{
    	
    	@Override
    	public void onClick(View view) {
    		/**Android 系统自带的 短息功能接口 **/
    		//设定发送短信的Uri  传送的数据
    		
    		Uri uri = Uri.parse("smsto:0800000123");
    		
    		//在Intent 创建时 指定动作 ACTION_SENDTO
    		Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
    		//短信功能接口 固定的 参数  填充页面
    		intent.putExtra("sms_body", "短信内容");
    		//启动发短信界面
    		startActivity(intent);
    	 
    	}
    }

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
    
    
    
}