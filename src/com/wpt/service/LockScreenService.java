package com.wpt.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wpt.pg.activity.R;
import com.wpt.pg.util.ConfigUtil;

public class LockScreenService extends Service {

	private EditText passwordEt;
	private LayoutInflater ifInflater;
	private Context context;
	private String passoword;
	private WindowManager wm;
	private View v;
	private WindowManager.LayoutParams params;
	private Button unLockBtn;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	
	}

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		
		ifInflater = LayoutInflater.from(context);
		v = ifInflater.inflate(R.layout.lock_screen, null);
		
		passwordEt = (EditText) v.findViewById(R.id.lock_screen_ET);
		
		unLockBtn = (Button)v.findViewById(R.id.lock_ok_btn);
		
		unLockBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (check()) {
					stopSelf();
				}
			}
		});
		
		wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		params = new WindowManager.LayoutParams();
		
		params.width=-1;
		params.height=-1;
		
		params.flags=1280;
		params.type=2002;
		wm.addView(v, params);
	}
	public boolean check(){
		passoword = passwordEt.getText().toString();
		ConfigUtil util = new ConfigUtil(context);
		if (passoword.equals(util.getPassword())) {
			return true;
		}else {
			Toast.makeText(context, "√‹¬Î¥ÌŒÛ", 1).show();
		}
		return false;
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (wm!=null&&v!=null) {
			wm.removeView(v);
		}
	}

}
