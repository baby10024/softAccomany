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
     * �̳�SimpleAdapter ʵ�ֵ������� 
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
		menu.add(0, 1, 1, "����");
		menu.add(0, 2, 2, "�˳�");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			AlertDialog.Builder adb = new Builder(ShouYe.this);
			adb.setTitle("��������");
			adb.setMessage("");
			adb.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(ShouYe.this, "Thanks",
							Toast.LENGTH_SHORT).show();
				}
			});
			adb.show();
			break;
		case 2:
			AlertDialog.Builder adb1 = new Builder(ShouYe.this);
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
	
	 @SuppressWarnings("deprecation")
	@Override  
	    public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  
	        {  
	            // �����˳��Ի���  
	            AlertDialog isExit = new AlertDialog.Builder(this).create();  
	            // ���öԻ������  
	            isExit.setTitle("��Ϣ");  
	            // ���öԻ�����Ϣ  
	            isExit.setMessage("ȷ��Ҫ�˳���");  
	            // ���ѡ��ť��ע�����  
	            isExit.setButton("ȷ��", listener);  
	            isExit.setButton2("ȡ��", listener);  
	            // ��ʾ�Ի���  
	            isExit.show();  
	  
	        }  
	          
	        return false;  
	          
	    }  
	    /**�����Ի��������button����¼�*/  
	    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
	    {  
	        public void onClick(DialogInterface dialog, int which)  
	        {  
	            switch (which)  
	            {  
	            case AlertDialog.BUTTON_POSITIVE:// "ȷ��"��ť�˳�����  
	            {//am1.exitAllProgress();
	            	Intent home = new Intent(Intent.ACTION_MAIN);  
	            	home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            	home.addCategory(Intent.CATEGORY_HOME);  
	            	startActivity(home); 
	                finish();  
	                break;  }
	            case AlertDialog.BUTTON_NEGATIVE:// "ȡ��"�ڶ�����ťȡ���Ի���  
	                break;  
	            default:  
	                break;  
	            }  
	        }  
	    };  
}

