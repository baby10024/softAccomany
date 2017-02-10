package com.example.softAccomany.mark;

import java.util.Calendar;
import com.example.android_robot_01.R;
import com.example.softAccomary.guide.WeixinChatDemoActivity;
import com.example.softAccomary.guide.WeixinChatDemoActivity2;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
public class Add extends Activity {
	
	
	   private TextView view01;
	   private Spinner spinner01;
	   private ArrayAdapter<?> adapter1;
	   private TextView view02;
	   private Spinner spinner02;
	   private ArrayAdapter<?> adapter2;
	   public Button date_btn;
	   public Button time_btn;	
	   public Button save_btn;
	   public Button can_btn;
	   public String str_actiontype;
	   public String str_remidtype;
	   private String str_date;
	   private String str_time;
	   private int str_minute;
	   private int myhour;
	   private String str_time1;
	   private AlarmManager am;
	   private ActivityManager am1;
	   EditText et_title;
	   EditText et_detail;
	   DBHelper helper;
	   public int id= 0 ;
	   private Calendar cal = Calendar.getInstance();
	   Calendar c = Calendar.getInstance();
	   Calendar c1 = Calendar.getInstance();
	   Calendar c2 = Calendar.getInstance();
	   
	   
	  
	   

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnew);
		
		am1 = ActivityManager.getInstance();
		am1.addActivity(this);
		
	      IntentFilter intentFilter = new IntentFilter();  
	      intentFilter.addAction("action.cancleAlarm");  
	      registerReceiver(cancleAlarmBroadcastReceiver, intentFilter); 
	    
		am = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		//�������ݿ������
	     helper = new DBHelper(Add.this);
		//�����ݿ�
		helper.openDatabase();
		
		loadAction();
		
	    date_btn.setOnClickListener(new OnClickListener() {		
				public void onClick(View v) {
					cal.setTimeInMillis(System.currentTimeMillis());
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH);
					int day = cal.get(Calendar.DAY_OF_MONTH);
					new DatePickerDialog(Add.this,new DatePickerDialog.OnDateSetListener(){
						public void onDateSet(DatePicker view, int year, int monthOfYear,
								int dayOfMonth) {
							str_date = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
							date_btn.setText(str_date);}
					},year,month,day).show();
				}
			});
		   		   

	       time_btn.setOnClickListener(new OnClickListener() {		
				public void onClick(View v) {
					cal.setTimeInMillis(System.currentTimeMillis());
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					int minute = cal.get(Calendar.MINUTE);
					new TimePickerDialog(Add.this,new TimePickerDialog.OnTimeSetListener(){
						public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
							str_time = hourOfDay+":"+minute;
							str_minute = minute;
							myhour = hourOfDay;
							time_btn.setText(str_time);}						
					},hour,minute,true).show();}
			});
	       
	       save_btn.setOnClickListener(new OnClickListener() {		
				public void onClick(View v) {
					Action  action = new Action();	
					action.flag = Integer.valueOf(0);
					action.actiontitle = et_title.getText().toString();
					action.actiondetail = et_detail.getText().toString();
					action.actiontype = str_actiontype;
					action.date = date_btn.getText().toString();
					action.time = time_btn.getText().toString();
					action.remidtype = str_remidtype;
					
					if(action.actiontype.equals("ѧϰ")){
						action.type_id = Integer.valueOf(0);
					}	
					
					if(action.actiontype.equals("ѧ������")){
						action.type_id = Integer.valueOf(1);
					}
					
					if(action.actiontype.equals("���Ż")){
						action.type_id = Integer.valueOf(2);
					}
					
					if(action.actiontype.equals("��������")){
						action.type_id = Integer.valueOf(3);
					}
					
					if(action.actiontype.equals("����")){
						action.type_id =Integer.valueOf(4);
					}
					
					if(action.actiontype.equals("����")){
						action.type_id =Integer.valueOf(5);
					}
					
                    String str_title_null = et_title.getText().toString();
			
		          	if (str_title_null == null || str_title_null.equals("")){
				
	            		Toast.makeText(Add.this, "���ⲻ����Ϊ��", Toast.LENGTH_SHORT).show();}
		          	else{

					//��user�洢�����ݿ���
					long result = helper.insert(action);
					
					//ͨ��������ж��Ƿ����ɹ�����Ϊ-1�����ʾ��������ʧ��
					if(result == -1 ) {
						Toast.makeText(Add.this, "���ʧ��", Toast.LENGTH_LONG).show();
					}else{
					setTitle("�û���ӳɹ�!");}
					
					
					
					baocunAction();
					
					//setStartAlertDialog();
					displayNotifacation();
					
					
					/*
					for(int k=Calendar.MINUTE;k<str_minute;k++)
					{
						
						System.out.println(str_minute);
						str_time1 = myhour+":"+(str_minute+k);
						Action  action1 = new Action();	
						action1.flag = action.flag;
						action1.actiontitle = action.actiontitle;
						action1.actiondetail = action.actiondetail;
						action1.actiontype = action.actiontype;
						action1.date = action.date;
						action1.time = str_time1;
						action1.remidtype = action.remidtype;
						
						action1.type_id = action.type_id;
						
						long result1 = helper.insert(action1);
	                    
	                    baocunAction();
					}
					*/
		          	}
		          	
		          	//����������ɶԻ���
		          	
		          	
		          	
				}
			});
	       
	       can_btn.setOnClickListener(new OnClickListener() {		
				public void onClick(View v) {
						finish();
				}
			});
	    	       
	}   
	 public void displayNotifacation() {
		 Log.i("�Ƿ�ִ��֪ͨ��","yes");
			
			
			
        // ����һ��NotificationManager������   
        NotificationManager notificationManager = (NotificationManager)    
            this.getSystemService(android.content.Context.NOTIFICATION_SERVICE);   
          
        // ����Notification�ĸ�������   
        Notification notification =new Notification(R.drawable.ic_launcher,   
                "��ŷ����һ����Ϣ", System.currentTimeMillis()); 
        //FLAG_AUTO_CANCEL   ��֪ͨ�ܱ�״̬���������ť�������
        //FLAG_NO_CLEAR      ��֪ͨ���ܱ�״̬���������ť�������
        //FLAG_ONGOING_EVENT ֪ͨ��������������
        //FLAG_INSISTENT     �Ƿ�һֱ���У���������һֱ���ţ�֪���û���Ӧ
        notification.flags |= Notification.FLAG_ONGOING_EVENT; // ����֪ͨ�ŵ�֪ͨ����"Ongoing"��"��������"����   
        notification.flags |= Notification.FLAG_NO_CLEAR; // �����ڵ����֪ͨ���е�"���֪ͨ"�󣬴�֪ͨ�������������FLAG_ONGOING_EVENTһ��ʹ��   
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;   
        //DEFAULT_ALL     ʹ������Ĭ��ֵ�������������𶯣������ȵ�
        //DEFAULT_LIGHTS  ʹ��Ĭ��������ʾ
        //DEFAULT_SOUNDS  ʹ��Ĭ����ʾ����
        //DEFAULT_VIBRATE ʹ��Ĭ���ֻ��𶯣������<uses-permission android:name="android.permission.VIBRATE" />Ȩ��
        notification.defaults = Notification.DEFAULT_LIGHTS; 
        //����Ч������
        //notification.defaults=Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND;
        notification.ledARGB = Color.BLUE;   
        notification.ledOnMS =5000; //����ʱ�䣬����
         
        // ����֪ͨ���¼���Ϣ   
        CharSequence contentTitle ="�����"; // ֪ͨ������   
        CharSequence contentText ="��ŷ����һ����Ϣ"; // ֪ͨ������   
        Intent notificationIntent =new Intent(Add.this, WeixinChatDemoActivity.class); // �����֪ͨ��Ҫ��ת��Activity   
        PendingIntent contentItent = PendingIntent.getActivity(this, 0, notificationIntent, 0);   
        //notificationIntent.putExtra("talk_id", 1 );
        notification.setLatestEventInfo(this, contentTitle, contentText, contentItent);   
        notification.vibrate = new long[] {100,250,100,500};
    	  
        // ��Notification���ݸ�NotificationManager   
        notificationManager.notify(0, notification);   
    
        		}
	 /*
		public void setStartAlertDialog() {
			
		
			
			AlertDialog.Builder adb2 = new Builder(Add.this);
			adb2.setTitle("��ŷ����һ����Ϣ");
			adb2.setMessage(msgArray[i1][j1]);
			adb2.setPositiveButton(meArray[m1][n1], new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					if(msgArray[i][j].equals("end"))Add.this.finish();
					else setStartAlertDialog();
				}
			});
			
			adb2.show();
			j++;
			n++;
		}
*/
	   //ʹ��XML��ʽ����
	    class Spinner01XMLSelectedListener implements OnItemSelectedListener{
	        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
	            view01.setText("��ѡ����ӵ��¼�����:"+adapter1.getItem(arg2));
	            str_actiontype = (String) adapter1.getItem(arg2);
	        }
	        public void onNothingSelected(AdapterView<?> arg0) {
	        }
	    }
	    
		   //ʹ��XML��ʽ����
	    class Spinner02XMLSelectedListener implements OnItemSelectedListener{
	        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
	            view02.setText("��ѡ�����ѷ�ʽ��"+adapter2.getItem(arg2));
	            str_remidtype = (String) adapter2.getItem(arg2);
	        }
	        public void onNothingSelected(AdapterView<?> arg0) {
	        }
	    }
	    
	    // broadcast receiver   
	    private BroadcastReceiver cancleAlarmBroadcastReceiver = new BroadcastReceiver() {  
	    
	        @Override  
	        public void onReceive(Context context, Intent intent) {  
	            String action = intent.getAction();  
	            if (action.equals("action.cancleAlarm"))  
	            {  
	                Log.i("add_receiver","cancleAlarm"); 
	            }  
	        }  
	    };  
		  
	    @Override  
	    protected void onDestroy() {  
	        super.onDestroy();  
	        unregisterReceiver(cancleAlarmBroadcastReceiver);  
	    }  
	    
	    public void loadAction(){
	    	
			save_btn = (Button) findViewById(R.id.save);
			can_btn = (Button) findViewById(R.id.cancel);
			date_btn = (Button) findViewById(R.id.date_btn);
			time_btn = (Button) findViewById(R.id.time_btn);	
			et_title = (EditText)findViewById(R.id.title);
			et_detail = (EditText)findViewById(R.id.detail);
			date_btn.setText(c1.get(Calendar.YEAR)+"-" +(c1.get(Calendar.MONTH)+1)+ "-" +c1.get(Calendar.DAY_OF_MONTH));
			time_btn.setText(c1.get(Calendar.HOUR_OF_DAY)+":" +c1.get(Calendar.MINUTE));
			Log.i("date_btn",c1.get(Calendar.YEAR)+"-" +(c1.get(Calendar.MONTH)+1)+ "-" +c1.get(Calendar.DAY_OF_MONTH));
			Log.i("time_btn",c1.get(Calendar.HOUR_OF_DAY)+":" +c1.get(Calendar.MINUTE));
//			str_date = c1.get(Calendar.YEAR)+"��" +(c1.get(Calendar.MONTH)+1)+ "��" +c1.get(Calendar.DAY_OF_MONTH)+"��";
//			str_time = c1.get(Calendar.HOUR_OF_DAY)+"ʱ" +c1.get(Calendar.MINUTE)+"��";
			str_date = c1.get(Calendar.YEAR)+"-" +(c1.get(Calendar.MONTH)+1)+ "-" +c1.get(Calendar.DAY_OF_MONTH);
			str_time = c1.get(Calendar.HOUR_OF_DAY)+":" +c1.get(Calendar.MINUTE);
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
			  //����Ĭ��ֵ
			spinner02.setVisibility(View.VISIBLE);
	    	
	    }
	    
	   
	    
		public void baocunAction() {

			    id = helper.lastId();
				cal.setTimeInMillis(System.currentTimeMillis());
				if(!str_time.equals("")&&!str_date.equals("")){
					String[] t1 = str_date.split("-");
					String[] t2 = str_time.split(":");
					cal.set(Integer.parseInt(t1[0]), (Integer.parseInt(t1[1])-1),Integer.parseInt(t1[2]));
					cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(t2[0]));
					cal.set(Calendar.MINUTE, Integer.parseInt(t2[1]));
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);

					Intent intent = new Intent(Add.this,CallAlarm.class);	
					String str_id = id+"";
					long TIME1=cal.getTimeInMillis();
					intent.putExtra("action_id", str_id);
					intent.putExtra("action_time", TIME1+"");
					Log.i("Ҫ����CLL��ʱ��",TIME1+"");
					PendingIntent pi = PendingIntent.getBroadcast(Add.this, id, intent, 0);
					
					long TIME = (cal.getTimeInMillis() - System.currentTimeMillis())/5;
					
					//am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);
					am.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+TIME,TIME,pi );
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
				AlertDialog.Builder adb = new Builder(Add.this);
				adb.setTitle("��������");
				adb.setMessage("");
				adb.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(Add.this, "Thanks",
								Toast.LENGTH_SHORT).show();
					}
				});
				adb.show();
				break;
			case 2:
				AlertDialog.Builder adb1 = new Builder(Add.this);
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


