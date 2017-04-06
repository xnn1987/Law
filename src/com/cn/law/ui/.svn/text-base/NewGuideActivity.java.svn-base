package com.cn.law.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cn.law.R;
import com.cn.law.bean.Constants;
import com.cn.law.util.New_Guide_Interface;
import com.cn.law.util.New_guide_ScrollLayout;

public class NewGuideActivity extends Activity implements New_Guide_Interface {
	private New_guide_ScrollLayout mScrollLayout;
	private int count;
	private int currentItem;
	private Button startBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newguide_layout);
		initView();

	}

	private void initView() {
		mScrollLayout = (New_guide_ScrollLayout) findViewById(R.id.ScrollLayout);
		startBtn = (Button) findViewById(R.id.startBtn);
		startBtn.setOnClickListener(listener);
		count = mScrollLayout.getChildCount();
		mScrollLayout.SetOnViewChangeListener(this);

	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			SharedPreferences sp = getSharedPreferences(Constants.APPNAME, 0);
			Editor edit = sp.edit();
			edit.putBoolean("isFirstIn", false);
			edit.commit();
			Intent intent = new Intent(NewGuideActivity.this,
					HomeActivity.class);
			startActivity(intent);
			finish();

		}
	};

	@Override
	public void OnViewChange(int position) {
		setcurrentPoint(position);
	}

	private void setcurrentPoint(int position) {
		if (position < 0 || position > count - 1 || currentItem == position) {
			return;
		}
		currentItem = position;
	}
}
