package com.cn.law.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts.Intents;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.cn.law.R;
import com.cn.law.Helper.DBHelper;
import com.cn.law.adapter.LawTypeAdapter;
import com.cn.law.adapter.MainAdpter;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.Constants;
import com.cn.law.bean.MainFastBean;
import com.cn.law.util.HashUtil;
import com.cn.law.util.JsonUtil;
import com.common.internet.AjaxCallBack;
import com.common.internet.FastHttp;
import com.common.internet.ResponseEntity;

public class LawTypeItemItemActivity extends BaseActivity{
	private Intent intent;
	private String sortKey,folderName;
	private ListView typeList;
	private MainAdpter adapter;
	private List<MainFastBean> data = new ArrayList<MainFastBean>();
	private Button backBtn,headBtn;
	private TextView headTxt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.law_type_item_item);
    	intent=getIntent();
    	sortKey=intent.getStringExtra("sortkey");
    	folderName=intent.getStringExtra("folderName");
    	initView();
		getData();
      intent=new Intent();
      Bundle bundle=new Bundle();
      bundle.putSerializable("ldata", (Serializable)data);
       intent.putExtras(bundle);
      setResult(11, intent);
	}
	private void initView() {
   	   backBtn=(Button) findViewById(R.id.back_btn);
 	   headBtn=(Button) findViewById(R.id.head_btn);
 	   headTxt=(TextView) findViewById(R.id.head_txt);
 	   headTxt.setText(getString(R.string.fagui_list));
  	   typeList=(ListView) findViewById(R.id.type_item_item_list);
  	   headBtn.setVisibility(View.GONE);
  	   typeList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg3) {
					Intent intent = new Intent(LawTypeItemItemActivity.this,
							LawDetialActivity.class);
					intent.putExtra("data", position);
					startActivity(intent);
			
			}
		});
	}
	private void getData(){
        HashMap<String, String> params = new HashMap<String, String>();
   	     params.put("sortkey", sortKey);
		 params.put("username","xnn1987");
		 String hash=HashUtil.formhash("xnn1987", sortKey);
		 params.put("hash", hash);
		 FastHttp.ajax(Constants.LAWTYPEITEM, params, new
		 AjaxCallBack() {
		 @Override
		 public void callBack(ResponseEntity entity) {
			 System.out.println(entity.toString());
		 	switch (entity.getStatus()) {
			case FastHttp.result_ok:
				data=JsonUtil.getNewData(entity.getContentAsString());
				adapter = new MainAdpter(data,LawTypeItemItemActivity.this);
				typeList.setAdapter(adapter);
				break;
			default:
				break;
			}
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
