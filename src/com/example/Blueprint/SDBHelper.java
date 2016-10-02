package com.example.Blueprint;

import java.io.File;

import android.os.Environment;

public class SDBHelper {
	public static final String DB_DIR = Environment
			.getExternalStorageDirectory().getPath()
			+ File.separator
			+ "HMAC"
			+ File.separator
			+ SDBHelper.class.getPackage().getName();
	static {
		while (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		File dbFolder = new File(DB_DIR);
		// 目錄不存在則自動創建目錄
		if (!dbFolder.exists()) {
			dbFolder.mkdirs();
		}
	}
}
