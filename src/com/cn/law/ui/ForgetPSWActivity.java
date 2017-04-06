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
import android.widget.Toast;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;
import com.cn.law.util.XmlParserUtil;
import com.common.internet.AjaxCallBack;
import com.common.internet.FastHttp;
import com.common.internet.InternetConfig;
import com.common.internet.ResponseEntity;

public class ForgetPSWActivity extends BaseActivity {
	private Button backBtn,forgetBtn,headBtn;
	private TextView headTxt;
	private EditText forgetEdt;
	private List<Map<String, String>> list=new ArrayList<Map<String,String>>();
    private Map<String, String> map=new HashMap<String, String>();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.forget_psw_layout);
    	initView();
    }
	private void initView() {
		   backBtn=(Button) findViewById(R.id.back_btn);
		   headBtn=(Button) findViewById(R.id.head_btn);
		   headTxt=(TextView) findViewById(R.id.head_txt);
		   forgetBtn=(Button) findViewById(R.id.forget_psw_btn);
		   headBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.body_bg_2x135));
		   headBtn.setOnClickListener(this);
		   headTxt.setText(getString(R.string.forget_psw));
		   backBtn.setOnClickListener(this);
		   forgetBtn.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
    switch (v.getId()) {
	case R.id.back_btn:
		finish();
		break;
	case R.id.head_btn:
		Toast.makeText(ForgetPSWActivity.this, "提交成功", 1).show();
		break;
		
	case R.id.forget_psw_btn:
		gotoUpdate();
		break;
	}
	}
private void gotoUpdate(){
	String forget=forgetEdt.getText().toString();
	HashMap<String, String> params=new HashMap<String, String>();
	params.put("email",forget);
	InternetConfig config=new InternetConfig();
	config.setName_space("");
	FastHttp.ajaxWebServer("url","", params, config, new AjaxCallBack() {
		
		@Override
		public void callBack(ResponseEntity entity) {
			switch (entity.getStatus()) {
			case FastHttp.result_ok:
				try {
					list=XmlParserUtil.main(entity.getContentAsString());
					//map=list.
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent=new Intent(ForgetPSWActivity.this,UpdatePSWActivity.class);
				startActivity(intent);
				finish();
				break;

			default:
				break;
			}
		}
	});

}
}
