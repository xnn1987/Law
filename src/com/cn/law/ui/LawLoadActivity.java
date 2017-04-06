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
import com.cn.law.R.id;
import com.cn.law.Helper.DBHelper;
import com.cn.law.adapter.LawTypeAdapter;
import com.cn.law.adapter.MainAdpter;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.LawTypeBean;
import com.cn.law.bean.MainFastBean;

public class LawLoadActivity extends BaseActivity {
	private Button backBtn,headBtn;
	private TextView headtxt;
	private ListView llist;
	private List<LawTypeBean> ldata;
	private LawTypeBean lBean;
	private LawTypeAdapter adapter;
	private DBHelper dbHelper;
	private String typename;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download_save_layout);
	    dbHelper=new DBHelper(this);
		ldata=dbHelper.getLawType();
		initView();
	}
	private void initView() {
	   backBtn=(Button) findViewById(R.id.back_btn);
	   headBtn=(Button) findViewById(R.id.head_btn);
	   headtxt=(TextView) findViewById(R.id.head_txt);
	   llist=(ListView) findViewById(R.id.download_list);
	   backBtn.setVisibility(View.GONE);
	   headBtn.setVisibility(View.VISIBLE);
		headtxt.setText(getString(R.string.my_xizai));
		headBtn.setText(getString(R.string.delete));
		headBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
		adapter=new LawTypeAdapter(ldata, LawLoadActivity.this);
        llist.setAdapter(adapter);
		llist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long arg3) {
				Intent intent=new Intent(LawLoadActivity.this,LawLoadItemActivity.class);
		           lBean=ldata.get(position);
		           typename=lBean.getFolderName();
			    intent.putExtra("typename", typename);
			    startActivity(intent);
			}
		});
		
	}
	@Override
	public void onClick(View v) {
	}

}
