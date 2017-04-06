package com.cn.law.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;

public class AboutActivity extends BaseActivity {
	private Button backBtn,headBtn;
	private TextView headTxt;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.about_layout);
    	   initView();
    }
	private void initView() {
		   backBtn=(Button) findViewById(R.id.back_btn);
		   headTxt=(TextView) findViewById(R.id.head_txt);
		   headTxt.setText(getString(R.string.about_us));
		   backBtn.setOnClickListener(this);
		  
	}
	@Override
	public void onClick(View v) {
       switch (v.getId()) {
	case R.id.back_btn:
		finish();
		break;
	}
	}

}
