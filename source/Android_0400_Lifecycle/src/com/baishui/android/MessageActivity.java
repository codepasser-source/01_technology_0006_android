package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class MessageActivity extends Activity {

	private Button button;
	
	
	
	public Button getButton() {
		return button;
	}



	public void setButton(Button button) {
		this.button = button;
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.message);
	 
	}
	
	
}
