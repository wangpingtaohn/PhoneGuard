package com.wpt.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.wpt.pg.activity.R;
import com.wpt.pg.util.ConfigUtil;

public class SimCheckChangeService extends IntentService {

	private String friendNumber1, friendNumber2, oldSimNumber, newSimNumber;
	private TelephonyManager tm;
	private ConfigUtil util;
	private SmsManager smsManager;
	private String myNumber;

	public SimCheckChangeService() {
		super("SimCheckChangeService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		util = new ConfigUtil(getApplicationContext());
		tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		newSimNumber = tm.getSimSerialNumber();// 获取新的sim序列号

		friendNumber1 = util.getFriendNumber1();
		friendNumber2 = util.getFriendNumber2();
		oldSimNumber = util.getSimNumber();
		
		if (!newSimNumber.equals(oldSimNumber) && newSimNumber == null) {
			sendSms(friendNumber1, friendNumber2);
			// 序列号与原来的不配合,锁定屏幕
			startService(new Intent(getApplicationContext(),
					LockScreenService.class));
		}
	}

	public void sendSms(String num1, String num2) {
		smsManager = SmsManager.getDefault();
		myNumber = tm.getLine1Number();

		smsManager.sendTextMessage(num1, null, getString(R.string.send_sms)
				+ "手机号为:" + myNumber, null, null);
		smsManager.sendTextMessage(num2, null, getString(R.string.send_sms),
				null, null);
	}

}
