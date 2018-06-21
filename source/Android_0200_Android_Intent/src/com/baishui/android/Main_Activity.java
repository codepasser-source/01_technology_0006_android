package com.baishui.android;

import android.app.Activity;
 
import android.content.Intent;
 
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main_Activity extends Activity {
	
	private Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.main_activity); 
       this.button = (Button)this.findViewById(R.id.nextButton);
       
       
       this.button.setOnClickListener( new MyButtonListener());
    }
    
    class  MyButtonListener implements OnClickListener { 
		@Override
		public void onClick(View arg0) {
			//创建一个Intent 对象
    		Intent intent = new Intent(); 
    		//向intent中添加E
    		intent.putExtra("extraParamName", "extraParamValue"); 
         	//设置关联
    		intent.setClass(Main_Activity.this,Next_Activity.class); 
    		//利用启动其他Activity方法：startActivity(intent)  执行的操作
    		Main_Activity.this.startActivity(intent); 
		}  
    }
    
    
    
    
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
    
    
    
}