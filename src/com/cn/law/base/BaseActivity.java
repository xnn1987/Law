package com.cn.law.base;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.law.R;
import com.cn.law.util.ProgressDialogUtil;

public abstract class BaseActivity extends Activity implements OnClickListener {

	public ProgressDialogUtil pdu;
	  public static ArrayList<Activity> allActivity=new  ArrayList<Activity>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		allActivity.add(this);
		if (pdu == null)
			pdu = new ProgressDialogUtil(BaseActivity.this);
	}
	  public static Activity  getActivityByName(String name){
		   for(Activity ac:allActivity){
			   if(ac.getClass().getName().indexOf(name)>0){
				   return ac;
			   }else{
				   return null;
			   }
		   }
		   return null;
	  }
	   /**监听用户操作*/
	  @Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if(KeyEvent.KEYCODE_BACK==keyCode){
				promptExit(this);
			}	  
		  return super.onKeyDown(keyCode, event);
	  }
	  /**退出程序*/
	public void promptExit(Context context) {
		new AlertDialog.Builder(context)
		.setTitle(R.string.layout_title)
		.setMessage(R.string.layout_body)
		.setPositiveButton(R.string.submit,new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				for(Activity ac:allActivity){
					if(ac!=null){
						ac.finish();
					}
				}
				
			}
		}).setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
			}
		}).show();
	}
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
	}

	protected void onPause() {
		super.onPause();
		System.gc();
		// MobclickAgent.onPause(this);
	}

	protected void onResume() {
		super.onResume();
		// MobclickAgent.onResume(this);
	}

	protected void onStop() {
		System.gc();
		super.onStop();
	}

	protected void showProcess(String message) {
		pdu.show(ProgressDialog.STYLE_SPINNER, null, message, 0, true);
	}

	protected void cancleProcess() {
		pdu.cancel();
	}

	protected void showToast(int paramInt) {
		Toast.makeText(this, paramInt, 0).show();
	}

	protected void showToast(String message) {

		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
		BaseActivity.this.getWindowManager().getDefaultDisplay()
				.getMetrics(localDisplayMetrics);

		if (localDisplayMetrics.heightPixels < 800) {
			LayoutInflater inflater = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.toast,
					(ViewGroup) findViewById(R.id.toast_layout));

			TextView text = (TextView) layout.findViewById(R.id.info_toast);
			text.setText(message);

			Toast toast = new Toast(this);
			toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 50);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(layout);
			toast.show();
		} else {
			LayoutInflater inflater = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.toast,
					(ViewGroup) findViewById(R.id.toast_layout));

			TextView text = (TextView) layout.findViewById(R.id.info_toast);
			text.setText(message);

			Toast toast = new Toast(this);
			toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 80);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(layout);
			toast.show();
		}
	}

}
