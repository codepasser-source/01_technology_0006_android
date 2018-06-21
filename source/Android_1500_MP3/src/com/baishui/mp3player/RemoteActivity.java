package com.baishui.mp3player;  

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
 
import com.baishui.download.HttpDownLoader;
import com.baishui.model.Mp3Info;
import com.baishui.mp3player.service.DownLoadService;
import com.baishui.util.MyException;
import com.baishui.util.MyXMLContentHandler;

 
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RemoteActivity extends android.app.ListActivity{
	
	private static final int MENU_BT_UPDATE = 1;
	private static final int MENU_BT_ABOUT = 2;  
	
	private static final String REQUEST_MP3LIST= "http://192.168.1.102:8080/AndroidDownload/mp3/resources.xml"; 
	
	private List<Mp3Info> mp3List = new ArrayList<Mp3Info>();
	 
//------------------------------------------------------------------Android 系统回调方法	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mp3listview); 
		
		//显示数据
		try {
			updateListAdapterData();
		} catch (MyException e) {
		 
			e.printStackTrace();
		}
	} 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0,1,1,R.string.menu_update);
		menu.add(0,2,2,R.string.menu_about);
		return super.onCreateOptionsMenu(menu);
	}
	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 if(item.getItemId() == MENU_BT_UPDATE){  
			 try {
			   //更新listView 显示
			   updateListAdapterData();		
			} catch (MyException e) { //捕获自定义异常  操作控制界面不会阻塞 
				e.printStackTrace();
			}
			 
		 }else if(item.getItemId() == MENU_BT_ABOUT){   
			 			 
		 }
		return super.onOptionsItemSelected(item);
	}
	 
	//点击列表事件
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) { 
		 
		Intent intent = new Intent();
		intent.putExtra("mp3Info", mp3List.get(position));
		intent.setClass(RemoteActivity.this, DownLoadService.class);
		
		RemoteActivity.this.startService(intent);
		
		super.onListItemClick(l, v, position, id);
		
	}
	/**
	 * 退出时  停止DownLoadService
	 */
	@Override
	protected void onDestroy() {
		System.out.println("[RemoteActivity] onDestroy");
		Intent intent = new Intent();
		intent.setClass(RemoteActivity.this, DownLoadService.class);
		RemoteActivity.this.stopService(intent);
		super.onDestroy();
	}
	
	
//-------------------------------------------------------自定义方法（可重用）	 
	    /**
	     * 更新ListView Adapter dataList数据 
	     */
	    public  void  updateListAdapterData() throws MyException {
	    	
	    	 //文件下载
			String xmlData = downLoadXML(REQUEST_MP3LIST);
			System.out.println(xmlData);
			//xml解析
			 
		     mp3List =paresXML2List(xmlData);   
	    	//将创建好的 SimpleAdapter 显示出来
	        setListAdapter(bulidSimpleAdapter()); 
	    }
	 
	 /**
	  * 创建 SimpleAdapter
	  * @param mp3List
	  * @return
	  */
	    public SimpleAdapter bulidSimpleAdapter(){
	    
	    	ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String,String>>();
	    	 
	    	for(Mp3Info mp3 : mp3List){
	    		HashMap<String, String> dataItem = new HashMap<String, String>();
	    		dataItem.put("mp3Name",mp3.getMp3Name());
	    		dataItem.put("mp3Size",mp3.getMp3Size());
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
	    
	 /**
	  * 文件下载 
	  * @param uri
	  * @return
	  * @throws MyException
	  */
	    public String downLoadXML(String uri) throws MyException{
	    	HttpDownLoader downLoader = new HttpDownLoader(); 
	    	return downLoader.download(uri);
	    } 
	    /**
	     * xml数据解析
	     * @param xml
	     * @return
	     * @throws MyException
	     */
	    public List<Mp3Info> paresXML2List(String xml) throws MyException{
	    	 
	    	
	    	try {
	    		SAXParserFactory saxFactory = SAXParserFactory.newInstance();
	  		    XMLReader xmlReader = saxFactory.newSAXParser().getXMLReader(); 
	  		    mp3List.clear(); //清空在添加
	  		    xmlReader.setContentHandler(new MyXMLContentHandler(mp3List));
	  		    xmlReader.parse(new InputSource(new StringReader(xml))); 
	  		    
	  		     System.out.println("paresXML2List mp3List Size:"+mp3List.size());	
	  		    
			} catch (Exception e) {
				e.printStackTrace();
				throw new MyException("xml文件解析异常"); 
			} 
			  return mp3List;
	    }
    
	    
	    
}