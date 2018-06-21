package com.ensun.esy.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

 
import com.ensun.esy.model.Word;
import com.google.gson.stream.JsonReader;

public class JsonReaderUtil {

	public JsonReaderUtil(){ 
	} 
	
	/**
	 * 读取一个对象
	 * @param jsonData
	 * @return
	 */
	public static List<Word> parseJsonToModelList(String jsonData){  
		JsonReader jsonReader = new JsonReader(new StringReader(jsonData));
		List<Word> words = new ArrayList<Word>();
		try { 
			jsonReader.beginArray(); 
			while ( jsonReader.hasNext()) { 
				jsonReader.beginObject(); 
				 Word word = new Word();
				while (jsonReader.hasNext()) {  
					String tagName = jsonReader.nextName(); 
					if(tagName.endsWith("word")){
						word.setWord(jsonReader.nextString());
					}else if(tagName.endsWith("vedioName")){
						word.setVedioName(jsonReader.nextString());
					}else if(tagName.endsWith("vedioSize")){
						word.setVedioSize(jsonReader.nextString());
					}else if(tagName.endsWith("vedioUri")){
						word.setVedioUri(jsonReader.nextString());
					}  
				}	 
				jsonReader.endObject();
				
				words.add(word);
			} 
			jsonReader.endArray();
			
		} catch (IOException e) {  
			e.printStackTrace();
		} 
		return words;
	} 
	
	
	 
}
