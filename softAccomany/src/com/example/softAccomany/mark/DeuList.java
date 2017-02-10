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
import android.widget.TextView;


public class DeuList extends Activity {

	private int type_id;
    Integer type_id_i;
    Integer flag_i;
    Integer flag_i_integer = Integer.valueOf(0);
  //  private Button btn_ret_main;
    private Button btn_add;
    private TextView tv_type;
	private ActivityManager am1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deu_action);
		
		am1 = ActivityManager.getInstance();
		am1.addActivity(this);
		
		IntentFilter intentFilter = new IntentFilter();  
	    intentFilter.addAction("action.refreshAction");  
	    registerReceiver(mRefreshBroadcastReceiver, intentFilter);
	    
	//	btn_ret_main = (Button) findViewById(R.id.return_main);
		btn_add = (Button) findViewById(R.id.addnew);
		tv_type = (TextView)findViewById(R.id.type_title);
		
	  /*  btn_ret_main.setOnClickListener(new OnClickListener() {		
				public void onClick(View v) {
	                Intent intent = new Intent();  
	                intent.setClass(DeuList.this, Activity2.class);  
	                startActivity(intent);  
	                finish();//ֹͣ��ǰ��Activity,�����д,�򰴷��ؼ�����ת��ԭ����Activity
				}
			});*/
	    
	    btn_add.setOnClickListener(new OnClickListener() {		
				public void onClick(View v) {
	                Intent intent = new Intent();  
	                intent.setClass(DeuList.this, Add.class);  
	                startActivity(intent);  
	                finish();//ֹͣ��ǰ��Activity,�����д,�򰴷��ؼ�����ת��ԭ����Activity
				}
			});
	    
	    reFreshFrinedList();
        
	}
	
	 // broadcast receiver  
	  private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {  
	  
	      @Override  
	      public void onReceive(Context context, Intent intent) {  
	          String action = intent.getAction();  
	          if (action.equals("action.refreshAction"))  
	          {  String str_id = (String) intent.getStringExtra("cancle_action_id");

	        	  Log.i("deuList_receiver",str_id);
	              reFreshFrinedList();  
	          }  
	      }  
	  };
	  
	  @Override  
	   protected void onDestroy() {  
	       super.onDestroy();  
	       unregisterReceiver(mRefreshBroadcastReceiver);  
	   }
	  
	  @SuppressWarnings("unchecked")
	private void  reFreshFrinedList(){
		  
		    ArrayList list1 = new ArrayList();	
			ListView lv = (ListView)findViewById(R.id.DeuList); //����ListView����
			//�������ݿ������
			DBHelper helper = new DBHelper(DeuList.this);
			helper.openDatabase();
			ArrayList list = helper.getAllAction();//�õ������û���list
	        Bundle bundle=this.getIntent().getExtras();
	        type_id = bundle.getInt("type_id");
	        Integer type_id_integer = Integer.valueOf(type_id);
	        
	        switch(type_id) 
	        { 
	        case 0: 
	            tv_type.setText("ѧϰ��"); 
	        break; 
	        case 1: 
		        tv_type.setText("ѧ��������"); 
	        break; 
	        case 2: 
		        tv_type.setText("���Ż��"); 
	        break; 
	        case 3: 
		        tv_type.setText("����������"); 
	        break; 
	        case 4: 
		        tv_type.setText("���ѣ�"); 
	        break; 
	        case 5: 
		        tv_type.setText("������"); 
	        break; 
	        default: 
		        tv_type.setText("ѧϰ"); 
	        break; 
	        } 
	        
	        Log.i("type_id",type_id +"");
	        
	        Log.i("size",list.size()+"");	

	        for(int i=0;i<list.size();i++){
			HashMap item = (HashMap) list.get(i);
	        type_id_i=(Integer) item.get("type_id");
	        flag_i= (Integer) item.get("flag");
			if(type_id_i.compareTo(type_id_integer) ==0 && flag_i.compareTo(flag_i_integer) ==0){
		    list1.add(item);} 
			}
	        
			SimpleAdapter adapter = new SimpleAdapter(this, 
					list1, 
					R.layout.deu_action_list, 
					new String[]{"actiontitle"}, 
					new int[]{R.id.deuactionlist});

	        lv.setAdapter(adapter);

	         
	        lv.setOnItemClickListener(new OnItemClickListener() {
	        	/**
	        	 * ��Ӧ�����¼��������ĳһ��ѡ���ʱ����ת���û���ϸ��Ϣҳ��
	        	 */

				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					HashMap item = (HashMap)arg0.getItemAtPosition(arg2);
					int num_id = Integer.parseInt(String.valueOf(item.get("num_id")));
//					int id = Integer.parseInt(String.valueOf(item.get("type_id")));
					
					Intent intent1 = new Intent(DeuList.this,Edit.class);
					Action action = new Action();
					action.num_id = Integer.parseInt(String.valueOf(item.get("num_id")));
					action.type_id = Integer.parseInt(String.valueOf(item.get("type_id")));
					action.flag = Integer.parseInt(String.valueOf(item.get("flag")));
					action.actiontitle = String.valueOf(item.get("actiontitle"));
					action.actiondetail = String.valueOf(item.get("actiondetail"));
					action.actiontype = String.valueOf(item.get("actiontype"));
					action.date = String.valueOf(item.get("date"));
					action.time = String.valueOf(item.get("time"));
					action.remidtype = String.valueOf(item.get("remidtype"));
					
					intent1.putExtra("action", action);
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
				AlertDialog.Builder adb = new Builder(DeuList.this);
				adb.setTitle("��������");
				adb.setMessage("");
				adb.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(DeuList.this, "Thanks",
								Toast.LENGTH_SHORT).show();
					}
				});
				adb.show();
				break;
			case 2:
				AlertDialog.Builder adb1 = new Builder(DeuList.this);
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