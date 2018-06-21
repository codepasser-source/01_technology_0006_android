package com.baishui.android;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baishui.android.provider.MyProviderMetaData;

public class ContentProviderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contentprovider_activity);

		Button backBtn = (Button) this.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new OnBackBtnClickListener());

		Button getTypeBT = (Button) findViewById(R.id.providerGetTypeBtn);
		getTypeBT.setOnClickListener(new OnGetTypeBTListener());

		Button providerAddBtn = (Button) findViewById(R.id.providerAddBtn);
		providerAddBtn.setOnClickListener(new OnInsertBTListener());
	}

	class OnBackBtnClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}

	class OnGetTypeBTListener implements View.OnClickListener {
		@Override
		public void onClick(View arg0) {

			String url = getContentResolver().getType(
					MyProviderMetaData.UserTableMetaData.CONTENT_URI);
			Toast.makeText(ContentProviderActivity.this, url,
					Toast.LENGTH_SHORT).show();
		}
	}

	class OnInsertBTListener implements View.OnClickListener {
		@Override
		public void onClick(View arg0) {
			ContentValues values = new ContentValues();
			values.put(MyProviderMetaData.UserTableMetaData.USER_NAME,
					"zhangsan");
			Uri uri = getContentResolver().insert(
					MyProviderMetaData.UserTableMetaData.CONTENT_URI, values);
			Toast.makeText(ContentProviderActivity.this,"OnInsertBTListener result uri : " + uri,Toast.LENGTH_SHORT).show();
		}
	}
}
