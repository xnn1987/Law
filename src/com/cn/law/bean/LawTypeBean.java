package com.cn.law.bean;

import java.io.Serializable;

public class LawTypeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   private int id;
   private String folderName;
   private String shortkey;
public LawTypeBean (){
}
public LawTypeBean(String folderName, String shortkey) {
	super();
	this.folderName = folderName;
	this.shortkey = shortkey;
}
public LawTypeBean(int id, String folderName, String shortkey) {
	super();
	this.id = id;
	this.folderName = folderName;
	this.shortkey = shortkey;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFolderName() {
	return folderName;
}
public void setFolderName(String folderName) {
	this.folderName = folderName;
}
public String getShortkey() {
	return shortkey;
}
public void setShortkey(String shortkey) {
	this.shortkey = shortkey;
}


   
}
