package com.ensun.esy;

 
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List; 
import com.ensun.esy.model.Word;
import com.ensun.esy.util.Appconstant;
import com.ensun.esy.util.HttpHelper;
import com.ensun.esy.util.JsonReaderUtil;
import com.ensun.esy.util.LogHelper;
import com.ensun.esy.util.UrlCodeHelper; 

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
 
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector.OnGestureListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SeachListActivity extends ListActivity  implements OnGestureListener{
	
	private static int  OPTIONMENU_OPTION = 1;
	
	private static int  OPTIONMENU_BACK = 2; 
	
	private Button seachBT = null;
	
	private EditText wordText = null;
	
	private String urlcode=null; 
	
	private  List<Word> words = null; 
	
	private GestureDetector myGestureDetector;
	
	private ImageButton main_mb0_bt;
	private ImageButton main_mb1_bt;
	private ImageButton main_mb2_bt;
	private ImageButton main_mb3_bt;
	private ImageButton main_mb4_bt;
	
/****************************************************************系统事件******************/
     @Override
    protected void onCreate(Bundle savedInstanceState) { 
    	super.onCreate(savedInstanceState); 
    	setContentView(R.layout.seach_list_view); 
    	
    	initializationMember();
    	
    	
    	
    }
     
     class  OnSeachClickListener implements View.OnClickListener{
    	 @Override
    	public void onClick(View v) {
    		 String wordStr = wordText.getText().toString().trim();
    		 if(wordStr.length()!=0){
    			 refreshUrlCode(wordStr);
    			 refreshListViewData();
    		 }else{
    			 Toast.makeText(SeachListActivity.this, "请输入要搜索的词语", Toast.LENGTH_SHORT).show();
    		 }
    	}
     }
     
     /**
      *  UI按钮点击事件监听
      */
      class  OnViewBtnClickListenter implements View.OnClickListener{
      	@Override
      	public void onClick(View view) {  
      		if(StartActivity.isConnected){ 
 			    if(view.getId() == R.id.main_mb0_bt){  
 			      finish();
 	    		}else if(view.getId() == R.id.main_mb1_bt){  
 	    		  Intent intent = new Intent(); 
 			      intent.setClass(SeachListActivity.this, NewListActivity.class);
 			      SeachListActivity.this.startActivity(intent);
 			      finish();
 	    		}else if(view.getId() == R.id.main_mb2_bt){ 
 	    		  Intent intent = new Intent(); 
 	 		      intent.setClass(SeachListActivity.this, CollectListActivity.class);
 	 		      SeachListActivity.this.startActivity(intent);
 	 		      finish();
 	    		}else if(view.getId() == R.id.main_mb3_bt){
 	    		 
 	    		}else if(view.getId() == R.id.main_mb4_bt){  
 	    			openContextMenu(view);
 	    		}  
 		    }else{ 
   		      Toast.makeText(SeachListActivity.this, "请检查当前的网络连接", Toast.LENGTH_SHORT).show();  
   	        }
      	}
      }
      /**
       *  UI按钮焦点切换事件监听
       */
      class OnViewBtnFocusChangeListener implements View.OnFocusChangeListener{
      	@Override
      	public void onFocusChange(View view, boolean hasFocus) {
      		 if(hasFocus){    			
     			if(view.getId()==R.id.main_mb0_bt){
  					view.setBackgroundResource(R.drawable.menubar_0_1);
  				}else if(view.getId()==R.id.main_mb1_bt){
  					view.setBackgroundResource(R.drawable.menubar_1_1);
  				}else if(view.getId()==R.id.main_mb2_bt){
  					view.setBackgroundResource(R.drawable.menubar_2_1);
  				}else if(view.getId()==R.id.main_mb3_bt){
  					view.setBackgroundResource(R.drawable.menubar_3_1);
  				}else if(view.getId()==R.id.main_mb4_bt){
  					view.setBackgroundResource(R.drawable.menubar_4_1);
  				}
     		 }else{ 
     			if(view.getId()==R.id.main_mb0_bt){
  					view.setBackgroundResource(R.drawable.menubar_0);
  				}else if(view.getId()==R.id.main_mb1_bt){
  					view.setBackgroundResource(R.drawable.menubar_1);
  				}else if(view.getId()==R.id.main_mb2_bt){
  					view.setBackgroundResource(R.drawable.menubar_2);
  				}else if(view.getId()==R.id.main_mb3_bt){
  					view.setBackgroundResource(R.drawable.menubar_3_0);
  				}else if(view.getId()==R.id.main_mb4_bt){
  					view.setBackgroundResource(R.drawable.menubar_4);
  				}
     		 }
      	}
      }
      
      /**
       * UI按钮触摸获取事件监听
       */
      class OnViewBtnTouchListener implements View.OnTouchListener{ 
  		@Override
  		public boolean onTouch(View view, MotionEvent event) {
  			if(event.getAction() == MotionEvent.ACTION_DOWN){
  				if(view.getId()==R.id.main_mb0_bt){
  					view.setBackgroundResource(R.drawable.menubar_0_1);
  				}else if(view.getId()==R.id.main_mb1_bt){
  					view.setBackgroundResource(R.drawable.menubar_1_1);
  				}else if(view.getId()==R.id.main_mb2_bt){
  					view.setBackgroundResource(R.drawable.menubar_2_1);
  				}else if(view.getId()==R.id.main_mb3_bt){
  					view.setBackgroundResource(R.drawable.menubar_3_1);
  				}else if(view.getId()==R.id.main_mb4_bt){
  					view.setBackgroundResource(R.drawable.menubar_4_1);
  				}
  			}else if(event.getAction() == MotionEvent.ACTION_UP){
  				if(view.getId()==R.id.main_mb0_bt){
  					view.setBackgroundResource(R.drawable.menubar_0);
  				}else if(view.getId()==R.id.main_mb1_bt){
  					view.setBackgroundResource(R.drawable.menubar_1);
  				}else if(view.getId()==R.id.main_mb2_bt){
  					view.setBackgroundResource(R.drawable.menubar_2);
  				}else if(view.getId()==R.id.main_mb3_bt){
  					view.setBackgroundResource(R.drawable.menubar_3_0);
  				}else if(view.getId()==R.id.main_mb4_bt){
  					view.setBackgroundResource(R.drawable.menubar_4);
  				}
  			}
  			// TODO Auto-generated method stub
  			return false;
  		} 
      }
     
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, OPTIONMENU_OPTION, OPTIONMENU_OPTION,R.string.optionsMenu_Option);
		menu .add(1,OPTIONMENU_BACK, OPTIONMENU_BACK,R.string.optionsMenu_Back);
		return super.onCreateOptionsMenu(menu);
	}
     
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == OPTIONMENU_OPTION) { 
		} else if (item.getItemId() == OPTIONMENU_BACK) {
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
	   menu.setHeaderIcon(R.drawable.logo);                                // 设定头部图标 
       menu.setHeaderTitle("选项菜单 ");                                        // 设定头部标题 
	  if (v==findViewById(R.id.main_mb4_bt)) {
            menu.add(0, MainActivity.CONTEXTMENU_LOGOUT, 0,R.string.contextMenu_Logout);
            menu.add(0, MainActivity.CONTEXTMENU_ABOUT, 0,R.string.contextMenu_About);
            menu.add(0, MainActivity.CONTEXTMENU_BACK, 0,R.string.contextMenu_Back);
            menu.add(0, MainActivity.CONTEXTMENU_CANCEL, 0,R.string.contextMenu_Cancel);
        } 
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MainActivity.CONTEXTMENU_LOGOUT:
			MainActivity.isLogout = true; //注销
			finish();
			break;
		case MainActivity.CONTEXTMENU_ABOUT: 
			break;
		case MainActivity.CONTEXTMENU_BACK:
			finish();
			break;
		case MainActivity.CONTEXTMENU_CANCEL:
			break; 
		}	 
		return super.onContextItemSelected(item);
	}
     
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		 if(MainActivity.isLogin){
			 Intent intent = new Intent(); 
			   intent.putExtra("word", words.get(position));		
			   intent.setClass(SeachListActivity.this, RemoteDailog.class); 
			   SeachListActivity.this.startActivity(intent);
		 }else{
			 Toast.makeText(SeachListActivity.this, "对不起你没有登陆,请先登录", Toast.LENGTH_SHORT).show();
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
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
    	myGestureDetector.onTouchEvent(event);
    	return super.onTouchEvent(event);
    }
 	
 	@Override
 	public boolean onFling(MotionEvent e1, MotionEvent e2, float arg2, float arg3) { 
 		if(e1.getX() > e2.getX()) {//move to left    
 	       /* Intent intent = new Intent();
 	        intent.setClass(SeachListActivity.this, MainActivity.class);
 	        SeachListActivity.this.startActivity(intent);*/
 		  }else if(e1.getX() < e2.getX()) {    
 	    	   finish();
 	       }     
 	    return true;  
 	}
		@Override
		public boolean onDown(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}



		@Override
		public void onLongPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onShowPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onSingleTapUp(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}
     
/********************************************自定义方法*********************************/
	
	private  void initializationMember(){
		 myGestureDetector = new GestureDetector(this);
		this.seachBT = (Button) this.findViewById(R.id.seachBT);
    	this.wordText = (EditText) this.findViewById(R.id.wordText);
    	this.seachBT.setOnClickListener(new OnSeachClickListener());
		
    	this.main_mb0_bt = (ImageButton) this.findViewById(R.id.main_mb0_bt);
        this.main_mb0_bt.setOnClickListener(new OnViewBtnClickListenter());
        this.main_mb0_bt.setOnFocusChangeListener(new OnViewBtnFocusChangeListener());
        this.main_mb0_bt.setOnTouchListener(new OnViewBtnTouchListener());
        
        this.main_mb1_bt = (ImageButton) this.findViewById(R.id.main_mb1_bt);
        this.main_mb1_bt.setOnClickListener(new OnViewBtnClickListenter());
        this.main_mb1_bt.setOnFocusChangeListener(new OnViewBtnFocusChangeListener());
        this.main_mb1_bt.setOnTouchListener(new OnViewBtnTouchListener());
        
        this.main_mb2_bt = (ImageButton) this.findViewById(R.id.main_mb2_bt);
        this.main_mb2_bt.setOnClickListener(new OnViewBtnClickListenter());
        this.main_mb2_bt.setOnFocusChangeListener(new OnViewBtnFocusChangeListener());
        this.main_mb2_bt.setOnTouchListener(new OnViewBtnTouchListener());
        
        this.main_mb3_bt = (ImageButton) this.findViewById(R.id.main_mb3_bt);
        this.main_mb3_bt.setOnClickListener(new OnViewBtnClickListenter());
        this.main_mb3_bt.setOnFocusChangeListener(new OnViewBtnFocusChangeListener());
        this.main_mb3_bt.setOnTouchListener(new OnViewBtnTouchListener());
        
        this.main_mb4_bt = (ImageButton) this.findViewById(R.id.main_mb4_bt);
        this.main_mb4_bt.setOnClickListener(new OnViewBtnClickListenter());
        this.main_mb4_bt.setOnFocusChangeListener(new OnViewBtnFocusChangeListener());
        this.main_mb4_bt.setOnTouchListener(new OnViewBtnTouchListener());
        //注册上下文菜单
        this.registerForContextMenu(main_mb4_bt);  
	}
	
   /**
    * 解析当前汉字 转换为 URLCODE
    */
	private  void refreshUrlCode(String wordString){ 
    	
    	this.urlcode = UrlCodeHelper.urlCodeFormat(wordString);   
    	// System.out.println("urlcode:"+urlcode);
    }
	
	/**
      * 刷新当前列表数据
      */
     private  void  refreshListViewData(){
    	 String jsonData = getNewListActionResponse(); 
    	 LogHelper.sysoLog("SeachListActivity", "refreshListViewData","jsonData:"+jsonData);
    	 readerJsonToModelList(jsonData); 
    	 SimpleAdapter listAdapter =  getSimpleAdapter(); 
    	 setListAdapter(listAdapter); 
    	 if(words.size()==0){
    	  Toast.makeText(SeachListActivity.this, "对不起没有相关记录", Toast.LENGTH_SHORT).show();	 
    	 } 
    	 
     }
     
      /**
       * 最新更新请求 得到应答数据
       * @return
       */
      private String  getNewListActionResponse(){  
	      return HttpHelper.getHttpResponse(Appconstant.RequestURI.SEACHLIST_ACTION+this.urlcode);	 
      }
      
       private List<Word>  readerJsonToModelList(String jsonData){ 
    	     words = JsonReaderUtil.parseJsonToModelList(jsonData);
    	     return words;
       }
     
     	/**
     	 * 更新当前 Adapter
      	 * @return
      	 */
       private SimpleAdapter getSimpleAdapter(){
    	   
    	 List<HashMap<String, String>> dataList = new ArrayList<HashMap<String,String>>(); 
    	 
    	 for (Word word : words) {
    		 HashMap<String, String> data = new HashMap<String, String>(); 
             data.put("word",word.getWord());
             data.put("vedioSize",word.getVedioSize());
         	 dataList.add(data); 
		 }
         
       
       	SimpleAdapter listAdapter = new SimpleAdapter(this, 
   	                 dataList, 
   	                 R.layout.list_item,
   	                 new String[]{"word","vedioSize"},
   	                 new int[]{R.id.word,R.id.vedioSize}); 
       	
       	return  listAdapter;
       }

       
       
       
       
}
