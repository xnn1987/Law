package com.cn.law.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.Helper.DBHelper;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.LawDetialBean;

public class LawLoadItemDetialActivity extends BaseActivity{
	private LawDetialBean data;
	private Button backBtn,headBtn;
	private TextView headTxt;
	private DBHelper dbHelper;
	private ImageView shixiaoImg;
	private TextView titleTxt, lawNoTxt, departmentTxt, releaseTxt, tradeTxt,
	typeTxt, actualizeTxt, inputTxt, contentTxt;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.downloaddetial_layout);	
    	dbHelper=new DBHelper(this);
    	data=dbHelper.getLaw();
    	initView();
    	setView();
    }
	private void initView() {
		backBtn =(Button) findViewById(R.id.back_btn);
		titleTxt = (TextView) findViewById(R.id.law_detail_title);
		lawNoTxt = (TextView) findViewById(R.id.fawen_wenhao_txt);
		departmentTxt = (TextView) findViewById(R.id.fawen_bumen_txt);
		releaseTxt = (TextView) findViewById(R.id.fawen_time_txt);
		tradeTxt = (TextView) findViewById(R.id.fawen_hangye_txt);
		typeTxt = (TextView) findViewById(R.id.fawen_style_txt);
		actualizeTxt = (TextView) findViewById(R.id.start_time_txt);
		inputTxt = (TextView) findViewById(R.id.bianji_time_txt);
		contentTxt = (TextView) findViewById(R.id.fawen_detail_txt);
		backBtn.setOnClickListener(this);
	}
	private void setView() {
		titleTxt.setText(data.getTitle());
		lawNoTxt.setText(data.getfLawNo());
		departmentTxt.setText(data.getfDepartmentName());
		releaseTxt.setText(data.getfReleaseDate());
		tradeTxt.setText(data.getfTradeName());
		typeTxt.setText(data.getfTypeName());
		actualizeTxt.setText(data.getfActualize());
		inputTxt.setText(data.getfInputTime());
		contentTxt.setText(data.getfContent());
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
