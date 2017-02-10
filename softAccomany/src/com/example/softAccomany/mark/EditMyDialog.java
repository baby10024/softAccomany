package com.example.softAccomany.mark;

import java.util.Calendar;

import com.example.android_robot_01.R;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;


/**
* �Զ���dialog
* @author Mr.Xu
*
*/
public class EditMyDialog extends Dialog {
	
		//����ص��¼�������dialog�ĵ���¼�
        public interface OnDialogListener{
                public void back(String name);
        }
        private Context context;
        private String name;
        private OnDialogListener DialogListener;
        private EditText et_classname;
        private EditText et_classroom;
        private EditText et_classteacher;
        private Spinner spinner_week;
   	    private ArrayAdapter<?> adapter_week;
   	    private Button btn_ok;
   	    private Button btn_starttime;
   	    private Button btn_endtime;
   	    private String str_starttime;
   	    private String str_endtime;
   	    private String str_classday;
   	    private Lesson lesson;
 	    DBHelper helper;
 	    private Calendar cal = Calendar.getInstance();
 	    
        public EditMyDialog(Context context,Lesson lesson,OnDialogListener DialogListener) {
                super(context);
                this.context = context;
                this.lesson = lesson;
                this.DialogListener = DialogListener;
        }
        
        @Override
        protected void onCreate(Bundle savedInstanceState) { 
                super.onCreate(savedInstanceState);
                setContentView(R.layout.dialog);
                
                loadLessonData();
                //���ñ���
                setTitle(name); 
                Log.i("context_dialog",context+"");

                btn_starttime.setOnClickListener(clickListener_starttitme);
     	       
                btn_endtime.setOnClickListener(clickListener_endtime);

                btn_ok.setOnClickListener(clickListener_ok);              
        }

		private void loadLessonData() {
			// TODO Auto-generated method stub

			// ���EditText�ؼ�
            et_classname = (EditText)findViewById(R.id.et_classname);
            et_classroom = (EditText)findViewById(R.id.et_classroom);
            et_classteacher = (EditText)findViewById(R.id.et_classteacher);
            spinner_week = (Spinner)findViewById(R.id.class_week);
            btn_starttime = (Button)findViewById(R.id.class_starttime);
            btn_endtime = (Button)findViewById(R.id.class_endtime);
            btn_ok = (Button) findViewById(R.id.add_ok);
			et_classname.setText(lesson.classname);
            et_classroom.setText(lesson.classroom);
            et_classteacher.setText(lesson.classteacher);
            btn_starttime.setText(lesson.classstarttime);
            btn_endtime.setText(lesson.classendtime);
		    //����ѡ������ArrayAdapter��������
            adapter_week = ArrayAdapter.createFromResource(context, R.array.week, android.R.layout.simple_spinner_item);
		    //���������б�ķ��
            adapter_week.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //��adapter1 ��ӵ�spinner��
		    spinner_week.setAdapter(adapter_week);
		    //����¼�Spinner�¼����� 
		    spinner_week.setOnItemSelectedListener(new Spinner01XMLSelectedListener());
		   //����Ĭ��ֵ
		    if(lesson.classday.equals("����һ")){	spinner_week.setSelection(0);}
		    if(lesson.classday.equals("���ڶ�")){	spinner_week.setSelection(1);}
		    if(lesson.classday.equals("������")){	spinner_week.setSelection(2);}
		    if(lesson.classday.equals("������")){	spinner_week.setSelection(3);}
		    if(lesson.classday.equals("������")){	spinner_week.setSelection(4);}
		    if(lesson.classday.equals("������")){	spinner_week.setSelection(5);}
		    if(lesson.classday.equals("������")){	spinner_week.setSelection(5);}
		    spinner_week.setVisibility(View.VISIBLE);    	   
		}

		private View.OnClickListener clickListener_ok = new View.OnClickListener() {
                
                public void onClick(View v) {
//                    DialogListener.back(String.valueOf(et_classname.getText()));
                    Lesson  lesson1 = new Lesson();	
                    lesson1.num_class_id = lesson.num_class_id;
                    lesson1.classname = et_classname.getText().toString();
                    lesson1.classteacher = et_classteacher.getText().toString();
                    lesson1.classroom = et_classroom.getText().toString();
                    lesson1.classstarttime = btn_starttime.getText().toString();
                    lesson1.classendtime = btn_endtime.getText().toString();
                    lesson1.classday = str_classday;
				
					if(lesson1.classday.equals("����һ")){
						lesson1.classday_id = Integer.valueOf(0);
					}	
					
					if(lesson1.classday.equals("���ڶ�")){
						lesson1.classday_id = Integer.valueOf(1);
					}
					
					if(lesson1.classday.equals("������")){
						lesson1.classday_id = Integer.valueOf(2);
					}
					
					if(lesson1.classday.equals("������")){
						lesson1.classday_id = Integer.valueOf(3);
					}
					
					if(lesson1.classday.equals("������")){
						lesson1.classday_id =Integer.valueOf(4);
					}
					
					if(lesson1.classday.equals("������")){
						lesson1.classday_id =Integer.valueOf(5);
					}
					
					if(lesson1.classday.equals("������")){
						lesson1.classday_id =Integer.valueOf(6);
					}
					
					String str_classname_null = et_classname.getText().toString();
					String str_classroom_null = et_classroom.getText().toString();
					
		          	if (str_classname_null == null || str_classname_null.equals("") ||
		          			str_classroom_null == null || str_classroom_null.equals("")	){
						
	            		Toast.makeText(context, "�������ƻ�ע1������Ϊ��", Toast.LENGTH_SHORT).show();}
		          	else{

		            //�������ݿ������
		       	    helper = new DBHelper(context);
		       		//�����ݿ�
		       		helper.openDatabase();
					//��user�洢�����ݿ���
					helper.modifyLesson(lesson1);
					setTitle("�����޸ĳɹ�!");
					EditMyDialog.this.dismiss();
                    DialogListener.back(et_classname.getText().toString());
                }

        }
		};
        private View.OnClickListener clickListener_starttitme = new View.OnClickListener() {
            
            public void onClick(View v) {
				cal.setTimeInMillis(System.currentTimeMillis());
				int hour = cal.get(Calendar.HOUR_OF_DAY);
				int minute = cal.get(Calendar.MINUTE);
				new TimePickerDialog(context,new TimePickerDialog.OnTimeSetListener(){
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						str_starttime = hourOfDay+":"+minute;
						btn_starttime.setText(str_starttime);
						Log.i("EditMyDialog_str_starttime",str_starttime);
					}
					
				},hour,minute,true).show();
            }
         };
         
        private View.OnClickListener clickListener_endtime = new View.OnClickListener() {    
            public void onClick(View v) {
				cal.setTimeInMillis(System.currentTimeMillis());
				int hour = cal.get(Calendar.HOUR_OF_DAY);
				int minute = cal.get(Calendar.MINUTE);
				new TimePickerDialog(context,new TimePickerDialog.OnTimeSetListener(){
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						str_endtime = hourOfDay+":"+minute;
						btn_endtime.setText(str_endtime);
						Log.i("EditMyDialog_str_endtime",str_endtime);
					}
				},hour,minute,true).show();
            }
       };
      
 	   //ʹ��XML��ʽ����
        class Spinner01XMLSelectedListener implements OnItemSelectedListener{
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            	 str_classday = (String) adapter_week.getItem(arg2);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        }

}