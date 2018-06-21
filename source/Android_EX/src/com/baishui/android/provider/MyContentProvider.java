package com.baishui.android.provider;
 
import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.baishui.android.provider.MyProviderMetaData.UserTableMetaData;
import com.baishui.android.sqlite.DatabaseHelper;

public class MyContentProvider extends ContentProvider {

	//定一个  uri匹配的工具属性  UriMatcher ，用来检索操作的Uri格式
	public static final UriMatcher uriMatcher;
	//定义UriMatcher规则
	public static final int INTCOMING_USER_COLLECTION = 1; 
	public static final int INTCOMING_USER_SINGLE = 2; 
	
	
	 //添加UriMatcher规则
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH); 
		//*****注意添加规则时的数据格式  path 前没有 /
		//uriMatcher.addURI(authority, path, code)
		uriMatcher.addURI(MyProviderMetaData.AUTHORIY, "users", INTCOMING_USER_COLLECTION);
		uriMatcher.addURI(MyProviderMetaData.AUTHORIY, "users/#",INTCOMING_USER_SINGLE);// # 通配符表示id
	 }
	
	//为查询操作时，执行查询语句定义每一列的别名
	public static HashMap<String,String> userProjectionMap; 
	static{
		userProjectionMap = new HashMap<String, String>();
		//对应创建表示的列名，由于MyProviderMetaData.UserTableMetaData继承的BaseColumn类中封装好Id 和自定义的 UER_NAME自动匹配
		userProjectionMap.put(UserTableMetaData._ID, UserTableMetaData._ID);
		userProjectionMap.put(UserTableMetaData.USER_NAME, UserTableMetaData.USER_NAME);
	}
	
	
	//声明一个SQLite 操作的实现类对象，为本 ContentProvider提供数据库操作
	public DatabaseHelper databaseHelper;
 
	
	
	/**在启动时调用
	 * 当SQLite中没用当前数据库时 就会执行 此方法 调用  DatabaseHelper onCreate 创建数据库 表
	 */
	@Override
	public boolean onCreate() {
		System.out.println("MyContentProvider onCreate");
		databaseHelper = new DatabaseHelper(getContext(),MyProviderMetaData.DATABASE_NAME);
		databaseHelper.getReadableDatabase();
		return true;
	}
	 
	/**
	 * 根据传入的Uri 返回该Uri的格式所操作的结果 类型（自定义的数据类型）
	 */
	@Override
	public String getType(Uri uri) {
		System.out.println("MyContentProvider getType  uri : " +uri);
		switch (uriMatcher.match(uri)) {//匹配方法
		case INTCOMING_USER_COLLECTION:
			return UserTableMetaData.CONTENT_TYPE; 
		case INTCOMING_USER_SINGLE:
			return UserTableMetaData.CONTENT_TYPE_ITEM;
		default:
			throw new IllegalArgumentException("Unknow URI : " + uri);
		}
		
	}

	
	/**插入操作
	 * uri : 相当于一个请求 例如 ： content://com.baishui.provider.MyContentProvider/users 向users表格插入数据
	 * 返回content://com.baishui.provider.MyContentProvider/users/1"  1为当前插入的数据生成的Id,也为但前记录的id
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values){
		System.out.println("MyContentProvider insert  uri : " +uri +" values : obj");
		
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		//执行插入操作
		long rowId = db.insert(UserTableMetaData.TABLE_NAME, null, values);
		if(rowId > 0){
			Uri insertedUserUri = ContentUris.withAppendedId(UserTableMetaData.CONTENT_URI, rowId);
			//ContentResolver 来访问 ContentProvider，通知监听器数据已经改变
			getContext().getContentResolver().notifyChange(insertedUserUri, null);
		    return insertedUserUri;//返回新插入数据的Uri
		} else {
			return uri;//未成功时返归原来的uri
		}
			// throw new SQLException("Failed to insert row into" + uri);   
	  
	}
	
	
	/**查询操作
	 * uri : 相当于一个请求 例如 ： content://com.baishui.provider.MyContentProvider/users 指向表
	 *  返回数据Cursor
	 */
	@Override
	public Cursor query(Uri uri, String[] columns, String selection, String[] selectionArgs,
			String sortOrder) {
		System.out.println("MyContentProvider query  uri :" +uri); 
		//SQLiteQueryBuilder根据参数创建一个查询语句
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder(); 
		
	/***/
		switch(uriMatcher.match(uri)){
		case INTCOMING_USER_COLLECTION: //查询一个集合 
			queryBuilder.setTables(UserTableMetaData.TABLE_NAME);
			queryBuilder.setProjectionMap(userProjectionMap);// 查询时的列别名
			System.out.println(INTCOMING_USER_COLLECTION);
		  break;
		case INTCOMING_USER_SINGLE: //查询单个
			queryBuilder.setTables(UserTableMetaData.TABLE_NAME);
			queryBuilder.setProjectionMap(userProjectionMap);
			queryBuilder.appendWhere(UserTableMetaData._ID+"=" + uri.getPathSegments().get(1));
			System.out.println(INTCOMING_USER_SINGLE);
			break;
		default:
			throw new IllegalArgumentException("Unknow URI : " + uri);
		}
	 
		 
		
		//排序拼入
		String orderBy; 
		if(TextUtils.isEmpty(sortOrder)){
			 orderBy = UserTableMetaData.DEFAULT_SORT_ORDER; 
		}else{
			 orderBy = sortOrder; 
		}
		System.out.println("orderBy:"+orderBy);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		System.out.println("Cursor cursor = queryBuilder.query(db, columns, selection, selectionArgs, null, null, orderBy)");
		Cursor cursor = queryBuilder.query(db, columns, selection, selectionArgs, null, null, orderBy);
		 //ContentResolver 来访问 ContentProvider，通知监听器查询数据的结果
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return cursor;
	} 
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	} 
	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
