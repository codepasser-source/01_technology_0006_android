package com.baishui.android;

import com.baishui.sqlite3.DatabaseHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	private Button createBT;
	private Button upgradeBT;
	private Button insertBT;
	private Button deleteBT;
	private Button updataBT;
	private Button selectBT;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.createBT = (Button) this.findViewById(R.id.createBT); 
        this.upgradeBT = (Button) this.findViewById(R.id.upgradeBT);
        this.insertBT = (Button) this.findViewById(R.id.insertBT);
        this.deleteBT = (Button) this.findViewById(R.id.deleteBT);
        this.updataBT = (Button) this.findViewById(R.id.updataBT);
        this.selectBT = (Button) this.findViewById(R.id.selectBT);
        
        this.createBT.setOnClickListener(new OnCreateBTListener());
        this.upgradeBT.setOnClickListener(new OnUpgradeBTListener());
        this.insertBT.setOnClickListener(new OnInsertBTListener());
        this.updataBT.setOnClickListener(new OnUpdataBTListener());
        this.deleteBT.setOnClickListener(new OnDeleteBTListener());
        this.selectBT.setOnClickListener(new OnSelectBTListener());
    }

    class OnCreateBTListener implements OnClickListener{ 
		@Override
		public void onClick(View arg0) {
			//调用DatabaseHelper的构造器 默认的数据库版本 为 1 , 将数据库名绑定到 当前需要获得的数据库
			 DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_db"); 
			//调用一个得到数据库的方法 Android SQLiteOpenHelper 会判断如果数据库版本不同时会执行更新onUpgrade回调函数
			 dbHelper.getReadableDatabase(); //或执行dbHelper.getWritableDatabase() 
			 Toast.makeText(MainActivity.this,"创建数据库成功", Toast.LENGTH_SHORT).show();
		}  
    }
    class OnUpgradeBTListener implements OnClickListener{ 
		@Override
		public void onClick(View arg0) {
			//调用DatabaseHelper的构造器，设置一个与默认版本不同的数值就会更新数据库, 将数据库名绑定到 当前需要获得的数据库
			 DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_db",2); 
			//调用一个得到数据库的方法 Android SQLiteOpenHelper 会判断如果没有此数据库就会执行 onCreate 回调函数，创建一个数据库
			 dbHelper.getReadableDatabase(); //或执行dbHelper.getWritableDatabase() 
			 Toast.makeText(MainActivity.this,"修改数据库成功", Toast.LENGTH_SHORT).show();
		}  
    }
    class OnInsertBTListener implements OnClickListener{ 
		@Override
		public void onClick(View arg0) { 
			System.out.println("listener insert");
			//创建一个 数据表  记录  结构 对象
			ContentValues values = new ContentValues();
			//设置记录 每列的值
			values.put("id",1);
			values.put("name", "zhangsan");
			//利用DatabaseHelper得到database对象
			DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_db"); 
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			//执行插入语句
			db.insert("user", null, values);
			Toast.makeText(MainActivity.this,"插入成功", Toast.LENGTH_SHORT).show();
		}  
    }
    class OnUpdataBTListener implements OnClickListener{ 
		@Override
		public void onClick(View arg0) { 
			System.out.println("listener update");
			//创建一个 数据表  记录  结构 对象
			ContentValues values = new ContentValues();
			//设置记录 每列的值
			 
			values.put("name", "lisi");
			//利用DatabaseHelper得到database对象
			DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_db"); 
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			//执行更新语句
			 //"id=?" 设置更新条件    new String[]{"1"} 解析条件语句中的?值为1
			db.update("user", values, "id=?",new String[]{"1"});
			Toast.makeText(MainActivity.this,"更新成功", Toast.LENGTH_SHORT).show();
		}  
    }
    
    class OnDeleteBTListener implements OnClickListener{ 
		@Override
		public void onClick(View arg0) { 
			System.out.println("listener delete"); 
			//利用DatabaseHelper得到database对象
			DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_db"); 
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			//执行删除语句
			//"name=?" 设置删除条件    new String[]{"lisi"} 解析条件语句中的?值为lisi
			db.delete("user", "name=?", new String[]{"lisi"});
			Toast.makeText(MainActivity.this,"删除成功", Toast.LENGTH_SHORT).show();
		}  
    }
    
    class OnSelectBTListener implements OnClickListener{ 
		@Override
		public void onClick(View arg0) { 
			System.out.println("listener query"); 
			//利用DatabaseHelper得到database对象
			DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_db"); 
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			//执行查询语句
			//"name=?" 设置删除条件    new String[]{"lisi"} 解析条件语句中的?值为lisi
			 
			Cursor cursor = db.query("user", new String[]{"id","name"}, "id=?", new String[]{"1"},"","","");
			
			while (cursor.moveToNext()) {
				 int id = cursor.getInt(cursor.getColumnIndex("id"));
				 String name = cursor.getString(cursor.getColumnIndex("name"));
				 Toast.makeText(MainActivity.this, "id:"+id+"name:"+name, Toast.LENGTH_SHORT).show();
			}
			 
		}  
    }
    
    
    
	public Button getCreateBT() {
		return createBT;
	}

	public void setCreateBT(Button createBT) {
		this.createBT = createBT;
	}

	public Button getUpgradeBT() {
		return upgradeBT;
	}

	public void setUpgradeBT(Button upgradeBT) {
		this.upgradeBT = upgradeBT;
	}

	public Button getInsertBT() {
		return insertBT;
	}

	public void setInsertBT(Button insertBT) {
		this.insertBT = insertBT;
	}

	public Button getDeleteBT() {
		return deleteBT;
	}

	public void setDeleteBT(Button deleteBT) {
		this.deleteBT = deleteBT;
	}

	public Button getUpdataBT() {
		return updataBT;
	}

	public void setUpdataBT(Button updataBT) {
		this.updataBT = updataBT;
	}

	public Button getSelectBT() {
		return selectBT;
	}

	public void setSelectBT(Button selectBT) {
		this.selectBT = selectBT;
	}
    
    
    
}