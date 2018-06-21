package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Widget_0600_RadioGroup extends Activity {
	
	
	private RadioGroup sexGroup;
	private RadioButton maleButton;
	private RadioButton femaleButton;
	
	
	
    public RadioGroup getSexGroup() {
		return sexGroup;
	}



	public void setSexGroup(RadioGroup sexGroup) {
		this.sexGroup = sexGroup;
	}



	public RadioButton getMaleButton() {
		return maleButton;
	}



	public void setMaleButton(RadioButton maleButton) {
		this.maleButton = maleButton;
	}



	public RadioButton getFemaleButton() {
		return femaleButton;
	}



	public void setFemaleButton(RadioButton femaleButton) {
		this.femaleButton = femaleButton;
	}



	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiogroup);
        
        this.sexGroup = (RadioGroup)this.findViewById(R.id.sexGroup);
        
        this.femaleButton = (RadioButton)this.findViewById(R.id.femaleButton);
        this.maleButton=(RadioButton)this.findViewById(R.id.maleButton);
        
        
        
        this.sexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkId) {
				 if(femaleButton.getId()==checkId){
					 System.out.println(femaleButton.getText());
					  Toast.makeText(Widget_0600_RadioGroup.this, femaleButton.getText(), Toast.LENGTH_SHORT).show();
				 }else if(maleButton.getId()==checkId){
					 System.out.println(maleButton.getText());
					  Toast.makeText(Widget_0600_RadioGroup.this,  maleButton.getText(), Toast.LENGTH_SHORT).show();
				 }
			}
		});
    }
}