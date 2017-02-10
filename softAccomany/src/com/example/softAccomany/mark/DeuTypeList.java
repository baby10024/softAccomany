package com.example.softAccomany.mark;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.android_robot_01.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class DeuTypeList extends Activity {
	private ActivityManager am1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deu_action_typeshow);
		
		am1 = ActivityManager.getInstance();
		am1.addActivity(this);
		
		// ��Layout�����ListView
		ListView list = (ListView) findViewById(R.id.deu_typeshow_listview);
		// ���ɶ�̬���飬��������
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("ItemImage", R.drawable.work);// ͼ����Դ��ID
		map1.put("ItemText", "       ѧϰ ");
		listItem.add(map1);

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("ItemImage", R.drawable.study);// ͼ����Դ��ID
		map2.put("ItemText", "       ѧ������");
		listItem.add(map2);

		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put("ItemImage", R.drawable.shetuan);// ͼ����Դ��ID
		map3.put("ItemText", "       ���Ż");
		listItem.add(map3);

		HashMap<String, Object> map4 = new HashMap<String, Object>();
		map4.put("ItemImage", R.drawable.tiyu);// ͼ����Դ��ID
		map4.put("ItemText", "       �������� ");
		listItem.add(map4);

		HashMap<String, Object> map5 = new HashMap<String, Object>();
		map5.put("ItemImage", R.drawable.friend);// ͼ����Դ��ID
		map5.put("ItemText", "       ���� ");
		listItem.add(map5);

		HashMap<String, Object> map6 = new HashMap<String, Object>();
		map6.put("ItemImage", R.drawable.other);// ͼ����Դ��ID
		map6.put("ItemText", "       ����");
		listItem.add(map6);
		
		// ������������Item�Ͷ�̬�����Ӧ��Ԫ�أ�������SimpleAdapter��ΪListView������Դ
		// �����Ŀ���ֱȽϸ��ӣ����Լ̳�BaseAdapter�������Լ�������Դ��
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// ����Դ
				R.layout.deu_action_typeshow_listitem,// ListItem��XMLʵ��
				// ��̬������ImageItem��Ӧ������
				new String[] { "ItemImage", "ItemText" },
				// ImageItem��XML�ļ������һ��ImageView,����TextView ID
				new int[] { R.id.deu_typeshow_itemimage, R.id.deu_typeshow_itemtext });
		// ��Ӳ�����ʾ
		list.setAdapter(listItemAdapter);
		        
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				    Bundle bundle = new Bundle();
				    bundle.putInt("type_id", arg2);
				    Intent intent = new Intent();
					intent.putExtras(bundle);
					intent.setClass(DeuTypeList.this,DeuList.class);
					DeuTypeList.this.startActivity(intent);
		
			}
		});
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
			AlertDialog.Builder adb = new Builder(DeuTypeList.this);
			adb.setTitle("��������");
			adb.setMessage("");
			adb.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(DeuTypeList.this, "Thanks",
							Toast.LENGTH_SHORT).show();
				}
			});
			adb.show();
			break;
		case 2:
			AlertDialog.Builder adb1 = new Builder(DeuTypeList.this);
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