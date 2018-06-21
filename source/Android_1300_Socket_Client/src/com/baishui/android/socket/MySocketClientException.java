package com.baishui.android.socket;

public class MySocketClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int ERROR_CONNECTION_DISABLED=1;
	
	public int error;
	public String errorInfo;
	
	
	public MySocketClientException(int error,String errorInfo) {
		this.error = error;
		this.errorInfo = errorInfo;
		 System.out.println("[MySocketClientException]:  error :"+error +" errorInfo : "+errorInfo);
	}
	
	
}
