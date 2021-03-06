package com.cn.law.bean;

import java.io.Serializable;

public class MainFastBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int fid;
	private String Title;
	private String fDepartmentName;
	private String fLawNo;
	private String fReleaseDate;

	public MainFastBean() {

	}

	public MainFastBean(int fid, String title, String fDepartmentName,
			String fLawNo, String fReleaseDate) {
		super();
		this.fid = fid;
		Title = title;
		this.fDepartmentName = fDepartmentName;
		this.fLawNo = fLawNo;
		this.fReleaseDate = fReleaseDate;
	}

	public MainFastBean(String title, String fDepartmentName, String fLawNo,
			String fReleaseDate) {
		super();
		Title = title;
		this.fDepartmentName = fDepartmentName;
		this.fLawNo = fLawNo;
		this.fReleaseDate = fReleaseDate;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getfDepartmentName() {
		return fDepartmentName;
	}

	public void setfDepartmentName(String fDepartmentName) {
		this.fDepartmentName = fDepartmentName;
	}

	public String getfLawNo() {
		return fLawNo;
	}

	public void setfLawNo(String fLawNo) {
		this.fLawNo = fLawNo;
	}

	public String getfReleaseDate() {
		return fReleaseDate;
	}

	public void setfReleaseDate(String fReleaseDate) {
		this.fReleaseDate = fReleaseDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
