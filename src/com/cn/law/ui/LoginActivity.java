package com.cn.law.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.base.BaseActivity;
import com.cn.law.bean.Constants;
import com.cn.law.util.XmlParserUtil;
import com.common.internet.AjaxCallBack;
import com.common.internet.FastHttp;
import com.common.internet.InternetConfig;
import com.common.internet.ResponseEntity;
import com.weibo.net.SinaWeibo;

public class LoginActivity extends BaseActivity {
	private Button backBtn,headBtn,qqBtn,sinaBtn,tencetBtn;
	private TextView headTxt,forgetPSW;
	private EditText userEdt,pswEdt;
	private Button loginBtn;
    private CheckBox userBx;
    private SharedPreferences sp;
	private List<Map<String, String>> list=new ArrayList<Map<String,String>>();
	private Map<String, String> map=new HashMap<String, String>();
    private int  status;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.login_layout);
    	sp=getSharedPreferences("userInfo", MODE_PRIVATE);
    	initView();
    }
	private void initView() {
		   backBtn=(Button) findViewById(R.id.back_btn);
		   headBtn=(Button) findViewById(R.id.head_btn);
		   loginBtn=(Button) findViewById(R.id.login_btn);
		   headTxt=(TextView) findViewById(R.id.head_txt);
		   userEdt=(EditText) findViewById(R.id.user_edt);
		   pswEdt=(EditText) findViewById(R.id.pass_edt);
		   userBx=(CheckBox) findViewById(R.id.user_box);
		   forgetPSW=(TextView) findViewById(R.id.forget_txt);
		   qqBtn=(Button) findViewById(R.id.qq_login_btn);
		   sinaBtn=(Button) findViewById(R.id.sina_login_btn);
		   tencetBtn=(Button) findViewById(R.id.tencet_login_btn);
		   backBtn.setOnClickListener(this);
		   headBtn.setOnClickListener(this);
		   loginBtn.setOnClickListener(this);
		   forgetPSW.setOnClickListener(this);
		   qqBtn.setOnClickListener(this);
		   sinaBtn.setOnClickListener(this);
		   tencetBtn.setOnClickListener(this);
		   headBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.body_bg_2x1100));
		   headBtn.setVisibility(View.VISIBLE);
		   headTxt.setText(getString(R.string.regester));
		   userBx.setChecked(sp.getBoolean("remember", false));
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_btn:
			finish();
			break;
		case R.id.head_btn:
			Intent intent=new Intent(LoginActivity.this,RegesterActivity.class);
			startActivity(intent);
			break;
		case R.id.login_btn:
			    gotoLogin();
			break;
		case R.id.forget_txt:
			Intent intent0=new Intent(LoginActivity.this,ForgetPSWActivity.class);
			startActivity(intent0);
			break;
		case R.id.qq_login_btn:
			gotoQQ();
			break;
		case R.id.sina_login_btn:	
			
				gotoSina();
				
			break;
		case R.id.tencet_login_btn:
			gototencet();
			break;
		}
	}


	private void gotoLogin() {
		String username=userEdt.getText().toString();
		String psword=pswEdt.getText().toString();
		HashMap<String, String> params=new HashMap<String, String>();
		if (userBx.isChecked()) {
			SharedPreferences.Editor editor=sp.edit();
			editor.putBoolean("remember", userBx.isChecked());
			editor.putString("username", username);
			editor.putString("password", psword);
			editor.commit();	
			String u=sp.getString("username", null);
			String p=sp.getString("password", null);
			params.put("username", u);
			params.put("password", p);	
		}else{
			params.put("username", username);
			params.put("password",psword);	
		}
		InternetConfig config=new InternetConfig();
		config.setName_space("http://soap-esnai");
		String login="login";
		FastHttp.ajaxWebServer(Constants.LOGIN,login,params,config,new AjaxCallBack() {
			
			@Override
			public void callBack(ResponseEntity entity) {
				System.out.println(entity.toString());
            switch (entity.getStatus()) {
			case FastHttp.result_ok:
				 try {
					list=XmlParserUtil.main(entity.getContentAsString());
					for (int i = 0; i < list.size(); i++) {
					   map=list.get(i);
                       status=Integer.parseInt(map.get("status")); 
					}
					if (status==0) {
						Intent intent2=new Intent(LoginActivity.this,HomeActivity.class);
					    startActivity(intent2);
					    finish();	
					}

				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("sssssssss"+list);
				break;
			default:
				break;
			}
				
			}
		});
	}
	//QQ登陆
	private void gotoQQ() {
		Intent intent1 = new Intent(LoginActivity.this,AuthorizActivity.class);
		intent1.putExtra("type", 0);
		startActivity(intent1);
		
	}
	//sina微博登陆
	private void gotoSina()  {
			Intent intent2 = new Intent(LoginActivity.this,AuthorizActivity.class);
			intent2.putExtra("type", 1);
			startActivity(intent2);
	}
  //tencet 微博登陆
	private void gototencet(){
		Intent intent3 = new Intent(LoginActivity.this,AuthorizActivity.class);
		intent3.putExtra("type", 2);
		startActivity(intent3);
	}
}
