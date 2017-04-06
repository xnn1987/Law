package com.cn.law.ui;

import java.util.ArrayList;
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
import com.cn.law.adapter.MainAdpter;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.Constants;
import com.cn.law.bean.MainFastBean;
import com.cn.law.util.Base64ZipUtil;
import com.cn.law.util.HashUtil;
import com.cn.law.util.JsonUtil;
import com.cn.law.util.ZipUtil;
import com.common.internet.AjaxCallBack;
import com.common.internet.FastHttp;
import com.common.internet.ResponseEntity;

public class LawSousuoContentActivity extends BaseActivity{
  private Intent intent;
  private List<MainFastBean> data = new ArrayList<MainFastBean>();
  private ListView lawlist;
  private MainAdpter adapter;
  private Button backBtn;
  private TextView headTxt;
  private String title=null ,fullindex=null,lawno=null;

@Override
     protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.searchcontent_layout);
	   intent=getIntent();
	   title=intent.getStringExtra("title");
	   fullindex=intent.getStringExtra("fullindex");
	   lawno=intent.getStringExtra("lawno");
	initView();
	getData();
   }

	private void initView() {
    lawlist=(ListView) findViewById(R.id.search_list);
	backBtn=(Button) findViewById(R.id.back_btn);
	headTxt=(TextView) findViewById(R.id.head_txt);
    backBtn.setOnClickListener(this);
    headTxt.setText(getString(R.string.search_content));
	lawlist.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long arg3) {
		
				Intent intent = new Intent(LawSousuoContentActivity.this,
						LawDetialActivity.class);
				startActivity(intent);
		
			
		}
	});
}
	private void getData() {
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("title", title);
		params.put("fullindex","会计");
		params.put("lawno",lawno);
		params.put("username", "xnn1987");
		String hash=HashUtil.formhash("xnn1987",title+"会计"+lawno);
		System.out.println(hash);
		params.put("hash",hash);
//		params.put("n", "10");
//		params.put("zip", "1");
		FastHttp.ajax(Constants.LAWSEARCH, params,new AjaxCallBack() {		
			@Override
			public void callBack(ResponseEntity entity) {
		    	System.out.println(entity.getContentAsString());
				switch (entity.getStatus()) {
				case FastHttp.result_ok:
//					String data=Base64ZipUtil.UnBase64AndUnZip(entity.getContentAsString());
//					System.out.println("sssssssssss"+data);
					data=JsonUtil.getNewData(entity.getContentAsString());
					adapter = new MainAdpter(data, LawSousuoContentActivity.this);
					lawlist.setAdapter(adapter);
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
