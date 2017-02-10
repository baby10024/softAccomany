package com.example.softAccomany.mark;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.android_robot_01.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class History extends Activity {
	private ActivityManager am1;
	private Integer flag_i;
	private Integer flag_i_integer = Integer.valueOf(1);
	private Button btn_ret_main;
	private Button btn_add;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history);

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("action.refreshAction");
		registerReceiver(mRefreshBroadcastReceiver, intentFilter);

		am1 = ActivityManager.getInstance();
		am1.addActivity(this);

		btn_ret_main = (Button) findViewById(R.id.return_main);
		btn_add = (Button) findViewById(R.id.addnew);

		btn_ret_main.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(History.this, Activity2.class);
				startActivity(intent);
				finish();// ֹͣ��ǰ��Activity,�����д,�򰴷��ؼ�����ת��ԭ����Activity
			}
		});

		btn_add.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(History.this, Add.class);
				startActivity(intent);
				finish();// ֹͣ��ǰ��Activity,�����д,�򰴷��ؼ�����ת��ԭ����Activity
			}
		});

		reFreshFrinedList();

	}

	// broadcast receiver
	private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals("action.refreshAction")) {
				reFreshFrinedList();
			}
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mRefreshBroadcastReceiver);
	}

	private void reFreshFrinedList() {

		ArrayList list1 = new ArrayList();
		ListView lv = (ListView) findViewById(R.id.histroy_DeuList); // ����ListView����
		// �������ݿ������
		DBHelper helper = new DBHelper(History.this);
		helper.openDatabase();
		ArrayList list = helper.getAllAction();// �õ������û���list

		Log.i("size", list.size() + "");

		for (int i = 0; i < list.size(); i++) {

			HashMap item = (HashMap) list.get(i);
			flag_i = (Integer) item.get("flag");

			Log.i("history_flag_i", flag_i + "");

			// if(flag_i.compareTo(flag_i_integer) ==0){
			list1.add(item);
			// Log.i("history_lll","ddd");}
		}

		SimpleAdapter adapter = new SimpleAdapter(this, list1,
				R.layout.history_list, new String[] { "actiontitle" },
				new int[] { R.id.historylist });

		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {
			/**
			 * ��Ӧ�����¼��������ĳһ��ѡ���ʱ����ת���û���ϸ��Ϣҳ��
			 */

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				HashMap item = (HashMap) arg0.getItemAtPosition(arg2);
				int num_id = Integer.parseInt(String.valueOf(item.get("num_id")));
				// int id =
				// Integer.parseInt(String.valueOf(item.get("type_id")));

				Intent intent1 = new Intent(History.this, Edit.class);
				Action action = new Action();
				action.num_id = Integer.parseInt(String.valueOf(item
						.get("num_id")));
				action.type_id = Integer.parseInt(String.valueOf(item
						.get("type_id")));
				action.flag = Integer.parseInt(String.valueOf(item.get("flag")));
				action.actiontitle = String.valueOf(item.get("actiontitle"));
				action.actiondetail = String.valueOf(item.get("actiondetail"));
				action.actiontype = String.valueOf(item.get("actiontype"));
				action.date = String.valueOf(item.get("date"));
				action.time = String.valueOf(item.get("time"));
				action.remidtype = String.valueOf(item.get("remidtype"));
				intent1.putExtra("action", action);

				/* ��arg2��Ϊ�����봫��ȥ ���ڱ�ʶ�޸����λ�� */
				startActivityForResult(intent1, arg2);
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
			AlertDialog.Builder adb = new Builder(History.this);
			adb.setTitle("��������");
			adb.setMessage("");
			adb.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(History.this, "Thanks", Toast.LENGTH_SHORT)
							.show();
				}
			});
			adb.show();
			break;
		case 2:
			AlertDialog.Builder adb1 = new Builder(History.this);
			adb1.setTitle("��Ϣ");
			adb1.setMessage("���Ҫ�˳���");
			adb1.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					{// am1.exitAllProgress();
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