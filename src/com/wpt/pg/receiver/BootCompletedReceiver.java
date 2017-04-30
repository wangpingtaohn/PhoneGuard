package com.wpt.pg.receiver;



import com.wpt.service.SimCheckChangeService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompletedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		context.startService(new Intent(context, SimCheckChangeService.class));
	}

}
