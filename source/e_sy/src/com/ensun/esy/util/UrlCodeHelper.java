package com.ensun.esy.util;

import java.net.URLEncoder;

public class UrlCodeHelper {

	public static String wordToUrlCode(String wordStr){  
		String urlCode = URLEncoder.encode(wordStr);
		return  urlCode;
	}

	
	public static String urlCodeFormat(String wordStr){ 
		String urlCodeFormat =  wordToUrlCode(wordStr);
		String []code = urlCodeFormat.split("%");
         //System.out.println(urlCodeFormat);
         urlCodeFormat = "";
		 for (int i = 0; i < code.length; i++) {
		    urlCodeFormat = urlCodeFormat+code[i];
			 //System.out.println("code : "+code[i]);
		}
		 //System.out.println(urlCodeFormat);
	 
		//去掉+号
		  urlCodeFormat = urlCodeFormat.replaceAll("\\+",""); 
		
        //System.out.println(urlCodeFormat.length());
		return  urlCodeFormat;
	}
	
	
}
