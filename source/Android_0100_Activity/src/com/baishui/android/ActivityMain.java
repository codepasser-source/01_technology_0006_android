package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ActivityMain extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/**调用父类的方法. */
        super.onCreate(savedInstanceState);
        /**设置当前Activity布局配置文件*/
        setContentView(R.layout.main); 
        
       TextView text = (TextView)findViewById(R.id.myText);
       Button button = (Button)  findViewById(R.id.myButton);
      
       text.setText("我的第一个TextView");
       button.setText("我的第一个Button");
       
      
        
    }
}