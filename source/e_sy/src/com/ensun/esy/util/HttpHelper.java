package com.ensun.esy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

 
 

public class HttpHelper {

	 /**
	 * 返回Http请求应答 
	 * @param urlStr
	 * @return
     * @throws Exception 
	 */
	public static String getHttpResponse(String urlStr){
		
		StringBuffer downloadData = new StringBuffer();
		String readedLine = null; 
		BufferedReader bufferReader = null; 
		try {
			 
			URL url = new URL(urlStr); 
			HttpURLConnection  httpConn = (HttpURLConnection)url.openConnection(); 
			bufferReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));  
			while((readedLine = bufferReader.readLine()) != null){ 
				downloadData.append(readedLine);
			} 
		 
		} catch (Exception e) { 
			e.printStackTrace(); 
			LogHelper.sysoLog("HttpHelper", "getHttpResponse","Exception : 服务地址错误");
		}finally{
			try {
				if(bufferReader!=null){
					bufferReader.close();  
				} 
			} catch (IOException e) { 
				e.printStackTrace();
			}
		} 
		return downloadData.toString();
	}	
	
	
	/**
	 * 
	 * @param urlString
	 * @param dirName
	 * @param fileName
	 * @return  1 : 下载文件成功  -1 ：下载文件失败  0 ： 文件已存在
	 */
  public static int downloadFile(String urlStr,String dirName,String fileName){ 
	 
	  InputStream inputStream = null; 
	  
	  try {
		  
		  FileUtiles fileUtil = new FileUtiles();
		
		  if(fileUtil.isFileExist(dirName+"/"+fileName)){
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
  public static InputStream getInputStreamFromUrl(String urlStr) throws IOException{
	  
	      InputStream inputStream = null; 
		  URL url  = new URL(urlStr); 
		  HttpURLConnection  httpConn = (HttpURLConnection)url.openConnection();
		  inputStream = httpConn.getInputStream();
	 
	     return inputStream;
  }
  
}
