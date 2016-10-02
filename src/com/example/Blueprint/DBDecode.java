package com.example.Blueprint;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class DBDecode extends Activity {

	private static final int ZXING_SCAN = 3;
	private Button btScan;
	private TextView txtResult,txtResult1,txtResult2,txtResult3,txtResult4,
	                 txtResult5,txtResult6,txtResult7,txtResult8,txtResult9,
	                 txtResult10,txtResult11,txtResult12;
	private ScrollView scrollView1;
	private SQLiteDatabase db;
	DBHelper dateDB;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbdecode);

		txtResult = (TextView) findViewById(R.id.txtResult);
		txtResult1 = (TextView) findViewById(R.id.txtResult1);
		txtResult2 = (TextView) findViewById(R.id.txtResult2);
		txtResult3 = (TextView) findViewById(R.id.txtResult3);
		txtResult4 = (TextView) findViewById(R.id.txtResult4);
		txtResult5 = (TextView) findViewById(R.id.txtResult5);
		txtResult6 = (TextView) findViewById(R.id.txtResult6);
		txtResult7 = (TextView) findViewById(R.id.txtResult7);
		txtResult8 = (TextView) findViewById(R.id.txtResult8);
		txtResult9 = (TextView) findViewById(R.id.txtResult9);
		txtResult10 = (TextView) findViewById(R.id.txtResult10);
		txtResult11 = (TextView) findViewById(R.id.txtResult11);
		txtResult12 = (TextView) findViewById(R.id.txtResult12);
		txtResult12.setAutoLinkMask(Linkify.WEB_URLS);
		scrollView1 = (ScrollView) findViewById(R.id.scrollView1);
		imageView = (ImageView) findViewById(R.id.image);
		btScan = (Button) findViewById(R.id.btScan);
		btScan.setOnClickListener(qrcode_scanner);
	}

	private OnClickListener qrcode_scanner = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			startActivityForResult(intent, ZXING_SCAN);
		}
	};

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {

		super.onActivityResult(requestCode, resultCode, intent);

		if (requestCode == ZXING_SCAN) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");

				dateDB = new DBHelper(getApplicationContext());
				db = dateDB.getReadableDatabase();

				scrollView1.setVisibility(View.VISIBLE);
				String TABLE_NAME = "InformationTable";
				String qrCodePlaintext = contents.substring(0);
				String URI = "";
				String ID1 = "";
				String ID2 = "";
				String ID3 = "";
				String ChName = "";
				String EnName = "";
				String Provider = "";
				String MadeCompany = "";
				String PackCompany = "";
				String Form = "";
				String PackSpe = "";
				String Year = "";
				String Date = "";
				String WWW = "";

				
				Cursor c = null;

				c = db.query(true, TABLE_NAME, new String[] { "_id",
						"Colname","URI","ID1","ID2","ID3","ChName","EnName",
						"Provider","MadeCompany","PackCompany",
						"Form","PackSpe","Year","Date","WWW"},
						"Colname="+ "\"" + qrCodePlaintext + "\"", 
						null, null, null, null, null);

				if (c != null) {
					c.moveToFirst();
					for (int i = 0; i < c.getCount(); i++) {
						
						URI = c.getString(2);
						ID1 = c.getString(3);
						ID2 = c.getString(4);
						ID3 = c.getString(5);
						ChName = c.getString(6);
						EnName = c.getString(7);
						Provider = c.getString(8);
						MadeCompany = c.getString(9);
						PackCompany = c.getString(10);
						Form = c.getString(11);
						PackSpe = c.getString(12);
						Year = c.getString(13);
						Date = c.getString(14);
						WWW = c.getString(15);

						
						c.moveToNext();
					}
				}

				c.close();
				db.close();

				
						scrollView1.setVisibility(View.VISIBLE);
						
						Bitmap imageBitmap = BitmapFactory.decodeFile(URI);
						ImageView imageView = (ImageView) findViewById(R.id.image);
						// 將Bitmap設定到ImageView
						imageView.setImageBitmap(imageBitmap);
						
						txtResult.setText(ID1);
						txtResult1.setText(ID2);
						txtResult2.setText(ID3);
						txtResult3.setText(ChName);
						txtResult4.setText(EnName);
						txtResult5.setText(Provider);
						txtResult6.setText(MadeCompany);
						txtResult7.setText(PackCompany);
						txtResult8.setText(Form);
						txtResult9.setText(PackSpe);
						txtResult10.setText(Year);
						txtResult11.setText(Date);
						txtResult12.setText(WWW);
					}
					
				}

				else {
					Toast.makeText(this, "驗證錯誤", Toast.LENGTH_SHORT).show();
				}
	}
}
