package com.baishui.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
 


/**
 * 接收短信广播  接收器
 * @author Administrator
 *
 */
public class SMSReceiver extends BroadcastReceiver {

	/**
	 * 接收短信广播的事件处理
	 *   得到短信内容的方法
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
	   
		System.out.println("SMSReceiver  receiver one message");
		//Android SMS——Action 封装好的 intent
		//intent action 为 ：android.provider.Telephony.SMS_RECEIVED，intent中的Extras的结构如下 
		Bundle bundle = intent.getExtras();
		//键值对 为  pdus --> 数组
		Object[] myOBJpdus = (Object[]) bundle.get("pdus");
		 //利用数组就可以创建信息对象数组
		 SmsMessage[] messages = new SmsMessage[myOBJpdus.length];
		 
		 //根据消息个数 输出消息内容
		 for (int i = 0; i < myOBJpdus.length; i++) {
			messages[i] = SmsMessage.createFromPdu((byte[])myOBJpdus[i]);
			//输出短信内容的方法
			System.out.println("message content :" + messages[i].getDisplayMessageBody());
		}
		 
		 
	}

}
