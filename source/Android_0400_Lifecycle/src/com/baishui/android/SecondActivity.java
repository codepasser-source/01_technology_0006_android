package com.baishui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {

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
			 Intent intent = new Intent();
			 intent.setClass(SecondActivity.this, ThirdActivity.class);
			 
			 SecondActivity.this.startActivity(intent);
		}
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		
		System.out.println("second activity ->>>> onCreate");
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.second);
		this.button = (Button) this.findViewById(R.id.secondButton);
		
		this.button.setOnClickListener(new BT_Click_Listeners());
	}
	
	
	 @Override
	    protected void onStart() {
	    	System.out.println("second activity ->>>> onStart");
	    	super.onStart();
	    }
	     
	    @Override
	    protected void onResume() {
	    	System.out.println("second activity ->>>> onResume");
	    	super.onResume();
	    }
	    
	    
	    @Override
	    protected void onPause() {
	    	System.out.println("second activity ->>>> onPause");
	    	super.onPause();
	    }
	    
	    @Override
	    protected void onStop() {
	    	System.out.println("second activity ->>>> onStop");
	    	super.onStop();
	    }
	    
	    @Override
	    protected void onRestart() {
	    	System.out.println("second activity ->>>> onRestart");
	    	super.onRestart();
	    }
	    
	    
	    @Override
	    protected void onDestroy() {
	    	System.out.println("second activity ->>>> onDestroy");
	    	super.onDestroy();
	    }
}
