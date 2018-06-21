package com.baishui.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class FileUtiles {

	private String SDPATH; 
	
	
	public String getSDPATH() {
		return SDPATH;
	} 
	public void setSDPATH(String SDPATH) { 
	
		this.SDPATH = SDPATH;
	}
	
	
	public FileUtiles(){
		
		//获取当前手机的SD卡的跟目录
		SDPATH = Environment.getExternalStorageDirectory() + "/";
		System.out.println("FileUtiles SDPATH : " +SDPATH);
	}
	/**
	 * 在SD创建一个文件
	 * @param fileName   文件名
	 * @return 创建的文件
	 * @throws IOException
	 */
	public File createSDFile(String fileName) throws IOException{
		System.out.println("FileUtiles  createSDFile(String fileName) param fileName:"+fileName);
		File file = new File(SDPATH+fileName);
		file.createNewFile();
		return file;
	}
	
	
	/**
	 * 创建一个目录
	 * @param dirName 
	 * @return
	 * @throws IOException
	 */
	public File createSDDir(String dirName) throws IOException{
		System.out.println("FileUtiles  createSDDir(String dirName) param dirName:"+dirName);
		File dir = new File(SDPATH+dirName);
		dir.mkdir();
		return dir;
	}
	
	/**
	 * 判断文件夹是否存在
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public boolean isFileExist(String fileName) throws IOException{ 
		System.out.println("FileUtiles  isFileExist(String fileName) param fileName : "+fileName);
		 File file = new File(SDPATH+fileName); 
		return file.exists(); 
	}
	
	
	
	
	public File  write2SDFromInput(String dirName,String fileName,InputStream input) throws IOException{
		
		File file = null;
		OutputStream output = null;
		 
		try { 
			
			createSDDir(dirName);
			
			file = createSDFile(dirName+fileName);
			output = new FileOutputStream(file);
			
			//还可以利用BufferReader 来读取
			byte buffer [] = new byte[4*1024]; 
			int temp;
			while((temp=input.read(buffer))!= -1){
				output.write(buffer,0,temp);
			} 
			output.flush();
			
		} catch (Exception e) {
	      
		  e.printStackTrace();
		}finally{ 
			try {
				if(output!=null){
					output.close();
					System.out.println("FileUtiles  write2SDFromInput  output.close();");
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		} 
		return file;
		
	}
	
	
	
	
	
	
	
	
	
}
