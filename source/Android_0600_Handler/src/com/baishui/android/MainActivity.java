package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	private Handler handler = new Handler();
	
	
	//实现 与Activity  异步线程
	private Runnable myThead = new Runnable() { 
		@Override
		public void run() { 
			Toast.makeText(MainActivity.this, "每五秒显示一次", Toast.LENGTH_SHORT).show();
		   handler.postDelayed(myThead, 5000); // 循环调用Run方法
		}
	};
	
	private Button startBT;
	private Button endBT;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        this.startBT = (Button) this.findViewById(R.id.start);
        this.endBT = (Button) this.findViewById(R.id.end);
        /**
         * 点击开始按钮时  将线程 压入 堆栈
         */
        this.startBT.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View arg0) { 
				handler.post(myThead); // 执行 myThead 的run方法
		   }
		});
        
        this.endBT.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View arg0) {
				Toast.makeText(MainActivity.this, "停止", Toast.LENGTH_SHORT).show();
				handler.removeCallbacks(myThead); //从堆栈中删除线程的方法
			}
		});
        
    }
    public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Runnable getMyThead() {
		return myThead;
	}

	public void setMyThead(Runnable myThead) {
		this.myThead = myThead;
	}

	public Button getStartBT() {
		return startBT;
	}

	public void setStartBT(Button startBT) {
		this.startBT = startBT;
	}

	public Button getEndBT() {
		return endBT;
	}

	public void setEndBT(Button endBT) {
		this.endBT = endBT;
	}

	
}