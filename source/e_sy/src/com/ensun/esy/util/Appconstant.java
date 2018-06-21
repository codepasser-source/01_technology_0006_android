package com.ensun.esy.util;

import android.os.Environment;

public class Appconstant {
  
	 public static class  RequestURI{ 
		/*** 测试环境
		 public static  final String NEWLIST_ACTION ="http://192.168.1.139:8080/esy/NewListAction"; 
		 public static  final String SEACHLIST_ACTION ="http://192.168.1.139:8080/esy/SeachListAction?word="; 
		 public static  final String USERLOGIN_ACTION ="http://192.168.1.139:8080/esy/UserLoginAction?"; 
		 public static  final String USERREGIST_ACTION ="http://192.168.1.139:8080/esy/UserRegistAction?"; 
       */	
	   /***正式环境**/
		 public static  final String NEWLIST_ACTION ="http://222.33.78.135:8080/Android_Ensun_Server/NewListAction"; 
		 public static  final String SEACHLIST_ACTION ="http://222.33.78.135:8080/Android_Ensun_Server/SeachListAction?word=";
		 public static  final String USERLOGIN_ACTION ="http://222.33.78.135:8080/Android_Ensun_Server/UserLoginAction?";
		 public static  final String USERREGIST_ACTION ="http://222.33.78.135:8080/Android_Ensun_Server/UserRegistAction?";
	 }
	
	 public static class SDCardDir{ 
		 public static final String SDPATH = Environment.getExternalStorageDirectory() + "/";   
		 public static final String SDDIR = "esy";
	 }
	 
	 public static class DataBase{
		 public static final String DATABASE_NAME = "esy";  
		 public static class CollectTable{
			 public static final String TABLENAME= "collect"; 
			 public static final String COLUMN_ID = "id";	
			 public static final String COLUMN_WORD = "word";	
			 public static final String COLUMN_VEDIONAME = "vedioName";	
			 public static final String COLUMN_VEDIOSIZE = "vedioSize";	
			 public static final String COLUMN_VEDIOURI = "vedioUri";	
			 public static final String COLUMN_ADDTIME="addTime";
		 }
	 }
	 
}
