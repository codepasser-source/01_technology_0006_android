package com.baishui.android;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.baishui.android.sqlite.DatabaseHelper;

public class SqliteActivity extends Activity {

	private static String DB_NAME = "TestSqlite.db";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite_activity);
		Button backBtn = (Button) this.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new OnBackBtnClickListener());

		Button createBtn = (Button) this.findViewById(R.id.createBtn);
		createBtn.setOnClickListener(new OnCreateBtnListener());
		Button upgradeBtn = (Button) this.findViewById(R.id.upgradeBtn);
		upgradeBtn.setOnClickListener(new OnUpgradeBtnListener());

		Button addBtn = (Button) this.findViewById(R.id.addBtn);
		addBtn.setOnClickListener(new OnAddBtnListener());

		Button deleteBtn = (Button) this.findViewById(R.id.deleteBtn);
		deleteBtn.setOnClickListener(new OnDeleteBtnListener());

		Button modifyBtn = (Button) this.findViewById(R.id.modifyBtn);
		modifyBtn.setOnClickListener(new OnModifyBtnListener());

		Button queryBtn = (Button) this.findViewById(R.id.queryBtn);
		queryBtn.setOnClickListener(new OnQueryBtnListener());
	}

	class OnBackBtnClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}

	class OnCreateBtnListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			// 调用DatabaseHelper的构造器 默认的数据库版本 为 1 , 将数据库名绑定到 当前需要获得的数据库
			DatabaseHelper dbHelper = new DatabaseHelper(SqliteActivity.this,
					DB_NAME);
			// 调用一个得到数据库的方法 Android SQLiteOpenHelper
			// 会判断如果数据库版本不同时会执行更新onUpgrade回调函数
			dbHelper.getReadableDatabase(); // 或执行dbHelper.getWritableDatabase()
			Toast.makeText(SqliteActivity.this, "创建数据库成功", Toast.LENGTH_SHORT)
					.show();
		}
	}

	class OnUpgradeBtnListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			// 调用DatabaseHelper的构造器，设置一个与默认版本不同的数值就会更新数据库, 将数据库名绑定到 当前需要获得的数据库
			DatabaseHelper dbHelper = new DatabaseHelper(SqliteActivity.this,
					DB_NAME, 2);
			// 调用一个得到数据库的方法 Android SQLiteOpenHelper 会判断如果没有此数据库就会执行 onCreate
			// 回调函数，创建一个数据库
			dbHelper.getReadableDatabase(); // 或执行dbHelper.getWritableDatabase()
			Toast.makeText(SqliteActivity.this, "修改数据库成功", Toast.LENGTH_SHORT)
					.show();
		}
	}

	class OnAddBtnListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			// 创建一个 数据表 记录 结构 对象
			ContentValues values = new ContentValues();
			// 设置记录 每列的值
			values.put("_id", 1);
			values.put("name", "zhangsan");
			// 利用DatabaseHelper得到database对象
			DatabaseHelper dbHelper = new DatabaseHelper(SqliteActivity.this,
					DB_NAME);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			// 执行插入语句
			db.insert("users", null, values);
			Toast.makeText(SqliteActivity.this, "插入成功", Toast.LENGTH_SHORT)
					.show();
		}
	}

	class OnDeleteBtnListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			// 利用DatabaseHelper得到database对象
			DatabaseHelper dbHelper = new DatabaseHelper(SqliteActivity.this,
					DB_NAME);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			//执行删除
			db.delete("users", "name=?", new String[]{"lisi"});
			//db.execSQL("delte from user where name = 'zhangsan'");
			Toast.makeText(SqliteActivity.this, "删除成功", Toast.LENGTH_SHORT)
					.show();
		}
	}
	
	class OnModifyBtnListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			// 创建一个 数据表 记录 结构 对象
			ContentValues values = new ContentValues();
			// 设置记录 每列的值
			values.put("name", "lisi");
			// 利用DatabaseHelper得到database对象
			DatabaseHelper dbHelper = new DatabaseHelper(SqliteActivity.this,
					DB_NAME);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			//执行修改
			db.update("users", values, "name=?", new String[]{"zhangsan"});
			Toast.makeText(SqliteActivity.this, "修改成功", Toast.LENGTH_SHORT)
					.show();
		}
	}
	
	class OnQueryBtnListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			// 利用DatabaseHelper得到database对象
			DatabaseHelper dbHelper = new DatabaseHelper(SqliteActivity.this,
					DB_NAME);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Cursor resultCursor = db.query("users", new String[]{"_id","name"}, "_id=?", new String[]{"1"},"","","");
			while (resultCursor.moveToNext()) {
				 int id = resultCursor.getInt(resultCursor.getColumnIndex("_id"));
				 String name = resultCursor.getString(resultCursor.getColumnIndex("name"));
				 Toast.makeText(SqliteActivity.this, "id:"+id+"name:"+name, Toast.LENGTH_SHORT).show();
			}
		}
	}

}
