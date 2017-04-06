package com.cn.law.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;

public class MyCollectActivity extends BaseActivity {
	  private Button backBtn,sendBtn,sendBtn2;
	  private TextView headTxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection_layout);
		intitView();
	}

	private void intitView() {
		backBtn =(Button) findViewById(R.id.back_btn);
		   headTxt=(TextView) findViewById(R.id.head_txt);
		   headTxt.setText(getString(R.string.myshouchang));
	}

	@Override
	public void onClick(View v) {
    switch (v.getId()) {
	case R.id.back_btn:
        finish();
			break;

	default:
		break;
	}
	}

}
