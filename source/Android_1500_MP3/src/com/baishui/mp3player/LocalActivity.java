package com.baishui.mp3player;

import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List;

import com.baishui.download.FileUtiles;
import com.baishui.model.Mp3Info;
 
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LocalActivity extends ListActivity { 
	
   private static final	String FILEDIRPATH = "testDir";
  
   private List<Mp3Info> mp3List = null;
 
   private static final int MENU_BT_UPDATE = 1;
	private static final int MENU_BT_ABOUT = 2;  
   
 //--------------------------------------------------------------系统方法
	/**
	 * 启动方法
	 */
   @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mp3listview); 
		
		//刷新本地文件列表
		updateListAdapterData();
		 
	}  
	
   
   @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	     menu.add(0,1,1,R.string.menu_update);
	     menu.add(0,2,2,R.string.menu_about);
	     
		return super.onCreateOptionsMenu(menu);
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem item) { 
		  if(item.getItemId() == MENU_BT_UPDATE ){
			  updateListAdapterData();
		  }else if(item.getItemId() == MENU_BT_ABOUT){
			  
		  } 
		 return super.onOptionsItemSelected(item);
	 }
	
	@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			  
			 Intent intent = new Intent();
			 intent.putExtra("mp3Info",  mp3List.get(position));
		     intent.setClass(LocalActivity.this, PlayerActivity.class); 
		     LocalActivity.this.startActivity(intent); 
		}
	
 //--------------------------------------------------------------自定义方法
	
	/**
	 * 刷新本地文件数据方法
	 */
	private void updateListAdapterData(){   
		 getLoaclFilesToMp3Info();		 
		 setListAdapter(bulidSimpleAdapter());  
	}
	
	/**
	 * 检索本地文件 封装数据
	 */
	private List<Mp3Info>  getLoaclFilesToMp3Info(){
		FileUtiles fileUtil = new FileUtiles(); 
	    mp3List =  fileUtil.getLoaclFiels(FILEDIRPATH);  
	    System.out.println("[LocalActivity] getLoaclFilesToMp3Info: "+mp3List.size());
		return mp3List;
	}
	
	/**
	 * 创建 SimpleAdapter
	 * @return SimpleAdapter解析对象
	 */
	  public SimpleAdapter bulidSimpleAdapter(){
		
		   ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String,String>>();  
			for(Mp3Info mp3Info : mp3List){
				HashMap<String, String> dataItem = new HashMap<String, String>();
				dataItem.put("mp3Name", mp3Info.getMp3Name());
				dataItem.put("mp3Size", mp3Info.getMp3Size());
				//System.out.println( mp3Info.getMp3Name()+"---"+ mp3Info.getMp3Size()); 
				dataList.add(dataItem);
			}  
			
			//添加 list Adapter
			 SimpleAdapter listAdapter = new SimpleAdapter(this, 
	                dataList, 
	                R.layout.mp3listitem,
	                new String[]{"mp3Name","mp3Size"},
	                new int[]{R.id.mp3Name,R.id.mp3Size});  
	      return listAdapter;
	  }
 
	  
}
