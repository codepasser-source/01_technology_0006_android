package com.baishui.android;

import android.app.Activity;
 
 
import android.os.Bundle; 
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends Activity{
	
	  private ViewFlipper mViewFlipper;  
	  
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
        
        Button buttonNext1 = (Button) findViewById(R.id.Button_next1);    
        mViewFlipper = (ViewFlipper) findViewById(R.id.details);  
        
        buttonNext1.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {
				mViewFlipper.showNext();
			}
		}); 
        
        

    }

	
    
	
}