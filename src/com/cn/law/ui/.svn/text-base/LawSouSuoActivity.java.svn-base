package com.cn.law.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.MainFastBean;

public class LawSouSuoActivity extends BaseActivity {
	private EditText titleEdt,allEdt,numberEdt;
	private  Button backBtn,searchBtn;
	private TextView headTxt;
	private List<MainFastBean> data = new ArrayList<MainFastBean>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		initView();
	}

	private void initView() {
      titleEdt=(EditText) findViewById(R.id.title_edt);
      allEdt=(EditText) findViewById(R.id.quanwen_search_edt);
      numberEdt=(EditText) findViewById(R.id.wenhao_edt);
      backBtn=(Button) findViewById(R.id.back_btn);
      searchBtn=(Button) findViewById(R.id.start_search_btn);
      headTxt=(TextView) findViewById(R.id.head_txt);
      backBtn.setVisibility(View.GONE);
      headTxt.setText(getString(R.string.shousuo));
	  searchBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
  switch (v.getId()) {
    case R.id.start_search_btn:
	    gotoSearch();
	   // finish();
	break;
}
	}

	private void gotoSearch() {
		String title=titleEdt.getText().toString()+"";
		String fullindex=allEdt.getText().toString()+"";
		String lawno=numberEdt.getText().toString()+"";
		  Intent intent=new Intent(LawSouSuoActivity.this,LawSousuoContentActivity.class);
		  intent.putExtra("title", title);
		  intent.putExtra("fullindex", fullindex);
		  intent.putExtra("lawno", lawno);
		  startActivity(intent);
 	}
}
