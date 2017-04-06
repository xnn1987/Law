package com.cn.law.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.cn.law.bean.MainFastBean;

public class XmlParserUtil {

	public static List<Map<String, String>> main(String string) throws XmlPullParserException,
			IOException {
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		Map<String, String> map=new HashMap<String, String>();
		ByteArrayInputStream in=new ByteArrayInputStream(string.getBytes());
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(in, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:

				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("status")) {
					String status=parser.nextText();
					map.put("status", status);
					list.add(map);
					System.out.println("状态"+status);
				}
				else 
					if (parser.getName().equals("uid_authcode")) {
					String uid = parser.nextText();
					map.put("uid", uid);
					list.add(map);
					System.out.println("姓名:" + uid );
				} else if (parser.getName().equals("username_authcode")) {
					String username= parser.nextText();
					map.put("username", username);
					list.add(map);
					System.out.println("年龄:" + username);
				}else if(parser.getName().equals("error")){
					String error= parser.nextText();
					map.put("error", error);
					list.add(map);
					System.out.println("年龄:" + error);
				}
				break;		
			case XmlPullParser.END_TAG:
				break;	
			}
			eventType = parser.next();
		
		}
		return list;
	}
}
