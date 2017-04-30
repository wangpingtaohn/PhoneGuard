package com.wpt.service;

import com.wpt.pg.activity.R;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

public class RemoteAlarmService extends IntentService {

	private MediaPlayer player;
	private static final long TIMEOUT = 1000 * 30;

	public RemoteAlarmService() {
		super("RemoteAlarmService----Thread");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		player = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
		player.setLooping(true);
		player.start();
		long stopTime = System.currentTimeMillis() + TIMEOUT;
		while (System.currentTimeMillis() > stopTime) {
			
			System.out.println("RemoteAlarmService----Thread");
			player.stop();
		}
	}

}
