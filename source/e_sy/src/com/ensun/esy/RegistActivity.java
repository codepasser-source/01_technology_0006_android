package com.ensun.esy;

 
import com.ensun.esy.util.Appconstant;
import com.ensun.esy.util.HttpHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector.OnGestureListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
 

public class RegistActivity extends Activity  implements OnGestureListener{

	private GestureDetector myGestureDetector; 
	
	private EditText reg_username;
	private EditText reg_password;
	private EditText reg_checkpassword;
	private EditText reg_email;
	private Button reg_commitBT;
	private Button reg_resetBT;
	
	private ImageButton main_mb0_bt;
	private ImageButton main_mb1_bt;
	private ImageButton main_mb2_bt;
	private ImageButton main_mb3_bt;
	private ImageButton main_mb4_bt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)  { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist);
	    initializationMember();
	}
	 
	
	class OnRegistClickListener implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			String username = reg_username.getText().toString().trim();
			String password = reg_password.getText().toString().trim();
			String checkpassword= reg_checkpassword.getText().toString().trim();
			String email = reg_email.getText().toString().trim();
			if(username.equals("")){
				 Toast.makeText(RegistActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show(); 
			 }else if(password.length()==0){
				 Toast.makeText(RegistActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();  
			 }else if(!password.equals(checkpassword) ){
				 Toast.makeText(RegistActivity.this, "确认密码不一致", Toast.LENGTH_SHORT).show(); 
			 }else{
				 if(userRegistHttpRequest(username,password,email)){
					 Toast.makeText(RegistActivity.this, "注册成功,请登录", Toast.LENGTH_SHORT).show();
					 RegistActivity.this.finish();
				 }else{
					 Toast.makeText(RegistActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
				 }	 
			 } 
		}
	}
	
	
	class OnResetClickListener implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			  reg_username.setText("");
			  reg_password.setText("");  
			  reg_checkpassword.setText(""); 
			  reg_email.setText("");
		}
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
			      intent.setClass(RegistActivity.this, NewListActivity.class);
			      RegistActivity.this.startActivity(intent);
			      finish();
	    		}else if(view.getId() == R.id.main_mb2_bt){ 
	    		  Intent intent = new Intent(); 
				  intent.setClass(RegistActivity.this, CollectListActivity.class);
				  RegistActivity.this.startActivity(intent);
				  finish();
	    		}else if(view.getId() == R.id.main_mb3_bt){
	    		 Intent intent = new Intent(); 
		    	 intent.setClass(RegistActivity.this, SeachListActivity.class);
		    	 RegistActivity.this.startActivity(intent);
		    	 finish();
	    		}else if(view.getId() == R.id.main_mb4_bt){  
	    			openContextMenu(view);
	    		}  
		    }else{ 
  		      Toast.makeText(RegistActivity.this, "请检查当前的网络连接", Toast.LENGTH_SHORT).show();  
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
 					view.setBackgroundResource(R.drawable.menubar_3);
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
 					view.setBackgroundResource(R.drawable.menubar_3);
 				}else if(view.getId()==R.id.main_mb4_bt){
 					view.setBackgroundResource(R.drawable.menubar_4);
 				}
 			}
 			// TODO Auto-generated method stub
 			return false;
 		} 
     }
     
     @Override
 	public boolean onTouchEvent(MotionEvent event) {
 		myGestureDetector.onTouchEvent(event);
 		return super.onTouchEvent(event);
 	}

 	@Override
 	public boolean onDown(MotionEvent e) {
 		
 		return false;
 	}

 	@Override
 	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
 			float velocityY) {
 		if(e1.getX() > e2.getX()) {//move to left   
 			       
 			       Intent intent = new Intent();
 	    	       intent.setClass(RegistActivity.this, NewListActivity.class);
 	    	       RegistActivity.this.startActivity(intent); 
 	    	       finish();
 	       }else if(e1.getX() < e2.getX()) {   
 	    	   finish();
 	       }     
 	    return true;  
 		 
 	}

 	@Override
 	public void onLongPress(MotionEvent e) {
 		// TODO Auto-generated method stub
 		
 	}

 	@Override
 	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
 			float distanceY) {
 		// TODO Auto-generated method stub
 		return false;
 	}

 	@Override
 	public void onShowPress(MotionEvent e) {
 		// TODO Auto-generated method stub
 		
 	}

 	@Override
 	public boolean onSingleTapUp(MotionEvent e) {
 		// TODO Auto-generated method stub
 		return false;
 	}
	 
//-----------------------------------------------------------------自定义方法	
	private boolean userRegistHttpRequest(String username,String password,String email){ 
		
		String responseMsg = HttpHelper.getHttpResponse(Appconstant.RequestURI.USERREGIST_ACTION+"username="+username+"&password="+password+"&email="+email);
		System.out.println("responseMsg:"+responseMsg);
		if(responseMsg!=null && responseMsg.equals("true")){
			return true;
		}else{
			return false; 
		}
		
		 
	}
	
	
	
	
	private  void initializationMember(){
		
		myGestureDetector = new GestureDetector(this);    
	    this.reg_username = (EditText) this.findViewById(R.id.reg_username);
	    this.reg_password =(EditText) this.findViewById(R.id.reg_password);
	    this.reg_checkpassword =  (EditText) this.findViewById(R.id.reg_checkpassword);
	    this.reg_email = (EditText) this.findViewById(R.id.reg_email);
	    this.reg_commitBT = (Button) this.findViewById(R.id.reg_commitBT);
	    this.reg_resetBT = (Button) this.findViewById(R.id.reg_resetBT);	    
	    this.reg_commitBT.setOnClickListener(new OnRegistClickListener());
	    this.reg_resetBT.setOnClickListener(new OnResetClickListener());
		
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
	
	
}
