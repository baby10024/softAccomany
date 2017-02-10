package com.example.softAccomany.mark;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.android_robot_01.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Activity2 extends Activity {
	private ActivityManager am1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_listview);			
		
		am1 = ActivityManager.getInstance();
		am1.addActivity(this);
		
		// 绑定Layout里面的ListView
		ListView list = (ListView) findViewById(R.id.ListView01);
		// 生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("ItemImage", R.drawable.classtable);// 图像资源的ID
		map1.put("ItemTitle", "       短期计划列表 ");
		listItem.add(map1);
		
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("ItemImage", R.drawable.action_list);// 图像资源的ID
		map2.put("ItemTitle", "       长期计划列表 ");
		listItem.add(map2);

		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put("ItemImage", R.drawable.add_action);// 图像资源的ID
		map3.put("ItemTitle", "       短期计划添加 ");
		listItem.add(map3);

		HashMap<String, Object> map4 = new HashMap<String, Object>();
		map4.put("ItemImage", R.drawable.done_action);// 图像资源的ID
		map4.put("ItemTitle", "       历史记录 ");
		listItem.add(map4);

		HashMap<String, Object> map5 = new HashMap<String, Object>();
		map5.put("ItemImage", R.drawable.calendar);// 图像资源的ID
		map5.put("ItemTitle", "       日历 ");
		listItem.add(map5);

		HashMap<String, Object> map6 = new HashMap<String, Object>();
		map6.put("ItemImage", R.drawable.setting);// 图像资源的ID
		map6.put("ItemTitle", "       设置 ");
		listItem.add(map6);

		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,
				R.layout.main_listview_item,     
				new String[] { "ItemImage", "ItemTitle" },	 
				new int[] { R.id.ItemImage, R.id.ItemTitle });
		list.setAdapter(listItemAdapter);		

		// 添加点击
		list.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("deprecation")
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Intent intent = new Intent();
				
				if(arg2==0){
					intent.setClass(Activity2.this,DeuTypeList.class);
					Activity2.this.startActivity(intent);
					}
				else if(arg2==1){
					intent.setClass(Activity2.this,Schedual.class);
					Activity2.this.startActivity(intent);
					  }	
				else if(arg2==2){
					intent.setClass(Activity2.this,Add.class);
					Activity2.this.startActivity(intent);
					  }	
				else if(arg2==3){
					intent.setClass(Activity2.this,History.class);
					Activity2.this.startActivity(intent);
					  }
				else if(arg2==4){
					
					try {
			            Intent i = new Intent();
			            ComponentName cn = null;
			            if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
			                cn = new ComponentName("com.android.calendar",
			                        "com.android.calendar.LaunchActivity");
			            } else {
			                cn = new ComponentName("com.google.android.calendar",
			                        "com.android.calendar.LaunchActivity");}
			            i.setComponent(cn);
			            startActivity(i);
			        } catch (ActivityNotFoundException e) {
			            // TODO: handle exception
			            Log.e("ActivityNotFoundException", e.toString());
			        }
					  
				}
				else if(arg2==5){
					intent.setClass(Activity2.this,Setting.class);
					Activity2.this.startActivity(intent);
					  }
			}
		});
				
	}
	
	
}


