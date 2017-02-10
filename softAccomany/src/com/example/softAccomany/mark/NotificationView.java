package com.example.softAccomany.mark;

import java.net.URISyntaxException;

import com.example.android_robot_01.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationView extends Activity {
	Button cancle_button;
	public void onCreate(Bundle saveInstanceState){
		super.onCreate(saveInstanceState);
		setContentView(R.layout.my_nofication);
		
		cancle_button = (Button)findViewById(R.id.button);
		cancle_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/*
				Intent intent=getIntent();
				Log.i("获得Intent","yes");
				//Intent intent = Intent.getIntent(".Alarmalert");
				String str_id = intent.getStringExtra("action_id");
				Log.i("获得的str_id",str_id);
				 int id = Integer.parseInt(str_id);
				 Log.i("取消闹铃ID",str_id);
				*/ 
				 Intent intent1 = new Intent(NotificationView.this,CallAlarm.class);  
				   PendingIntent pi = PendingIntent.getBroadcast(NotificationView.this, 61, intent1, 0);  
				   AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);  
				   am.cancel(pi);  
			}
		});
		/*
		NotificationManager nm = (NotificationManager)
				getSystemService(NOTIFICATION_SERVICE);
		nm.cancel(getIntent().getExtras().getInt("notifacationID"));
		*/
		clearNotification();
	}
	
	 private void clearNotification(){
	        // 启动后删除之前我们定义的通知   
	        NotificationManager notificationManager = (NotificationManager) this
	                .getSystemService(NOTIFICATION_SERVICE);   
	        notificationManager.cancel(0);  
	  
	    }
}
