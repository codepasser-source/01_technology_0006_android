package com.ensun.esy;
 
import com.ensun.esy.model.Word;
 
import com.ensun.esy.util.Appconstant;
import com.ensun.esy.util.DatabaseHelper;
import com.ensun.esy.util.LogHelper;
 

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
 

public class CollectDailog extends Activity {
  
	private Button  playBT;
	private Button  deleteBT;
	 
	private Button  cancelBT;
	
	private Word word = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collect_dailog);
		
		Intent intent = getIntent();
    	word = (Word) intent.getExtras().get("word");
		
        LogHelper.sysoLog("CollectDailog","onCreate","id :"+word.getId()+" word:"+word.getWord()+" vedioName:"+word.getVedioName()+" vedioSize:"+word.getVedioSize()+" vedioUri:"+word.getVedioUri());
     	
    	this.playBT = (Button) this.findViewById(R.id.collectDailog_play_BT);
    	this.deleteBT= (Button) this.findViewById(R.id.collectDailog_delete_BT);
    	 
    	this.cancelBT = (Button) this.findViewById(R.id.collectDailog_cancel_BT);  
    	
    	this.playBT.setOnClickListener(new OnPalyClickListener());
    	this.deleteBT.setOnClickListener(new OnDeleteClickListener());
    	 
    	this.cancelBT.setOnClickListener(new OnCancelClickListener());
	}
	
	
	class OnPalyClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.putExtra("word", word);
			intent.setClass(CollectDailog.this, MediaPlayer.class);
			CollectDailog.this.startActivity(intent);
			finish();
		}
	}
	class OnDeleteClickListener implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			 DatabaseHelper dbHelper = new DatabaseHelper(CollectDailog.this,Appconstant.DataBase.DATABASE_NAME);
			 SQLiteDatabase db = dbHelper.getWritableDatabase();
			 int result = db.delete(Appconstant.DataBase.CollectTable.TABLENAME, "id=?", new String[]{word.getId()+""});
			 String resultMsg ="删除失败";
			 if(result==1){
				 resultMsg="删除成功";
			 }
			 Toast.makeText(CollectDailog.this, resultMsg, Toast.LENGTH_SHORT).show();
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
