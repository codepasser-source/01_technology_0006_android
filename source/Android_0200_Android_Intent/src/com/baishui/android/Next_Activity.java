package com.baishui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Next_Activity extends Activity {
  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.next_activity); 
		//得到当前的intent
		Intent intent = this.getIntent();
		TextView textView = (TextView) this.findViewById(R.id.nextactivity_text);
		//数据传递  intent中的 Extra 键值对
		textView.setText(intent.getStringExtra("extraParamName")); 
		
	}
}
