package com.cn.law.bean;

import java.io.Serializable;

public class CommendBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String ferror;
    private String  merror;
    public CommendBean(){}
	public CommendBean(String ferror, String merror) {
		super();
		this.ferror = ferror;
		this.merror = merror;
	}
	public String getFerror() {
		return ferror;
	}
	public void setFerror(String ferror) {
		this.ferror = ferror;
	}
	public String getMerror() {
		return merror;
	}
	public void setMerror(String merror) {
		this.merror = merror;
	}
    
}
