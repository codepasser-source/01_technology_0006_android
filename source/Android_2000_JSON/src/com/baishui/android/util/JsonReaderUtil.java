package com.baishui.android.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.baishui.android.model.User;
import com.google.gson.stream.JsonReader;

public class JsonReaderUtil {

	public JsonReaderUtil(){ 
	} 
	
	/**
	 * 读取一个对象
	 * @param jsonData
	 * @return
	 */
	public List<User> parseJsonToUsers(String jsonData){  
		JsonReader jsonReader = new JsonReader(new StringReader(jsonData));
		List<User> users = new ArrayList<User>();
		try { 
			jsonReader.beginArray(); 
			while ( jsonReader.hasNext()) {
				System.out.println(" jsonReader beginArray");
				jsonReader.beginObject(); 
				User user = new User();
				while (jsonReader.hasNext()) { 
					System.out.println(" --------jsonReader beginObject"); 
					String tagName = jsonReader.nextName(); 
					if(tagName.endsWith("username")){
						user.setUsername(jsonReader.nextString());
					}else if(tagName.endsWith("age")){
						user.setAge(jsonReader.nextInt());
					}else if(tagName.endsWith("sex")){
						user.setSex(jsonReader.nextString());
					} 
				}	 
				jsonReader.endObject();
				
				users.add(user);
			} 
			jsonReader.endArray();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return users;
	} 
	
	
	public  User  parseJsonToUser(String jsonData){
		User user = new User();
		JsonReader jsonReader = new JsonReader(new StringReader(jsonData));
       try{
		   jsonReader.beginObject();  
		   while (jsonReader.hasNext()) { 
			   System.out.println(" --------jsonReader beginObject"); 
			   String tagName = jsonReader.nextName(); 
			   if(tagName.endsWith("username")){
			    	user.setUsername(jsonReader.nextString());
			   }else if(tagName.endsWith("age")){
			    	user.setAge(jsonReader.nextInt());
			   }else if(tagName.endsWith("sex")){
			    	user.setSex(jsonReader.nextString());
			   } 
	      	}	 
		  jsonReader.endObject();
	   } catch (IOException e) { 
		 e.printStackTrace();
	   } 	
		return user;
	}
	 
}
