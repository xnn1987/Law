package com.cn.law.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.adapter.MainAdpter;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.Constants;
import com.cn.law.bean.MainFastBean;
import com.cn.law.util.JsonUtil;
import com.common.internet.AjaxCallBack;
import com.common.internet.FastHttp;
import com.common.internet.ResponseEntity;

public class MainActivity extends BaseActivity {
	private TextView fuguiTxt;
	private PopupWindow popuwindow;
	private ListView mainList;
	private MainAdpter adapter;
	private TextView newTxt, hotTxt, nativeTxt, shishiTxt, shixiaoTxt;
	private List<MainFastBean> data = new ArrayList<MainFastBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		initView();
		getData();
	}

	private void initView() {
		fuguiTxt = (TextView) findViewById(R.id.law_fast_txt);
		mainList = (ListView) findViewById(R.id.main_list);
		fuguiTxt.setOnClickListener(this);
		adapter = new MainAdpter(data, this);
		mainList.setAdapter(adapter);
		mainList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg3) {
				
					Intent intent = new Intent(MainActivity.this,
							LawDetialActivity.class);
					intent.putExtra("data", position);
					startActivity(intent);
	
			}
		});
	}

	private void initPoPuWindow() {
		View v = LayoutInflater.from(this).inflate(R.layout.popu_layout, null);
		popuwindow = new PopupWindow(v, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, false);
		popuwindow.setBackgroundDrawable(new BitmapDrawable());
		popuwindow.setFocusable(true);
		popuwindow.setOutsideTouchable(true);
		newTxt = (TextView) v.findViewById(R.id.new_fagui_txt);
		hotTxt = (TextView) v.findViewById(R.id.hot_fagui_txt);
		nativeTxt = (TextView) v.findViewById(R.id.native_fagui_txt);
		shishiTxt = (TextView) v.findViewById(R.id.shisshi_fagui_txt);
		shixiaoTxt = (TextView) v.findViewById(R.id.shixiao_fagui_txt);
		newTxt.setOnClickListener(this);
		hotTxt.setOnClickListener(this);
		nativeTxt.setOnClickListener(this);
		shishiTxt.setOnClickListener(this);
		shixiaoTxt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.law_fast_txt:
			initPoPuWindow();
			if (popuwindow.isShowing()) {
				popuwindow.dismiss();
			} else {
				popuwindow.showAsDropDown(v);
			}
			break;
		case R.id.new_fagui_txt:
			fuguiTxt.setText(R.string.new_fagui);
			getNewData();
			break;
		case R.id.hot_fagui_txt:
			fuguiTxt.setText(R.string.hot_fagui);
			getHotData();
			break;
		case R.id.native_fagui_txt:
			fuguiTxt.setText(R.string.native_fagui);
			getNativeData();
			break;
		case R.id.shisshi_fagui_txt:
			fuguiTxt.setText(R.string.shishi_fagui);
			getShiShiData();
			break;
		case R.id.shixiao_fagui_txt:
			fuguiTxt.setText(R.string.shixiao_fagui);
			getShiXiaoData();
			break;
		}

	}

	private void getData() {
		FastHttp.ajaxGet(Constants.MAINURL, new AjaxCallBack() {
			@Override
			public void callBack(ResponseEntity entity) {
				switch (entity.getStatus()) {
				case FastHttp.result_ok:
					try {
						data.clear();
						data.addAll(JsonUtil.getMainData(entity
								.getContentAsString()));
						adapter.notifyDataSetChanged();
				 System.out.println(entity.getContentAsString());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				}

			}
		});

	}

	private void getNewData() {
		FastHttp.ajaxGet(Constants.MAINNEWURL, new AjaxCallBack() {

			@Override
			public void callBack(ResponseEntity entity) {
				switch (entity.getStatus()) {
				case FastHttp.result_ok:
					notifyChanged(entity.getContentAsString());
					break;

				default:
					break;
				}

			}
		});

	}

	private void getHotData() {
		FastHttp.ajaxGet(Constants.MAINHOTURL, new AjaxCallBack() {

			@Override
			public void callBack(ResponseEntity entity) {
				switch (entity.getStatus()) {
				case FastHttp.result_ok:
					notifyChanged(entity.getContentAsString());
					break;

				default:
					break;
				}

			}
		});
	}

	private void getNativeData() {
		FastHttp.ajaxGet(Constants.MAINNATIVEURL, new AjaxCallBack() {

			@Override
			public void callBack(ResponseEntity entity) {
				switch (entity.getStatus()) {
				case FastHttp.result_ok:
					notifyChanged(entity.getContentAsString());
					break;

				default:
					break;
				}

			}
		});
	}

	private void getShiShiData() {
		FastHttp.ajaxGet(Constants.MAINSHISHIURL, new AjaxCallBack() {

			@Override
			public void callBack(ResponseEntity entity) {
				switch (entity.getStatus()) {
				case FastHttp.result_ok:
					notifyChanged(entity.getContentAsString());
					break;

				default:
					break;
				}

			}
		});
	}

	private void getShiXiaoData() {
		FastHttp.ajaxGet(Constants.MAINSHIXIAOURL, new AjaxCallBack() {

			@Override
			public void callBack(ResponseEntity entity) {
				switch (entity.getStatus()) {
				case FastHttp.result_ok:
					notifyChanged(entity.getContentAsString());
					break;

				default:
					break;
				}

			}
		});
	}

	private void notifyChanged(String str) {
		try {
			data.clear();
			data.addAll(JsonUtil.getNewData(str));
			// Log.i("output", "data:" + data);
			adapter.notifyDataSetChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
