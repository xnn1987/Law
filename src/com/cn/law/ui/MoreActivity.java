package com.cn.law.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;

public class MoreActivity extends BaseActivity {
	private RelativeLayout  centerRL,shouchangRL,shezhiRL,xiugaiRL,fankuiRL
	       ,guiyuRL,tuichuRL,zhuxiaoRL;
	private Intent intent=new Intent();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_layout);
		initView();
	}

	private void initView() {
	centerRL=(RelativeLayout) findViewById(R.id.center_layout);
	shouchangRL=(RelativeLayout) findViewById(R.id.shouchang_layout);
	shezhiRL=(RelativeLayout) findViewById(R.id.shezhi_layout);
	xiugaiRL=(RelativeLayout) findViewById(R.id.update_layout);
	fankuiRL=(RelativeLayout) findViewById(R.id.fankui_layout);
	guiyuRL=(RelativeLayout) findViewById(R.id.guanyu_layout);
	tuichuRL=(RelativeLayout) findViewById(R.id.tuichu_layout);
	zhuxiaoRL=(RelativeLayout) findViewById(R.id.zhuxiao_layout);
	centerRL.setOnClickListener(this);
	shouchangRL.setOnClickListener(this);
	shezhiRL.setOnClickListener(this);
	xiugaiRL.setOnClickListener(this);
	fankuiRL.setOnClickListener(this);
	guiyuRL.setOnClickListener(this);
	tuichuRL.setOnClickListener(this);
	zhuxiaoRL.setOnClickListener(this);
	
	}

	@Override
	public void onClick(View v) {
           switch (v.getId()) {
		case R.id.center_layout:
			intent.setClass(MoreActivity.this, MyAccountActivity.class);
			startActivity(intent);
			break;
		case R.id.shouchang_layout:
			intent.setClass(MoreActivity.this, MyCollectActivity.class);
			startActivity(intent);
			break;
		case R.id.shezhi_layout:
			intent.setClass(MoreActivity.this, UpdatePSWActivity.class);
			startActivity(intent);
			break;
		case R.id.update_layout:
             intent.setClass(MoreActivity.this, SuggestBackActivity.class);
         	startActivity(intent);
			break;
		case R.id.fankui_layout:
            intent.setClass(MoreActivity.this, HelpActivity.class);
        	startActivity(intent);	
			break;
		case R.id.guanyu_layout:
            intent.setClass(MoreActivity.this, AboutActivity.class);
        	startActivity(intent);
			break;
		case R.id.tuichu_layout:
			finish();
			break;
		case R.id.zhuxiao_layout:
			finish();
			break;
			
		}
	}

}
