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
	
		Log.i("从CallAlarm接收到的id",str_id);
		Log.i("alarmalert","目标地");
		id = Integer.parseInt(str_id);
		Log.i("Alarmalert",id+".");
		DBHelper helper = new DBHelper(Alarmalert.this);
		helper.openDatabase();
		action = helper.query(id);
		Log.i("AlarmalertAction_id1",action.num_id+"");	
		Log.i("AlarmalertAction_flag1",action.flag+"");	
		//转化为毫秒 
		my_time=action.time;
		Log.i("action的时间",my_time);
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
			if(action.remidtype.equals("铃声")){		
				mp.setAudioStreamType(AudioManager.STREAM_ALARM);
				mp.setLooping(true);
				mp.prepare();
				mp.start();}
			if(action.remidtype.equals("振动")){
				v.vibrate(new long[]{1000,3000,500,2000}, 2);
			}
			if(action.remidtype.equals("振动和铃声")){	
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
		 Log.i("是否执行通知栏","yes");
			
			
		num++;
        // 创建一个NotificationManager的引用   
        NotificationManager notificationManager = (NotificationManager)    
            this.getSystemService(android.content.Context.NOTIFICATION_SERVICE);   
          
        // 定义Notification的各种属性   
        Notification notification =new Notification(R.drawable.ic_launcher,   
                "雷欧发来消息", millionSeconds); 
        //FLAG_AUTO_CANCEL   该通知能被状态栏的清除按钮给清除掉
        //FLAG_NO_CLEAR      该通知不能被状态栏的清除按钮给清除掉
        //FLAG_ONGOING_EVENT 通知放置在正在运行
        //FLAG_INSISTENT     是否一直进行，比如音乐一直播放，知道用户响应
       // notification.flags |= Notification.FLAG_ONGOING_EVENT; // 将此通知放到通知栏的"Ongoing"即"正在运行"组中   
       // notification.flags |= Notification.FLAG_AUTO_CANCEL; // 表明在点击了通知栏中的"清除通知"后，此通知不清除，经常与FLAG_ONGOING_EVENT一起使用   
        //notification.flags |= Notification.FLAG_SHOW_LIGHTS;   
        //DEFAULT_ALL     使用所有默认值，比如声音，震动，闪屏等等
        //DEFAULT_LIGHTS  使用默认闪光提示
        //DEFAULT_SOUNDS  使用默认提示声音
        //DEFAULT_VIBRATE 使用默认手机震动，需加上<uses-permission android:name="android.permission.VIBRATE" />权限
        //notification.defaults = Notification.DEFAULT_LIGHTS; 
        //叠加效果常量
        //notification.defaults=Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND;
       // notification.ledARGB = Color.BLUE;   
        //notification.ledOnMS =5000; //闪光时间，毫秒
          
        // 设置通知的事件消息   
        CharSequence contentTitle ="雷欧：时间到"; // 通知栏标题   
        CharSequence contentText ="结算"; // 通知栏内容   
        Intent notificationIntent =new Intent(Alarmalert.this, WeixinChatDemoActivity2.class); // 点击该通知后要跳转的Activity   
        Bundle mBundle = new Bundle();  
        mBundle.putString("action2",str_id);
        notificationIntent.putExtras(mBundle);
        PendingIntent contentItent = PendingIntent.getActivity(this, 1, notificationIntent, 0); 
        Log.i("获得的action",str_id);
        notification.setLatestEventInfo(this, contentTitle, contentText, contentItent);   
        notification.vibrate = new long[] {100,250,100,500};
       // Notification.Builder builder = new Notification.Builder(mContext);
    	
        // 把Notification传递给NotificationManager   
        notificationManager.notify(1, notification);   
    
        		}
	 
	 private void clearNotification(){
	        // 启动后删除之前我们定义的通知   
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
		menu.add(0, 1, 1, "关于");
		menu.add(0, 2, 2, "退出");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			AlertDialog.Builder adb = new Builder(Alarmalert.this);
			adb.setTitle("关于我们");
			adb.setMessage("");
			adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(Alarmalert.this, "Thanks",
							Toast.LENGTH_SHORT).show();
				}
			});
			adb.show();
			break;
		case 2:
			AlertDialog.Builder adb1 = new Builder(Alarmalert.this);
			adb1.setTitle("消息");
			adb1.setMessage("真的要退出吗？");
			adb1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					am1.exitAllProgress();
				}
			});
			adb1.setNegativeButton("取消", null);
			adb1.show();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
