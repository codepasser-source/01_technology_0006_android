package com.baishui.sqlite3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final int VERSION = 1 ; 
    //创建构造器1 调用构造器2 将version 传递
	public DatabaseHelper(Context context, String databaseName ) {
		this(context, databaseName,VERSION); 
	}
	//创建构造器2 调用构造器3
	public DatabaseHelper(Context context, String databaseName ,int version) {
		this(context, databaseName, null, version); 
	}
	//创建构造器3   调用构造器父类的构造器
	//此构造器是必须实现的  
	public DatabaseHelper(Context context, String databaseName, CursorFactory factory,
			int version) {
		super(context, databaseName, factory, version);
		 
	}
	
	/***
	 * 第一次创建数据库时调用
	 * 这是一个回调函数，被调用
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
       System.out.println("DatabaseHelper onCreate create a database table user ");
       db.execSQL("create table user(id int,name varchar(20)) ");
	}
	/***
	 * 修改一个表结构时调用
	 * 这是一个回调函数，被调用
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 System.out.println("DatabaseHelper onUpgrade  updata a database table  ");
	}

}
