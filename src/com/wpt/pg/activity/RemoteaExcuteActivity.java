package com.wpt.pg.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.wpt.pg.util.Constant;

public class RemoteaExcuteActivity extends Activity {

	private EditText numEt, pwEt;
	private Button alarmBtn;
	private SmsManager smsManager;
	private Intent intent;
	private int action;
	private String title,headline;
	private TextView infoTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.remote_layout);

		numEt = (EditText) findViewById(R.id.alarm_num_ET);
		pwEt = (EditText) findViewById(R.id.alarm_pw_et);
		infoTv = (TextView)findViewById(R.id.send_tv);
		
		alarmBtn = (Button) findViewById(R.id.alarm_btn);

		smsManager = SmsManager.getDefault();
		intent = getIntent();
		action = intent.getIntExtra("action", -1); 
		title = intent.getStringExtra("title");
		headline = intent.getStringExtra("info");
		
		infoTv.setText(headline);
		setTitle(title);
		
		alarmBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (action) {
				case 0:
					sendSms(Constant.LOCKSCREEN);
					break;
				case 1:
					sendSms(Constant.BACKUP);
					break;
				case 2:
					sendSms(Constant.DELETE);
					break;
				case 3:
					
					break;
				case 4:
					sendSms(Constant.REMOTE_ALARM);
					break;
				case 5:
					
					break;

				default:
					break;
				}
			}
		});
	}

	private void sendSms(String content) {
		String number = numEt.getText().toString();
		String password = pwEt.getText().toString();
		if (number != null && !(number.equals("")) && password != null
				&& !(password.equals(""))) {
			smsManager.sendTextMessage(number, null, content+password,
					null, null);
			Toast.makeText(this, getString(R.string.send_info), 1).show();
		}
	}

}
