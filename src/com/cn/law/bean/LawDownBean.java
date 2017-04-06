package com.cn.law.bean;

import java.io.Serializable;

public class LawDownBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int lid;
	private String ltype;
	private String fname;
	private String fsize;
	private String time ;
	public LawDownBean (){
		
	}
	public LawDownBean(int lid, String ltype, String fname, String fsize,
			String time) {
		super();
		this.lid = lid;
		this.ltype = ltype;
		this.fname = fname;
		this.fsize = fsize;
		this.time = time;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLtype() {
		return ltype;
	}
	public void setLtype(String ltype) {
		this.ltype = ltype;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFsize() {
		return fsize;
	}
	public void setFsize(String fsize) {
		this.fsize = fsize;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
