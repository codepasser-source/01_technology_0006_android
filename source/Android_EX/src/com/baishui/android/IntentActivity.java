package com.baishui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IntentActivity extends Activity {
  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.intent_activity); 
		//得到当前的intent
		Intent intent = this.getIntent();
		EditText textView = (EditText) this.findViewById(R.id.intentTitleLabel);
		//数据传递  intent中的 Extra 键值对
		textView.setText(intent.getStringExtra("extraParamName"));
		
		Button backBtn = (Button)this.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new OnBackBtnClickListener());
	}
	
	class OnBackBtnClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}
}
