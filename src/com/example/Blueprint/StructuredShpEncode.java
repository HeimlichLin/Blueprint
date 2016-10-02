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


public class StructuredShpEncode extends Activity {

	private EditText input;
	
	private EditText EdtID1,EdtID2,EdtID3;
	private EditText EdtChName,EdtEnName;
	private EditText EdtProvider,EdtMadeCompany,EdtPackCompany;
	private EditText EdtForm,EdtPackSpe;
	private EditText EdtYear,EdtDate;
	private EditText EdtWWW;
	private Button lookSome;
	private Button goHash;
	private ImageView outimage;
	private StringBuilder data;
	private String mx;
	private String my;
	private String mx3;
	private String my3;
	private TextView show;
	RelativeLayout layout;
	TextView msg;
	TextView msx;
	TextView msy;
	private float touchX;
	private float touchY;
	private int   tvWidth  = LayoutParams.WRAP_CONTENT;
	private int   tvHeight = LayoutParams.WRAP_CONTENT;
	   
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.structuredshpencode);
        layout=(RelativeLayout)findViewById(R.id.rootlayout);
        msg=(TextView)findViewById(R.id.textView1);
        msx=(TextView)findViewById(R.id.textView4);
        msy=(TextView)findViewById(R.id.textView5);
        data=new StringBuilder();
        mx = new String();
        my = new String();
        
        mx3 = new String();
        my3 = new String();
      //註冊監聽物件
        layout.setOnTouchListener(new MytouchListener());
        
      //觸控監聽物件之類別，需實作OnTouchListener
        
        lookSome = (Button)findViewById(R.id.button2);
        lookSome.setOnClickListener(onClickBtnLook);
        goHash = (Button)findViewById(R.id.button1);
        goHash.setOnClickListener(onClickBtnEncoded);
        outimage = (ImageView)findViewById(R.id.imageView2);
        input = (EditText) findViewById(R.id.editText1);
        show = (TextView) findViewById(R.id.textView2);
        EdtID1 = (EditText)findViewById(R.id.EdtID1);
		EdtID2 = (EditText)findViewById(R.id.EdtID2);
		EdtID3 = (EditText)findViewById(R.id.EdtID3);
		EdtChName = (EditText)findViewById(R.id.EdtChName);
		EdtEnName = (EditText)findViewById(R.id.EdtEnName);
		EdtProvider = (EditText)findViewById(R.id.EdtProvider);
		EdtMadeCompany = (EditText)findViewById(R.id.EdtMadeCompany);
		EdtPackCompany = (EditText)findViewById(R.id.EdtPackCompany);
		EdtForm = (EditText)findViewById(R.id.EdtForm);
		EdtPackSpe = (EditText)findViewById(R.id.EdtPackSpe);
		EdtYear = (EditText)findViewById(R.id.EdtYear);
		EdtDate = (EditText)findViewById(R.id.EdtDate);
		EdtWWW = (EditText)findViewById(R.id.EdtWWW);
    }
	
	private class MytouchListener implements OnTouchListener{
		//實作OnTouchListener所需實作之方法
		public boolean onTouch(View viw, MotionEvent event){
			//判斷觸控事件
	    	switch(event.getAction()){
	    	case MotionEvent.ACTION_DOWN:
	    		data.delete(0, data.length());
	    		msg.setText(data.append("Action down"+"("+event.getX()+","+event.getY()+")"+"\n"));
	    		
	    		break;
	    	case MotionEvent.ACTION_UP:
	    		msg.setText(data.append("Action up"+"("+event.getX()+","+event.getY()+")"+"\n"));
	    		msx.setText("X軸目標位置:"+String.valueOf(event.getX()));
	    		msy.setText("Y軸目標位置:"+String.valueOf(event.getY()));
	    		mx = String.valueOf(event.getX());
	    		my = String.valueOf(event.getY());
	    		String mx1 = mx.toString();
				int num1 = (int)(Float.parseFloat(mx1)+0.5f);//�?�?
				show.setX(num1);
				String my2 = my.toString();
				int num2 = (int)(Float.parseFloat(my2)+0.5f);//�?�?
				show.setY(num2);
	    		break;	
	    	case MotionEvent.ACTION_MOVE:
	    		msg.setText(data.append("Action move"+"("+event.getX()+","+event.getY()+")"+"\n"));
	    		
	    		break;	
	    	}
	    	//取得多點觸控的點數
	    	
	    	int pCount=event.getPointerCount();
	    	data.append("多點觸控點數為:"+pCount+"\n");
	    	for(int i=0;i<pCount;i++){
	    	//(event.getX(0),event.getY(0))代表第一點觸控點之座標
	    		data.append(String.format("觸控點%d:(%.2f, %.2f)%n",event.getPointerId(i),event.getX(i),event.getY(i)));
	    	}
	    	msg.setText(data);
	    	return true;
	    	
	    	}
	    }
		
	private Button.OnClickListener onClickBtnLook = new Button.OnClickListener() {	
		public void onClick(View v) {
		String input1 = input.getText().toString();
			show.setText(input1);	
		}
	};
		
		private Button.OnClickListener onClickBtnEncoded = new Button.OnClickListener() {
			public void onClick(View v) {
				
			String where = input.getText().toString();	
			
			String mx1 = mx.toString();
			int num1 = (int)(Float.parseFloat(mx1)+0.5f);//正確
			String mx2 = String.valueOf(num1);//正確
			
			String my1 = my.toString();
			int num2 = (int)(Float.parseFloat(my1)+0.5f);//�?�?
			String my2 = String.valueOf(num2);//�?�?
			
			String ID1 = EdtID1.getText().toString();
			String ID2 = EdtID2.getText().toString();
			String ID3 = EdtID3.getText().toString();
			String ChName = EdtChName.getText().toString();
			String EnName = EdtEnName.getText().toString();
			String Provider = EdtProvider.getText().toString();
			String MadeCompany = EdtMadeCompany.getText().toString();
			String PackCompany = EdtPackCompany.getText().toString();
			String Form = EdtForm.getText().toString();
			String PackSpe = EdtPackSpe.getText().toString();
			String Year = EdtYear.getText().toString();
			String Date = EdtDate.getText().toString();
			String WWW = EdtWWW.getText().toString();
			
			Log.i("mx1", mx1);
			Log.i("my1", my1);
			
			String Plaintext = "shp" +"%"+ where +"%"+ mx2 +"%"+ my2 +"%"+ ID1 +"%"+ ID2 +"%"+ ID3+"%"+ChName+"%"+EnName+"%"
					  +Provider+"%"+MadeCompany+"%"+PackCompany+"%"+Form
					  +"%"+PackSpe+"%"+Year+"%"+Date+"%"+WWW;
			
			String HashInput = msg.getText().toString();
			String HashOutput = "";
			
			try {
				HashOutput = HashActivity.SHA1(Plaintext)+'\n'+'\n'+Plaintext;
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

    
}