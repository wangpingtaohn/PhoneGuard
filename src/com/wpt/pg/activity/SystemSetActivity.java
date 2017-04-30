package com.wpt.pg.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.wpt.pg.util.ConfigUtil;

public class SystemSetActivity extends Activity {

	private EditText numEt1, numEt2, emailEt1, emailEt2;
	private Button OkBtn;
	private ConfigUtil util;
	private String friendNum1, friendNum2, email1, email2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.system_set);

		util = new ConfigUtil(this);

		numEt1 = (EditText) findViewById(R.id.edit_friend_number1);
		numEt2 = (EditText) findViewById(R.id.edit_friend_number2);

		emailEt1 = (EditText) findViewById(R.id.edit_friend_email1);
		emailEt2 = (EditText) findViewById(R.id.edit_friend_email2);

		OkBtn = (Button) findViewById(R.id.button_setting);

		showInfo();

		OkBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addInfo();
			}
		});
	}

	public void showInfo() {
		friendNum1 = util.getFriendNumber1();
		friendNum2 = util.getFriendNumber2();

		email1 = util.getEmail1();
		email2 = util.getEmail2();

		numEt1.setText(friendNum1);
		numEt2.setText(friendNum2);

		emailEt1.setText(email1);
		emailEt2.setText(email2);
	}

	public void addInfo() {
		friendNum1 = numEt1.getText().toString();
		friendNum2 = numEt2.getText().toString();

		email1 = emailEt1.getText().toString();
		email2 = emailEt2.getText().toString();

		if (friendNum1 != null
				&& !(friendNum1.equals("") || friendNum2 != null
						&& !(friendNum2.equals("")))) {
			util.setEmail1(email1);
			util.setEmail2(email2);
			util.setFriendNumber1(friendNum1);
			util.setFriendNumber2(friendNum2);
			Toast.makeText(this, getString(R.string.set_friend_success), 1)
					.show();
		} else {
			Toast.makeText(this, getString(R.string.set_friend_failed), 1).show();
		}
	}
}
