package com.example.jeremy8;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btnEnd;   /*�g�ϥΪ̦W�٭������s-"����"*/
	private SharedPreferences preference;
	private String sname,msg;
	private TextView txtName;
	private EditText edtName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		       //���o��������
				btnEnd=(Button)findViewById(R.id.button1);
				txtName=(TextView)findViewById(R.id.textView1);
				edtName=(EditText)findViewById(R.id.editText1);
				//���ƥ�[�J��ť
				btnEnd.setOnClickListener(listener);
				
				preference=getSharedPreferences("username",MODE_PRIVATE);
				sname=preference.getString("name", "");
				
				//�p�G���إ߸�ƴN��ܿ�J���
				if(sname.equals("")){
					msg="Welcome!Please enter your name!";
					//�u�X�w�����
					Toast welcome1=
							Toast.makeText(MainActivity.this,
									msg, Toast.LENGTH_LONG);
					welcome1.show();
					
					//�⥴�r�ϳ]�w���i��
					txtName.setVisibility(TextView.VISIBLE);
					edtName.setVisibility(EditText.VISIBLE);
				}else{
					msg="Hi!"+sname+"!";
					//�u�X�w�����
					Toast welcome2=
							Toast.makeText(MainActivity.this,
									msg, Toast.LENGTH_LONG);
					welcome2.show();
					//������h�D����
					Intent intent=new Intent();
					intent.setClass(MainActivity.this,SelfSpace.class);
					startActivity(intent);
					
				}
				
				
				
	}
	
	//���s�B�z�ƥ�
		private Button.OnClickListener listener=
				new Button.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent();
				intent.setClass(MainActivity.this,SelfSpace.class);
				startActivity(intent);
			}
		};
		
		//�x�s���(�ϥΪ̦W��)
		protected void onStop(){
			super.onStop();
			if(sname.equals("")){//�p�G����J��ƴN�N��J�Ȧs��
				preference.edit()
				.putString("name",edtName.getText().toString())
				.commit();
			}
		}
		
		public boolean onKeyDown(int keyCode, KeyEvent event) {//������^��
	        if ((keyCode == KeyEvent.KEYCODE_BACK)) {   
	            ConfirmExit();//����^��A�h����h�X�T�{
	            return true;   
	        }   
	        return super.onKeyDown(keyCode, event);   
	    }
	    public void ConfirmExit(){//�h�X�T�{
	        AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
	        ad.setTitle("���}");
	        ad.setMessage("�T�w�n���}?");
	        ad.setPositiveButton("�O", new DialogInterface.OnClickListener() {//�h�X���s
	            public void onClick(DialogInterface dialog, int i) {
	                // TODO Auto-generated method stub
	            	MainActivity.this.finish();//����activity
	  
	            }
	        });
	        ad.setNegativeButton("�_",new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int i) {
	                //���h�X���ΰ������ާ@
	            }
	        });
	        ad.show();//�ܹ�ܮ�
	    }
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
