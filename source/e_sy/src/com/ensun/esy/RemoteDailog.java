package com.ensun.esy;
 
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ensun.esy.model.Word;
 
import com.ensun.esy.util.Appconstant;
import com.ensun.esy.util.DatabaseHelper;
import com.ensun.esy.util.LogHelper;
 

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RemoteDailog extends Activity {
	
	private Button  playBT;
	private Button  collectBT;
	 
	private Button  cancelBT;
	
	private Word word = null;
	
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState); 
    	setContentView(R.layout.remote_dailog);
    	
    	Intent intent = getIntent();
    	word = (Word) intent.getExtras().get("word");
    	
        LogHelper.sysoLog("RemoteDailog","onCreate","word:"+word.getWord()+" vedioName:"+word.getVedioName()+" vedioSize:"+word.getVedioSize()+" vedioUri:"+word.getVedioUri());
    	
    	this.playBT = (Button) this.findViewById(R.id.remoteDailog_play_BT);
    	this.collectBT= (Button) this.findViewById(R.id.remoteDailog_collect_BT);
    	 
    	this.cancelBT = (Button) this.findViewById(R.id.remoteDailog_cancel_BT);  
    	
    	this.playBT.setOnClickListener(new OnPalyClickListener());
    	this.collectBT.setOnClickListener(new OnCollectClickListener());
    	 
    	this.cancelBT.setOnClickListener(new OnCancelClickListener());
    	
    }
     class OnPalyClickListener implements  View.OnClickListener{
    	 @Override
    	public void onClick(View v) {
    		 Intent intent = new Intent(); 
    	     intent.putExtra("word",word);		
    		 intent.setClass(RemoteDailog.this, MediaPlayer.class); 
    		 RemoteDailog.this.startActivity(intent);
    		 finish();
    	}
     }
     
     class OnCollectClickListener implements  View.OnClickListener{
    	 @Override
    	public void onClick(View v) {
    		DatabaseHelper dataBaseHelper = new DatabaseHelper(RemoteDailog.this, Appconstant.DataBase.DATABASE_NAME);
    		SQLiteDatabase database =  dataBaseHelper.getWritableDatabase(); 
    		
    		Cursor cursor = database.query(Appconstant.DataBase.CollectTable.TABLENAME, new String []{"id"},"vedioName=?",new String[]{word.getVedioName()}, "", "", "");
    		 if (cursor.moveToNext()) {
    			 Toast.makeText(RemoteDailog.this,"收藏中已经存在" , Toast.LENGTH_SHORT).show();
			}else{
				Date date = new Date();
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				//System.out.println(sdFormat.format(date));
				ContentValues values = new ContentValues(); 
	    		values.put(Appconstant.DataBase.CollectTable.COLUMN_WORD, word.getWord());
	    		values.put(Appconstant.DataBase.CollectTable.COLUMN_VEDIONAME, word.getVedioName());
	    		values.put(Appconstant.DataBase.CollectTable.COLUMN_VEDIOSIZE, word.getVedioSize());
	    		values.put(Appconstant.DataBase.CollectTable.COLUMN_VEDIOURI, word.getVedioUri());
	    		values.put(Appconstant.DataBase.CollectTable.COLUMN_ADDTIME,sdFormat.format(date));  
	    		database.insert(Appconstant.DataBase.CollectTable.TABLENAME, null, values); 
	    		 Toast.makeText(RemoteDailog.this,"收藏成功" , Toast.LENGTH_SHORT).show();
			}  
    		finish();
    	}
     }
 
     class OnCancelClickListener implements  View.OnClickListener{
    	 @Override
    	public void onClick(View v) {
    		 finish();
    	}
     }
     
     @Override
     public void onConfigurationChanged(Configuration newConfig) { 
     	//横屏时
     	if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) { 
          }else 
           //竖屏时 
           if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) { 
     	}
     	super.onConfigurationChanged(newConfig);
     }
}
