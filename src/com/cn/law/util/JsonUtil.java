package com.cn.law.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cn.law.bean.CommendBean;
import com.cn.law.bean.LawDetialBean;
import com.cn.law.bean.LawDownBean;
import com.cn.law.bean.LawTypeBean;
import com.cn.law.bean.MainFastBean;

public class JsonUtil {
	public static CommendBean getError(String string){
		CommendBean cBean=new CommendBean();
		try {
			JSONObject cjson=new JSONObject(string);
			String fError=cjson.getString("foundErr");
			String mError=cjson.getString("errMsg");
			cBean=new CommendBean(fError, mError);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cBean;
		
	}
	public static List<MainFastBean> getMainData(String string) {
		List<MainFastBean> data = new ArrayList<MainFastBean>();
		// MainFastBean mainBean=new MainFastBean();
		try {
			JSONArray mArray = new JSONArray(string);
			if (mArray != null) {
				for (int i = 0; i < mArray.length(); i++) {
					JSONObject mObj = mArray.getJSONObject(i);
					int fId = mObj.getInt("ID");
					String fTitle = mObj.getString("Title");
					String fDepartment = mObj.getString("DepartmentName");
					String fLawNo = mObj.getString("LawNo");
					String fReleaseDate = mObj.getString("ReleaseDate");
					MainFastBean mainBeans = new MainFastBean(fId, fTitle,
							fDepartment, fLawNo, fReleaseDate);
					data.add(mainBeans);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public static List<MainFastBean> getNewData(String string) {
		List<MainFastBean> data = new ArrayList<MainFastBean>();
		// MainFastBean mainBean=new MainFastBean();
		try {
			JSONObject njson = new JSONObject(string);
			JSONArray mArray = njson.getJSONArray("laws_list");
			if (mArray != null) {
				for (int i = 0; i < mArray.length(); i++) {
					JSONObject mObj = mArray.getJSONObject(i);
					int fId = mObj.getInt("ID");
					String fTitle = mObj.getString("Title");
					String fDepartment = mObj.getString("DepartmentName");
					String fLawNo = mObj.getString("LawNo");
					String fReleaseDate = mObj.getString("ReleaseDate");
					MainFastBean mainBeans = new MainFastBean(fId, fTitle,
							fDepartment, fLawNo, fReleaseDate);
					data.add(mainBeans);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static LawDetialBean getLawContent(String str) {
		LawDetialBean detialBean = new LawDetialBean();
		try {
			JSONObject cjson = new JSONObject(str);
			JSONObject djson = cjson.getJSONObject("law");
			int fId = djson.getInt("ID");
			String fTitle = djson.getString("Title");
			String fLawNo = djson.getString("LawNo");
			String fDepartmentNamed = djson.getString("DepartmentName");
			String fReleaseDated = djson.getString("ReleaseDate");
			String fTradeName = djson.getString("TradeName");
			String fTypeName = djson.getString("LawTypeName");
			String fActualize = djson.getString("ActualizeDate");
			String fInputTime = djson.getString("InputTime");
			String fContent = djson.getString("Content");
			detialBean.setTitle(fTitle);
			detialBean.setfLawNo(fLawNo);
			detialBean.setfDepartmentName(fDepartmentNamed);
			detialBean.setfReleaseDate(fReleaseDated);
			detialBean.setfTradeName(fTradeName);
			detialBean.setfTypeName(fTypeName);
			detialBean.setfActualize(fActualize);
			detialBean.setfInputTime(fInputTime);
			detialBean.setfContent(fContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detialBean;
	}
	//第一级目录
	public static List<LawTypeBean> getLawTypesData(String string) {
		List<LawTypeBean> ldata = new ArrayList<LawTypeBean>();
		try {
		      JSONObject mJson=new JSONObject(string);
			 JSONArray mArray =mJson.getJSONArray("SubTypes") ;
			if (mArray != null) {
				for (int i = 0; i < mArray.length(); i++) {
					JSONObject lObj = mArray.getJSONObject(i);
					 int fId = lObj.getInt("ID");
		             String tfoldername=lObj.getString("folderName"); 
		             String tshortkey=lObj.getString("sortKey");
		             LawTypeBean lbean=new LawTypeBean(fId, tfoldername, tshortkey); 
					 ldata.add(lbean);
				}
	
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
		return ldata;
	}
	public static LawTypeBean getLawTypeData(String string) {
	//	List<LawTypeBean> data = new ArrayList<LawTypeBean>();
		LawTypeBean lbean=new LawTypeBean();
		// MainFastBean mainBean=new MainFastBean();
		try {
			JSONObject ljson = new JSONObject(string);
             String tfoldername=ljson.getString("folderName"); 
             String tshortkey=ljson.getString("sortKey");
		     lbean=new LawTypeBean(tfoldername, tshortkey);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lbean;
	}

	public static List<LawTypeBean> getLawTypesItemData(String string) {
		List<LawTypeBean> data = new ArrayList<LawTypeBean>();
		 LawTypeBean lbean=new LawTypeBean();
		try {
		
			JSONArray mArray =new JSONArray();
			if (mArray != null) {
				for (int i = 0; i < mArray.length(); i++) {
					JSONObject lObj = mArray.getJSONObject(i);
					JSONArray aObj=lObj.getJSONArray("Path");
					for (int j = 0; j < aObj.length(); j++) {
						JSONObject llObj=aObj.getJSONObject(j);
						 int fId = llObj.getInt("ID");
			             String tfoldername=llObj.getString("folderName"); 
			             String tshortkey=llObj.getString("sortKey");
			           lbean=new LawTypeBean(fId, tfoldername, tshortkey); 	
					}
				}
				data.add(lbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
 //法规下载
	public static LawDownBean getLawDownData(String string){
		LawDownBean lBean=null;
		try {
			JSONObject lJson=new JSONObject(string);
		    JSONObject djson=lJson.getJSONObject("downloadinfo");
		    int lid=djson.getInt("id");
		    String lawtype=djson.getString("lawtype");
		    String filename=djson.getString("filename");
		    String filesize=djson.getString("filesize");
		    String time=djson.getString("createtime");
	     lBean=new LawDownBean(lid, lawtype, filename, filesize, time);
		} catch (Exception e) {
		e.printStackTrace();
		}
	//	System.out.println(lBean.getFname());
		return lBean;		
	}
	//从本地得到json数据
	public static LawDetialBean getLawContentFromNative(String string) {
		LawDetialBean	detialBean=null;
		try {
			System.out.println(string.substring(1, string.length()));
		      string = string.substring(1, string.length());
			JSONObject cjson = new JSONObject(string);
			JSONObject djson = cjson.getJSONObject("law");
			String fTitle = djson.getString("Title");
			String fLawNo = djson.getString("LawNo");
			String fDepartmentNamed = djson.getString("DepartmentName");
			String fReleaseDated = djson.getString("ReleaseDate");
			String fTradeName = djson.getString("TradeName");
			String fTypeName = djson.getString("LawTypeName");
			String fActualize = djson.getString("ActualizeDate");
			String fInputTime = djson.getString("InputTime");
			String fContent = djson.getString("Content");
			detialBean = new LawDetialBean( fTitle, fLawNo,
					fDepartmentNamed, fReleaseDated, fTradeName, fTypeName,
					fActualize, fInputTime, fContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detialBean;
	}
}
