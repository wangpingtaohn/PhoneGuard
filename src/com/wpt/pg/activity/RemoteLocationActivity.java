package com.wpt.pg.activity;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.wpt.pg.util.Constant;

public class RemoteLocationActivity extends MapActivity {

	private EditText numEt, pwEt;
	private Button sendBtn;
	private SmsManager smsManager;
	private Intent intent;
	private String title;
	private LocationManager lm;
	private Location location;
	private GeoPoint point;
	private double lat, lon;
	private MapView mView;
	private MyItemizedOverLay itemizedOverLay;
	private TextView infoTv;
	private String headline;
	private String des;
	private boolean kill = false;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.map_layout);

		mView = (MapView) findViewById(R.id.mapview);
		mView.setBuiltInZoomControls(true);
		infoTv = (TextView) findViewById(R.id.lSend_tv);

		numEt = (EditText) findViewById(R.id.location_num_ET);
		pwEt = (EditText) findViewById(R.id.location_pw_et);

		sendBtn = (Button) findViewById(R.id.location_btn);

		sendBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendSms();
			}
		});

		smsManager = SmsManager.getDefault();
		intent = getIntent();

		title = intent.getStringExtra("title");
		headline = intent.getStringExtra("info");

		infoTv.setText(headline);
		setTitle(title);
		
		//---判断是被短信启动的还是软件本身自带功能的点击的实现的,此方法是定位短信来启动
		if (intent.toString().contains("flg")) {
			des = intent.getStringExtra("description");
			findLocation();
			infoTv.setText(getResources().getString(R.string.location_info));
			setTitle("远程定位");
			kill = true;
		}
		initLocation();
	}

	//收到定位模糊搜索短信后进行搜索定位
	public void findLocation() {
	
		des = intent.getStringExtra("description");
		if (des.contains("lat")) {
			int index = des.indexOf(",");
			lat = Double.valueOf((String) des.subSequence(4, index));
			lon = Double.valueOf(des.substring(index+5));
		}
		
				
	}

	public void sendSms() {
		String number = numEt.getText().toString();
		String password = pwEt.getText().toString();
		if (number != null && !(number.equals("")) && password != null
				&& !(password.equals(""))) {
			smsManager.sendTextMessage(number, null, Constant.LOCATION
					+ password, null, null);
			Toast.makeText(this, getString(R.string.send_info), 1).show();
		}
	}

	public void initLocation() {
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		Drawable drawable = getResources().getDrawable(R.drawable.coordinate);
		itemizedOverLay = new MyItemizedOverLay(drawable, this);
		if (location != null) {
			lat = location.getLatitude();
			lon = location.getLongitude();
		} else {
			lat = 39.92;
			lon = 116.46;
		}

		point = new GeoPoint(((int) (lat * 1e6)), ((int) (lon * 1e6)));

		OverlayItem overlayItem = new OverlayItem(point, "title", "snippet");

		itemizedOverLay.addOverLay(overlayItem);

		List<Overlay> overlays = mView.getOverlays();
		overlays.add(itemizedOverLay);
		mView.getController().animateTo(point);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (kill) {
			System.exit(0);
		}
	}

}
