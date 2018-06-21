
package com.baishui.util;

 
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.baishui.model.Mp3Info;

public class MyXMLContentHandler extends DefaultHandler {

	private List<Mp3Info> mp3List = null;
	private Mp3Info mp3 = null;
	private String tagName;  
	private boolean isStart = false;
	
	
	
	public MyXMLContentHandler(List<Mp3Info> mp3List) {
		super();
		this.mp3List = mp3List;
	}

	@Override
	public void startDocument() throws SAXException {
	   //System.out.println("MyContentHandler---->startDocument");
	}
	
	@Override
	public void endDocument() throws SAXException {
		 // System.out.println("MyContentHandler---->endDocument"); 
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
		 //System.out.println("MyContentHandler---->startElement");  
		 tagName = localName;  
		 if(tagName.equals("resource")){ 
			 //System.out.println("startElement  resource");  
			 mp3 = new Mp3Info();
		 } 
		 isStart= true;
	}
	
	@Override
	public void endElement(String namespaceUri, String localName, String qName)
			throws SAXException {
		 // System.out.println("MyContentHandler---->endElement localName:"+localName);  
		 if(localName.endsWith("resource")){
			// System.out.println("endElement  resource");  
			 mp3List.add(mp3);
		 }
	 
		 isStart= false;
	}

	
	/**
	 * 得到标签中间的内容
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		    
		 if(isStart){
			// System.out.println("MyContentHandler----> characters  tagName："+ tagName); 
			 if(tagName.equals("id")){
				 mp3.setId(new String(ch,start,length)); 
			 }else if(tagName.equals("mp3.name")){
				 mp3.setMp3Name( new String(ch,start,length)); 
			 } else if(tagName.equals("mp3.size")){
				 mp3.setMp3Size(new String(ch,start,length)); 
			 }else if(tagName.equals("lrc.name")){
				 mp3.setLrcName( new String(ch,start,length)); 
			 } else if(tagName.equals("lrc.size")){
				 mp3.setLrcSize(new String(ch,start,length)); 
			 }   
			 
		 }
		   
			 
	}
	
	
	
	
}
