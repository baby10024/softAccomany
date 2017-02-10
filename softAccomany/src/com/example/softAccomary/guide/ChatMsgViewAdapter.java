package com.example.softAccomary.guide;

import java.util.List;

import com.example.android_robot_01.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * 娑堟伅ListView鐨凙dapter
 * 
 * @author way
 */
public class ChatMsgViewAdapter extends BaseAdapter {

	public static interface IMsgViewType {
		int IMVT_COM_MSG = 0;// 鏀跺埌瀵规柟鐨勬秷鎭�
		int IMVT_TO_MSG = 1;// 鑷繁鍙戦�鍑哄幓鐨勬秷鎭�
	}

	private static final int ITEMCOUNT = 2;// 娑堟伅绫诲瀷鐨勬�鏁�
	private List<ChatMsgEntity> coll;// 娑堟伅瀵硅薄鏁扮粍
	private LayoutInflater mInflater;

	public ChatMsgViewAdapter(Context context, List<ChatMsgEntity> coll) {
		this.coll = coll;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return coll.size();
	}

	public Object getItem(int position) {
		return coll.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	/**
	 * 寰楀埌Item鐨勭被鍨嬶紝鏄鏂瑰彂杩囨潵鐨勬秷鎭紝杩樻槸鑷繁鍙戦�鍑哄幓鐨�
	 */
	public int getItemViewType(int position) {
		ChatMsgEntity entity = coll.get(position);

		if (entity.getMsgType()) {//鏀跺埌鐨勬秷鎭�
			return IMsgViewType.IMVT_COM_MSG;
		} 
		else {//鑷繁鍙戦�鐨勬秷鎭�
			return IMsgViewType.IMVT_TO_MSG;
		}
	}

	/**
	 * Item绫诲瀷鐨勬�鏁�
	 */
	public int getViewTypeCount() {
		return ITEMCOUNT;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ChatMsgEntity entity = coll.get(position);
		boolean isComMsg = entity.getMsgType();

		ViewHolder viewHolder = null;
		if (convertView == null) {
			if (isComMsg) {
				convertView = mInflater.inflate(
						R.layout.chatting_item_msg_text_left, null);
			} else {
				convertView = mInflater.inflate(
						R.layout.chatting_item_msg_text_right, null);
			}

			viewHolder = new ViewHolder();
			viewHolder.tvSendTime = (TextView) convertView
					.findViewById(R.id.tv_sendtime);
			//viewHolder.tvUserName = (TextView) convertView
			//		.findViewById(R.id.tv_username);
			viewHolder.tvContent = (TextView) convertView
					.findViewById(R.id.tv_chatcontent);
			viewHolder.isComMsg = isComMsg;

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tvSendTime.setText(entity.getDate());
		//viewHolder.tvUserName.setText(entity.getName());
		viewHolder.tvContent.setText(entity.getMessage());
		return convertView;
	}

	static class ViewHolder {
		public TextView tvSendTime;
	//	public TextView tvUserName;
		public TextView tvContent;
		public boolean isComMsg = true;
	}

}
