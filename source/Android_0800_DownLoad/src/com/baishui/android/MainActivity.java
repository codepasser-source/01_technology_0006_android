package com.baishui.android;

import java.io.File;
import java.io.IOException;

import com.baishui.util.FileUtiles;
import com.baishui.util.HttpDownLoader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button testBT1;
	private Button testBT2;
	private Button testBT3;
	
	private Button downloadBT1;
	private Button downloadBT2;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.downloadBT1 = (Button) this.findViewById(R.id.downloadBT1);
        this.downloadBT2 = (Button) this.findViewById(R.id.downloadBT2);
        
        this.downloadBT1.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View arg0) {
			  System.out.println("MainActivity DownLoad1 Click start download ");
			  HttpDownLoader downLoader = new HttpDownLoader();
			  String data = downLoader.download("http://192.168.1.81:8080/AndroidDownload/testUri.jsp?requestData=111");
			  Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
			  
			  System.out.println("MainActivity DownLoad Txt resutl:"+data);
			}
		});
        this.downloadBT2.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View arg0) {
				 System.out.println("MainActivity DownLoad2 Click start download ");
				HttpDownLoader downLoader = new HttpDownLoader();
			    int data = downLoader.downloadFile("http://192.168.1.81:8080/AndroidDownload/esy1.mp4", "testDir/", "esy1.mp4");
			  if(data == 1){
				  Toast.makeText(MainActivity.this, "文件下载成功", Toast.LENGTH_SHORT).show();
			  }else if(data == 0){
				  Toast.makeText(MainActivity.this, "文件已存在", Toast.LENGTH_SHORT).show();
			  }else{
				  Toast.makeText(MainActivity.this,"文件下载失败", Toast.LENGTH_SHORT).show();
			  }
			}
		});
        
        
        this.testBT1 = (Button) findViewById(R.id.testBT1);
        this.testBT2 = (Button) findViewById(R.id.testBT2);
        this.testBT3 = (Button) findViewById(R.id.testBT3);
        
        
        this.testBT1.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View arg0) {
			   FileUtiles fileUtil = new FileUtiles();
			   try {
				System.out.println(fileUtil.isFileExist("vava"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
        
        //创建文件夹时如果文件夹存在是 不会出错，替换
        this.testBT2.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View arg0) {
			   FileUtiles fileUtil = new FileUtiles();
			   try {
				  File file = fileUtil.createSDDir("testDir");
				  if(file!=null){
					  System.out.println("创建目录成功");
				  }else{
					  System.out.println("创建目录失败");
				  }
			    } catch (IOException e) {
				    System.out.println("创建目录异常");
				    e.printStackTrace();
			    }
			}
		});
        //创建文件时如果文件夹在是 不会出错，替换
        //如果创建文件的所在的文件夹不存在时 会出现异常
        this.testBT3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 FileUtiles fileUtil = new FileUtiles();
				   try {
					  File file = fileUtil.createSDFile("testDir1/test.txt");
					  if(file!=null){
						  System.out.println("创建文件成功");
					  }else{
						  System.out.println("创建文件失败");
					  }
				    } catch (IOException e) {
					    System.out.println("创建文件异常");
					    e.printStackTrace();
				    }
			}
		});
        
    }
	 
    
    
}