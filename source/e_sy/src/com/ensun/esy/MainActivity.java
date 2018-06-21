package com.ensun.esy; 
import com.ensun.esy.util.Appconstant;
import com.ensun.esy.util.HttpHelper;
  
import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
  

public class MainActivity extends Activity implements OnGestureListener{
	
	private static final int  OPTIONMENU_OPTION = 1;
	private static final int  OPTIONMENU_EXIT = 2;
	
	public static final int  CONTEXTMENU_LOGOUT=1;
	public static final int  CONTEXTMENU_ABOUT=2;
	public static final int  CONTEXTMENU_EXIT=3;
	public static final int  CONTEXTMENU_BACK=4;
	public static final int  CONTEXTMENU_CANCEL=5;
	
	public static boolean isLogin = false; 
	public static boolean isLogout = false; 
	
    //共享文件名
	private static final String PREFERENCE_NAME = "esy_user"; 
	//手势监听接口
	private GestureDetector myGestureDetector; 
	//共享文件操作对象
	private SharedPreferences mySharedPreferences;
 
	private ImageButton login_bt;
	private ImageButton regist_bt;
	private EditText username_text;
	private TextView username_label;
	private EditText password_text;
	private TextView password_label;
	private CheckBox remember_check;
	
	private ImageButton main_mb0_bt;
	private ImageButton main_mb1_bt;
	private ImageButton main_mb2_bt;
	private ImageButton main_mb3_bt;
	private ImageButton main_mb4_bt;
 
/***********************************************系统事件*********************************************************/    
    /**
     * 创建事件
     */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);     
        
        initializationMember();//初始化成员 
        readerUserInfoFromSharedPreferences(); //提取客户信息
        
        boolean isRember =  this.remember_check.isChecked();//验证自动登录
        if(isRember){
        	autoLogin();
        }
    }  
    /**
     * 销毁事件
     */
    @Override
    protected void onDestroy() { 
    	isLogin= false;
    	isLogout= false;
    	saveUserInfoToSharedPreferences(this.username_text.getText().toString(),this.password_text.getText().toString(),this.remember_check.isChecked());
    	super.onDestroy();
    }
    /**
     * 重启事件
     */
   @Override
   protected void onResume() {
	   if(isLogout){ //判断当前是否是执行注销
	     userLogout();
	     isLogout = false;
	   }
	   super.onResume();
   }
   
    
    /**
     * UI按钮点击监听
     */
    class  OnViewBtnClickListener implements View.OnClickListener{
    	@Override
    	public void onClick(View view) { 
    		if(StartActivity.isConnected){ 
    			    if(view.getId() == R.id.login_bt){
    				   userLogin();   
    			    }else if(view.getId() == R.id.regist_bt){  
    			       Intent intent = new Intent(); 
   		    		   intent.setClass(MainActivity.this, RegistActivity.class);
   			    	   MainActivity.this.startActivity(intent);
		    		}else if(view.getId() == R.id.main_mb0_bt){  
    				   
		    		}else if(view.getId() == R.id.main_mb1_bt){
		    		 Intent intent = new Intent(); 
		    		 intent.setClass(MainActivity.this, NewListActivity.class);
			    	 MainActivity.this.startActivity(intent);
		    		}else if(view.getId() == R.id.main_mb2_bt){
		    		 Intent intent = new Intent(); 
			    	 intent.setClass(MainActivity.this, CollectListActivity.class);
				     MainActivity.this.startActivity(intent);	
		    		}else if(view.getId() == R.id.main_mb3_bt){
		    		 Intent intent = new Intent(); 
			    	 intent.setClass(MainActivity.this, SeachListActivity.class);
				     MainActivity.this.startActivity(intent);
		    		}else if(view.getId() == R.id.main_mb4_bt){  
		    			openContextMenu(view);
		    		} 
		    		 
    		 }else{ 
      		   Toast.makeText(MainActivity.this, "请检查当前的网络连接", Toast.LENGTH_SHORT).show();  
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
    			 if(view.getId()==R.id.login_bt){
 					view.setBackgroundResource(R.drawable.btn_login_1);
 				}else if(view.getId()==R.id.regist_bt){
 					view.setBackgroundResource(R.drawable.btn_regist_1);
 				}else if(view.getId()==R.id.main_mb0_bt){
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
    			 if(view.getId()==R.id.login_bt){
 					view.setBackgroundResource(R.drawable.btn_login);
 				}else if(view.getId()==R.id.regist_bt){
 					view.setBackgroundResource(R.drawable.btn_regist);
 				}else if(view.getId()==R.id.main_mb0_bt){
 					view.setBackgroundResource(R.drawable.menubar_0_0);
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
				if(view.getId()==R.id.login_bt){
					view.setBackgroundResource(R.drawable.btn_login_1);
				}else if(view.getId()==R.id.regist_bt){
					view.setBackgroundResource(R.drawable.btn_regist_1);
				}else if(view.getId()==R.id.main_mb0_bt){
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
				if(view.getId()==R.id.login_bt){
					view.setBackgroundResource(R.drawable.btn_login);
				}else if(view.getId()==R.id.regist_bt){
					view.setBackgroundResource(R.drawable.btn_regist);
				}else if(view.getId()==R.id.main_mb0_bt){
					view.setBackgroundResource(R.drawable.menubar_0_0);
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
    
    /**
     * 创建选项菜单
     */
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
	   menu.add(0, OPTIONMENU_OPTION, OPTIONMENU_OPTION, R.string.optionsMenu_Option); 
	   menu.add(1,OPTIONMENU_EXIT, OPTIONMENU_EXIT, R.string.optionsMenu_Exit);
	   return super.onCreateOptionsMenu(menu);
   }
   
   /**
    * 选项菜单选择事件
    */
   @Override
  public boolean onOptionsItemSelected(MenuItem item) {
	 if(item.getItemId()==OPTIONMENU_OPTION){ 
		 
	 }else if(item.getItemId() == OPTIONMENU_EXIT){
		this.finish();
	 }
	return super.onOptionsItemSelected(item);
   }
   
   /**
    * 创建上下文菜单
    */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
	   menu.setHeaderIcon(R.drawable.logo);                                // 设定头部图标 
       menu.setHeaderTitle("选项菜单 ");                                        // 设定头部标题 
	  if (v==findViewById(R.id.main_mb4_bt)) {
            menu.add(0, CONTEXTMENU_LOGOUT, 0,R.string.contextMenu_Logout);
            menu.add(0, CONTEXTMENU_ABOUT, 0,R.string.contextMenu_About);
            menu.add(0, CONTEXTMENU_EXIT, 0,R.string.contextMenu_Exit);
            menu.add(0, CONTEXTMENU_CANCEL, 0,R.string.contextMenu_Cancel);
        } 
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	/**
     * 创建上下文菜单选择事件
     */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case CONTEXTMENU_LOGOUT:
			userLogout(); //注销
			break;
		case CONTEXTMENU_ABOUT: 
			break;
		case CONTEXTMENU_EXIT:
			finish();
			break; 
		case CONTEXTMENU_CANCEL:
			break; 
		}	  
		return super.onContextItemSelected(item);
	}
   
	/**
     * 横竖屏幕切换
     */
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
	
	/**
     * 手势事件监听
     * @see 事件对象传递给myGestureDetector
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	myGestureDetector.onTouchEvent(event);
    	return super.onTouchEvent(event);
    }
	
    /**
     * 左右滑屏事件监听
     */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float arg2, float arg3) { 
		if(e1.getX() > e2.getX()) {//move to left   
			   Intent intent = new Intent();
			   if(StartActivity.isConnected){
				   intent.setClass(MainActivity.this, NewListActivity.class);
			   }else{
				   intent.setClass(MainActivity.this, CollectListActivity.class);
				   Toast.makeText(MainActivity.this, "请检查当前的网络连接,当前只可查看收藏", Toast.LENGTH_SHORT).show();
			   } 
	    	   MainActivity.this.startActivity(intent); 
	       }else if(e1.getX() < e2.getX()) {    
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

/*********************************************自定义方法**************************************************/
	/**
	  * 初始化
	  */
	 private void  initializationMember(){
		    myGestureDetector = new GestureDetector(this);   
	        mySharedPreferences= getSharedPreferences(PREFERENCE_NAME,Activity.MODE_PRIVATE);   
	        this.username_text = (EditText) this.findViewById(R.id.username_text);
	        this.password_text = (EditText) this.findViewById(R.id.password_text); 
	        
	        this.login_bt=(ImageButton) this.findViewById(R.id.login_bt);
	        this.login_bt.setOnClickListener(new OnViewBtnClickListener());
	        this.login_bt.setOnFocusChangeListener(new OnViewBtnFocusChangeListener());
	        this.login_bt.setOnTouchListener(new OnViewBtnTouchListener());
	        
	        this.regist_bt=(ImageButton) this.findViewById(R.id.regist_bt); 
	        this.regist_bt.setOnClickListener(new OnViewBtnClickListener());   
	        this.regist_bt.setOnFocusChangeListener(new OnViewBtnFocusChangeListener());
	        this.regist_bt.setOnTouchListener(new OnViewBtnTouchListener()); 
	        
	        this.remember_check = (CheckBox) this.findViewById(R.id.remember_check);
	        this.username_label = (TextView) this.findViewById(R.id.username_label);
	        this.password_label = (TextView) this.findViewById(R.id.password_label);
	        
	        
	        this.main_mb0_bt = (ImageButton) this.findViewById(R.id.main_mb0_bt);
	        this.main_mb0_bt.setOnClickListener(new OnViewBtnClickListener());
	        this.main_mb0_bt.setOnFocusChangeListener(new OnViewBtnFocusChangeListener());
	        this.main_mb0_bt.setOnTouchListener(new OnViewBtnTouchListener());
	        
	        this.main_mb1_bt = (ImageButton) this.findViewById(R.id.main_mb1_bt);
	        this.main_mb1_bt.setOnClickListener(new OnViewBtnClickListener());
	        this.main_mb1_bt.setOnFocusChangeListener(new OnViewBtnFocusChangeListener());
	        this.main_mb1_bt.setOnTouchListener(new OnViewBtnTouchListener());
	        
	        this.main_mb2_bt = (ImageButton) this.findViewById(R.id.main_mb2_bt);
	        this.main_mb2_bt.setOnClickListener(new OnViewBtnClickListener());
	        this.main_mb2_bt.setOnFocusChangeListener(new OnViewBtnFocusChangeListener());
	        this.main_mb2_bt.setOnTouchListener(new OnViewBtnTouchListener());
	        
	        this.main_mb3_bt = (ImageButton) this.findViewById(R.id.main_mb3_bt);
	        this.main_mb3_bt.setOnClickListener(new OnViewBtnClickListener());
	        this.main_mb3_bt.setOnFocusChangeListener(new OnViewBtnFocusChangeListener());
	        this.main_mb3_bt.setOnTouchListener(new OnViewBtnTouchListener());
	        
	        this.main_mb4_bt = (ImageButton) this.findViewById(R.id.main_mb4_bt);
	        this.main_mb4_bt.setOnClickListener(new OnViewBtnClickListener());
	        this.main_mb4_bt.setOnFocusChangeListener(new OnViewBtnFocusChangeListener());
	        this.main_mb4_bt.setOnTouchListener(new OnViewBtnTouchListener());
	        
	        //注册上下文菜单
	        this.registerForContextMenu(main_mb4_bt);      
	 }
	
	/**
	 * 登录
	 */
	private void userLogin(){
		if(StartActivity.isConnected){
 		   if(isLogin==false){
 	    		  if(userLoginHttpRequest()){ 
 	      			 isLogin = true;
 	      			 Intent intent = new Intent();
 	  	    	     intent.setClass(MainActivity.this, NewListActivity.class);
 	  	    	     MainActivity.this.startActivity(intent); 
 	      		}else{ 
 	      		    Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show()	;
 	      		}
 	    	  }else{
 	    		    Toast.makeText(MainActivity.this, "当前已登录", Toast.LENGTH_SHORT).show()	;
 	    	  }  
 	   }else{ 
 		   Toast.makeText(MainActivity.this, "请检查当前的网络连接", Toast.LENGTH_SHORT).show();  
 	   }
	}
	/**
	 * 自动登录
	 */
	private void  autoLogin(){
		Toast.makeText(MainActivity.this, "正在登录请稍后...", Toast.LENGTH_SHORT).show();
		userLogin();
	}
	/**
	 * 登录http 请求
	 * @return
	 */
	private boolean userLoginHttpRequest(){ 
		String responseMsg = HttpHelper.getHttpResponse(Appconstant.RequestURI.USERLOGIN_ACTION+"username="+username_text.getText().toString()+"&password="+password_text.getText().toString());
		if(responseMsg!=null && responseMsg.equals("true")){
			return true;
		}else{
			return false; 
		} 
	}
	
	/**
	 * 注销
	 */
	private  void userLogout(){
        /**注销登录状态**/ 
		isLogin = false;
	    /**清空控件**/ 
		username_text.setText("");
		password_text.setText("");
		remember_check.setChecked(false);
		/**清空preferences数据**/
		 SharedPreferences.Editor editor=mySharedPreferences.edit(); 
		 editor.putString("username","");
		 editor.putString("password","");
		 editor.putBoolean("isRemember",false); //退出时 重置登录状态
		 editor.commit(); 
	}
	
	/**
	 * 保存登录信息 到 esy_user.xml
	 */
	 private void saveUserInfoToSharedPreferences(String username,String password,boolean isRemember){
		 //LogHelper.sysoLog("saveUserInfoToSharedPreferences","isRemember:"+isRemember);
		 SharedPreferences.Editor editor=mySharedPreferences.edit(); 
		 if(!"".equals(username)){
			 editor.putString("username",username.trim());
		 }
		 if(!"".equals(password)){
			 editor.putString("password",password.trim());
		 }  
		 editor.putBoolean("isRemember",isRemember); //退出时 重置登录状态
		 editor.commit();
	 }
	
	 /**
	  * 读取信息
	  */
	 private void readerUserInfoFromSharedPreferences(){
		 boolean isRemember = mySharedPreferences.getBoolean("isRemember", false);
		// LogHelper.sysoLog("readerUserInfoFromSharedPreferences","isRemember:"+isRemember);
		 this.username_text.setText(mySharedPreferences.getString("username", ""));
		 this.password_text.setText(mySharedPreferences.getString("password", "")); 
		 this.remember_check.setChecked(isRemember);	 
	 }
	 
	 
	 
	 /**
	  * 隐藏控件
	  */
	 public void hideLoginForm(){		 
		 this.username_text.setVisibility(View.GONE);
		 this.password_text.setVisibility(View.GONE);
		 this.password_label.setVisibility(View.GONE);
		 this.username_label.setVisibility(View.GONE);
		 this.remember_check.setVisibility(View.GONE);
		 this.login_bt.setVisibility(View.GONE);
		 this.regist_bt.setVisibility(View.GONE);
	 }

}