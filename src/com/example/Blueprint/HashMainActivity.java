package com.example.Blueprint;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class HashMainActivity extends Activity {

	private static final int ZXING_SCAN = 3;
	private EditText input;
	private Button goHash;
	private Button outHash;
	private ImageView outimage;
	private TextView txtResult;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hashmain);
        input = (EditText) findViewById(R.id.editText1);
        goHash = (Button)findViewById(R.id.button1);
        goHash.setOnClickListener(onClickBtnEncode);
        outHash = (Button) findViewById(R.id.button2);
		outHash.setOnClickListener(onClickBtnDecode);
        outimage = (ImageView)findViewById(R.id.imageView1);
        txtResult = (TextView) findViewById(R.id.textView1);

    }
	
	private Button.OnClickListener onClickBtnEncode = new Button.OnClickListener() {
			public void onClick(View v) {
			String HashInput = input.getText().toString();
			String HashOutput = "";
			
			try {
				HashOutput = HashActivity.SHA1(HashInput)+'\n'+'\n'+HashInput;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outimage.setImageBitmap(encodeAsBitmap(HashOutput, BarcodeFormat.QR_CODE, 300, 150));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	};

	public static Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int desiredWidth, int desiredHeight) throws WriterException
    {
	if (contents.length() == 0) return null;
	final int WHITE = 0xFFFFFFFF;
	final int BLACK = 0xFF000000;
	HashMap<EncodeHintType, String> hints = null;
	String encoding = null;
	for (int i = 0; i < contents.length(); i++)
	{
	    if (contents.charAt(i) > 0xFF)
	    {
		encoding = "UTF-8";
		break;
	    }
	}
	if (encoding != null)
	{
	    hints = new HashMap<EncodeHintType, String>(2);
	    hints.put(EncodeHintType.CHARACTER_SET, encoding);
	}
	MultiFormatWriter writer = new MultiFormatWriter();
	BitMatrix result = writer.encode(contents, format, desiredWidth, desiredHeight, hints);
	int width = result.getWidth();
	int height = result.getHeight();
	int[] pixels = new int[width * height];
	for (int y = 0; y < height; y++)
	{
	    int offset = y * width;
	    for (int x = 0; x < width; x++)
	    {
		pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
	    }
	}
	Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
	bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
	return bitmap;
    }

	private OnClickListener onClickBtnDecode = new OnClickListener() 
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
			//Toast.makeText(this, "ZXING_SCAN", 1).show();
			if (resultCode == RESULT_OK) {
				
				//time1 = System.currentTimeMillis();
				//System.out.println("time1:"+ (time1) + "毫秒");

				String contents = intent.getStringExtra("SCAN_RESULT");
				String qrCodeHash = contents.substring(0, 40);
				 //Toast.makeText(this, "qrCodeHmac", 10).show();
				String comPareHash = "";
				String qrCodePlaintext = contents.substring(42);
				 //Toast.makeText(this, "qrCodePlaintext", 10).show();

				try {
					comPareHash = HashActivity.SHA1(qrCodePlaintext);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
				
				if (qrCodeHash.equals(comPareHash)) {
					String[] splitPlaintext = qrCodePlaintext.split("%");
					for (int i = 0; i < qrCodePlaintext.length(); i++)
					{
						txtResult.setText(splitPlaintext[0]);
					}
				}else {
					Toast.makeText(this, "驗證錯誤", Toast.LENGTH_SHORT).show();
				}
				
				
			}
		}
	 else {
		Toast.makeText(this, "ZXING_SCAN<>3", Toast.LENGTH_SHORT).show();
		}
	}
}