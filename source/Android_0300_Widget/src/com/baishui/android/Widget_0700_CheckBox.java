package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class Widget_0700_CheckBox extends Activity {
   
	private CheckBox sportsCheck;
	private CheckBox musicCheck;
	private CheckBox artCheck;
	
	
	public CheckBox getSportsCheck() {
		return sportsCheck;
	}


	public void setSportsCheck(CheckBox sportsCheck) {
		this.sportsCheck = sportsCheck;
	}


	public CheckBox getMusicCheck() {
		return musicCheck;
	}


	public void setMusicCheck(CheckBox musicCheck) {
		this.musicCheck = musicCheck;
	}


 


	public CheckBox getArtCheck() {
		return artCheck;
	}


	public void setArtCheck(CheckBox artCheck) {
		this.artCheck = artCheck;
	}


	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox);
        
        this.sportsCheck=(CheckBox) this.findViewById(R.id.sportsCheck);
        this.musicCheck=(CheckBox) this.findViewById(R.id.musicCheck);
        this.artCheck=(CheckBox) this.findViewById(R.id.artCheck);
        
        this.sportsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { 
			@Override
			public void onCheckedChanged(CompoundButton bt, boolean checked) { 
				if(checked){
					Toast.makeText(Widget_0700_CheckBox.this, sportsCheck.getText()+"checked", Toast.LENGTH_SHORT).show();
				}else{
					
				} 
			}
		});
        this.musicCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { 
			@Override
			public void onCheckedChanged(CompoundButton bt, boolean checked) { 
				if(checked){
					Toast.makeText(Widget_0700_CheckBox.this, musicCheck.getText()+"checked", Toast.LENGTH_SHORT).show();
				}else{
					
				} 
			}
		});
        this.artCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { 
			@Override
			public void onCheckedChanged(CompoundButton bt, boolean checked) { 
				if(checked){
					Toast.makeText(Widget_0700_CheckBox.this, artCheck.getText()+"checked", Toast.LENGTH_SHORT).show();
				}else{
					
				} 
			}
		});
        
    }
    
    
    
    
}