package com.wpt.pg.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.wpt.pg.util.ConfigUtil;

public class FindPasswordActivity extends Activity {

	private Spinner spinner;
	private Button okBnt;
	private EditText answerEt;
	private ConfigUtil util;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.find_password);

		util = new ConfigUtil(this);
		spinner = (Spinner) findViewById(R.id.question_spinner2);
		answerEt = (EditText) findViewById(R.id.find_pw_answer);
		okBnt = (Button) findViewById(R.id.find_pw_btn);

		okBnt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				checkQuestion();
			}
		});
	}

	public void checkQuestion() {
		String oldQuestion = util.getQuestion();
		String oldAnswer = util.getAnswer();
		String password = util.getPassword();
		String newQuestion = spinner.getSelectedItem().toString();
		String newAnswer = answerEt.getText().toString();

		if (newQuestion.equals(oldQuestion) && newAnswer.equals(oldAnswer)) {
			Toast.makeText(this, "您的以前的密码为:" + password + ",请牢记", 1).show();
		}else {
			Toast.makeText(this, "问题或答案错误", 1).show();
		}
	}
}
