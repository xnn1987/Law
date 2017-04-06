package com.cn.law.ui;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.Helper.DBHelper;
import com.cn.law.adapter.LawTypeAdapter;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.LawTypeBean;

public class LawLoadItemActivity extends BaseActivity{
	private Button backBtn,headBtn;
	private TextView headTxt;
	private ListView loadList;
	private DBHelper dbHelper;
	private List<LawTypeBean> ldata;
	private LawTypeAdapter adapter;
	private Intent intent;
	private String typename;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.law_type_item);
	    dbHelper=new DBHelper(this);
		ldata=dbHelper.getLawTitle();
		intent=getIntent();
	   typename=intent.getStringExtra("typename");
		initView();
		
	}

	private void initView() {
   	   backBtn=(Button) findViewById(R.id.back_btn);
 	   headBtn=(Button) findViewById(R.id.head_btn);
 	   headTxt=(TextView) findViewById(R.id.head_txt);
 	   loadList=(ListView) findViewById(R.id.type_list);
 	   headTxt.setText(typename);
 	   backBtn.setOnClickListener(this);
 	   adapter=new LawTypeAdapter(ldata, this);
 	   loadList.setAdapter(adapter);
	   loadList.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long arg3) {
			Intent intent=new Intent(LawLoadItemActivity.this,LawLoadItemItemActivity.class);
		    intent.putExtra("data",position);
		    startActivity(intent);
			
		}
	});
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
