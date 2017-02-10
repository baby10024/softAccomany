package com.example.softAccomany.mark;

import com.example.android_robot_01.R;
import com.example.softAccomary.guide.WeixinChatDemoActivity2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class CallAlarm extends BroadcastReceiver {
	String id ;
	String TIME;
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.i("intent.getStringExtra(action_id)",intent.getStringExtra("action_id")+".");
		Log.i("intent.getStringExtra(action_id1)",intent.getStringExtra("action_id1")+".");
		id = intent.getStringExtra("action_id");
		TIME = intent.getStringExtra("action_time");
		Log.i("id===================",id+".");
		//displayNotifacation();
		 // ����һ��NotificationManager������   
		/*
	        NotificationManager notificationManager = (NotificationManager)    
	            context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);   
	          
	        // ����Notification�ĸ�������   
	        Notification notification =new Notification(R.drawable.ic_launcher,   
	                "��ŷ������Ϣ", System.currentTimeMillis()); 
	       
	        // ����֪ͨ���¼���Ϣ   
	        notification.vibrate = new long[] {100,250,100,500};
	       // Notification.Builder builder = new Notification.Builder(mContext);
	    	
	        // ��Notification���ݸ�NotificationManager   
	        notificationManager.notify(2, notification);   
	       
		*/
		  Log.i("��Add������ʱ��",TIME); 
		    
		
	        Toast.makeText(context, "ʱ�䵽�ˣ�������", Toast.LENGTH_LONG).show();  
	        Log.i("ϵͳʱ��",System.currentTimeMillis()+"");
	        String time,systemtime;
	        time = TIME.substring(0, 8);
	        systemtime= (System.currentTimeMillis()+"").substring(0,8);
	        
	     if(time.equals(systemtime)){   		
		
		Intent i = new Intent(context,Alarmalert.class);
		Log.i("�Ƿ���callalarm��ת��Alarmalert","yes");
		 Bundle mBundle = new Bundle();  
	        mBundle.putString("action_id1",id);
	        i.putExtras(mBundle);
		
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	     }
	}
	
    
        		
	
	
	
	}
