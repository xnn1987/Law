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
import android.widget.EditText;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;
import com.cn.law.util.XmlParserUtil;
import com.common.internet.AjaxCallBack;
import com.common.internet.FastHttp;
import com.common.internet.InternetConfig;
import com.common.internet.ResponseEntity;

public class UpdatePSWActivity extends BaseActivity {
	private Button backBtn,headBtn;
	private TextView headTxt;
	private EditText oldpwEdt, newpwEdt,newRepwEdt;
	private List<Map<String, String>> list=new ArrayList<Map<String,String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatepsw_layout);
		initView();
	}

	private void initView() {
		   backBtn=(Button) findViewById(R.id.back_btn);
		   headBtn=(Button) findViewById(R.id.head_btn);
		   headTxt=(TextView) findViewById(R.id.head_txt);
		   headBtn.setVisibility(View.VISIBLE);
		   headTxt.setText(getString(R.string.update_psw));
		   headBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.body_bg_2x135));
		   backBtn.setOnClickListener(this);
		   headBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
       switch (v.getId()) {
	case R.id.back_btn:
		finish();
		break;

	case R.id.head_btn:
		gotoUpdate();
		break;
	}
	}

	private void gotoUpdate() {
	   String oldpw=oldpwEdt.getText().toString();
	   String newpw=newpwEdt.getText().toString();
	   String newrepw=newRepwEdt.getText().toString();
	   HashMap<String, String> params=new HashMap<String, String>();
	   params.put("", oldpw);
	   params.put("", newpw);
	   InternetConfig config=new InternetConfig();
	   config.setName_space("");
	   FastHttp.ajaxWebServer("", "", params, config, new AjaxCallBack() {
		
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
				break;

			default:
				break;
			}
			
		}
	});
		
	}

}
