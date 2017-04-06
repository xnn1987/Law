package com.cn.law.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;
import com.cn.law.util.New_Guide_Interface;
import com.cn.law.util.New_guide_ScrollLayout;

public class HelpActivity extends BaseActivity implements New_Guide_Interface{
	private New_guide_ScrollLayout mScrollLayout;
	private int count;
	private int currentItem;
	private Button backBtn,startBtn;
	private TextView headTxt;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.helper_layout);
		initView();
    }
	private void initView() {
		
			mScrollLayout = (New_guide_ScrollLayout) findViewById(R.id.ScrollLayout);
			startBtn = (Button) findViewById(R.id.startBtn);
			backBtn=(Button) findViewById(R.id.back_btn);
			headTxt=(TextView) findViewById(R.id.head_txt);
			startBtn.setOnClickListener(this);
			count = mScrollLayout.getChildCount();
			mScrollLayout.SetOnViewChangeListener(this);
			backBtn.setOnClickListener(this);
			headTxt.setText(getString(R.string.my_help));
		
	}
	@Override
	public void onClick(View v) {
switch (v.getId()) {
case R.id.startBtn:
	Intent intent = new Intent(HelpActivity.this,
			MoreActivity.class);
	startActivity(intent);
	finish();
	break;

case R.id.back_btn:
	finish();
	break;
}
	}
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
