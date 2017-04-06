package com.cn.law.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.cn.law.Helper.DBHelper;
import com.cn.law.bean.LawDetialBean;
import com.cn.law.bean.MainFastBean;
import com.cn.law.util.JsonUtil;
import com.cn.law.util.ZipUtil;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownLoadService extends Service{	
	public static int position=0;
	public static File file;
	public static String zippath,path;
	public static List<LawDetialBean> lData=new ArrayList<LawDetialBean>();
	public static LawDetialBean lBean;
	public static MainFastBean mBean;
	private Thread thread;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		
		super.onCreate();
	}
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		position=intent.getIntExtra("position", 0);
	    thread=new Thread(run);
		switch (position) {
		case 0:
			zippath="mnt/sdcard/fz0.zip";
			path="mnt/sdcard/f0";        
			break;

		case 1:
			zippath="mnt/sdcard/fz1.zip";
			path="mnt/sdcard/f1"; 
			break;
		case 2:
			zippath="mnt/sdcard/fz2.zip";
			path="mnt/sdcard/f2"; 
			break;	
		case 3:
			zippath="mnt/sdcard/fz3.zip";
			path="mnt/sdcard/f3"; 
			break;	
		case 4:
			zippath="mnt/sdcard/fz4.zip";
			path="mnt/sdcard/f4"; 
			break;	
		case 5:
			zippath="mnt/sdcard/fz5.zip";
			path="mnt/sdcard/f5"; 
			break;	
		case 6:
			zippath="mnt/sdcard/fz6.zip";
			path="mnt/sdcard/f6"; 
			break;	
		case 7:
			zippath="mnt/sdcard/fz7.zip";
			path="mnt/sdcard/f7"; 
			break;	
		case 8:
			zippath="mnt/sdcard/fz8.zip";
			path="mnt/sdcard/f8"; 
			break;	
		case 9:
			zippath="mnt/sdcard/fz9.zip";
			path="mnt/sdcard/f9"; 
			break;	
		case 10:
			zippath="mnt/sdcard/fz10.zip";
			path="mnt/sdcard/f10"; 
			break;	
		case 11:
			zippath="mnt/sdcard/fz11.zip";
			path="mnt/sdcard/f11"; 
			break;	
		case 12:
			zippath="mnt/sdcard/fz12.zip";
			path="mnt/sdcard/f12"; 
			break;	
		case 13:
			zippath="mnt/sdcard/fz13.zip";
			path="mnt/sdcard/f13"; 
			break;
		case 14:
			zippath="mnt/sdcard/fz14.zip";
			path="mnt/sdcard/f14"; 
			break;
		case 15:
			zippath="mnt/sdcard/fz15.zip";
			path="mnt/sdcard/f15"; 
			break;
		case 16:
			zippath="mnt/sdcard/fz16.zip";
			path="mnt/sdcard/f16"; 
			break;
		}
		
		  thread.start();
	}
private  Runnable run =new Thread(){
	@Override
	public void run() {
	    getData(file,path);
		super.run();
		
	}
	
	
};
	private void getData(File file,String string) {

		    try {
		        file=new File(zippath);
				File[] files=ZipUtil.unzip(file,path ,"law.client");
				for (int i = 0; i < files.length; i++) {
					 System.out.println(files[i]);
				    FileInputStream in=new FileInputStream(files[i]);
					StringBuilder builder=new StringBuilder();
					BufferedReader buffers=new BufferedReader(new InputStreamReader(in));
					for (String s=buffers.readLine();s!=null;s=buffers.readLine()) {
						builder.append(s);
					}
					String ss=builder.toString();
					System.out.println(ss);
					lBean=JsonUtil.getLawContentFromNative(ss);
					System.out.println("1------------------"+lBean.toString());
					//mBean=new MainFastBean(lBean.getTitle(), lBean.getfDepartmentName(), lBean.getfLawNo(),lBean.getfReleaseDate());
					System.out.println("2------------------"+mBean.getfDepartmentName().toString());
					insertToDBHelper();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}		
	}
	//插入数据库
	private void insertToDBHelper() {
       DBHelper helper=new DBHelper(this);
       System.out.println("downloadList:"+helper.downloadList(mBean));
       // &&helper.downloadList(mBean)
      if (helper.downloadItem(lBean)) {
		System.out.println("ok!!!");
	}else{
		System.out.println("fail!!!");
	}
		
	}
	
}
