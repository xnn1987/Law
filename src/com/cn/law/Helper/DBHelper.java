package com.cn.law.Helper;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import com.cn.law.bean.LawDetialBean;
import com.cn.law.bean.LawTypeBean;
import com.cn.law.bean.MainFastBean;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	public DBHelper(Context context) {
		super(context, "faiguisave.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
    String sql= "create table if not exists fagui(_id integer primary key autoincrement,title varchar(20) ,lawno varchar(20),department varchar(20),release varchar(20),trade varchar(20),ftype varchar(20),actualize varchar(20),finput varchar(20),content varchar(100))";
		db.execSQL(sql);
    String sql1="create table if not exists faguitype(_fid integer primary key autoincrement,type varchar(20))";
        db.execSQL(sql1);
        String sql2="create table if not exists faguititle(_tid integer primary key autoincrement,title varchar(20))";
        db.execSQL(sql2);
    String sql3="create table if not exists faguilist(_lid integer primary key autoincrement, name varchar(20),department varchar(20),lawno varchar(20),release varchar(20))";
        db.execSQL(sql3);
    String sql4="create table if not exists faguiitem(_iid integer primary key autoincrement,item varchar(20) ,lawno varchar(20),department varchar(20),release varchar(20),trade varchar(20),ftype varchar(20),actualize varchar(20),finput varchar(20),content varchar(100))";
    db.execSQL(sql4);
	}
	@Override
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	
	}
/**
 * 法规收藏
 * */
public boolean save(LawDetialBean lawBean){
	try {
		System.out.println("-------------------------------------------------");
		System.out.println(lawBean.toString());
		SQLiteDatabase db=this.getWritableDatabase();
		String sql="insert into fagui(title,lawno,department,release ,trade,ftype,actualize,finput ,content)" +
				"values(?,?,?,?,?,?,?,?,?)";
		db.execSQL(sql, new String[]{lawBean.getTitle(),lawBean.getfLawNo(),lawBean.getfDepartmentName(),lawBean.getfReleaseDate(),
				lawBean.getfTradeName(),lawBean.getfTypeName(),lawBean.getfActualize(),lawBean.getfInputTime(),lawBean.getfContent()});
	    db.close();
	    return true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;	
}
/**
 * 法规类型下载
 * */
public boolean downloadType(String string ){
	 try {
		SQLiteDatabase db=this.getReadableDatabase();
		String sql="insert into faguitype(type) values(?)";
		db.execSQL(sql,new String[]{string});
		db.close();
		return true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
	
}
/**
 * 法规名称下载
 * */
public boolean downloadTitle(LawTypeBean lBean){
	 try {
		SQLiteDatabase db=this.getReadableDatabase();
		String sql="insert into faguititle(title) values(?)";
		db.execSQL(sql,new String[]{lBean.getFolderName()});
		db.close();
		return true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
	
}
/**
 * 法规列表下载
 * */
public boolean downloadList(MainFastBean mBean){
	 try {
		SQLiteDatabase db=this.getReadableDatabase();
		String sql="insert into faguilist(name,department,lawno,release) values(?,?,?,?)";
		db.execSQL(sql,new String[]{mBean.getTitle(),mBean.getfDepartmentName(),mBean.getfLawNo(),mBean.getfReleaseDate()});
		db.close();
		return true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}
/**
 * 法规详情
 * */
public boolean downloadItem(LawDetialBean lawBean){
	 try {
		SQLiteDatabase db=this.getWritableDatabase();
			String sql="insert into faguiitem(item,lawno,department,release ,trade,ftype,actualize,finput ,content)" +
					"values(?,?,?,?,?,?,?,?,?)";
			db.execSQL(sql, new String[]{lawBean.getTitle(),lawBean.getfLawNo(),lawBean.getfDepartmentName(),lawBean.getfReleaseDate(),
			    lawBean.getfTradeName(),lawBean.getfTypeName(),lawBean.getfActualize(),lawBean.getfInputTime(),lawBean.getfContent()});
		db.close();
		return true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}
/**
 * 法规类型搜索
 * */
public List<LawTypeBean> getLawType(){
	List<LawTypeBean>  ldata=new ArrayList<LawTypeBean>();
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor c=db.rawQuery("select * from faguitype",null);
	while (c!=null && c.moveToNext()) {
		LawTypeBean lBean=new LawTypeBean();
		  lBean.setFolderName(c.getString(c.getColumnIndex("type")));
		  ldata.add(lBean);
	}
	c.close();
	db.close();
	return ldata;	
}
/**
 * 法规名称搜索
 * */
public List<LawTypeBean> getLawTitle(){
	List<LawTypeBean>  ldata=new ArrayList<LawTypeBean>();
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor c=db.rawQuery("select * from faguititle",null);
	while (c!=null && c.moveToNext()) {
		LawTypeBean lBean=new LawTypeBean();
		  lBean.setFolderName(c.getString(c.getColumnIndex("title")));
		  ldata.add(lBean);
	}
	c.close();
	db.close();
	return ldata;	
}
/**
 * 法规列表搜索
 * */
public List<MainFastBean> getLawList(){
	List<MainFastBean>  mdata=new ArrayList<MainFastBean>();
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor c=db.rawQuery("select * from faguilist",null);
	while (c!=null && c.moveToNext()) {
		MainFastBean mBean=new MainFastBean();
		mBean.setTitle(c.getString(c.getColumnIndex("name")));
		mBean.setfDepartmentName(c.getString(c.getColumnIndex("department")));
		mBean.setfLawNo(c.getString(c.getColumnIndex("lawno")));
		mBean.setfReleaseDate(c.getString(c.getColumnIndex("release")));
		mdata.add(mBean);
	}
	c.close();
	db.close();
	return mdata;	
}
/**
 * 法规详情搜索
 * */
public LawDetialBean getLaw(){
	SQLiteDatabase db=this.getReadableDatabase();
	LawDetialBean mBean=new LawDetialBean();
	Cursor c=db.rawQuery("select * from faguiitem ",null);
	while (c!=null && c.moveToNext()) {
        mBean.setTitle(c.getString(c.getColumnIndex("item")));
        mBean.setfLawNo(c.getString(c.getColumnIndex("lawno")));
        mBean.setfDepartmentName(c.getString(c.getColumnIndex("department")));
        mBean.setfReleaseDate(c.getString(c.getColumnIndex("release")));
        mBean.setfTradeName(c.getString(c.getColumnIndex("trade")));
        mBean.setfTypeName(c.getString(c.getColumnIndex("ftype")));
        mBean.setfActualize(c.getString(c.getColumnIndex("actualize")));
        mBean.setfInputTime(c.getString(c.getColumnIndex("finput")));
        mBean.setfContent(c.getString(c.getColumnIndex("content")));

	}
	c.close();
	db.close();
	return mBean;	
}
}
