package com.example.Blueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MedMainActivity extends Activity{
	
	private Button btnEn,btnDe;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.medmain);
       
        btnEn = (Button)findViewById(R.id.btnEn);
        btnEn.setOnClickListener(onClickBtnEncoded);
        
        btnDe = (Button)findViewById(R.id.btnDe);
        btnDe.setOnClickListener(onClickBtnDecoded);
    }

	 private Button.OnClickListener onClickBtnEncoded = new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
	           	intent.setClass(MedMainActivity.this,MedEncode.class);
	           	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

	           	startActivity(intent);

				}
			
	};
	
	 private Button.OnClickListener onClickBtnDecoded = new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
	           	intent.setClass(MedMainActivity.this,MedDecode.class);
	           	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

	           	startActivity(intent);

			}
	};
}
