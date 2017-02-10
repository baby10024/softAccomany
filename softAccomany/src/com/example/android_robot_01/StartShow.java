package com.example.android_robot_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.softAccomany.chat.Activity1;
import com.example.softAccomany.mark.Activity2;
import com.example.softAccomany.mark.ActivityManager;
import com.example.softAccomary.guide.WeixinChatDemoActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.AlertDialog.Builder;

import android.content.ComponentName;
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
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class StartShow  extends ListActivity
{
	Button bton1,bton2,bton3;
	private ActivityManager am1;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		 //�������ݣ�һ��ArrayList��Ԫ������ΪMap<String, Object>  
        ArrayList<Map<String, Object>> array = new ArrayList<Map<String, Object>>();  
          
          
        /** ÿ��map��Ӧ��Ҫӳ�䵽ָ����ͼView�����ݡ������user.xml��Ӧ��user��ӦTextView 
        value��ӦImageView 
        */  
        Map<String, Object>  map1 = new HashMap<String, Object>();  
        map1.put("user", "      ���üƻ�");  
       // map1.put("value", R.drawable.b);  
        array.add(map1);  
          
        map1 = new HashMap<String, Object>();  
        map1.put("user", "      ����ŷ����");  
       // map1.put("value", R.drawable.a);  
        array.add(map1);  
         
          
        /** �Զ���һ��Adapter 
         * ��һ������Ϊ��ǰActivity���ڶ���arrayΪָ����Ҫ��ʾ�����ݼ��� 
         * �����������ǽ�Ҫ��ʾÿ�����ݵ�View���֣� 
         * ���ĸ��������ַ����飬��ӦView�����е�id���� 
         * �����������int���飬��Ӧ�����ļ��е�id�� 
         */  
        MyAdapter adapter = new MyAdapter(this, array, R.layout.xxx,  
                                            new String[]{"user", "value"},  
                                            new int[]{R.id.user, R.id.value});  
        //����������  
        setListAdapter(adapter);  
    }  
      
    @Override  
    protected void onListItemClick(ListView l, View v, int position, long id) {  
        // ��Ӧlist����¼�  
      /*  super.onListItemClick(l, v, position, id);  
        TextView user = (TextView) v.findViewById(R.id.user);  
        Toast.makeText(this, "��ѡ���ˣ�" + user.getText(), 1000).show();  
        System.out.println("debug:" + user.getText());  */

		Intent intent = new Intent();
		
		
		 if(position==0)
		{
			intent.setClass(StartShow.this, Activity2.class);
			StartShow.this.startActivity(intent);
		}
		else if(position==1)
		{
			intent.setClass(StartShow.this,Activity1.class);
			StartShow.this.startActivity(intent);
		}
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
    class MyAdapter extends SimpleAdapter  
    {  
        private LayoutInflater layout;  
        public MyAdapter(Context context, List<? extends Map<String, ?>> data,  
                int resource, String[] from, int[] to) {  
            super(context, data, resource, from, to);  
        }  
          
        @Override  
        public View getView(int position, View convertView, ViewGroup parent) {  
            // TODO Auto-generated method stub  
            View result = super.getView(position, convertView, parent);  
            //��ȡLayoutInflater��ʵ������  
            layout = getLayoutInflater();  
            if (result == null) {  
                //����ָ������  
                layout.inflate(R.layout.xxx, null);  
            }  
            return result;  
        }  
      
    }  
		
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
			AlertDialog.Builder adb = new Builder(StartShow.this);
			adb.setTitle("��������");
			adb.setMessage("");
			adb.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(StartShow.this, "Thanks",
							Toast.LENGTH_SHORT).show();
				}
			});
			adb.show();
			break;
		case 2:
			AlertDialog.Builder adb1 = new Builder(StartShow.this);
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
