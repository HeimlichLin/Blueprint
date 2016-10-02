package com.example.Blueprint;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class plt extends Activity {

	private static final int ZXING_SCAN = 3;
	private Button Button1;
	private TextView TextView1,TextView2,TextView3,TextView4,
	TextView5,TextView6,TextView7,TextView8,TextView9,TextView10,
	TextView11,TextView12,TextView13,TextView14,TextView15,TextView16,
	TextView17,TextView18,TextView19,TextView20,TextView21,TextView22,
	TextView23,TextView24,TextView25,TextView26,TextView27,TextView28,
	TextView29,TextView30,TextView31,TextView32,TextView33,TextView34,
	TextView35,TextView36,TextView37,TextView38,TextView39,TextView40,
	TextView41,TextView42,TextView43,TextView44,TextView45,TextView46,
	TextView47,TextView48,TextViewNum;
	private ScrollView scrollView1;
	private ImageView imageView1;
	private int intWidth,intHeight,intDefaultx,intDefaultY;
	private int intScreenX,intScreenY; 
	
	@Override
		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.structuredpltmain);
		
		
		Intent intent = getIntent();
		String qrCodePlaintext = intent.getStringExtra("qrCodePlaintext");

		TextView1 = (TextView) findViewById(R.id.textView1);
		TextView2 = (TextView) findViewById(R.id.textView2);
		TextView3 = (TextView) findViewById(R.id.textView3);
		TextView4 = (TextView) findViewById(R.id.textView4);
		TextView5 = (TextView) findViewById(R.id.textView5);
		TextView6 = (TextView) findViewById(R.id.textView6);
		TextView7 = (TextView) findViewById(R.id.textView7);
		TextView8 = (TextView) findViewById(R.id.textView8);
		TextView9 = (TextView) findViewById(R.id.textView9);
		TextView10 = (TextView) findViewById(R.id.textView10);
		TextView11 = (TextView) findViewById(R.id.textView11);
		TextView12 = (TextView) findViewById(R.id.textView12);
		TextView13 = (TextView) findViewById(R.id.textView13);
		TextView14 = (TextView) findViewById(R.id.textView14);
		TextView15 = (TextView) findViewById(R.id.textView15);
		TextView16 = (TextView) findViewById(R.id.textView16);
		TextView17 = (TextView) findViewById(R.id.textView17);
		TextView18 = (TextView) findViewById(R.id.textView18);
		TextView19 = (TextView) findViewById(R.id.textView19);
		TextView20 = (TextView) findViewById(R.id.textView20);
		TextView21 = (TextView) findViewById(R.id.textView21);
		TextView21 = (TextView) findViewById(R.id.textView22);
		TextView23 = (TextView) findViewById(R.id.textView23);
		TextView24 = (TextView) findViewById(R.id.textView24);
		TextView25 = (TextView) findViewById(R.id.textView25);
		TextView26 = (TextView) findViewById(R.id.textView26);
		TextView27 = (TextView) findViewById(R.id.textView27);
		TextView28 = (TextView) findViewById(R.id.textView28);
		TextView29 = (TextView) findViewById(R.id.textView29);
		TextView30 = (TextView) findViewById(R.id.textView30);
		TextView31 = (TextView) findViewById(R.id.textView31);
		TextView32 = (TextView) findViewById(R.id.textView32);
		TextView33 = (TextView) findViewById(R.id.textView33);
		TextView34 = (TextView) findViewById(R.id.textView34);
		TextView35 = (TextView) findViewById(R.id.textView35);
		TextView36 = (TextView) findViewById(R.id.textView36);
		TextView37 = (TextView) findViewById(R.id.textView37);
		TextView38 = (TextView) findViewById(R.id.textView38);
		TextView39 = (TextView) findViewById(R.id.textView39);
		TextView40 = (TextView) findViewById(R.id.textView40);
		TextView41 = (TextView) findViewById(R.id.textView41);
		TextView42 = (TextView) findViewById(R.id.textView42);
		TextView43 = (TextView) findViewById(R.id.textView43);
		TextView44 = (TextView) findViewById(R.id.textView44);
		TextView45 = (TextView) findViewById(R.id.textView45);
		TextView46 = (TextView) findViewById(R.id.textView46);
		TextView47 = (TextView) findViewById(R.id.textView47);
		
		scrollView1 = (ScrollView) findViewById(R.id.scrollView1);
		Button1 = (Button) findViewById(R.id.button1);
		Button1.setOnClickListener(qrcode_scanner);
		
		String[] splitPlaintext = qrCodePlaintext.split("#");
		
		int num = Integer.parseInt(splitPlaintext[0]);
		
		switch (num)
		{
		case 1:
			TextView1.setText(splitPlaintext[0]);
			//TextView1.setBackgroundColor(Color.RED);
			break;
		case 2:
			TextView2.setText(splitPlaintext[0]);
			break;
		case 3:
			TextView3.setText(splitPlaintext[0]);
			break;
		case 4:
			TextView4.setText(splitPlaintext[0]);
			break;
		case 5:
			TextView5.setText(splitPlaintext[0]);
			break;
		case 6:
			TextView6.setText(splitPlaintext[0]);
			break;
		case 7:
			TextView7.setText(splitPlaintext[0]);
			break;
		case 8:
			TextView8.setText(splitPlaintext[0]);
			break;
		case 9:
			TextView9.setText(splitPlaintext[0]);
			break;
		case 10:
			TextView10.setText(splitPlaintext[0]);
			break;
		case 11:
			TextView11.setText(splitPlaintext[0]);
			break;
		case 12:
			TextView12.setText(splitPlaintext[0]);
			break;
		case 13:
			TextView13.setText(splitPlaintext[0]);
			break;
		case 14:
			TextView14.setText(splitPlaintext[0]);
			break;
		case 15:
			TextView15.setText(splitPlaintext[0]);
			break;
		case 16:
			TextView16.setText(splitPlaintext[0]);
			break;
		case 17:
			TextView17.setText(splitPlaintext[0]);
			break;
		case 18:
			TextView18.setText(splitPlaintext[0]);
			break;
		case 19:
			TextView19.setText(splitPlaintext[0]);
			break;
		case 20:
			TextView20.setText(splitPlaintext[0]);
			break;
		case 21:
			TextView21.setText(splitPlaintext[0]);
			break;
		case 22:
			TextView22.setText(splitPlaintext[0]);
			break;
		case 23:
			TextView23.setText(splitPlaintext[0]);
			break;
		case 24:
			TextView24.setText(splitPlaintext[0]);
			break;
		case 25:
			TextView25.setText(splitPlaintext[0]);
			break;
		case 26:
			TextView26.setText(splitPlaintext[0]);
			break;
		case 27:
			TextView27.setText(splitPlaintext[0]);
			break;
		case 28:
			TextView28.setText(splitPlaintext[0]);
			break;
		case 29:
			TextView29.setText(splitPlaintext[0]);
			break;
		case 30:
			TextView30.setText(splitPlaintext[0]);
			break;
		case 31:
			TextView31.setText(splitPlaintext[0]);
			break;
		case 32:
			TextView32.setText(splitPlaintext[0]);
			break;
		case 33:
			TextView33.setText(splitPlaintext[0]);
			break;
		case 34:
			TextView34.setText(splitPlaintext[0]);
			break;
		case 35:
			TextView35.setText(splitPlaintext[0]);
			break;
		case 36:
			TextView36.setText(splitPlaintext[0]);
			break;
		case 37:
			TextView37.setText(splitPlaintext[0]);
			break;
		case 38:
			TextView38.setText(splitPlaintext[0]);
			break;
		case 39:
			TextView39.setText(splitPlaintext[0]);
			break;
		case 40:
			TextView40.setText(splitPlaintext[0]);
			break;
		case 41:
			TextView41.setText(splitPlaintext[0]);
			break;
		case 42:
			TextView42.setText(splitPlaintext[0]);
			break;
		case 43:
			TextView43.setText(splitPlaintext[0]);
			break;
		case 44:
			TextView44.setText(splitPlaintext[0]);
			break;
		case 45:
			TextView45.setText(splitPlaintext[0]);
			break;
		case 46:
			TextView46.setText(splitPlaintext[0]);
			break;
		case 47:
			TextView47.setText(splitPlaintext[0]);
			break;
		
		}
		
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
				
				String qrCodePlaintextPage = contents.substring(42);
				String qrCodeHmac = contents.substring(0, 40); 
				String comPareHmac = "";
				String act = contents.substring(42, 45);
				String qrCodePlaintext = contents.substring(46);
				
				try {
					comPareHmac = HashActivity.SHA1(qrCodePlaintextPage);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (qrCodeHmac.equals(comPareHmac)) {
					
					Intent intent1=new Intent();
					
					intent1.putExtra("qrCodePlaintext",qrCodePlaintext);
					
					try{
					intent1.setClass(this, Class.forName("com.example.Blueprint."+act));
					intent1.setFlags(intent1.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent1); 
					//tap.this.finish();
					Log.i("intent:","ok");
				} 
				catch (ClassNotFoundException e) 
				{Toast.makeText(this, "ÅçÃÒ¿ù»~", Toast.LENGTH_SHORT).show();
					// TODO Auto-generated catch block
					e.printStackTrace();
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
