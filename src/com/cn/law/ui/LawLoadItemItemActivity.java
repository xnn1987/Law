package com.cn.law.ui;

import java.util.ArrayList;
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
import com.cn.law.adapter.MainAdpter;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.MainFastBean;

public class LawLoadItemItemActivity extends BaseActivity{
	private ListView lList;
	private MainAdpter adapter;
	private List<MainFastBean> data = new ArrayList<MainFastBean>();
	private Button backBtn,headBtn;
	private TextView headTxt;
	private DBHelper dbHelper;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.law_type_item_item);
        	dbHelper=new DBHelper(this);
        	data=dbHelper.getLawList();
        	initView();
        }
	private void initView() {
	   	   backBtn=(Button) findViewById(R.id.back_btn);
	 	   headBtn=(Button) findViewById(R.id.head_btn);
	 	   headTxt=(TextView) findViewById(R.id.head_txt);
	 	   headTxt.setText(getString(R.string.fagui_list));
	  	   lList=(ListView) findViewById(R.id.type_item_item_list);
	  	   backBtn.setOnClickListener(this);
	  	   adapter=new MainAdpter(data, this);
	  	   lList.setAdapter(adapter);
	  	   lList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long arg3) {
				Intent intent = new Intent(LawLoadItemItemActivity.this,
						LawLoadItemDetialActivity.class);
				intent.putExtra("data", position);
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
