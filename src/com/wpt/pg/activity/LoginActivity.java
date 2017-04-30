package com.wpt.pg.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wpt.pg.util.ConfigUtil;

public class LoginActivity extends Activity {
	private EditText pwEt;
	private Button loginBtn, cancelBtn, findPwBtn;
	private ConfigUtil util;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		util = new ConfigUtil(this);

		pwEt = (EditText) findViewById(R.id.login_pw_ET);
		loginBtn = (Button) findViewById(R.id.login_ok_btn);
		cancelBtn = (Button) findViewById(R.id.login_cancel_btn);
		findPwBtn = (Button) findViewById(R.id.login_find_btn);

		loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				login();
			}
		});
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		findPwBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplication(),
						FindPasswordActivity.class));
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (check()) {
			Intent intent = new Intent(this, SettingsPwActivity.class);
			startActivity(intent);
		}
	}

	public boolean check() {
		String backupPassword = util.getPassword();
		if (backupPassword == null || backupPassword.equals("")) {
			return true;
		}
		return false;
	}

	public void login() {
		String password = pwEt.getText().toString();
		String backupPassword = util.getPassword();
		if (password.equals(backupPassword)) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(this, getString(R.string.login_pw_error), 1).show();
		}
	}
}