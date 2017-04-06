package com.cn.law.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.cn.law.R;

public class ProgressDialogUtil {
	private ProgressDialog pd;

	public ProgressDialogUtil(Context ctx) {
		pd = new ProgressDialog(ctx);
	}

	public void show(int style, String title, String message, int icon,
			boolean cancelable) {
		pd.setProgressStyle(style);
		if (title == null)
			pd.setTitle("");
		else
			pd.setTitle(title);

		pd.setMessage(message);

		if (icon == 0)
			pd.setIcon(R.drawable.arrow_1in);
		else
			pd.setIcon(icon);
		pd.setCancelable(cancelable);
		pd.setIndeterminate(false);
		pd.show();
	}

	public ProgressDialog getPd() {
		return pd;
	}

	public void cancel() {
		if (pd.isShowing()) {
			pd.cancel();
		}
	}
}
