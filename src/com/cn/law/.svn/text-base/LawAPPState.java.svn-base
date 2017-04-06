package com.cn.law;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.provider.Settings;

import com.cn.law.bean.VersionBean;

public class LawAPPState extends Application {
	public static LawAPPState _instance = new LawAPPState();
	private int screenHeight;
	private int screenWidth;

	@Override
	public void onCreate() {
		super.onCreate();
		_instance = this;
	}

	/**
	 * 检查网络
	 * */
	public void CheckNetworkState() {
		boolean flag = false;
		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		State mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		// 如果3G、wifi、2G等网络状态是连接的，则退出，否则显示提示信息进入网络设置界面
		if (mobile == State.CONNECTED || mobile == State.CONNECTING)
			return;
		if (wifi == State.CONNECTED || wifi == State.CONNECTING)
			return;
		showTips();
	}

	private void showTips() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle("提示");
		builder.setMessage("网络判断");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 如果没有网络连接，则进入网络设置界面
				startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
			}
		});
		builder.setNegativeButton("cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		builder.create();
		builder.show();
	}

	/**
	 * 获取当前APP版本号
	 * 
	 * @return
	 */
	public static int getVersionCode() {
		PackageInfo info;
		int versionCode = 1;
		try {
			info = _instance.getPackageManager().getPackageInfo(
					_instance.getPackageName(), 0);

			//
			versionCode = info.versionCode;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * 版本信息
	 * 
	 * @return
	 * */
	public static VersionBean getVersion() {
		PackageInfo info;
		String versionName = null;
		int versionCode = 1;
		try {
			info = _instance.getPackageManager().getPackageInfo(
					_instance.getPackageName(), 0);
			//
			versionName = info.versionName;
			//
			versionCode = info.versionCode;
			//
			// String packageNames = info.packageName;
			VersionBean updatebean = new VersionBean();
			updatebean.setVersionCode(versionCode);
			updatebean.setVersionName(versionName);
			return updatebean;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

}
