package com.example.android_robot_01;

import com.zhy.utils.HttpUtils;

import android.test.AndroidTestCase;

public class Test extends AndroidTestCase
{
	public void testSendMsg()
	{
	    HttpUtils.sendMsg("标记4");
		HttpUtils.sendMsg("标记5");
		HttpUtils.sendMsg("标记6");
		HttpUtils.sendMsg("标记7");
	}

}
