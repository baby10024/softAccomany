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
		 // 创建一个NotificationManager的引用   
		/*
	        NotificationManager notificationManager = (NotificationManager)    
	            context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);   
	          
	        // 定义Notification的各种属性   
	        Notification notification =new Notification(R.drawable.ic_launcher,   
	                "雷欧发来消息", System.currentTimeMillis()); 
	       
	        // 设置通知的事件消息   
	        notification.vibrate = new long[] {100,250,100,500};
	       // Notification.Builder builder = new Notification.Builder(mContext);
	    	
	        // 把Notification传递给NotificationManager   
	        notificationManager.notify(2, notification);   
	       
		*/
		  Log.i("从Add传来的时间",TIME); 
		    
		
	        Toast.makeText(context, "时间到了！！！！", Toast.LENGTH_LONG).show();  
	        Log.i("系统时间",System.currentTimeMillis()+"");
	        String time,systemtime;
	        time = TIME.substring(0, 8);
	        systemtime= (System.currentTimeMillis()+"").substring(0,8);
	        
	     if(time.equals(systemtime)){   		
		
		Intent i = new Intent(context,Alarmalert.class);
		Log.i("是否由callalarm跳转到Alarmalert","yes");
		 Bundle mBundle = new Bundle();  
	        mBundle.putString("action_id1",id);
	        i.putExtras(mBundle);
		
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	     }
	}
	
    
        		
	
	
	
	}
