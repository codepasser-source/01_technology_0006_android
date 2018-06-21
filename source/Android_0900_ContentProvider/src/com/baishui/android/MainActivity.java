package com.baishui.android;

import com.baishui.provider.MyContentProvider;
import com.baishui.provider.MyProviderMetaData;
import com.baishui.provider.MyProviderMetaData.UserTableMetaData;
 

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
 
import android.widget.Button;
  
/**
 * 本例程 是实现一个用户自定义 ContentProvider 
 *   以实现 SQLite3 的 onCreate insert delete query update 动态调用
 */
public class MainActivity extends Activity {
	
 
	private Button insertBT;
	private Button selectBT;
	private Button getTypeBT;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        this.insertBT = (Button) findViewById(R.id.insertBT);
        insertBT.setOnClickListener(new OnInsertBTListener());
        this.selectBT = (Button) findViewById(R.id.selectBT);
        selectBT.setOnClickListener(new OnSelectBTListener());
        
        this.getTypeBT=(Button) findViewById(R.id.getTypeBT);
        this.getTypeBT.setOnClickListener(new OnGetTypeBTListener());
    } 
    
  
    class OnInsertBTListener implements View.OnClickListener{ 
		@Override
		public void onClick(View arg0) {
			 ContentValues values = new ContentValues();
			 values.put(MyProviderMetaData.UserTableMetaData.USER_NAME, "zhangsan");
			 Uri uri = getContentResolver().insert(MyProviderMetaData.UserTableMetaData.CONTENT_URI, values);
			 System.out.println("OnInsertBTListener result uri : " + uri);
		} 
    }
    
    class OnSelectBTListener implements View.OnClickListener{ 
		@Override
		public void onClick(View arg0) {
	   
			//Cursor c = new DatabaseHelper(MainActivity.this,"TestProvider.db").getReadableDatabase().query("users",new String[]{"_id","name"}, "name=?", new String[]{"zhangsan"}, "", "", "");
	   Cursor c = getContentResolver().query(UserTableMetaData.CONTENT_URI, null, null, null, null);
        while(c.moveToNext()){
			  System.out.println("id:"+c.getInt(c.getColumnIndex(UserTableMetaData._ID))+
					              " name:"+c.getString(c.getColumnIndex(UserTableMetaData.USER_NAME)));
		  }
		} 
    }
    class OnGetTypeBTListener implements View.OnClickListener{ 
		@Override
		public void onClick(View arg0) {
	   
		  System.out.println( new MyContentProvider().getType(MyProviderMetaData.UserTableMetaData.CONTENT_URI));
		  
		} 
    }
}