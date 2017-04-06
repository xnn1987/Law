package com.cn.law.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TabHost;

import com.cn.law.R;

public class HomeActivity extends TabActivity implements
		RadioButton.OnCheckedChangeListener {

	RadioButton rb1, rb2, rb3, rb4, rb5;
	TabHost tabHost;
	private static final String MAIN = "main";
	private static final String TYPE = "type";
	private static final String SOUSUO = "sousuo";
	private static final String SAVE = "save";
	private static final String MORE = "more";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);
		init();

	}

	private void init() {
		getMenu();
		tabHost = getTabHost();
		setTabs();
		rb3.setChecked(true);
	}

	private void setTabs() {
		tabHost.addTab(tabHost.newTabSpec(MAIN).setIndicator(MAIN)
				.setContent(new Intent(HomeActivity.this, MainActivity.class)));
		tabHost.addTab(tabHost
				.newTabSpec(TYPE)
				.setIndicator(TYPE)
				.setContent(
						new Intent(HomeActivity.this, LawTypeActivity.class)));
		tabHost.addTab(tabHost
				.newTabSpec(SOUSUO)
				.setIndicator(SOUSUO)
				.setContent(
						new Intent(HomeActivity.this, LawSouSuoActivity.class)));
		tabHost.addTab(tabHost
				.newTabSpec(SAVE)
				.setIndicator(SAVE)
				.setContent(
						new Intent(HomeActivity.this, LawLoadActivity.class)));
		tabHost.addTab(tabHost.newTabSpec(MORE).setIndicator(MORE)
				.setContent(new Intent(HomeActivity.this, MoreActivity.class)));
	}

	private void getMenu() {
		rb1 = (RadioButton) findViewById(R.id.radio_button0);
		rb2 = (RadioButton) findViewById(R.id.radio_button1);
		rb3 = (RadioButton) findViewById(R.id.radio_button2);
		rb4 = (RadioButton) findViewById(R.id.radio_button3);
		rb5 = (RadioButton) findViewById(R.id.radio_button4);
		rb1.setOnCheckedChangeListener(this);
		rb2.setOnCheckedChangeListener(this);
		rb3.setOnCheckedChangeListener(this);
		rb4.setOnCheckedChangeListener(this);
		rb5.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if (isChecked) {
			if (rb1 == buttonView) {
				tabHost.setCurrentTabByTag(TYPE);
			} else if (rb2 == buttonView) {
				tabHost.setCurrentTabByTag(SOUSUO);
			} else if (rb3 == buttonView) {
				tabHost.setCurrentTabByTag(MAIN);

			} else if (rb4 == buttonView) {
				tabHost.setCurrentTabByTag(SAVE);
			} else if (rb5 == buttonView) {
				tabHost.setCurrentTabByTag(MORE);
			}
		}

	}

}
