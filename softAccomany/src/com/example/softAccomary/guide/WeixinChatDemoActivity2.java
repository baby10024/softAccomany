package com.example.softAccomary.guide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.android_robot_01.R;
import com.example.softAccomany.mark.CallAlarm;
import com.example.softAccomany.mark.NotificationView;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * @author way
 */
public class WeixinChatDemoActivity2 extends Activity implements OnClickListener {

	private int i=0;
	private int j=0;
	private int m = 0;
	private int n = 0;
	private double ran;
    int ran1;
	
	private Button mBtnSend;// 鍙戦�btn
	private Button mBtnSend2;
	private Button mBtnBack;// 杩斿洖btn
	//private EditText mEditTextContent;
	
	Button BtnSkip;//璺宠繃
	
	private ListView mListView;
	private ChatMsgViewAdapter mAdapter;// 娑堟伅瑙嗗浘鐨凙dapter
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 娑堟伅瀵硅薄鏁扮粍

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main2);
		
		initView();// 鍒濆鍖杤iew

		//初始问题
		initData();
		mListView.setSelection(mAdapter.getCount() - 1);
		
		BtnSkip = (Button) findViewById(R.id.btn_skip);
		BtnSkip.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
  			Intent i = new Intent("com.example.softAccomany.mark.intent.action.Activity2");
				startActivity(i);
				
			}
		});
		
		ran= 10*Math.random();
		ran1=(int)ran;
		i=ran1;
		m=ran1;
	}

	/**
	 * 鍒濆鍖杤iew
	 */
	public void initView() {
		mListView = (ListView) findViewById(R.id.listview);
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mBtnSend.setOnClickListener(this);
		mBtnSend2 = (Button) findViewById(R.id.btn_send2);
		mBtnSend2.setOnClickListener(this);
		mBtnBack = (Button) findViewById(R.id.btn_back);
		mBtnBack.setOnClickListener(this);
		//mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
	}

	private String[][] msgArray ={ {"你太棒了","button","别骄傲，别骄傲，别骄傲，（重要的事说三遍）","button","end"},
			{"我就知道你不会令我失望。","要再接再厉哦","end"},
			{"太棒了，要不要出去吹吹风？","果然和他们说的一样，你确实有点宅。","呃呃呃……….","end"},
			{"Good boy~","赶快休息休息","要学会劳逸结合哦！快把手机放下。","end"},
			{"我为你欢呼","衣服洗了吗？屋子收拾了吗？日用品买了吗？","这是上次没完成任务的惩罚。","开玩笑的，快去休息吧。","end"},
			{"这么久才跟我讲话，计划很难吧。","给自己冲一杯咖啡，在品尝的过程中给家里打个电话，最起码让家人的牵挂有寄托的地方！","end"},
			{"鼓掌鼓掌","要相信自己啊，人的潜力是无穷的","就是要在一次一次的挑战自己而进步","end"},
			{"你太棒了","争取做成功十个计划","哈哈，你真幽默","end"},
			{"厉害","你是21世纪最炫酷少年","我是22最炫酷机器人","end"},
			{"亲，你真棒。","被你猜到了，我要去继续购物了。拜拜~","end"}
		};

private String[][] meArray ={{"哈哈，我很厉害的","我知道了。。。"},
							{"你夸得我要不好意思了","好的。"},
							{"这个点了，我没事干出去吹什么风？","我宅？好！你别指望我带着你出去了！！！","呵呵"},
							{"累了一天，眼睛有点疼。","可是还有别的事情。","好的"},
							{"终于可以玩了","你怎么这样？","没天理啊，机器人欺负人。","嘻嘻"},
							{"是啊，最近的工作一大堆，连给家人打个电话的时间都没有。","也对，那就先不说了，完了聊！"},
							{"我成功了，不敢相信。","是啊。","嗯，下次争取做的更好。"},
							{"呵呵，真不好意思。","你是要召唤神龙吗？","。。。"},
							{"那当然，我是谁！","那你呢？","你够了。"},
							{"你上淘宝了吗？","天哪，你一个机器人，上的什么淘宝？"}
};
private String[][] msgArray2 ={ {"没事，有我在，我会一直这样陪着你！陪伴才是长情哦^-^","button","客气客气","button","end"},
		{"没事，别气馁哦。要找出失败的原因。","对呀，是不是你的效率比较差呢？，或者其他原因。","嗯，加油！","end"},
		{"没关系","你还年轻！人生的道路还很漫长。","只要自己没有做错，坚持自己的初衷，就一定会大放异彩！","end"},
		{"不是吧，什么原因？","经营自己的长处，能使你人生增值，经营自己的短处，会使你人生贬值。","明白就好。","end"},
		{"看你最近心情不好，是不是遇到什么不开心的事了？","坚持!,也许会收获巨大的成功","end"},
		{"别气馁啊，不就是被我打败了么","我承认我很厉害啦，但是那么容易就成功也没意思了。","end"},
		{"别气馁啊，不就是输了么。","这次不行，还有下次。","对，无奋斗，不青春。","end"},
		{"是不是骄傲了？","这次栽了个跟头，下次要注意。","end"},
		{"是不是骄傲了？","常在河边走，哪有不湿鞋。","哈哈，记住教训吧，小子。","end"},
		{"没关系,","别伤心，你还有机会啊。","请继续努力吧。","end"}
	};
private String[][] meArray2 ={{"嗯，感谢有你！","^-^",},
			 {"没有，我真是服了我自己了。","失败的原因？","估计是吧，我要好好想想了。"},
			 {"最近我感觉我干什么都不顺心，","可是，，，","嗯"},
			 {"最近老是胡思乱想。","我似乎明白了什么。","嗯"},
			 {"也没有，就是最近的工作太多，有点累了，不想干下去了…","我会继续努力的！"},
			 {"就是这点令我难过。","是啊，我会继续努力的。"},
			 {"唉，不开心。","我要奋斗！","fighting"},
			 {"是啊，大意了。","嗯，我要吸取经验教训。"},
			 {"是啊。","你有种老江湖的样子。。","(⊙o⊙)…"},
			 {"我好难过。","嗯，我知道了。","努力"}
};
	private final static int COUNT = 17;// 鍒濆鍖栨暟缁勬�鏁�
	private final static int COUNT1 = 6;
	
		
	//当计划结束的时候
	public void initData() {
		for (; j < 4; j++) {
			ChatMsgEntity entity = new ChatMsgEntity();
			
			entity.setDate(getDate());
			//鑷繁鍐�
			if (msgArray[0][j].equals("button")) {
				
				mBtnSend.setText(meArray[0][n]);
				n++;
				break;
				
			}
			
			
				entity.setName("雷欧");
				entity.setMsgType(true);// 鏀跺埌鐨勬秷鎭�
			 
			
			entity.setMessage(msgArray[0][j]);
			mDataArrays.add(entity);
			
		}
			
			mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
		mListView.setAdapter(mAdapter);
		
		j+=1;
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send:// 鍙戦�鎸夐挳鐐瑰嚮浜嬩欢
			send();
			break;
		case R.id.btn_send2:// 鍙戦�鎸夐挳鐐瑰嚮浜嬩欢
			send();
			break;
		case R.id.btn_back:// 杩斿洖鎸夐挳鐐瑰嚮浜嬩欢
			finish();// 缁撴潫,瀹為檯寮�彂涓紝鍙互杩斿洖涓荤晫闈�
			break;
		}
	}

	/**
	 * 鍙戦�娑堟伅
	 */
	private void send() {
		//String contString = mEditTextContent.getText().toString();
		String contString = mBtnSend.getText().toString();
		if (contString.length() > 0) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setName("我");
			entity.setDate(getDate());
			entity.setMessage(contString);
			entity.setMsgType(false);

			mDataArrays.add(entity);
			mAdapter.notifyDataSetChanged();// 閫氱煡ListView锛屾暟鎹凡鍙戠敓鏀瑰彉

			//mEditTextContent.setText("");// 娓呯┖缂栬緫妗嗘暟鎹�

			mListView.setSelection(mListView.getCount() - 1);// 鍙戦�涓�潯娑堟伅鏃讹紝ListView鏄剧ず閫夋嫨鏈�悗涓�」
		}
		if(j!=4){
		initData();
		mListView.setSelection(mListView.getCount() - 1);
		}
	
		if(n==2) {
			Bundle bundle = getIntent().getExtras();
			Log.i("获得Intent","yes");
			//Intent intent = Intent.getIntent(".Alarmalert");
			String str_id = bundle.getString("action2");
			Log.i("获得的action",str_id);
			 int id = Integer.parseInt(str_id);
			 Log.i("取消闹铃ID",str_id);
			
			 Intent intent1 = new Intent(WeixinChatDemoActivity2.this,CallAlarm.class);  
			   PendingIntent pi = PendingIntent.getBroadcast(WeixinChatDemoActivity2.this, id, intent1, 0);  
			   AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);  
			   am.cancel(pi); 
			 
			  
			clearNotification();
			Intent i = new Intent("com.example.softAccomany.mark.intent.action.Activity2");
			startActivity(i);
			
		}
		
		
	}
	
	

	/**
	 * 鍙戦�娑堟伅鏃讹紝鑾峰彇褰撳墠浜嬩欢
	 * 
	 * @return 褰撳墠鏃堕棿
	 */
	private String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(new Date());
	}
	 private void clearNotification(){
	        // 启动后删除之前我们定义的通知   
	        NotificationManager notificationManager = (NotificationManager) this
	                .getSystemService(NOTIFICATION_SERVICE);   
	        notificationManager.cancel(1);  
	  
	    }
}