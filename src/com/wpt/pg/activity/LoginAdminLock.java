package com.wpt.pg.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.wpt.pg.util.ConfigUtil;

public class LoginAdminLock extends DeviceAdminReceiver {

	public static class LoginActivity extends Activity {
		
		private EditText pwEt;
		private Button loginBtn, cancelBtn, findPwBtn;
		private ConfigUtil util;
		private boolean flag = false;
		private AlertDialog alertDialog;
		private DevicePolicyManager dpm;
		private ComponentName cName;

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
					dialogInfo();
				}
			});
			findPwBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(new Intent(getApplication(),
							FindPasswordActivity.class));
				}
			});

			dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
			cName = new ComponentName(LoginActivity.this, LoginAdminLock.class);

		}

		@Override
		protected void onResume() {
			super.onResume();
			lockAdmin();
			if (check()) {
				Intent intent = new Intent(this, SettingsPwActivity.class);
				startActivityForResult(intent, 0);

			}
		}
		//给程序加上Admin权限,需到选择管理器里才能卸载掉
		private void lockAdmin(){
			boolean active = dpm.isAdminActive(cName);
			if (!active) {
				
				Intent intent = new Intent(
						DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
				intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cName);
				intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, 0);

				startActivityForResult(intent, 1);
			}
		}

		@Override
		protected void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			if (data != null) {
				Bundle bundle = data.getExtras();
				flag = bundle.getBoolean("flag");
			}
			if (!flag) {
				System.exit(0);
			}

		}

		public boolean check() {
			String backupPassword = util.getPassword();
			if (backupPassword == null || backupPassword.equals("")) {
				return true;
			} else {
				return false;
			}
		}

		public void login() {
			String password = pwEt.getText().toString();
			String backupPassword = util.getPassword();
			if (password.equals(backupPassword)) {
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(this, getString(R.string.login_pw_error), 1)
						.show();
			}
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == event.KEYCODE_BACK) {
				dialogInfo();
			}
			return super.onKeyDown(keyCode, event);

		}

		private void dialogInfo() {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示信息");
			builder.setMessage("确定要退出？");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							System.exit(0);
						}
					});
			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							alertDialog.cancel();
						}
					});
			alertDialog = builder.create();
			alertDialog.show();
		}
	}
}