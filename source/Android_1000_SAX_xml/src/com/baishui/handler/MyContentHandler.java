package com.baishui.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyContentHandler extends DefaultHandler {

	
	String tagName , id, name , age;
	boolean isEnd = false;
	@Override
	public void startDocument() throws SAXException {
	   System.out.println("MyContentHandler---->startDocument");
	}
	
	@Override
	public void endDocument() throws SAXException {
		  System.out.println("MyContentHandler---->endDocument");
		  System.out.println("\n");
	}
	
	/**
	 * Param :
	 * String namespaceUri 命名空间
     * String localName 当前标签名
     * String qName  当前标签前缀名 ： 例如 ： <android:id /> 得到 android
     * Attributes attributes
	 */
	@Override
	public void startElement(String namespaceUri, String localName, String qName,
			Attributes attributes) throws SAXException {
		 System.out.println("MyContentHandler---->startElement");  
		 tagName = localName;  
		 if(tagName.equals("user")){ 
			 System.out.println("----"+tagName);
			 for (int i = 0; i < attributes.getLength(); i++) {
				 System.out.println("--------"+attributes.getLocalName(i)+"="+attributes.getValue(i));
			} 
		 }
	 
		 isEnd = false;
	}
	
	@Override
	public void endElement(String namespaceUri, String localName, String qName)
			throws SAXException {
		 System.out.println("MyContentHandler---->endElement");
		 isEnd = true;
		 System.out.println("\n");
	}

	
	/**
	 * 得到标签中间的内容
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		 System.out.println("MyContentHandler---->characters");
		 
		 
		if( isEnd == false){ //是startElement调用时才执行
			 if(tagName.equals("name")){
				 name = new String(ch,start,length);
				 System.out.println("--------name : " +name);
			 }else if(tagName.equals("age")){
				 age = new String(ch,start,length);
				 System.out.println("--------age : " +age);
			 }   
		}  
	}
	
	
	
	
}
