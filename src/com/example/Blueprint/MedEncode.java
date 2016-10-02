package com.example.Blueprint;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Hashtable;

import com.example.Blueprint.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MedEncode extends Activity{
	
	private EditText EdtID1,EdtID2,EdtID3;
	private EditText EdtChName,EdtEnName;
	private EditText EdtProvider,EdtMadeCompany,EdtPackCompany;
	private EditText EdtForm,EdtPackSpe;
	private EditText EdtYear,EdtDate;
	private EditText EdtWWW;

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
        setContentView(R.layout.medencode);
        initView();
        MedDBHelper hmacDB = new MedDBHelper(getApplicationContext());
		db = hmacDB.getWritableDatabase();
    }

	private void initView() {
		// TODO Auto-generated method stub
		
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
		
	    btnEncoded = (Button)findViewById(R.id.btnEncoded);
		btnEncoded.setOnClickListener(onClickBtnEncoded);
		
		imgQRCode = (ImageView)findViewById(R.id.imgQRCode);
      
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
			String Plaintext = ID1 +"%"+ ID2 +"%"+ ID3+"%"+ChName+"%"+EnName+"%"
							  +Provider+"%"+MadeCompany+"%"+PackCompany+"%"+Form
							  +"%"+PackSpe+"%"+Year+"%"+Date+"%"+WWW;//
			String keystring = "";
			String PlaintextHMAC = "";
			int keylength = 160;
			String PlaintextHash = "";
			String[] RegSNContent = { "0", "1", "2", "3", "4", "5", "6",
					"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
					"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
					"T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d",
					"e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
					"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
					"~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")",
					"_", "-", "+", "=", ",", ".", "/" };
									
			for (int i = 0; i < keylength; i++) {
				keystring += RegSNContent[(char) (Math.random() * RegSNContent.length)];
			}

			try {
				PlaintextHash = HashActivity.SHA1(Plaintext);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace(); 
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			try {
				PlaintextHMAC = HMAC.calculateHMAC(Plaintext, keystring);
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
			
			ContentValues cv = new ContentValues();  
			cv.put("Plaintext", Plaintext.toString()); 
	        cv.put("PlaintextHash", PlaintextHash.toString());  
	        cv.put("Key", keystring.toString());
	        db.insert(MedDBHelper.TABLE_NAME, null, cv);
			
			String HMacWithPlaint = PlaintextHMAC + Plaintext;//
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

