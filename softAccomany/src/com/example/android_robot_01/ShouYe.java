package com.example.android_robot_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.softAccomany.chat.Activity1;
import com.example.softAccomany.mark.Activity2;
import com.example.softAccomany.mark.ActivityManager;

public class ShouYe  extends ListActivity
{
	Button bton1,bton2,bton3;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shouye);
		
		bton1 = (Button) findViewById(R.id.button1);
		bton1.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
  			Intent i = new Intent("com.example.softAccomany.chat.intent.action.Aty1");
				startActivity(i);
				
			}
		});
		
		
		bton2 = (Button) findViewById(R.id.button2);
		bton2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) 
			{
				Intent i = new Intent("com.example.softAccomany.mark.intent.action.Activity2");
				startActivity(i);
			}
		});
		
		 
    }  
      
    @Override  
    
    public boolean onCreateOptionsMenu(Menu menu) {  
        // Inflate the menu; this adds items to the action bar if it is present.  
        getMenuInflater().inflate(R.menu.main, menu);  
        return true;  
    }  
      
    /**  
     *  
     * @author Administrator 
     * 继承SimpleAdapter 实现的适配器 
     * 
     */  
   
		
		/*bton1 = (Button) findViewById(R.id.aty1);
		bton1.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
  			Intent i = new Intent("com.example.softAccomany.chat.intent.action.Aty1");
				startActivity(i);
				
			}
		});
		
		
		bton2 = (Button) findViewById(R.id.aty2);
		bton2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) 
			{
				Intent i = new Intent("com.example.softAccomany.mark.intent.action.Activity2");
				startActivity(i);
			}
		});
		
		
		bton3 = (Button) findViewById(R.id.guide);
		bton3.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) 
			{
				Intent i = new Intent("com.example.softAccomary.guide.intent.action.zhidao");
				startActivity(i);
			}
		});*/
    

	public boolean onCreateOptionsMenu1(Menu menu) {
		menu.add(0, 1, 1, "关于");
		menu.add(0, 2, 2, "退出");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			AlertDialog.Builder adb = new Builder(ShouYe.this);
			adb.setTitle("关于我们");
			adb.setMessage("");
			adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(ShouYe.this, "Thanks",
							Toast.LENGTH_SHORT).show();
				}
			});
			adb.show();
			break;
		case 2:
			AlertDialog.Builder adb1 = new Builder(ShouYe.this);
			adb1.setTitle("消息");
			adb1.setMessage("真的要退出吗？");
			adb1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
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
			adb1.setNegativeButton("取消", null);
			adb1.show();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	 @SuppressWarnings("deprecation")
	@Override  
	    public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  
	        {  
	            // 创建退出对话框  
	            AlertDialog isExit = new AlertDialog.Builder(this).create();  
	            // 设置对话框标题  
	            isExit.setTitle("消息");  
	            // 设置对话框消息  
	            isExit.setMessage("确定要退出吗");  
	            // 添加选择按钮并注册监听  
	            isExit.setButton("确定", listener);  
	            isExit.setButton2("取消", listener);  
	            // 显示对话框  
	            isExit.show();  
	  
	        }  
	          
	        return false;  
	          
	    }  
	    /**监听对话框里面的button点击事件*/  
	    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
	    {  
	        public void onClick(DialogInterface dialog, int which)  
	        {  
	            switch (which)  
	            {  
	            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
	            {//am1.exitAllProgress();
	            	Intent home = new Intent(Intent.ACTION_MAIN);  
	            	home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            	home.addCategory(Intent.CATEGORY_HOME);  
	            	startActivity(home); 
	                finish();  
	                break;  }
	            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
	                break;  
	            default:  
	                break;  
	            }  
	        }  
	    };  
}

