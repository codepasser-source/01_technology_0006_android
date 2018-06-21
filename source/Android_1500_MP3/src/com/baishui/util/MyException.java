package com.baishui.util;

public class MyException extends Exception { 
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyException(String exceptionInfo){ 
		super();
		System.out.println(exceptionInfo);
	}
}
