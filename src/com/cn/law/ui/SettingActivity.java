package com.cn.law.ui;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;

public class SettingActivity extends BaseActivity {
	  private Button backBtn,headBtn;
	  private TextView headTxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_layout);
		initView();
	//	addPreferencesFromResource(R.xml.checkboxperference);
		// mContext=this;
		// CheckBoxPreference mCheckBox=(CheckBoxPreference) findPreference("");
		// mCheckBox.setOnPreferenceClickListener(new
		// OnPreferenceClickListener() {
		//
		// @Override
		// public boolean onPreferenceClick(Preference preference) {
		// //这里添加点击事件
		// return false;
		// }
		// });
		// mCheckBox.setOnPreferenceChangeListener(new
		// OnPreferenceChangeListener() {
		//
		// @Override
		// public boolean onPreferenceChange(Preference preference, Object
		// newValue) {
		// //这里监听值是否改变
		// //还可以得到新改变的值
		// boolean a=(Boolean) newValue;
		//
		//
		// return true;
		// }
		// });
	}

	private void initView() {
		   backBtn=(Button) findViewById(R.id.back_btn);
		   headBtn=(Button) findViewById(R.id.head_btn);
		   headTxt=(TextView) findViewById(R.id.head_txt);
		   headTxt.setText(getString(R.string.my_setting));
		   headBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.body_bg_2x1109));
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
			Toast.makeText(SettingActivity.this, "保存成功", 1).show();
			break;
		}
		
	}

}
