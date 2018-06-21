package com.baishui.android;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
 
import java.net.URLDecoder;
import java.net.URLEncoder;
 

import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.baishui.handler.MyContentHandler;
import com.baishui.util.HttpDownLoader;

 
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button download;
	private Button urlcodeBT;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.download = (Button) this.findViewById(R.id.download);
        
        this.download.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {
				
				 String str = "中文";
				 String encode = null;
				 
				 encode = URLEncoder.encode(str);
				  System.out.println("encode:"+ encode);
				 
				 
				
				 HttpDownLoader downLoader = new HttpDownLoader();
				 String resultData = downLoader.download("http://192.168.1.102:8080/AndroidDownload/xmlSAX?requestData="+encode);
				 System.out.println("resultData : " + resultData); 
				try {
					
					//使用SAX解析xml 数据的 写法
					 SAXParserFactory saxFactory = SAXParserFactory.newInstance();
					 XMLReader xmlReader = saxFactory.newSAXParser().getXMLReader(); 
					 xmlReader.setContentHandler(new MyContentHandler());
					 xmlReader.parse(new InputSource(new StringReader(resultData))); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
		});
        
        
        this.urlcodeBT = (Button) this.findViewById(R.id.urlcodeBT);
        
        urlcodeBT.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View arg0) {
				 String str = "中文";
			    	String encode;
					try {
						encode = URLEncoder.encode(str, "GBK");
						 System.out.println("encode:"+ encode);
						 String decode = URLDecoder.decode(encode, "GBK");
						 System.out.println("decode:"+decode); 
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		});
    }
}