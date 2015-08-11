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
import android.widget.ListView;
import android.widget.TextView;

public class GameRoom extends Activity {
	private TextView txtroomname;
	private SharedPreferences preference;
	private String readdata;
	
	//�M��+�H���C��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_room);
		
		//���o��������
		txtroomname=(TextView)findViewById(R.id.textView8);
		
		//�M���x�s��
		preference=getSharedPreferences("creatroom",MODE_PRIVATE);
		//�����
		readdata=preference.getString("data","unknown");
		//���
		txtroomname.setText(readdata);	
		
		//�������}�l~~
		new AlertDialog.Builder(GameRoom.this)
		.setTitle("~~Start~~")
		.setIcon(R.drawable.ic_launcher)
		.setMessage("Sure to Start?")
		.setPositiveButton("Sure",new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialoginterface,int i){
				Intent intent=new Intent();
				intent.setClass(GameRoom.this,Title.class);
				startActivity(intent);
			}
		})
		.show();
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {//������^��
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {   
            ConfirmExit();//����^��A�h����h�X�T�{
            return true;   
        }   
        return super.onKeyDown(keyCode, event);   
    }
    public void ConfirmExit(){//�h�X�T�{
        AlertDialog.Builder ad=new AlertDialog.Builder(GameRoom.this);
        ad.setTitle("Leave Room");
        ad.setMessage("Sure to Leave GameRoom?");
        ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {//�h�X���s
            public void onClick(DialogInterface dialog, int i) {
                // TODO Auto-generated method stub
            	Intent intent=new Intent();
				intent.setClass(GameRoom.this,OutSide.class);
				startActivity(intent);
                GameRoom.this.finish();//����activity
  
            }
        });
        ad.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                //���h�X���ΰ������ާ@
            }
        });
        ad.show();//�ܹ�ܮ�
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_room, menu);
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
