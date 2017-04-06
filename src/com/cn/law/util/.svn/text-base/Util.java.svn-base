package com.cn.law.util;

import com.cn.law.LawAPPState;

public class Util {
	static LawAPPState app = null;

	public static LawAPPState getApp() {
		app = LawAPPState._instance;
		return app;
	}

	/**
	 * 
	 * @return
	 */
	public static String getVersion() {
		if (app == null) {
			app = LawAPPState._instance;
		}

		return String.valueOf(app.getVersion());
	}

	/**
	 * 
	 * @return
	 */
	// public static boolean isLogin()
	// {
	// if (app == null)
	// {
	// app = LawApp._instance;
	// }
	// return app.isLogin();
	// }

	/**
	 * ��Ļ�߶�
	 * 
	 * @return
	 */
	public static int getScreenHeight() {
		if (app == null) {
			app = LawAPPState._instance;
		}
		return app.getScreenHeight();
	}

	public static void setScreenHeight(int screenHeight) {
		if (app == null) {
			app = LawAPPState._instance;
		}
		app.setScreenHeight(screenHeight);
	}

	public static int getScreenWidth() {
		if (app == null) {
			app = LawAPPState._instance;
		}
		return app.getScreenWidth();
	}

	public static void setScreenWidth(int screenWidth) {
		if (app == null) {
			app = LawAPPState._instance;
		}
		app.setScreenWidth(screenWidth);
	}

}
