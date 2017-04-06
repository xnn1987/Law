package com.cn.law.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;
import com.cn.law.util.XmlParserUtil;
import com.common.internet.AjaxCallBack;
import com.common.internet.FastHttp;
import com.common.internet.InternetConfig;
import com.common.internet.ResponseEntity;

public class SuggestBackActivity extends BaseActivity{
	private Button backBtn,headBtn;
	private TextView headTxt;
	private CheckBox  errorBx,outBx,bugBx,suggestBx;
	private  EditText suggestEdt;
	private List<Map<String, String>> list=new ArrayList<Map<String,String>>();
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.suggest_back_layout);
	    	initView();
	    }
	private void initView() {
		   backBtn=(Button) findViewById(R.id.back_btn);
		   headBtn=(Button) findViewById(R.id.head_btn);
		   headTxt=(TextView) findViewById(R.id.head_txt);
		   errorBx=(CheckBox) findViewById(R.id.fagui_error_box);
		   outBx=(CheckBox) findViewById(R.id.faguti_out_box);
		   bugBx=(CheckBox) findViewById(R.id.fagui_suggest_box);
		   suggestBx=(CheckBox) findViewById(R.id.fagui_suggest_box);
		   suggestEdt=(EditText) findViewById(R.id.suggest_edt);
		   headBtn.setVisibility(View.VISIBLE);
		   headTxt.setText(getString(R.string.suggest_back));
		   headBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.body_bg_2x135));
		   backBtn.setOnClickListener(this);
		   headBtn.setOnClickListener(this);
		   suggestBx.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	
				
			}
		});
		   errorBx.setChecked(false);
		   outBx.setChecked(false);
		   bugBx.setChecked(false);
		   suggestBx.setChecked(false);
		}

	@Override
	public void onClick(View v) {
	switch (v.getId()) {
	case R.id.back_btn:
		finish();
		break;

	case R.id.head_btn:
		gotoUpload();
		break;
	}
	}

	private void gotoUpload() {
		String suggest=suggestEdt.getText().toString();
	 HashMap<String, String> params=new HashMap<String, String>();
	 params.put("", suggest);
	 if (errorBx.isChecked()) {
		params.put("", getString(R.string.fagui_error));
		
	}else if(outBx.isChecked()){
		params.put("", getString(R.string.fagui_out));
	}else if(bugBx.isChecked()){
		params.put("", getString(R.string.fagui_bug));
	}else if(suggestBx.isChecked()){
		params.put("", getString(R.string.fagui_suggest));
	}
	 InternetConfig config=new InternetConfig();
	 config.setName_space("");
	 FastHttp.ajaxWebServer("url", "", params, config, new AjaxCallBack() {
		
		@Override
		public void callBack(ResponseEntity entity) {
			System.out.println(entity.toString());
	      switch (entity.getStatus()) {
		case FastHttp.result_ok:
			try {
				list=XmlParserUtil.main(entity.getContentAsString());
				System.out.println(entity.getContentAsString());
			} catch (XmlPullParserException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
			
		}
	});
	}

}
