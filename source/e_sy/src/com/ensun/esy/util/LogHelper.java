package com.ensun.esy.util;

import java.util.Date;

public class LogHelper {
	
	public static void sysoLog(String resource){ 
		Date date = new Date();
		System.out.println("["+date.toString()+"] ["+resource+"]");
	} 
	
	public static void sysoLog(String resource,String method){ 
		Date date = new Date();
		System.out.println("["+date.toString()+"] ["+resource+"] ["+method+"]");
	} 
	
	public static void sysoLog(String resource,String method,String logInfo){ 
		Date date = new Date();
		System.out.println("["+date.toString()+"] ["+resource+"] ["+method+"] ["+logInfo+"]");
	}

	public static void sysoLog(String resource,String method,String logInfo,String remark){ 
		Date date = new Date();
		System.out.println("["+date.toString()+"] ["+resource+"] ["+method+"] ["+logInfo+"] ["+remark+"]");
	}
 
	
}
