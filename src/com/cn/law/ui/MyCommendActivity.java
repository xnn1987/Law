package com.cn.law.ui;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.CommendBean;
import com.cn.law.bean.Constants;
import com.cn.law.util.HashUtil;
import com.cn.law.util.JsonUtil;
import com.cn.law.util.MD5Util;
import com.common.internet.AjaxCallBack;
import com.common.internet.FastHttp;
import com.common.internet.ResponseEntity;

public class MyCommendActivity extends BaseActivity {
	private Button backBtn,commendBtn;
	private TextView commendTxt;
	private EditText commendEdt;
	private HashMap<String, String> params;
	@SuppressWarnings("unused")
	private Intent intent;
	private int data;
	private CommendBean cData;
	private String fError;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycommend_layout);
		InitView();
		intent=getIntent();
		data=intent.getIntExtra("data", 0);
	}

	private void InitView() {
	     backBtn=(Button) findViewById(R.id.back_btn);
	     commendBtn=(Button) findViewById(R.id.head_btn);
	     commendTxt=(TextView) findViewById(R.id.head_txt);
	     commendEdt=(EditText) findViewById(R.id.commend_head_edt);
	     backBtn.setOnClickListener(this);
	     commendBtn.setOnClickListener(this);
	     commendBtn.setText(getString(R.string.send));
	     commendBtn.setVisibility(View.VISIBLE);
	     commendTxt.setText(getString(R.string.commend));
	     
	}

	@Override
	public void onClick(View v) {
      switch (v.getId()) {
	case R.id.back_btn:
		finish();
		break;
	case R.id.head_btn:

		params=new HashMap<String, String>();
		String content=commendEdt.getText().toString();
		params.put("username","xnn1987");
		params.put("lawid", "135429");
		params.put("comment", content);
		String hash=HashUtil.formhash("xnn1987", "135429");
		params.put("hash",hash);
		//byte[] buffer=new byte[1024];
        
	//  System.out.println("i="+i);

//		params.put("hash",String.valueOf(params.hashCode()));
		FastHttp.ajax(Constants.COMMENTURL,params, new AjaxCallBack() {
			
			@Override
			public void callBack(ResponseEntity entity) {
				 System.out.println("POST："+entity.toString());
				switch (entity.getStatus()) {
				case FastHttp.result_ok:
		          cData=JsonUtil.getError(entity.getContentAsString());
		          fError=cData.getFerror();
		          if (fError=="false") {
		        	  Toast.makeText(MyCommendActivity.this, getString(R.string.commend_success), 1).show();
				}else{
					  Toast.makeText(MyCommendActivity.this, getString(R.string.commend_fail), 2).show();
				}
					
					break;
				default:
					break;
				}
				
			}
		});
		break;
	}
	}
}
