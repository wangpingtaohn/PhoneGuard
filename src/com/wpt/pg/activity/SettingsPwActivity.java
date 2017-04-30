package com.wpt.pg.activity;

import android.app.Activity;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.wpt.pg.util.ConfigUtil;


	public class SettingsPwActivity extends Activity {

		private EditText pwEt, pwConfirmEt, answerEt;
		private Spinner questionSpinner;
		private Button setPwBtn;
		private ConfigUtil util;
		private TelephonyManager tm;
		private String simNumber, myNumber;
		

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			setContentView(R.layout.setting_password);

			util = new ConfigUtil(this);

			tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

			pwEt = (EditText) findViewById(R.id.set_pw_ET);
			pwConfirmEt = (EditText) findViewById(R.id.set_pw_comfirm_et);
			answerEt = (EditText) findViewById(R.id.set_pw_answer);
			questionSpinner = (Spinner) findViewById(R.id.question_spinner1);
			setPwBtn = (Button) findViewById(R.id.set_pw_btn);
			
			

			setPwBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setPw();
				}
			});
		}

		public void setPw() {

			if (tm != null) {
				simNumber = tm.getSimSerialNumber();// 获取sim卡序列号
				myNumber = tm.getLine1Number();// 获取我的电话号码
			}

			if (pwEt.getText() != null && pwConfirmEt != null
					&& answerEt != null && !pwEt.getText().equals("")
					&& !pwConfirmEt.getText().equals("")
					&& !answerEt.getText().equals("")) {
				String password = pwEt.getText().toString();
				String confirmPassword = pwConfirmEt.getText().toString();
				String question = questionSpinner.getSelectedItem().toString();
				String answer = answerEt.getText().toString();
				if (password.equals(confirmPassword)) {

					util.setPassword(confirmPassword);
					util.setQuestion(question);
					util.setAnswer(answer);
					util.setSimNumber(simNumber);
					util.setMyNumber(myNumber);

					// 设置密码成功,返回true
					Intent intent = getIntent();
					Bundle bundle = new Bundle();
					bundle.putBoolean("flag", true);
					intent.putExtras(bundle);
					setResult(0, intent);

					Toast.makeText(this, getString(R.string.set_pw_success), 1)
							.show();
					finish();
				} else {
					Toast.makeText(this, getString(R.string.set_pw_different),
							1).show();
				}
			} else {
				Toast.makeText(this, getString(R.string.set_pw_empty), 1)
						.show();
			}
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == event.KEYCODE_BACK) {
				this.finish();
			}
			return super.onKeyDown(keyCode, event);
		}
	
}
