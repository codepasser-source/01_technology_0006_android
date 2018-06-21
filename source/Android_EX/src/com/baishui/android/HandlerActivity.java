package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HandlerActivity extends Activity {

	private Handler handler = new Handler();

	private Runnable myThead = new Runnable() {
		@Override
		public void run() {
			Toast.makeText(HandlerActivity.this, "每五秒显示一次", Toast.LENGTH_SHORT)
					.show();
			handler.postDelayed(myThead, 5000); // 循环调用Run方法
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler_activity);
		Button backBtn = (Button) this.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new OnBackBtnClickListener());

		Button startBtn = (Button) this.findViewById(R.id.startBtn);
		Button endBtn = (Button) this.findViewById(R.id.endBtn);

		startBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				handler.post(myThead); // 执行 myThead 的run方法
			}
		});

		endBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(HandlerActivity.this, "停止", Toast.LENGTH_SHORT)
						.show();
				handler.removeCallbacks(myThead); // 从堆栈中删除线程的方法
			}
		});

	}

	class OnBackBtnClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}
}
