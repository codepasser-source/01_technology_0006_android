package com.baishui.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
 
import java.net.URL;
 

public class HttpDownLoader {

	private URL url; 
	/**
	 * 
	 * @param urlString
	 * @param dirName
	 * @param fileName
	 * @return  1 : 下载文件成功  -1 ：下载文件失败  0 ： 文件已存在
	 */
  public int downloadFile(String urlStr,String dirName,String fileName){
	  
	  
	  System.out.println("HttpDownLoader downloadFile(String urlStr,String dirName,String fileName)");
	  System.out.println("HttpDownLoader downloadFile param urlString：" + urlStr);
	  System.out.println("HttpDownLoader downloadFile param dirName：" + dirName);
	  System.out.println("HttpDownLoader downloadFile param fileName：" + fileName);
	  
	  
	  InputStream inputStream = null;
	  
	  try {
		  
		  FileUtiles fileUtil = new FileUtiles();
		
		  if(fileUtil.isFileExist(dirName+fileName)){
			  return 0;
		  }else{  
			  inputStream = getInputStreamFromUrl(urlStr);
			  if(inputStream!=null){
				  //下载正常
				  File resultFile = fileUtil.write2SDFromInput(dirName, fileName, inputStream);
				  if(resultFile!=null){
					  return 1; 
				  }else{
					  return -1; 
				  }
				 
			  }else{ 
				  //无法连接
				  return -1;
			  } 
		  } 
	  } catch (Exception e) {
		    //出现异常
		    e.printStackTrace();
		    return -1;
	  }
	  
	  
  }
	
  /**
   * 创建 HttpURLConnection  inputStream
   * @param urlStr
   * @return
   * @throws IOException
   */
  public InputStream getInputStreamFromUrl(String urlStr) throws IOException{
	  
	      InputStream inputStream = null; 
	      System.out.println("HttpDownLoader getInputStreamFromUrl(String urlStr) url:" +urlStr);
		  url = new URL(urlStr); 
		  HttpURLConnection  httpConn = (HttpURLConnection)url.openConnection();
		  System.out.println("HttpDownLoader getInputStreamFromUrl(String urlStr) url.openConnection");
		  inputStream = httpConn.getInputStream();
	 
	     return inputStream;
  }
  
  
  
  
  /**
	 * JAVA利用HttpURLConnection 下载文件的Demo
	 * 下载任何文件类型，返回文件内容
	 * @param urlStr
	 * @return
	 */
	public String download(String urlStr){
		
		StringBuffer downloadData = new StringBuffer();
		String readedLine = null;
		
		BufferedReader bufferReader = null; 
		try {
			System.out.println("HttpDownLoader download() param url:"+urlStr);
			url = new URL(urlStr); 
			HttpURLConnection  httpConn = (HttpURLConnection)url.openConnection();
			System.out.println("HttpDownLoader download() url.openConnection()");
			bufferReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			System.out.println("HttpDownLoader download() httpConn.getInputStream()");
			
			while((readedLine = bufferReader.readLine()) != null){ 
				downloadData.append(readedLine);
			} 
		 
		} catch (Exception e) {
			System.out.println("HttpDownLoader download() error ");
			e.printStackTrace();
		}finally{
			try {
				if(bufferReader!=null){
					bufferReader.close(); 
					System.out.println("HttpDownLoader download() bufferReader close");
				} 
			} catch (IOException e) { 
				e.printStackTrace();
			}
		} 
		return downloadData.toString();
	}
}
