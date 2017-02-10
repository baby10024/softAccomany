package com.example.softAccomary.guide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.android_robot_01.R;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * @author way
 */
public class WeixinChatDemoActivity extends Activity implements OnClickListener {

	private int i=0;
	private int j=0;
	private int i1 = 0;
    private int j1 = 0;
	private int m1 = 0;
	private int n1 = 0;
	private double ran;
    int ran1;
	
	private Button mBtnSend;// 鍙戦�btn
	private Button mBtnBack;// 杩斿洖btn
	//private EditText mEditTextContent;
	
	Button BtnSkip;//璺宠繃
	
	private ListView mListView;
	private ChatMsgViewAdapter mAdapter;// 娑堟伅瑙嗗浘鐨凙dapter
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 娑堟伅瀵硅薄鏁扮粍

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		initView();// 鍒濆鍖杤iew

		initData1();// 鍒濆鍖栨暟鎹�
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
		i1=ran1;
		m1=ran1;
	}

	/**
	 * 鍒濆鍖杤iew
	 */
	public void initView() {
		mListView = (ListView) findViewById(R.id.listview);
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mBtnSend.setOnClickListener(this);
		mBtnBack = (Button) findViewById(R.id.btn_back);
		mBtnBack.setOnClickListener(this);
		//mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
	}

private String[] msgArray = new String[] { "咳咳，","你好，","有人在吗？" ,"button","我是雷欧，来自22世纪的机器人，型号T-3681。","是你的孙子将我制造出来，并通过粒子空间传送至此。",
			"但是仍然有个问题，技术尚未成熟，我还不能生成实体，所以，，，","button","是的，你真是太机智了。"	,"我来到的这段时间，不会打扰你的生活","只需在手机给我个小小的空间就行了","button",
			"是这样的，你的孙子正在做家族研究，我会与你虚拟同步，收集你的任务完成效率，并且将数据传回。","button","没有啦，传输数据只是我的附属任务，我的主要任务是帮助你制定计划，更高效的完成它。",
			"你一定不会失望的，","现在开始设置任务","button"};
private String[] meArray = new String[] {"在的，请问你是？","所以你要在我的手机里？","好吧（无奈），可你来我这里做什么呢？","我似乎被利用了","看在你说的这么好的份上，我就试试吧。（不好用就删掉你。）"};

private String[][] msgArray1 ={ { "计划设置的不错嘛，","button","相信我，你一定可以完成的","button","那让我们开始吧" ,"button","end"},
			  {"今天还玩手机吗？","哎呦，难得啊。","可信度不高。","end"},
			  {"工作累了要休息哦","要注意保护眼睛","我这是为你好。","end"},
			  {"计划的香气","这个计划，难到爆了。","我知道，有你一定没问题","关爱计划，更关爱你","end"},
			  {"计划设置的不错嘛，","自信高涨啊","有信心是好事，fighting！","end"},
			  {"计划设置的不错嘛，","我会在你身边默默支持你哦","完成了记得奖励我哦","end"},
			  {"我们一起合作吧","我会在这边虚拟同步的哦，","是的，赶快干活吧~my partner！","end"},
			  {"我们比赛吧","对，我会虚拟同步，完成你的计划。","口气真大。","end"},
			  {"我们比赛吧","那就放马过来吧，我好歹也是22世纪文明的产物。","开始吧！输了会很丢人的哦","end"},
			  {"设置的新的计划了吗？","勇于挑战自己是好事哦。","是的，人生就是要不停的奋斗。","end"}
			};
private String[][] meArray1 = {{"还好还好，我希望可以完成","听了你的话我有信心多了","我感觉热情高涨，我要燃烧了!"},
			 {"今天我可以不玩手机","怎么  你不信？","好，你等着，我做给你看！！！"},
			 {"挺贴心呀","","好的，（啰啰嗦嗦的）","知道了。"},
			 {"行家呀，看看我自创的完美计划","是你信心不足吧，大叔。放心吧，没问题。","对我挺放心的啊"},
			 {"那当然，你也不看看我是谁(骄傲)","是呀，我要努力了。","好的。"},
			 {"还好还好，不知道能不能完成。","放心吧，我会完成的。","嗯"},
			 {"怎么合作？","数据传回22世纪？","好的"},
			 {"比赛？","那一定会是我赢的。","让我们开始比赛吧！"},
			 {"好呀，打败你很有成就感！","别得意，等着瞧！","废话少说，开始吧！"},
			 {"对啊。","挑战自己很有成就感。","我是一个热血青年。"}
			};
	
	private final static int COUNT = 17;// 鍒濆鍖栨暟缁勬�鏁�
	private final static int COUNT1 = 6;
	
	/**
	 * 妯℃嫙鍔犺浇娑堟伅鍘嗗彶锛屽疄闄呭紑鍙戝彲浠ヤ粠鏁版嵁搴撲腑璇诲嚭
	 */
	/*
	public void initData() {
		for (; i < COUNT; i++) {
			ChatMsgEntity entity = new ChatMsgEntity();
			
			entity.setDate(getDate());
			//鑷繁鍐�
			if (msgArray[i].equals("button")) {
				
				mBtnSend.setText(meArray[j]);
				j++;
				break;
				
			}
			
			
				entity.setName("雷欧");
				entity.setMsgType(true);// 鏀跺埌鐨勬秷鎭�
			 
			
			entity.setMessage(msgArray[i]);
			mDataArrays.add(entity);
			
		}
			
			mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
		mListView.setAdapter(mAdapter);
		
		i+=1;
		
	}
	*/
	
	//当添加计划成功的时候
	public void initData1() {
		for (; j1 < 6; j1++) {
			ChatMsgEntity entity = new ChatMsgEntity();
			
			entity.setDate(getDate());
			//鑷繁鍐�
			if (msgArray1[0][j1].equals("button")) {
				
				mBtnSend.setText(meArray1[0][n1]);
				n1++;
				break;
				
			}
			
			
				entity.setName("雷欧");
				entity.setMsgType(true);// 鏀跺埌鐨勬秷鎭�
			 
			
			entity.setMessage(msgArray1[0][j1]);
			mDataArrays.add(entity);
			
		}
			
			mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
		mListView.setAdapter(mAdapter);
		
		j1+=1;
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send:// 鍙戦�鎸夐挳鐐瑰嚮浜嬩欢
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
		if(j1!=6){
		initData1();
		mListView.setSelection(mListView.getCount() - 1);
		}
		
		if(n1==3) {
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
	        notificationManager.cancel(0);  
	  
	    }
}