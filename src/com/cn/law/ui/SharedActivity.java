package com.cn.law.ui;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.Constants;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXImageObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.weibo.TencentRequestListener;
import com.tencent.weibo.TencentWeibo;
import com.weibo.net.SinaRequestListener;
import com.weibo.net.SinaWeibo;
import com.weibo.net.WeiboException;

public class SharedActivity extends BaseActivity{
	private Intent intent;
	private  IWXAPI api;
	private static final int THUMB_SIZE = 150;
	private static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	api=WXAPIFactory.createWXAPI(this, Constants.APP_ID);
    	api.registerApp(Constants.APP_ID);
    	intent=getIntent();
    	int type=intent.getIntExtra("type", 0);
    	switch (type) {
		case 0:
			try {
				// SinaWeibo.shareToweibo(SharedActivity.this, "sdddddddddddd",  requestListener);
				SinaWeibo.shareToweibo(SharedActivity.this, "sdddddddddddd", "/sdcard/s1.png", requestListener);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case 1:
			try {
				TencentWeibo.shareToweibo(SharedActivity.this,"dfgggggggg", "/sdcard/s1.png",requestListener1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 2:
		  shareTextWeiXin();
			shareImg();
			break;
		case 3:
        shareTextFriend();
	    shareImgFriend();
	    break;
		}

    }





	


	//sina回调监听事件
	SinaRequestListener requestListener = new SinaRequestListener() {
		@Override
		public void onError(WeiboException e) {
			e.printStackTrace();
		//	handler.sendEmptyMessage(1);
		}

		@Override
		public void onComplete(String response) {
			System.out.println(response);
			finish();
		//	handler.sendEmptyMessage(0);
		}
	};
	// tencet回调监听事件
	TencentRequestListener requestListener1=new TencentRequestListener() {
		
		@Override
		public void onError(Exception error) {
//			handler.sendEmptyMessage(1);
			
		}
		
		@Override
		public void onComplete(String response) {
			System.out.println(response);
			finish();
		//	handler.sendEmptyMessage(0);
			
		}
	};
	//分享评论WeiXin
	private void shareTextWeiXin() {
	//文字容器实例化
	WXTextObject wxText=new WXTextObject();
	wxText.text="ddddddddddddddd";
	//实体队列的实例化
	WXMediaMessage wxMSg=new WXMediaMessage();
	//将文字wxText放到实体队列里
	wxMSg.mediaObject=wxText;
	//发送实体
	SendMessageToWX.Req req=new SendMessageToWX.Req();

	req.transaction=buildTransation("text");

	req.message=wxMSg;

	req.scene=SendMessageToWX.Req.WXSceneTimeline;

	//发送请求
    api.sendReq(req);
 
	}
	//分享图片WeiXin
	private void shareImg() {
		String path = SDCARD_ROOT + "/s1.png";
		File file = new File(path);
		if (!file.exists()) {
			Toast.makeText(SharedActivity.this,getString(R.string.fagui_img_unexist), Toast.LENGTH_LONG).show();

		}
		
		WXImageObject imgObj = new WXImageObject();
		imgObj.setImagePath(path);
		
		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = imgObj;
		
		Bitmap bmp = BitmapFactory.decodeFile(path);
		Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
		bmp.recycle();
		msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
		
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransation("img");
		req.message = msg;
		req.scene =SendMessageToWX.Req.WXSceneTimeline;
		api.sendReq(req);
		finish();
		
	}

   // 分享评论到微信朋友圈
	private void shareTextFriend() {
		//文字容器实例化
		WXTextObject wxText=new WXTextObject();
		wxText.text="ddddddddddddddd";
		
        //实体队列的实例化
		WXMediaMessage wxMSg=new WXMediaMessage();

		//将文字wxText放到实体队列里
		wxMSg.mediaObject=wxText;

		//发送实体
		SendMessageToWX.Req req=new SendMessageToWX.Req();

		req.transaction=buildTransation("text");

		req.message=wxMSg;
	
		req.scene=SendMessageToWX.Req.WXSceneSession;
	
		//发送请求
	    api.sendReq(req);

	}
	  // 分享图片到微信朋友圈
	private void shareImgFriend() {
		String path = SDCARD_ROOT + "/s1.png";
		File file = new File(path);
		if (!file.exists()) {
			Toast.makeText(SharedActivity.this,getString(R.string.fagui_img_unexist), Toast.LENGTH_LONG).show();

		}
		
		WXImageObject imgObj = new WXImageObject();
		imgObj.setImagePath(path);
		
		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = imgObj;
		
		Bitmap bmp = BitmapFactory.decodeFile(path);
		Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
		bmp.recycle();
		msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
		
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransation("img");
		req.message = msg;
		req.scene =SendMessageToWX.Req.WXSceneSession;
		api.sendReq(req);
		finish();
		
	}


	private String buildTransation(String type) {
		return (type==null)?String.valueOf(System.currentTimeMillis()):type+System.currentTimeMillis();
	}
	@Override
	public void onClick(View v) {
		
	}
}
