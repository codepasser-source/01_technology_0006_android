package com.baishui.android;

import android.app.Activity;
 
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnGestureListener{
   
	
	//要走上面的程序中添加滑动手势来实现屏幕切换的话，首先需要定义一个GestureDetector：
	public GestureDetector mGestureDetector; 

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      //并在onCreate函数中初始化：
    	mGestureDetector = new GestureDetector(this); 
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	mGestureDetector.onTouchEvent(event);
    	return super.onTouchEvent(event);
    }
    

	@Override
	public boolean onDown(MotionEvent e) { 
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) { 
	       if(e1.getX() > e2.getX()) {//move to left    
	    	   Toast.makeText(MainActivity.this, "向左滑动", Toast.LENGTH_SHORT).show();
	       }else if(e1.getX() < e2.getX()) {    
	          Toast.makeText(MainActivity.this, "向右滑动", Toast.LENGTH_SHORT).show();
	       }    
	       Toast.makeText(MainActivity.this, "滑动", Toast.LENGTH_SHORT).show();
	       return true; 
      }

	@Override
	public void onLongPress(MotionEvent e) {
		 
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) { 
		return false;
	}

	 
}