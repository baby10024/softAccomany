package com.example.softAccomany.mark;

import java.util.Calendar;

import com.example.android_robot_01.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class Edit extends Activity {
	   private TextView view01;
	   private Spinner spinner01;
	   private ArrayAdapter<?> adapter1;
	   private TextView view02;
	   private Spinner spinner02;
	   private ArrayAdapter<?> adapter2;
	   private Button date_btn;
	   private Button time_btn;	
	   private Button save_btn;
	   private Button can_btn;
	   private Button det_btn;
	   private String str_actiontype;
	   private String str_remidtype;
	   EditText et_title;
	   EditText et_detail;  
	   private String str_date_old;
	   private String str_time_old;
	   private String str_date_new;
	   private String str_time_new;
	   DBHelper helper;
	   public int id= 0 ;
	   private boolean flag = false;
	   private Calendar cal = Calendar.getInstance();
	   AlarmManager am;
	   Action action;
	   private ActivityManager am1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		
		am1 = ActivityManager.getInstance();
		am1.addActivity(this);
		
        am = (AlarmManager)getSystemService(ALARM_SERVICE);   
        
		//�������ݿ������
	    helper = new DBHelper(Edit.this);
		//�����ݿ�
		helper.openDatabase();
		
		//�����ͼ
		Intent intent = getIntent();
		//����ͼ�еõ���Ҫ��user����
		action = (Action) intent.getSerializableExtra("action");
		
		id = action.num_id;
		Log.i("edit_action_num_id",id+"");
		loadActionData();
		
		setEditTextDisable();
		  
	    date_btn.setOnClickListener(new OnClickListener() {		
			public void onClick(View v) {
				cal.setTimeInMillis(System.currentTimeMillis());
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DAY_OF_MONTH);
				
				//����DatePickerDialog���趨ʱ��
				new DatePickerDialog(Edit.this,
						new OnDateSetListener() {
					public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
						str_date_new = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
						Log.i("str_date_new",str_date_new);
						date_btn.setText(str_date_new);
							}
						},year,month,day).show();
				}
			});
		   		   

	       time_btn.setOnClickListener(new OnClickListener() {		
	    	   public void onClick(View v) {
					// ȡ�ð��°�ť��ʱ����ΪTimePickerDialog��Ĭ��ֵ
					cal.setTimeInMillis(System.currentTimeMillis());
					//�����ȡʱ��
					int mHour = cal.get(Calendar.HOUR_OF_DAY);
					int mMinute = cal.get(Calendar.MINUTE);
					//����TimePickerDialog���趨ʱ��
					new TimePickerDialog(Edit.this,
							new TimePickerDialog.OnTimeSetListener() {
								public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
									str_time_new = hourOfDay+":"+minute;
									time_btn.setText(str_time_new);
									Log.i("str_time_new",str_time_new);
								}
							},mHour, mMinute, true).show();
					}
			});
	       
	       save_btn.setOnClickListener(new OnClickListener() {		
				public void onClick(View v) {
					if(!flag)
					{	save_btn.setText("�����޸�");
					    setEditTextAble();
						setTitle("�޸�");
					    flag = true;}
					else{
						//�����ݿ������������
						setTitle("����");
						modify();
						
						if(!str_date_old.equals(str_date_new) || !str_time_old.equals(str_time_new)){
					        Log.i("new alarm",str_date_new+str_time_new); 
				               
					     // �㲥֪ͨ   
					         Intent intent = new Intent();  
					         intent.setAction("action.cancleAlarm");  
					         sendBroadcast(intent);
//				            PendingIntent pend = PendingIntent.getBroadcast(Edit.this,    
//				                    action.num_id, new Intent(Edit.this,CallAlarm.class), 0);   
//				            am.cancel(pend);
				            Log.i("cancel_alarm",action.num_id+"");
				            
				            baocunAction() ; 
						}
						
//						Intent intent1 = new Intent();  
//						intent1.putExtra("cancle_action_id",action.num_id+"");
//				        intent1.setAction("action.CancleAlarm");  
//				        sendBroadcast(intent1); 
				        
						Intent intent = new Intent();  
						intent.putExtra("cancle_action_id",action.num_id+"");
				        intent.setAction("action.refreshAction");  
				        sendBroadcast(intent); 
				         
						setEditTextDisable();
						setColorToGray();
						save_btn.setText("�޸�");
						flag = false;
				}
				}
			});
	       
	       can_btn.setOnClickListener(new OnClickListener() {		
				public void onClick(View v) {
						finish();
				}
			});
	       
	       det_btn.setOnClickListener(new OnClickListener() {		
				public void onClick(View v) {

					helper.delete(action.num_id);
					
					// �㲥֪ͨ   
			        Intent intent1 = new Intent();  
			        intent1.setAction("action.cancleAlarm");  
			        sendBroadcast(intent1);
//					Intent intent1 = new Intent();  
//					intent1.putExtra("cancle_action_id",action.num_id+"");
//			        intent1.setAction("action.CancleAlarm");  
//			        sendBroadcast(intent1); 
			        
					Intent intent = new Intent();  
					intent.putExtra("cancle_action_id",action.num_id+"");
			        intent.setAction("action.refreshAction");  
			        sendBroadcast(intent); 


			        
//		            PendingIntent pend = PendingIntent.getBroadcast(Edit.this,    
//		                    action.num_id, new Intent(Edit.this,CallAlarm.class), 0);   
//		            am.cancel(pend);
//		            Log.i("delet_cancel_alarm",action.num_id+"");
		            
					setEditTextDisable();
					setColorToGray();
					Log.i("num_id",action.num_id+"");
					finish();
				}
			});
	    	       
	}

	    
	    /**
		 * ��ò����ļ��еĿؼ������Ҹ��ݴ��ݹ���action����Կؼ����и�ֵ
		 */
		public void loadActionData() {
			// ���EditText�ؼ�
			Log.i("num_id_intent1",action.num_id+"");
			save_btn = (Button) findViewById(R.id.save);
			can_btn = (Button) findViewById(R.id.cancel);
			det_btn = (Button) findViewById(R.id.delete);
			date_btn = (Button) findViewById(R.id.date_btn);
			time_btn = (Button) findViewById(R.id.time_btn);	
			et_title = (EditText)findViewById(R.id.title);
			et_detail = (EditText)findViewById(R.id.detail);
			save_btn.setText("�޸�");
			date_btn.setText(action.date);
			time_btn.setText(action.time);
			str_date_old = action.date;
			str_time_old = action.time;
			Log.i("str_date_old",str_date_old);
			Log.i("str_time_old",str_time_old);
			str_date_new = action.date;
			str_time_new = action.time;
			et_title.setText(action.actiontitle);
			et_detail.setText(action.actiondetail);
			
			spinner01 = (Spinner) findViewById(R.id.spinner01);
	        view01 = (TextView) findViewById(R.id.spinnerText01);
			    //����ѡ������ArrayAdapter��������
	        adapter1 = ArrayAdapter.createFromResource(this, R.array.planets, android.R.layout.simple_spinner_item);
			   //���������б�ķ��
			adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	           //��adapter1 ��ӵ�spinner��
			spinner01.setAdapter(adapter1);
			   //����¼�Spinner�¼����� 
		    spinner01.setOnItemSelectedListener(new Spinner01XMLSelectedListener());
			  //����Ĭ��ֵ
		    if(action.actiontype.equals("ѧϰ")){	spinner01.setSelection(0);}
		    if(action.actiontype.equals("ѧ������")){	spinner01.setSelection(1);}
		    if(action.actiontype.equals("���Ż")){	spinner01.setSelection(2);}
		    if(action.actiontype.equals("��������")){	spinner01.setSelection(3);}
		    if(action.actiontype.equals("����")){	spinner01.setSelection(4);}
		    if(action.actiontype.equals("����")){	spinner01.setSelection(5);}
			spinner01.setVisibility(View.VISIBLE);
			
			spinner02 = (Spinner) findViewById(R.id.spinner02);
	        view02 = (TextView) findViewById(R.id.spinnerText02);
			    //����ѡ������ArrayAdapter��������
	     	adapter2 = ArrayAdapter.createFromResource(this, R.array.model, android.R.layout.simple_spinner_item);
			   //���������б�ķ��
			adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	           //��adapter2 ��ӵ�spinner��
			spinner02.setAdapter(adapter2);
			   //����¼�Spinner�¼����� 
		    spinner02.setOnItemSelectedListener(new Spinner02XMLSelectedListener());
		    if(action.remidtype.equals("��")){	spinner02.setSelection(0);}
		    if(action.remidtype.equals("����")){	spinner02.setSelection(1);}
		    if(action.remidtype.equals("�񶯺�����")){	spinner02.setSelection(2);}		
			  //����Ĭ��ֵ
			spinner02.setVisibility(View.VISIBLE);
		}
		
		   //ʹ��XML��ʽ����
		    class Spinner01XMLSelectedListener implements OnItemSelectedListener{
		        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
		            view01.setText("��ѡ�����ѷ�ʽ:"+adapter1.getItem(arg2));
		            str_actiontype = (String) adapter1.getItem(arg2);
		        }
		        public void onNothingSelected(AdapterView<?> arg0) {
		        }
		    }
		    
			   //ʹ��XML��ʽ����
		    class Spinner02XMLSelectedListener implements OnItemSelectedListener{
		        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
		            view02.setText("����ӵ��¼����ͣ�"+adapter2.getItem(arg2));
		            str_remidtype = (String) adapter2.getItem(arg2);
		        }
		        public void onNothingSelected(AdapterView<?> arg0) {
		        }
		    }
		
		/**
		 * ����EditTextΪ������
		 */
		private void setEditTextDisable() {
			et_title.setEnabled(false);
			et_detail.setEnabled(false);
			date_btn.setEnabled(false);
			time_btn.setEnabled(false);
			spinner01.setEnabled(false);
			spinner02.setEnabled(false);
			setColorToGray();

		}
	     
		/**
		 *  ������ʾ��������ɫΪ��ɫ
		 */
		private void setColorToGray() {
			et_title.setTextColor(Color.GRAY);
			et_detail.setTextColor(Color.GRAY);
			date_btn.setTextColor(Color.GRAY);
			time_btn.setTextColor(Color.GRAY);
			view01.setTextColor(Color.GRAY);
			view02.setTextColor(Color.GRAY);
		}
		
		/**
		 * ����EditTextΪ����״̬
		 */
		private void setEditTextAble() {
			et_title.setEnabled(true);
			et_detail.setEnabled(true);
			date_btn.setEnabled(true);
			time_btn.setEnabled(true);
			spinner01.setEnabled(true);
			spinner02.setEnabled(true);
			setColorToBlack();
		}
		
		/**
		 *  ������ʾ��������ɫΪ��ɫ
		 */
		private void setColorToBlack() {
			et_title.setTextColor(Color.BLACK);
			et_detail.setTextColor(Color.BLACK);
			date_btn.setTextColor(Color.BLACK);
			time_btn.setTextColor(Color.BLACK);
			view01.setTextColor(Color.BLACK);
			view02.setTextColor(Color.BLACK);
		}
		
		/**
		 * ����������ݣ�����DBHelper���󣬸������ݿ�
		 */
		private void modify() {
			Log.i("num_id_intent2",action.num_id+"");
			Action  action1 = new Action();	
			action1.num_id = action.num_id;
			action1.flag =  Integer.valueOf(0);
			action1.flag = action.flag;
			action1.actiontitle = et_title.getText().toString();
			action1.actiondetail = et_detail.getText().toString();
			action1.actiontype = str_actiontype;
			action1.date = date_btn.getText().toString();
			action1.time = time_btn.getText().toString();
			action1.remidtype = str_remidtype;
			
			if(action1.actiontype.equals("ѧϰ")){
				action1.type_id = new Integer(0);
			}	
			
			if(action1.actiontype.equals("ѧ������")){
				action1.type_id = new Integer(1);
			}
			
			if(action1.actiontype.equals("���Ż")){
				action1.type_id = new Integer (2);
			}
			
			if(action1.actiontype.equals("��������")){
				action1.type_id = new Integer(3);
			}
			
			if(action1.actiontype.equals("����")){
				action1.type_id =new Integer(4);
			}
			
			if(action1.actiontype.equals("����")){
				action1.type_id =new Integer(5);
			}
			
            String str_title_null = et_title.getText().toString();
	
          	if (str_title_null == null || str_title_null.equals("")){
		
        		Toast.makeText(Edit.this, "���ⲻ����Ϊ��", Toast.LENGTH_SHORT).show();}
          	else{

			Log.i("action_num_id_modify",action1.num_id+"");
			helper.modify(action1);
					
          	}
		}
		
		public String format(int i){
			String s = ""+i;
			if(s.length()==1) s = "0"+s;
			return s;
		}
		
		public void baocunAction() {

			Log.i("Edit_num_id_last",helper.lastId()+".");
			cal.setTimeInMillis(System.currentTimeMillis());
			if(!str_time_new.equals("")&&!str_date_new.equals("")){
				String[] t1 = str_date_new.split("-");
				String[] t2 = str_time_new.split(":");
				Log.i("date",t1[0]+t1[1]+t1[2]);
				cal.set(Integer.parseInt(t1[0]), (Integer.parseInt(t1[1])-1),Integer.parseInt(t1[2]));
				cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(t2[0]));
				cal.set(Calendar.MINUTE, Integer.parseInt(t2[1]));
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
								
				Intent intent = new Intent(Edit.this,CallAlarm1.class);	
				String str_id = id+"";
				intent.putExtra("action_id1", str_id);
				Log.i("action_id1", str_id);
				Log.i("success","success");
				intent.setAction("editAction");
				PendingIntent pi2 = PendingIntent.getBroadcast(Edit.this, id, intent, 0);
				am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi2);
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
				AlertDialog.Builder adb = new Builder(Edit.this);
				adb.setTitle("��������");
				adb.setMessage("");
				adb.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(Edit.this, "Thanks",
								Toast.LENGTH_SHORT).show();
					}
				});
				adb.show();
				break;
			case 2:
				AlertDialog.Builder adb1 = new Builder(Edit.this);
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