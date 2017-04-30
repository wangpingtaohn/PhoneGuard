package com.wpt.service;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.wpt.pg.util.Constant;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.provider.SyncStateContract.Constants;
import android.telephony.SmsManager;

public class LocationService extends Service {

	private Context context;
	private LocationManager lm;
	private Location lt;
	private double lat, lon;
	private SmsManager sManager;
	private String number;

	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		number = intent.getStringExtra("number");
		sendSMS();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		sManager = SmsManager.getDefault();

		

	}
	private void sendSMS(){
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lt = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (lt != null) {
			lat = lt.getLatitude();
			lon = lt.getLongitude();
			sManager.sendTextMessage(number, null, Constant.DESCRIPTION+"lat="+lat+",lon="+lon, null, null);
		}else {
			sManager.sendTextMessage(number, null, "暂时无法定位!!", null, null);
		}
	}

}
