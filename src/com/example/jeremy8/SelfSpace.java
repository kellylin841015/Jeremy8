package com.example.jeremy8;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SelfSpace extends Activity {
	private ListView lstPrefer;
	private int[] rexIds=new int[]{R.drawable.pictitle01,R.drawable.pictitle02,
			R.drawable.pictitle03,R.drawable.pictitle01,R.drawable.pictitle02,
			R.drawable.pictitle03,R.drawable.pictitle01,R.drawable.pictitle02,
			R.drawable.pictitle03};
	private String[] roomName=new String[]{"Animal","Human","Food","Animal",
			"Human","Food","Animal","Human","Food"};
	private String[] ans=new String[]{"giraffe","hero","rice","giraffe",
			"hero","rice","giraffe","hero","rice"};
	private Button btnoutside,btnname;
	private SharedPreferences preference;
	private String readname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_self_space);
		
		//���o�귽���O�ɤ���������
		lstPrefer=(ListView)findViewById(R.id.listView1);
				
		//���o���s��������
		btnname=(Button)findViewById(R.id.button2);
		btnoutside=(Button)findViewById(R.id.button3);		
		
		//Ĳ�o�ƥ�
		btnoutside.setOnClickListener(btnResponse);
		btnname.setOnClickListener(btnResponse);
						
		//�إߦۭqadapter
		MyAdapter adapter=new MyAdapter(this);
				
		//�]�wlistview����ƨӷ�
		lstPrefer.setAdapter(adapter);
		
		//�M���x�s��
		preference=getSharedPreferences("username",MODE_PRIVATE);
		//Ū�����
		readname=preference.getString("name","unknown");
		//���s��ܩm�W
		btnname.setText(readname);
		
		//�]�witemclick�ƥ�
		lstPrefer.setOnItemClickListener(lstPreferListener);
		
		}
	
	// ���sĲ�o�ƥ�
	private Button.OnClickListener btnResponse=
			new Button.OnClickListener(){
		@Override
		public void onClick(View v){
			
			switch(v.getId()){
			case R.id.button2:
				Intent intent1=new Intent();
				intent1.setClass(SelfSpace.this,ChangName.class);
				startActivity(intent1);
				break;
			case R.id.button3:
				Intent intent2=new Intent();
				intent2.setClass(SelfSpace.this,OutSide.class);
				startActivity(intent2);
				break;
			default:
				break;			
			}
		}
	};
		
		public class MyAdapter extends BaseAdapter{
			private LayoutInflater myInflater;
			public MyAdapter(Context c){
				myInflater=LayoutInflater.from(c);
			}
			
			@Override
			public int getCount(){
				return ans.length;
			}
			
			@Override
			public Object getItem(int position){
				return ans[position];
			}
			
			@Override
			public long getItemId(int position){
				return position;
			}
			
			@Override
			public View getView(int position,View convertView,
					ViewGroup parent){
				
				//���o�ۭq����
				convertView=myInflater.inflate(R.layout.mylayout,null);
				
				//���o mylayout.xml �������
				ImageView pictitle=(ImageView)
						convertView.findViewById(R.id.imageview1);
				TextView txtName=(TextView)
						convertView.findViewById(R.id.textView2);
				TextView txtengName=(TextView)
						convertView.findViewById(R.id.textView3);
				
				
				//�]�w���󤺮e
				pictitle.setImageResource(rexIds[position]);
				txtName.setText(roomName[position]);
				txtengName.setText(ans[position]);
				
				return convertView;
			}
			
		}
		
		//�w�qonItemClick��k
		private ListView.OnItemClickListener lstPreferListener=
				new ListView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent,
					View view,int position,long id){
				//���listView�ﶵ���e
				Intent intent3=new Intent();
				intent3.setClass(SelfSpace.this,FileContent.class);
				startActivity(intent3);
			}
		};
		
		public boolean onKeyDown(int keyCode, KeyEvent event) {//������^��
	        if ((keyCode == KeyEvent.KEYCODE_BACK)) {   
	            ConfirmExit();//����^��A�h����h�X�T�{
	            return true;   
	        }   
	        return super.onKeyDown(keyCode, event);   
	    }
	    public void ConfirmExit(){//�h�X�T�{
	        AlertDialog.Builder ad=new AlertDialog.Builder(SelfSpace.this);
	        ad.setTitle("���}");
	        ad.setMessage("�T�w�n���}?");
	        ad.setPositiveButton("�O", new DialogInterface.OnClickListener() {//�h�X���s
	            public void onClick(DialogInterface dialog, int i) {
	                // TODO Auto-generated method stub
	            	SelfSpace.this.finish();//����activity
	  
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
		getMenuInflater().inflate(R.menu.self_space, menu);
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
