package com.baishui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		reistViewElement();
	}

	private void reistViewElement() {
		Button sayHelloBtn = (Button) findViewById(R.id.sayHelloBtn);
		sayHelloBtn.setOnClickListener(new SayHelloButtonListener());

		// Button layoutBtn = (Button) findViewById(R.id.layoutBtn);
		// sayHelloBtn.setOnClickListener(new SayHelloButtonListener());

		// Button widgetBtn = (Button) findViewById(R.id.widgetBtn);
		// sayHelloBtn.setOnClickListener(new SayHelloButtonListener());

		Button intentBtn = (Button) findViewById(R.id.intentBtn);
		intentBtn.setOnClickListener(new IntentButtonListener());

		Button handlerBtn = (Button) findViewById(R.id.handlerBtn);
		handlerBtn.setOnClickListener(new HandlerButtonListener());

		Button sqliteBtn = (Button) findViewById(R.id.sqliteBtn);
		sqliteBtn.setOnClickListener(new SqliteButtonListener());

		Button providerBtn = (Button) findViewById(R.id.providerBtn);
		providerBtn.setOnClickListener(new ProviderButtonListener());
	}

	class SayHelloButtonListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			Toast.makeText(MainActivity.this, R.string.hello, Toast.LENGTH_LONG)
					.show();
		}
	}

	class IntentButtonListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// 创建一个Intent 对象
			Intent intent = new Intent();
			// 向intent中添加E
			intent.putExtra("extraParamName", "extraParamValue");
			// 设置关联
			intent.setClass(MainActivity.this, IntentActivity.class);
			// 利用启动其他Activity方法：startActivity(intent) 执行的操作
			MainActivity.this.startActivity(intent);
		}

	}

	class HandlerButtonListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// 创建一个Intent 对象
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, HandlerActivity.class);
			// 利用启动其他Activity方法：startActivity(intent) 执行的操作
			MainActivity.this.startActivity(intent);
		}

	}

	class SqliteButtonListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// 创建一个Intent 对象
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, SqliteActivity.class);
			// 利用启动其他Activity方法：startActivity(intent) 执行的操作
			MainActivity.this.startActivity(intent);
		}

	}

	class ProviderButtonListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// 创建一个Intent 对象
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, ContentProviderActivity.class);
			// 利用启动其他Activity方法：startActivity(intent) 执行的操作
			MainActivity.this.startActivity(intent);
		}

	}

}