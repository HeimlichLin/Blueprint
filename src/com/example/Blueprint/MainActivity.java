package com.example.Blueprint;

import com.example.Blueprint.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity{
	
	private Button btn1,btn2,btn3,btn4,btn5;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
       
        btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(onClickBtn1);
        btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(onClickBtn2);
        btn3 = (Button)findViewById(R.id.button3);
        btn3.setOnClickListener(onClickBtn3);
        btn4 = (Button)findViewById(R.id.button4);
        btn4.setOnClickListener(onClickBtn4);
        btn5 = (Button)findViewById(R.id.button5);
        btn5.setOnClickListener(onClickBtn5);
        
       
    }

	 private Button.OnClickListener onClickBtn1 = new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
	           	intent.setClass(MainActivity.this,SampleMainActivity.class);
	           	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	           	
	           	startActivity(intent);
				}
			
	};
	
	 private Button.OnClickListener onClickBtn2 = new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
	           	intent.setClass(MainActivity.this,HashMainActivity.class);
	           	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	           	
	           	startActivity(intent);;
				}
			
	};
	
	 private Button.OnClickListener onClickBtn3 = new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
	           	intent.setClass(MainActivity.this,MedMainActivity.class);
	           	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	           	
	           	startActivity(intent);;
				}
			
	};
	
	 private Button.OnClickListener onClickBtn4 = new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
	           	intent.setClass(MainActivity.this,StructuredShpMainActivity.class);
	           	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	           	
	           	startActivity(intent);;
				}
			
	};
	
	 private Button.OnClickListener onClickBtn5 = new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
	           	intent.setClass(MainActivity.this,DBMainActivity.class);
	           	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	           	
	           	startActivity(intent);
			}
	};
}
