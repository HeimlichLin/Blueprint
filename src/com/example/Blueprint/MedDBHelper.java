package com.example.Blueprint;

import static android.provider.BaseColumns._ID;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MedDBHelper extends SQLiteOpenHelper {

	
	public static final String TABLE_NAME = "medTable";
	public static final String PlaintextHash = "plaintextHash";
	public static final String Key = "key";
	public static final String Plaintext = "plaintext";

	public static final String mDbName = MedSDBHelper.DB_DIR + File.separator
			+ "hmac.db";
	private final static int DATABASE_VERSION = 1;

	public MedDBHelper(Context context) {
		super(context, mDbName, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		final String INIT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + Plaintext
				+ " CHAR, " + PlaintextHash + " CHAR," + Key
				+ " CHAR);";
		db.execSQL(INIT_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(DROP_TABLE);
		onCreate(db);
	}

}