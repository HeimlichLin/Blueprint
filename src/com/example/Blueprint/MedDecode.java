package com.example.Blueprint;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MedDecode extends Activity {

	private static final int ZXING_SCAN = 3;
	private Button btScan;
	private TextView txtResult,txtResult1,txtResult2,txtResult3,txtResult4,
	                 txtResult5,txtResult6,txtResult7,txtResult8,txtResult9,
	                 txtResult10,txtResult11,txtResult12;
	private ScrollView scrollView1;
	private SQLiteDatabase db;
	MedDBHelper hmacDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meddecode);

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

				hmacDB = new MedDBHelper(getApplicationContext());
				db = hmacDB.getReadableDatabase();

				String TABLE_NAME = "medTable";
				String qrCodePlaintext = contents.substring(40);
				String qrCodeHmac = contents.substring(0, 40);
				String comPareHmac = "";
				String comParePlaintextHash = "";

				try {
					comParePlaintextHash = HashActivity.SHA1(qrCodePlaintext);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Cursor c = null;

				c = db.query(true, TABLE_NAME, new String[] { "_id",
						"Plaintext", "PlaintextHash", "Key" }, "PlaintextHash="
						+ "\"" + comParePlaintextHash + "\"", null, null, null,
						null, null);

				if (c != null) {
					c.moveToFirst();
					for (int i = 0; i < c.getCount(); i++) {
						try {
							comPareHmac = HMAC.calculateHMAC(qrCodePlaintext,
									c.getString(3));
						} catch (InvalidKeyException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SignatureException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						c.moveToNext();
					}
				}

				c.close();
				db.close();

				if (qrCodeHmac.equals(comPareHmac)) {
					String[] splitPlaintext = qrCodePlaintext.split("%");
					for (int i = 0; i < qrCodePlaintext.length(); i++)
					{
						scrollView1.setVisibility(View.VISIBLE);
						txtResult.setText(splitPlaintext[0]);
						txtResult1.setText(splitPlaintext[1]);
						txtResult2.setText(splitPlaintext[2]);
						txtResult3.setText(splitPlaintext[3]);
						txtResult4.setText(splitPlaintext[4]);
						txtResult5.setText(splitPlaintext[5]);
						txtResult6.setText(splitPlaintext[6]);
						txtResult7.setText(splitPlaintext[7]);
						txtResult8.setText(splitPlaintext[8]);
						txtResult9.setText(splitPlaintext[9]);
						txtResult10.setText(splitPlaintext[10]);
						txtResult11.setText(splitPlaintext[11]);
						txtResult12.setText(splitPlaintext[12]);
					}
					
				}

				else {
					Toast.makeText(this, "ÅçÃÒ¿ù»~", Toast.LENGTH_SHORT).show();
				}
			}
		} else {
			Toast.makeText(this, "ZXING_SCAN<>3", Toast.LENGTH_SHORT).show();
		}
	}

}