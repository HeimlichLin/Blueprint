package com.example.Blueprint;

import static android.provider.BaseColumns._ID;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

public class DBHelper extends SQLiteOpenHelper {

	

	public static final String TABLE_NAME = "InformationTable";
	public static final String Colname = "Colname";
	public static final String URI = "URI";
	public static final String ID1 = "ID1";
	public static final String ID2 = "ID2";
	public static final String ID3 = "ID3";
	public static final String ChName = "ChName";
	public static final String EnName = "EnName";
	public static final String Provider = "Provider";
	public static final String MadeCompany = "MadeCompany";
	public static final String PackCompany = "PackCompany";
	public static final String Form = "Form";
	public static final String PackSpe = "PackSpe";
	public static final String Year = "Year";
	public static final String Date = "Date";
	public static final String WWW = "WWW";
	
	
	public static final String mDbName = SDBHelper.DB_DIR + File.separator
			+ "Data.db";
	private final static int DATABASE_VERSION = 1;

	public DBHelper(Context context) {
		super(context, mDbName, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		final String INIT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ Colname + " CHAR, " + URI + " CHAR, " + ID1 + " CHAR," + ID2 + " CHAR," + ID3 + " CHAR, " + ChName
				+ " CHAR, " + EnName + " CHAR, " + Provider + " CHAR," + MadeCompany + " CHAR," + PackCompany +
				" CHAR," + Form + " CHAR," + PackSpe+ " CHAR, " + Year + " CHAR,"+ Date + " CHAR,"+ WWW + " CHAR);";
		db.execSQL(INIT_TABLE);
	}

	@Override
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(DROP_TABLE);
		onCreate(db);
	}

}