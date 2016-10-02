package com.example.Blueprint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;


public class DBEncode extends Activity{
	
private EditText inputWhere;
private EditText inputName;
	
	private EditText EdtID1,EdtID2,EdtID3;
	private EditText EdtChName,EdtEnName;
	private EditText EdtProvider,EdtMadeCompany,EdtPackCompany;
	private EditText EdtForm,EdtPackSpe;
	private EditText EdtYear,EdtDate;
	private EditText EdtWWW;
	
	private Button where;
	private ImageView outimage;
	private StringBuilder data;
	
	private TextView show;
	private TextView Text;
	
	private String uri1;
	private String uri2;
	
	RelativeLayout layout;
	
	private Button btnEncoded;
	
	private ImageView imgQRCode;
	private SQLiteDatabase db;
	private static final String CODE = "utf-8";  
    private static final int BLACK = 0xff000000;  
    private static final int WHITE = 0xFFFFFFFF;  
    private String filepath = "/sdcard/1.png";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dbencode);
        initView();
        DBHelper hmacDB = new DBHelper(getApplicationContext());
		db = hmacDB.getWritableDatabase();
    }

	private void initView() {
		// TODO Auto-generated method stub
		
        layout=(RelativeLayout)findViewById(R.id.rootlayout);
		
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
		
		where = (Button)findViewById(R.id.button2);
        where.setOnClickListener(onClickBtnLook);
        where.setText("選擇圖片");
        
        uri1 = new String();
        uri2 = new String();

        Text = (TextView)findViewById(R.id.textView2);
        
        btnEncoded = (Button)findViewById(R.id.btnEncoded);
		btnEncoded.setOnClickListener(onClickBtnEncoded);
        
		imgQRCode = (ImageView)findViewById(R.id.imgQRCode);
      
	}
	
	
	private Button.OnClickListener onClickBtnLook = new Button.OnClickListener() {	
		public void onClick(View v) {
	        Intent intent = new Intent();
	        //開啟Pictures畫面Type設定為image
	           intent.setType("image/*");
	            //使用Intent.ACTION_GET_CONTENT這個Action
	           //會開啟選取圖檔視窗選取手機內圖檔
	            intent.setAction(Intent.ACTION_GET_CONTENT); 
	        //取得相片後返回本畫面
	            startActivityForResult(intent, 1);
		}
	};
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //當使用者按下確定後
    if (resultCode == RESULT_OK) {
       //取得圖檔的路徑位置
       Uri uri = data.getData();
       //寫log
       Log.e("uri", uri.toString());
       //抽象資料的接口
       ContentResolver cr = this.getContentResolver();
     try {
       //由抽象資料接口轉換圖檔路徑為Bitmap
       Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
       //取得圖片控制項ImageView
       ImageView imageView = (ImageView) findViewById(R.id.imageView1);
       // 將Bitmap設定到ImageView
       imageView.setImageBitmap(bitmap);
      } catch (FileNotFoundException e) {
        Log.e("Exception", e.getMessage(),e);
      }
    }
      super.onActivityResult(requestCode, resultCode, data);
      
           Uri uri3 = data.getData();
           String[] proj = { MediaStore.Images.Media.DATA };
           Cursor actualimagecursor = managedQuery(uri3,proj,null,null,null);
           int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
           actualimagecursor.moveToFirst();
           String img_path = actualimagecursor.getString(actual_image_column_index);
           File file = new File(img_path);
           uri1 = String.valueOf(file);
           Text.setText(uri1);
           }
    

	
	 private Button.OnClickListener onClickBtnEncoded = new Button.OnClickListener() {
			public void onClick(View v) {
			
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
			
			String Plaintext1 = ID1;
			String Plaintext2 = ID2;
			String Plaintext3 = ID3;
			String Plaintext4 = ChName;
			String Plaintext5 = EnName;
			String Plaintext6 = Provider;
			String Plaintext7 = MadeCompany;
			String Plaintext8 = PackCompany;
			String Plaintext9 = Form;
			String Plaintext10 = PackSpe;
			String Plaintext11 = Year;
			String Plaintext12 = Date;
			String Plaintext13 = WWW;
			
			String keystring = "";
			
			String[] RegSNContent = { "0", "1", "2", "3", "4", "5", "6","7", "8", "9",
					"A", "B", "C", "D", "E", "F", "G","H", "I", "J","K", "L", "M", "N", "O",
					"P", "Q", "R", "S", "T","U", "V", "W", "X", "Y", "Z", "a","b", "c", "d",
					"e", "f", "g", "h", "i", "j", "k","l", "m", "n", "o", "p", "q", "r", "s",
					"t","u", "v", "w","x","y", "z", "~", "!", "@", "#", "$", "%", "^", "&",
					"*", "(", ")","_", "-", "+", "=", ",", ".", "/"};
									
			for (int i = 0; i < 8; i++) {
				keystring += RegSNContent[(char) (Math.random() * RegSNContent.length)];
			}
			
			ContentValues cv = new ContentValues();
			
			cv.put("Colname", keystring.toString());
	        cv.put("URI", uri1.toString());
	        cv.put("ID1", Plaintext1.toString());
	        cv.put("ID2", Plaintext2.toString());
	        cv.put("ID3", Plaintext3.toString());
	        cv.put("ChName", Plaintext4.toString());
	        cv.put("EnName", Plaintext5.toString());
	        cv.put("Provider", Plaintext6.toString());
	        cv.put("MadeCompany", Plaintext7.toString());
	        cv.put("PackCompany", Plaintext8.toString());
	        cv.put("Form", Plaintext9.toString());
	        cv.put("PackSpe", Plaintext10.toString());
	        cv.put("Year", Plaintext11.toString());
	        cv.put("Date", Plaintext12.toString());
	        cv.put("WWW", Plaintext13.toString());
	        
	        db.insert(DBHelper.TABLE_NAME, null, cv);
			
			String HMacWithPlaint = keystring;
			Bitmap image = null;          
			try {
				image = createTwoQRCode(HMacWithPlaint);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          
             if(image != null){
                 
            	 imgQRCode.setImageBitmap(image);
            	 image = BitmapFactory.decodeFile(filepath);
             }
			}
	 };
	
	public Bitmap createTwoQRCode(String content) throws Exception {
        
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
        BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 500, 500, hints);
        
        int width = matrix.getWidth();
        
        int height = matrix.getHeight();
        
        int[] pixels = new int[width * height];
        
        for(int y = 0;y<height;y++){
            for(int x = 0;x<width;x++){
                if(matrix.get(x, y)){
                    
                    pixels[y * width +x] = 0xff000000;
                }
            }
        }
        
        Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        
        bm.setPixels(pixels, 0, width, 0, 0, width, height);
        
        return bm;
    }
}

