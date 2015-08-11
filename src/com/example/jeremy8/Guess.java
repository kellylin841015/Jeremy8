package com.example.jeremy8;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Guess extends Activity {
	private SharedPreferences preference;
	private EditText edtguess;
	private TextView mTextView;//�˼ƭp��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess);
		
		//���o��������
		edtguess=(EditText)findViewById(R.id.editText6);
		
		preference=getSharedPreferences("guess",MODE_PRIVATE);
		
		//�˼ƭp��
		time();
	}
	
	public void time(){	
		// �˼ƭp��     
		mTextView = (TextView)findViewById(R.id.timeView3);
		new CountDownTimer(5000,1000){
		            
			@Override
			public void onFinish() {
			// TODO Auto-generated method stub
				mTextView.setText("Time is up");
				//����~��
				Intent intent=new Intent();
				intent.setClass(Guess.this,EndGame.class);
				startActivity(intent);
			}

			@Override
			public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			mTextView.setText("seconds remaining:"+millisUntilFinished/1000);
			}
			            
			}.start();
		}
	
	//�x�s���(�ϥΪ̦W��)
	protected void onStop(){
		super.onStop();
			preference.edit()
			.putString("name",edtguess.getText().toString())
			.commit();
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guess, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
