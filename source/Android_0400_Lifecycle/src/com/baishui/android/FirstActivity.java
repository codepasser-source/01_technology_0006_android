package com.baishui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity {
	
	private Button button;
	
	private Button msgbutton;
	
    public Button getButton() {
		return button;
	} 
	public void setButton(Button button) {
		this.button = button;
	}

	public Button getMsgbutton() {
		return msgbutton;
	}
	public void setMsgbutton(Button msgbutton) {
		this.msgbutton = msgbutton;
	}

	class BT_Click_listeners implements OnClickListener{
		@Override
		public void onClick(View v) {
			 Intent intent = new Intent();
			 
			  intent.setClass(FirstActivity.this, SecondActivity.class);
			 
			 FirstActivity.this.startActivity(intent);
			 
		} 
	}

	class Msg_BT_Click_listeners implements OnClickListener{
		@Override
		public void onClick(View v) {
			 Intent intent = new Intent();
			 
			  intent.setClass(FirstActivity.this, MessageActivity.class);
			 
			 FirstActivity.this.startActivity(intent);
			 
		} 
	}
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        System.out.println("first activity ->>>> onCreate");
        setContentView(R.layout.main); 
        this.button = (Button) this.findViewById( R.id.firstButton);
        this.button.setOnClickListener(new BT_Click_listeners());
        
        this.msgbutton = (Button) findViewById(R.id.toMsgButton);
        this.msgbutton.setOnClickListener(new Msg_BT_Click_listeners());
        
    }
    
    @Override
    protected void onStart() {
    	System.out.println("first activity ->>>> onStart");
    	super.onStart();
    }
     
    @Override
    protected void onResume() {
    	System.out.println("first activity ->>>> onResume");
    	super.onResume();
    }
    
    
    @Override
    protected void onPause() {
    	System.out.println("first activity ->>>> onPause");
    	super.onPause();
    }
    
    @Override
    protected void onStop() {
    	System.out.println("first activity ->>>> onStop");
    	super.onStop();
    }
    
    @Override
    protected void onRestart() {
    	System.out.println("first activity ->>>> onRestart");
    	super.onRestart();
    }
    
    
    @Override
    protected void onDestroy() {
    	System.out.println("first activity ->>>> onDestroy");
    	super.onDestroy();
    } 
    
    
}