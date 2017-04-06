package com.cn.law.ui;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.cn.law.base.BaseActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tencent.weibo.TencentWeibo;
import com.tencent.weibo.TencentWeiboListener;
import com.tencent.weibo.oauthv2.OAuthV2;
import com.weibo.net.DialogError;
import com.weibo.net.SinaWeibo;
import com.weibo.net.SinaWeiboListener;
import com.weibo.net.WeiboException;

public class AuthorizActivity extends BaseActivity {
   private WebView webView;
   private Intent intent;
   private Tencent mTencent;
   private static final String APP_ID = "222222";
   private static final String SCOPE = "all";
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	final Context context=this.getApplicationContext();
    	int type=getIntent().getIntExtra("type", 0);
    	mTencent=Tencent.createInstance(APP_ID, context);
    	switch (type) {
		case 0:
        if (!mTencent.isSessionValid()) {
		IUiListener listener=new BaseUiListener(){

			@Override
			protected void doComplete(JSONObject values) {
				super.doComplete(values);
				System.out.println(values);
				 finish();
			}		
		};
		mTencent.login(this,SCOPE,listener);
        }
        
			break;

		case 1:
			try {
			    SinaWeibo weibo=new SinaWeibo();
		         webView=weibo.authorizeByView(this, new OauthDialogListener());
			} catch (WeiboException e) {
				e.printStackTrace();
			}

			webView.setBackgroundColor(0);
			setContentView(webView);
			break;
    	case 2:
			try {
				webView=TencentWeibo.authorizeByView(this, new TencetOauthListener() );
			} catch (Exception e) {
				e.printStackTrace();
			}

			webView.setBackgroundColor(0);
			setContentView(webView);
			break;
    		
		}

    }

	//sina授权回调方法
	private class OauthDialogListener implements SinaWeiboListener{

		@Override
		public void onCancel() {
			
		}
		@Override
		public void onComplete(Bundle bundle) {
		System.out.println(bundle);
			finish();
		}

		@Override
		public void onError(DialogError arg0) {
		
			
		}

		@Override
		public void onWeiboException(WeiboException arg0) {
		
			
		}
		
	}
	@Override
	public void onClick(View v) {

	}
	//tencet授权回调方法
	private class TencetOauthListener implements TencentWeiboListener{

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onComplete(OAuthV2 bundle) {
			System.out.println(bundle);
			finish();
		}

		@Override
		public void onWeiboException(Exception arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	//qq登陆回调方法
	private class BaseUiListener  implements IUiListener{

		@Override
		public void onCancel() {
			
			
		}

		@Override
		public void onComplete(JSONObject response) {
		doComplete(response);
			
		}

		protected void doComplete(JSONObject values) {
		
			
		}

		@Override
		public void onError(UiError arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
