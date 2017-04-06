package com.cn.law.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.Constants;
import com.cn.law.util.Util;
import com.common.internet.AjaxCallBack;
import com.common.internet.FastHttp;
import com.common.internet.ResponseEntity;

public class AppStart extends BaseActivity {
	private boolean isFirstIn = true;
	private int isFirstInversonCode = 0;
	private int screenHeight;
	private int screenWidth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = View.inflate(this, R.layout.start, null);
		setContentView(view);
		getScreenHW();
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(3000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				isFirst();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});
	}

	private void getScreenHW() {
		DisplayMetrics dm = new DisplayMetrics();
		dm = this.getApplicationContext().getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		Util.getApp().setScreenWidth(screenWidth);
		Util.getApp().setScreenHeight(screenHeight);

	}

	// 判断是否第一次登陆
	private void isFirst() {
		SharedPreferences sp = getSharedPreferences(Constants.APPNAME, 0);
		isFirstIn = sp.getBoolean("isFirstIn", true);
		Intent intent = null;
		if (true == isFirstIn) {
			intent = new Intent(AppStart.this, NewGuideActivity.class);
		} else {
			intent = new Intent(AppStart.this, HomeActivity.class);
		}
		startActivity(intent);
		finish();
	}

	@Override
	public void onClick(View v) {

	}
}
