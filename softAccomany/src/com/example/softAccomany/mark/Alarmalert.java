package com.example.softAccomany.mark;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.example.android_robot_01.R;
import com.example.softAccomary.guide.WeixinChatDemoActivity2;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class Alarmalert extends Activity  {
	int notificationID =1;
	private MediaPlayer mp;
	private Vibrator v;
	Action action;
	AudioManager am;
	private ActivityManager am1;

	int id;
	String str_id ;
	
	 int num=1; 

	String my_time;
	long millionSeconds;
	
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		Log.i("AlarmAlert","ljk");	
		am1 = ActivityManager.getInstance();
		am1.addActivity(this);
		
		v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		mp = new MediaPlayer();
        am = (AudioManager)getSystemService(AUDIO_SERVICE);
		
        Log.i("Alarm", Environment.getExternalStorageDirectory()+".");
		
		Bundle bundle1 = getIntent().getExtras();
		str_id=bundle1.getString("action_id1");
//		String rings = intent.getStringExtra(Environment.getExternalStorageDirectory()+"/a.mp3");
	
		Log.i("��CallAlarm���յ���id",str_id);
		Log.i("alarmalert","Ŀ���");
		id = Integer.parseInt(str_id);
		Log.i("Alarmalert",id+".");
		DBHelper helper = new DBHelper(Alarmalert.this);
		helper.openDatabase();
		action = helper.query(id);
		Log.i("AlarmalertAction_id1",action.num_id+"");	
		Log.i("AlarmalertAction_flag1",action.flag+"");	
		//ת��Ϊ���� 
		my_time=action.time;
		Log.i("action��ʱ��",my_time);
		 SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		 try {
			long millionSeconds = sdf.parse(my_time).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		action.flag = Integer.valueOf(1);
		Log.i("AlarmalertAction_id2",action.num_id+"");	
		Log.i("AlarmalertAction_flag2",action.flag+"");	
		helper.modify(action);
		Log.i("actiontitle",action.actiontitle);

		displayNotifacation();
		try {
        mp.setDataSource(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));

//		if(rings.equals("")){
//			mp.setDataSource(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
//		}else{
//			mp.setDataSource(this, Uri.parse(rings));
//		}
			/*new AlertDialog.Builder(this)
			.setTitle(action.time)
			.setMessage("ffd")
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {
					mp.stop();
					v.cancel();
					finish();
				}
				
			}).show();
			
			*/
        /*
        ran= 10*Math.random();
		ran1=(int)ran;
		i=ran1;
		m=ran1;
    	*/	
    		
       //setStartAlertDialog();
		
		
			/*
			if(action.remidtype.equals("����")){		
				mp.setAudioStreamType(AudioManager.STREAM_ALARM);
				mp.setLooping(true);
				mp.prepare();
				mp.start();}
			if(action.remidtype.equals("��")){
				v.vibrate(new long[]{1000,3000,500,2000}, 2);
			}
			if(action.remidtype.equals("�񶯺�����")){	
				v.vibrate(new long[]{1000,3000,500,2000}, 2);
				mp.setAudioStreamType(AudioManager.STREAM_ALARM);
				mp.setLooping(true);
				mp.prepare();
				mp.start();
			}
			*/
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	 public void displayNotifacation() {
		 Log.i("�Ƿ�ִ��֪ͨ��","yes");
			
			
		num++;
        // ����һ��NotificationManager������   
        NotificationManager notificationManager = (NotificationManager)    
            this.getSystemService(android.content.Context.NOTIFICATION_SERVICE);   
          
        // ����Notification�ĸ�������   
        Notification notification =new Notification(R.drawable.ic_launcher,   
                "��ŷ������Ϣ", millionSeconds); 
        //FLAG_AUTO_CANCEL   ��֪ͨ�ܱ�״̬���������ť�������
        //FLAG_NO_CLEAR      ��֪ͨ���ܱ�״̬���������ť�������
        //FLAG_ONGOING_EVENT ֪ͨ��������������
        //FLAG_INSISTENT     �Ƿ�һֱ���У���������һֱ���ţ�֪���û���Ӧ
       // notification.flags |= Notification.FLAG_ONGOING_EVENT; // ����֪ͨ�ŵ�֪ͨ����"Ongoing"��"��������"����   
       // notification.flags |= Notification.FLAG_AUTO_CANCEL; // �����ڵ����֪ͨ���е�"���֪ͨ"�󣬴�֪ͨ�������������FLAG_ONGOING_EVENTһ��ʹ��   
        //notification.flags |= Notification.FLAG_SHOW_LIGHTS;   
        //DEFAULT_ALL     ʹ������Ĭ��ֵ�������������𶯣������ȵ�
        //DEFAULT_LIGHTS  ʹ��Ĭ��������ʾ
        //DEFAULT_SOUNDS  ʹ��Ĭ����ʾ����
        //DEFAULT_VIBRATE ʹ��Ĭ���ֻ��𶯣������<uses-permission android:name="android.permission.VIBRATE" />Ȩ��
        //notification.defaults = Notification.DEFAULT_LIGHTS; 
        //����Ч������
        //notification.defaults=Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND;
       // notification.ledARGB = Color.BLUE;   
        //notification.ledOnMS =5000; //����ʱ�䣬����
          
        // ����֪ͨ���¼���Ϣ   
        CharSequence contentTitle ="��ŷ��ʱ�䵽"; // ֪ͨ������   
        CharSequence contentText ="����"; // ֪ͨ������   
        Intent notificationIntent =new Intent(Alarmalert.this, WeixinChatDemoActivity2.class); // �����֪ͨ��Ҫ��ת��Activity   
        Bundle mBundle = new Bundle();  
        mBundle.putString("action2",str_id);
        notificationIntent.putExtras(mBundle);
        PendingIntent contentItent = PendingIntent.getActivity(this, 1, notificationIntent, 0); 
        Log.i("��õ�action",str_id);
        notification.setLatestEventInfo(this, contentTitle, contentText, contentItent);   
        notification.vibrate = new long[] {100,250,100,500};
       // Notification.Builder builder = new Notification.Builder(mContext);
    	
        // ��Notification���ݸ�NotificationManager   
        notificationManager.notify(1, notification);   
    
        		}
	 
	 private void clearNotification(){
	        // ������ɾ��֮ǰ���Ƕ����֪ͨ   
	        NotificationManager notificationManager = (NotificationManager) this
	                .getSystemService(NOTIFICATION_SERVICE);   
	        notificationManager.cancel(0);  
	  
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
			AlertDialog.Builder adb = new Builder(Alarmalert.this);
			adb.setTitle("��������");
			adb.setMessage("");
			adb.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(Alarmalert.this, "Thanks",
							Toast.LENGTH_SHORT).show();
				}
			});
			adb.show();
			break;
		case 2:
			AlertDialog.Builder adb1 = new Builder(Alarmalert.this);
			adb1.setTitle("��Ϣ");
			adb1.setMessage("���Ҫ�˳���");
			adb1.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					am1.exitAllProgress();
				}
			});
			adb1.setNegativeButton("ȡ��", null);
			adb1.show();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
