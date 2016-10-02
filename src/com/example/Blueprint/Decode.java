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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Decode extends Activity {

	private static final int ZXING_SCAN = 3;
	private Button Button1;
	private TextView ActMed,ActInf;
	
	long time1, time2, time3;

	@Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.decode);

		Button1 = (Button) findViewById(R.id.button1);
		Button1.setOnClickListener(qrcode_scanner);
	}

	private OnClickListener qrcode_scanner = new OnClickListener() 
	{

		public void onClick(View v) 
		{

			Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			startActivityForResult(intent, ZXING_SCAN);
			

		}

	};


	public void onActivityResult(int requestCode, int resultCode, Intent intent) {

		super.onActivityResult(requestCode, resultCode, intent);

		if (requestCode == ZXING_SCAN) {
			if (resultCode == RESULT_OK) {
				
				String contents = intent.getStringExtra("SCAN_RESULT");

				String qrCodePlaintextPage = contents.substring(42);
				String qrCodeHmac = contents.substring(0, 40); 
				String comPareHmac = "";
				String act = contents.substring(42, 45);
				String qrCodePlaintext = contents.substring(46);
				
				System.out.println(qrCodePlaintextPage);
				System.out.println(qrCodeHmac);
				System.out.println(comPareHmac);
				System.out.println(act);
				System.out.println(qrCodePlaintext);
				
				try{
					Intent intent1=new Intent();
					intent1.putExtra("qrCodePlaintext",qrCodePlaintext);
					
						intent1.setClass(this, Class.forName("com.example.Blueprint."+act));
						intent1.setFlags(intent1.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent1); 
						
						Log.i("intent:","ok");
					
				}
				catch (ClassNotFoundException e){ 
					Toast.makeText(this, "驗證錯誤", Toast.LENGTH_SHORT).show();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				}

				
			else {
				//System.out.println("out put toast");
				Toast.makeText(this, "驗證錯誤", Toast.LENGTH_SHORT).show();}
		} else {
			Toast.makeText(this, "ZXING_SCAN<>3", Toast.LENGTH_SHORT).show();
		}
	}

}
