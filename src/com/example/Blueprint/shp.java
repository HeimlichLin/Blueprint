package com.example.Blueprint;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Hashtable;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class shp extends Activity {
		
	private static final int ZXING_SCAN = 3;
	private Button Button1;
	private ScrollView scrollView1;
	private ScrollView scrollView2;
	private TextView TextView111;
	private TextView TextView31,TextView32,TextView33,TextView34,
	TextView35,TextView36,TextView37,TextView38,TextView39,TextView40,
	TextView41,TextView42,TextView43;
	
	long time1, time2, time3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.structuredshpdecode);
		
		Intent intent = getIntent();
		String qrCodePlaintext = intent.getStringExtra("qrCodePlaintext");
		
		TextView111 = (TextView) findViewById(R.id.textView111);
		scrollView1 = (ScrollView) findViewById(R.id.scrollView1);
		scrollView2 = (ScrollView) findViewById(R.id.scrollView2);
		Button1 = (Button) findViewById(R.id.button1);
		Button1.setOnClickListener(qrcode_scanner);
		
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
		TextView43.setAutoLinkMask(Linkify.WEB_URLS);
		
		
		String[] splitPlaintext = qrCodePlaintext.split("%");
		for (int i = 0; i < qrCodePlaintext.length(); i++)
		{
			
			scrollView1.setVisibility(View.VISIBLE);
			scrollView2.setVisibility(View.VISIBLE);
			TextView111.setText(splitPlaintext[0]);
			
			
			String input1 = splitPlaintext[1];
			String input2 = splitPlaintext[2];
			
			int intValueA = Integer.parseInt(input1);
			int intValueB = Integer.parseInt(input2);
			
			TextView111.setX(intValueA);
			TextView111.setY(intValueB);
			
			TextView31.setText(splitPlaintext[3]);
			TextView32.setText(splitPlaintext[4]);
			TextView33.setText(splitPlaintext[5]);
			TextView34.setText(splitPlaintext[6]);
			TextView35.setText(splitPlaintext[7]);
			TextView36.setText(splitPlaintext[8]);
			TextView37.setText(splitPlaintext[9]);
			TextView38.setText(splitPlaintext[10]);
			TextView39.setText(splitPlaintext[11]);
			TextView40.setText(splitPlaintext[12]);
			TextView41.setText(splitPlaintext[13]);
			TextView42.setText(splitPlaintext[14]);
			TextView43.setText(splitPlaintext[15]);
			
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
