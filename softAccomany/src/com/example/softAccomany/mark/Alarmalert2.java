package com.example.softAccomany.mark;

import java.io.IOException;

import com.example.android_robot_01.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class Alarmalert2 extends Activity {

	private MediaPlayer mp;
	private Vibrator v;
	private Lesson lesson;
	AudioManager am;
	private ActivityManager am1;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		Log.i("AlarmAlert","ljk");
		
		am1 = ActivityManager.getInstance();
		am1.addActivity(this);
		
		v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		mp = new MediaPlayer();
        am = (AudioManager)getSystemService(AUDIO_SERVICE);
		
		Intent intent = getIntent();
		String str_id = intent.getStringExtra("class_id");
		int id = Integer.parseInt(str_id);
		Log.i("Alarmalert",id+".");
		DBHelper helper = new DBHelper(Alarmalert2.this);
		helper.openDatabase();
		lesson = helper.queryLesson(id);

	try {
        mp.setDataSource(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
      
			new AlertDialog.Builder(this)
			.setTitle(lesson.classstarttime)
			.setMessage(lesson.classname+"--"+lesson.classroom)
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {
					mp.stop();
					v.cancel();
					finish();
				}
				
			}).show();

			if(lesson.classremidtype.equals("����")){		
				mp.setAudioStreamType(AudioManager.STREAM_ALARM);
				mp.setLooping(true);
				mp.prepare();
				mp.start();}
			if(lesson.classremidtype.equals("��")){
				v.vibrate(new long[]{1000,3000,500,2000}, 2);
			}
			if(lesson.classremidtype.equals("�񶯺�����")){	
				v.vibrate(new long[]{1000,3000,500,2000}, 2);
				mp.setAudioStreamType(AudioManager.STREAM_ALARM);
				mp.setLooping(true);
				mp.prepare();
				mp.start();
			}

			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	  
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
			mp.stop();
		}
		super.onConfigurationChanged(newConfig);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 1, "����");
		menu.add(0, 2, 2, "�˳�");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			AlertDialog.Builder adb = new Builder(Alarmalert2.this);
			adb.setTitle("��������");
			adb.setMessage("");
			adb.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(Alarmalert2.this, "Thanks",
							Toast.LENGTH_SHORT).show();
				}
			});
			adb.show();
			break;
		case 2:
			AlertDialog.Builder adb1 = new Builder(Alarmalert2.this);
			adb1.setTitle("��Ϣ");
			adb1.setMessage("���Ҫ�˳���");
			adb1.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					{//am1.exitAllProgress();
		            	Intent home = new Intent(Intent.ACTION_MAIN);  
		            	home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
		            	home.addCategory(Intent.CATEGORY_HOME);  
		            	startActivity(home); 
		                finish();  
		                 }
				}
			});
			adb1.setNegativeButton("ȡ��", null);
			adb1.show();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
