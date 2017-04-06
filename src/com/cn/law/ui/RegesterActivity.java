package com.cn.law.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.Constants;
import com.cn.law.util.XmlParserUtil;
import com.common.internet.AjaxCallBack;
import com.common.internet.FastHttp;
import com.common.internet.InternetConfig;
import com.common.internet.ResponseEntity;

public class RegesterActivity extends BaseActivity {
	private Button backBtn,headBtn;
	private TextView headTxt;
	private EditText userEdt,pswEdt,repswEdt,emEdt;
	private List<Map<String, String>> list=new ArrayList<Map<String,String>>();
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.register_layout);
    	initView();
    }
	private void initView() {
		   backBtn=(Button) findViewById(R.id.back_btn);
		   headBtn=(Button) findViewById(R.id.head_btn);
		   headTxt=(TextView) findViewById(R.id.head_txt);
		   userEdt=(EditText) findViewById(R.id.user_edt);
		   emEdt=(EditText) findViewById(R.id.email_edt);
		   pswEdt=(EditText) findViewById(R.id.pass_edt);
		   repswEdt=(EditText) findViewById(R.id.repsw_edt);
		   backBtn.setOnClickListener(this);
		   headBtn.setOnClickListener(this);
		   headTxt.setText(getString(R.string.regester));
		   headBtn.setVisibility(View.VISIBLE);
		   headBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.body_bg_2x135));
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_btn:
			finish();
			break;
		case R.id.head_btn:
			gotoRegester();
//			Intent intent=new Intent(RegesterActivity.this,MainActivity.class);
//			startActivity(intent);
//			finish();
			break;

		}
	}
	private void gotoRegester() {
		String username=userEdt.getText().toString();
		String psword=pswEdt.getText().toString();
		String repsword=pswEdt.getText().toString();
		String email=emEdt.getText().toString();
		Map<String, String> params=new HashMap<String, String>();
		params.put("email", email);	
		params.put("password", psword);	
		params.put("username", username);
		System.out.println("PPPPP"+params);
		InternetConfig config=new InternetConfig();
		config.setName_space("http://soap-esnai");
		String regester="user_register";
		FastHttp.ajaxWebServer(Constants.LOGIN,regester,config,new AjaxCallBack() {
			
			@Override
			public void callBack(ResponseEntity entity) {
				System.out.println(entity.toString());
              switch (entity.getStatus()) {
			case FastHttp.result_ok:
				 try {
					list=XmlParserUtil.main(entity.getContentAsString());
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("sssssssss"+list);
				break;
			default:
				break;
			}
				
			}
		});
	}

}
