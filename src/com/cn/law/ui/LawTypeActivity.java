package com.cn.law.ui;

import java.util.HashMap;
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
import com.cn.law.adapter.LawTypeAdapter;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.Constants;
import com.cn.law.bean.LawTypeBean;
import com.cn.law.util.HashUtil;
import com.cn.law.util.JsonUtil;
import com.common.internet.AjaxCallBack;
import com.common.internet.FastHttp;
import com.common.internet.ResponseEntity;

public class LawTypeActivity extends BaseActivity {
	private ListView typeList;
	private Button backBtn,headBtn;
	private TextView headTxt;
	private String sortkey,folderName;
	private List<LawTypeBean> data;
	private LawTypeAdapter adapter;
	LawTypeBean lBean;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lawtype_layout);
	    initView();
		getData();
	    
	}

	private void initView() {
	   backBtn=(Button) findViewById(R.id.back_btn);
	   headBtn=(Button) findViewById(R.id.head_btn);
	   headTxt=(TextView) findViewById(R.id.head_txt);
	   typeList=(ListView) findViewById(R.id.fagui_type_list);
	   backBtn.setVisibility(View.GONE);
	   headTxt.setText(getString(R.string.fagui_type));
	   typeList.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position,
				long arg3) {
			
			Intent intent=new Intent(LawTypeActivity.this,LawTypeItemActivity.class);
		    lBean=data.get(position);
		    folderName=lBean.getFolderName();
			sortkey=lBean.getShortkey();
			intent.putExtra("folderName",folderName);
			intent.putExtra("sortkey",sortkey);
			intent.putExtra("position", position);
			System.out.println("PPPPPPPPPP"+position);
			startActivity(intent);
		}
	});
	}
       private void getData(){
    	   HashMap<String, String> params = new HashMap<String, String>();
   		 params.put("username","xnn1987");
   		 params.put("sortkey", "");
   		 String hash=HashUtil.formhash("xnn1987", "");
   		 params.put("hash", hash);
   		 FastHttp.ajax(Constants.LAWTYPE,params, new
   		 AjaxCallBack() {
   		 @Override
   		 public void callBack(ResponseEntity entity) {
   	//	 System.out.println("POST："+entity.toString());
   		 	switch (entity.getStatus()) {
   			case FastHttp.result_ok:
				data=JsonUtil.getLawTypesData(entity.getContentAsString());
			//	System.out.println(data);
			     adapter=new LawTypeAdapter(data, LawTypeActivity.this);
			     typeList.setAdapter(adapter);
		//	System.out.println(entity.getContentAsString());
   				break;
   			}
   		 }
   		 });   
       }
	@Override
	public void onClick(View v) {

	}

}
