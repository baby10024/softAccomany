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
		
		 //定义数据，一个ArrayList，元素类型为Map<String, Object>  
        ArrayList<Map<String, Object>> array = new ArrayList<Map<String, Object>>();  
          
          
        /** 每个map对应将要映射到指定视图View的数据。这里和user.xml对应，user对应TextView 
        value对应ImageView 
        */  
        Map<String, Object>  map1 = new HashMap<String, Object>();  
        map1.put("user", "      设置计划");  
       // map1.put("value", R.drawable.b);  
        array.add(map1);  
          
        map1 = new HashMap<String, Object>();  
        map1.put("user", "      与雷欧聊天");  
       // map1.put("value", R.drawable.a);  
        array.add(map1);  
         
          
        /** 自定义一个Adapter 
         * 第一个参数为当前Activity，第二个array为指定需要显示的数据集合 
         * 第三个参数是将要显示每行数据的View布局， 
         * 第四个参数是字符数组，对应View布局中的id名称 
         * 第五个参数是int数组，对应布局文件中的id。 
         */  
        MyAdapter adapter = new MyAdapter(this, array, R.layout.xxx,  
                                            new String[]{"user", "value"},  
                                            new int[]{R.id.user, R.id.value});  
        //设置适配器  
        setListAdapter(adapter);  
    }  
      
    @Override  
    protected void onListItemClick(ListView l, View v, int position, long id) {  
        // 响应list点击事件  
      /*  super.onListItemClick(l, v, position, id);  
        TextView user = (TextView) v.findViewById(R.id.user);  
        Toast.makeText(this, "你选择了：" + user.getText(), 1000).show();  
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
     * 继承SimpleAdapter 实现的适配器 
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
            //获取LayoutInflater的实例对象  
            layout = getLayoutInflater();  
            if (result == null) {  
                //载入指定布局  
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
		menu.add(0, 1, 1, "关于");
		menu.add(0, 2, 2, "退出");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			AlertDialog.Builder adb = new Builder(StartShow.this);
			adb.setTitle("关于我们");
			adb.setMessage("");
			adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(StartShow.this, "Thanks",
							Toast.LENGTH_SHORT).show();
				}
			});
			adb.show();
			break;
		case 2:
			AlertDialog.Builder adb1 = new Builder(StartShow.this);
			adb1.setTitle("消息");
			adb1.setMessage("真的要退出吗？");
			adb1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
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
			adb1.setNegativeButton("取消", null);
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
	            // 创建退出对话框  
	            AlertDialog isExit = new AlertDialog.Builder(this).create();  
	            // 设置对话框标题  
	            isExit.setTitle("消息");  
	            // 设置对话框消息  
	            isExit.setMessage("确定要退出吗");  
	            // 添加选择按钮并注册监听  
	            isExit.setButton("确定", listener);  
	            isExit.setButton2("取消", listener);  
	            // 显示对话框  
	            isExit.show();  
	  
	        }  
	          
	        return false;  
	          
	    }  
	    /**监听对话框里面的button点击事件*/  
	    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
	    {  
	        public void onClick(DialogInterface dialog, int which)  
	        {  
	            switch (which)  
	            {  
	            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
	            {//am1.exitAllProgress();
	            	Intent home = new Intent(Intent.ACTION_MAIN);  
	            	home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            	home.addCategory(Intent.CATEGORY_HOME);  
	            	startActivity(home); 
	                finish();  
	                break;  }
	            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
	                break;  
	            default:  
	                break;  
	            }  
	        }  
	    };  
}
