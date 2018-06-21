package com.ensun.esy.model;

import java.io.Serializable;

 

public class Word implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String  word;
	private String  vedioName;
	private String  vedioSize;
	private String  vedioUri;
	private String  addTime;
	
	public Word() { 
	} 
 
	public Word(int id, String word, String vedioName, String vedioSize,
			String vedioUri, String addTime) {
		super();
		this.id = id;
		this.word = word;
		this.vedioName = vedioName;
		this.vedioSize = vedioSize;
		this.vedioUri = vedioUri;
		this.addTime = addTime;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getVedioName() {
		return vedioName;
	}

	public void setVedioName(String vedioName) {
		this.vedioName = vedioName;
	}

	public String getVedioSize() {
		return vedioSize;
	}

	public void setVedioSize(String vedioSize) {
		this.vedioSize = vedioSize;
	}

	public String getVedioUri() {
		return vedioUri;
	}

	public void setVedioUri(String vedioUri) {
		this.vedioUri = vedioUri;
	}




	public String getAddTime() {
		return addTime;
	}




	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	
	
	
}
