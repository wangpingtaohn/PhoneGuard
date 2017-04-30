package com.wpt.pg.receiver;

import com.wpt.pg.activity.RemoteLocationActivity;
import com.wpt.pg.util.ConfigUtil;
import com.wpt.pg.util.Constant;
import com.wpt.service.BackupContactService;
import com.wpt.service.LocationService;
import com.wpt.service.LockScreenService;
import com.wpt.service.RemoteAlarmService;
import com.wpt.service.RemoteDeleteService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class RemoteAssistReceiver extends BroadcastReceiver {

	private SmsMessage smsMessage;
	private ConfigUtil util;
	private Context context;
	private String oldPassword, number;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		util = new ConfigUtil(context);
		oldPassword = util.getPassword();
		Bundle bundle = intent.getExtras();// 获取内容

		Object[] object = (Object[]) bundle.get("pdus");
		SmsMessage[] smsMessages = new SmsMessage[object.length];

		for (int i = 0; i < object.length; i++) {
			smsMessages[i] = SmsMessage.createFromPdu((byte[]) object[i]);
		}
		for (int i = 0; i < smsMessages.length; i++) {
			smsMessage = smsMessages[i];
			number = smsMessage.getDisplayOriginatingAddress();
			String content = smsMessage.getDisplayMessageBody();
	
			exetude(content);
		}
	}

	public void exetude(String message) {

		// -----------警报短信------------
		if (message != null && message.contains(Constant.REMOTE_ALARM)) {
			int len = Constant.REMOTE_ALARM.length();
			String password = message.substring(len);
			if (password.equals(oldPassword)) {
				context.startService(new Intent(context,
						RemoteAlarmService.class));
				abortBroadcast();// 拦截短信不让其显示
			}
			// -----------备份短信------------
		}
		if (message != null && message.contains(Constant.BACKUP)) {
			int len = Constant.BACKUP.length();
			String password = message.substring(len);
			if (password.equals(oldPassword)) {
				/*
				 * context.startService(new Intent(context,
				 * BackupContactService.class));
				 */
				Intent intent = new Intent(context, BackupContactService.class);
				intent.putExtra("number", number);
				context.startService(intent);
				abortBroadcast();
			}
		}// 删除数据----------
		if (message != null && message.contains(Constant.DELETE)) {
			int len = Constant.DELETE.length();
			String password = message.substring(len);
			if (password.equals(oldPassword)) {
				context.startService(new Intent(context,
						RemoteDeleteService.class));
				abortBroadcast();
			}
		}
		// 锁屏----------
		if (message != null && message.contains(Constant.LOCKSCREEN)) {
			int len = Constant.LOCKSCREEN.length();
			String password = message.substring(len);
			if (password.equals(oldPassword)) {
				/*
				 * context.startActivity(new Intent(context,
				 * LockScreenActivity.class));
				 */
				context.startService(new Intent(context,
						LockScreenService.class));
				abortBroadcast();
			}
		}// 定位----------
		if (message != null && message.contains(Constant.LOCATION)) {
			int len = Constant.LOCATION.length();
			String password = message.substring(len);
			if (password.equals(oldPassword)) {
				Intent intent = new Intent(context, LocationService.class);
				intent.putExtra("number", number);
				context.startService(intent);
				// context.startService(new Intent(context,
				// LocationService.class));
				abortBroadcast();
			}
		}// 获取经纬度描述来定位----------
		if (message != null && message.contains(Constant.DESCRIPTION)) {
			int len = Constant.DESCRIPTION.length();
			String description = message.substring(len);

			if (description != null && !description.equals("")) {
				//abortBroadcast();
				Intent intent = new Intent(context,
						RemoteLocationActivity.class);
				
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("description", description);
				context.startActivity(intent);
				
			}
		}
	}

}
