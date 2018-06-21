package com.baishui.provider;

import android.net.Uri;
import android.provider.BaseColumns; 
/**
 * SQLite Database 结构，
 * 设定好 自定义的ContentProvider 所需要的 值数据
 * 在创建  查询 插入 修改 删除 数据库时 直接就调用 这些属性 就可以做到操作，不用自己写名字
 * 这就是ContentProvider 的方便所在 
 * @author think
 */
public class MyProviderMetaData {

	//声明 自定义的 ContentProvider 名字 ，注意要在 Androidmanifest.xml配置这个名字 才能使用Android ContentProvider
	public static final String AUTHORIY="com.baishui.provider.MyContentProvider";
	//数据库文件名 ，即数据库名 ，调用时会创建TestProvider.db这个数据库文件，注意扩展名
	public static final String DATABASE_NAME = "TestProvider.db";
	//数据库版本,DatabaseHelper 默认保持一致
	public static final int DATABASE_VERSION=1;
	//本数据库中所包含的表名，如有多个 都定义一下一个变量存储表名
	public static final String USERS_TABLE_NAME="users";
	
	
	//数据库  users 表  结构  解析类
	public static final class UserTableMetaData implements BaseColumns{
		
		//_id 属性 不用自己定义， 因为  BaseColumns 接口中已经 定义一个  _id 的属性，注意在创建表示id 应为自增
		//当前表名
		public static final String TABLE_NAME="users";
		//访问当前表的ContentProvider 的URI，如有多个表 则改变 /users对应要操作的表
		public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORIY+"/users");
		//MyContentProvider中 getType 返回的值，这个值为自定义的格式，表示所有列的数据类型
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.testprovider.user";
	    // 表示一列的数据类型
		public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.testprovider.user";
	    //列名，如有多个列 再次添加其他列的定义，存储列名
		public static final String USER_NAME = "name";
	    //默认排序，在查询中拼如的条件 ，还可以添加group by 条件
	    public static final String DEFAULT_SORT_ORDER = "_id desc";
	} 
}
