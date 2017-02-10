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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class Setting extends Activity {
	private ActivityManager am1;
	private TextView view01;
	private Spinner spinner01;
    private ArrayAdapter<?> adapter1;
    private TextView view02;
    private Spinner spinner02;
    private ArrayAdapter<?> adapter2;
	DBHelper helper;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		
		am1 = ActivityManager.getInstance();
		am1.addActivity(this);
		
		//�������ݿ������
	    helper = new DBHelper(Setting.this);
		//�����ݿ�
		helper.openDatabase();
		
		spinner01 = (Spinner) findViewById(R.id.setting_spinner01);
        view01 = (TextView) findViewById(R.id.setting_spinnerText01);
		    //����ѡ������ArrayAdapter��������
     	adapter1 = ArrayAdapter.createFromResource(this, R.array.model, android.R.layout.simple_spinner_item);
		   //���������б�ķ��
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           //��adapter1 ��ӵ�spinner��
		spinner01.setAdapter(adapter1);
		   //����¼�Spinner�¼����� 
	    spinner01.setOnItemSelectedListener(new Spinner01XMLSelectedListener());
		  //����Ĭ��ֵ
		spinner01.setVisibility(View.VISIBLE);
		
		spinner02 = (Spinner) findViewById(R.id.setting_spinner02);
        view02 = (TextView) findViewById(R.id.setting_spinnerText02);
		    //����ѡ������ArrayAdapter��������
     	adapter2 = ArrayAdapter.createFromResource(this, R.array.model, android.R.layout.simple_spinner_item);
		   //���������б�ķ��
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           //��adapter2 ��ӵ�spinner��
		spinner02.setAdapter(adapter2);
		   //����¼�Spinner�¼����� 
	    spinner02.setOnItemSelectedListener(new Spinner02XMLSelectedListener());
		  //����Ĭ��ֵ
		spinner02.setVisibility(View.VISIBLE);
		
		// ��Layout�����ListView
		ListView list = (ListView) findViewById(R.id.setting_listview);
		// ���ɶ�̬���飬��������
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("ItemText", "�������ɵĽ����¼�");
		listItem.add(map1);

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("ItemText", "������н����¼�");
		listItem.add(map2);

		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put("ItemText", "������г�������");
		listItem.add(map3);
		
		// ������������Item�Ͷ�̬�����Ӧ��Ԫ�أ�������SimpleAdapter��ΪListView������Դ
		// �����Ŀ���ֱȽϸ��ӣ����Լ̳�BaseAdapter�������Լ�������Դ��
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// ����Դ
				R.layout.setting_item,// ListItem��XMLʵ��
				// ��̬������ImageItem��Ӧ������
				new String[] {"ItemText" },
				// ImageItem��XML�ļ������һ��ImageView,����TextView ID
				new int[] {R.id.setting_listview_itemtext });
		// ��Ӳ�����ʾ
		list.setAdapter(listItemAdapter);
		        
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2 )
				{  case 0:
					
					//�����������ɵ��¼�
					ArrayList list = helper.getAllAction();//�õ������û���list
			        
			        Log.i("size",list.size()+"");	

			        for(int i=0;i<list.size();i++){
			        	
					HashMap item = (HashMap) list.get(i);
			        Integer flag_i= (Integer) item.get("flag");
                    Integer num_id = (Integer) item.get("num_id");
                    
					if(flag_i.compareTo(1) ==0){
						helper.delete(num_id);
				    } 
					}
					
                   break;

				   case 1:
						//������е��¼�
						ArrayList list1 = helper.getAllAction();//�õ������û���list
				        
				        Log.i("size",list1.size()+"");	

				        for(int i=0;i<list1.size();i++){
				        	
						HashMap item = (HashMap) list1.get(i);
	                    Integer num_id = (Integer) item.get("num_id");
					    helper.delete(num_id); 
						}
                   break;

				   case 2:
						//������еĿγ�
						ArrayList list2 = helper.getAllLesson();//�õ������û���list
				        
				        Log.i("size",list2.size()+"");	

				        for(int i=0;i<list2.size();i++){
				        	
						HashMap item = (HashMap) list2.get(i);
	                    Integer num_class_id = (Integer) item.get("num_class_id");
					    helper.deleteLesson(num_class_id); 
						}
				   break;
				}

			}
		});
}
	
	   //ʹ��XML��ʽ����
    class Spinner01XMLSelectedListener implements OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            view01.setText("��ѡ���¼������ѷ�ʽ:"+adapter1.getItem(arg2));
			ArrayList list = helper.getAllAction();//�õ������û���list
			
	        for(int i=0;i<list.size();i++){	        	
			HashMap item = (HashMap) list.get(i);	        
			Action action = new Action();
			action.num_id = Integer.parseInt(String.valueOf(item.get("num_id")));
			action.type_id = Integer.parseInt(String.valueOf(item.get("type_id")));
			action.flag = Integer.parseInt(String.valueOf(item.get("flag")));
			action.actiontitle = String.valueOf(item.get("actiontitle"));
			action.actiondetail = String.valueOf(item.get("actiondetail"));
			action.actiontype = String.valueOf(item.get("actiontype"));
			action.date = String.valueOf(item.get("date"));
			action.time = String.valueOf(item.get("time"));
			if(arg2==0){
			action.remidtype = "��";}
			if(arg2==1){
			action.remidtype = "����";}
			if(arg2==2){
			action.remidtype = "�񶯺�����";}
            helper.modify(action);
			}

        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
    
	   //ʹ��XML��ʽ����
    class Spinner02XMLSelectedListener implements OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            view02.setText("��ѡ������������ѷ�ʽ:"+adapter2.getItem(arg2));

            ArrayList list = helper.getAllLesson();//�õ������û���list
			
	        for(int i=0;i<list.size();i++){	        	
			HashMap item = (HashMap) list.get(i);	        
			Lesson lesson = new Lesson();
			lesson.num_class_id = Integer.parseInt(String.valueOf(item.get("num_class_id")));
			lesson.classname = String.valueOf(String.valueOf(item.get("classname")));
			lesson.classteacher = String.valueOf(String.valueOf(item.get("classteacher")));
			lesson.classroom = String.valueOf(item.get("classroom"));
			lesson.classstarttime = String.valueOf(item.get("classstarttime"));
			lesson.classendtime = String.valueOf(item.get("classendtime"));
			lesson.startendtime = String.valueOf(item.get("startendtime"));
			lesson.classday = String.valueOf(item.get("classday"));
			lesson.classday_id = Integer.parseInt(String.valueOf(item.get("classday_id")));
			if(arg2==0){
			    lesson.classremidtype = "��";}
			if(arg2==1){
				lesson.classremidtype = "����";}
			if(arg2==2){
				lesson.classremidtype = "�񶯺�����";}
            helper.modifyLesson(lesson);
            }
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
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
			AlertDialog.Builder adb = new Builder(Setting.this);
			adb.setTitle("��������");
			adb.setMessage("");
			adb.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(Setting.this, "Thanks",
							Toast.LENGTH_SHORT).show();
				}
			});
			adb.show();
			break;
		case 2:
			AlertDialog.Builder adb1 = new Builder(Setting.this);
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
	
}