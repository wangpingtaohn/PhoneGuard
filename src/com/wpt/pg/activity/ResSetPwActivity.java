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

public class ResSetPwActivity extends Activity {

	private EditText oldPwEt, newPwEt, resAnswerEt;
	private Spinner resQuestionSpinner;
	private Button resSetPwBtn;
	private ConfigUtil resUtil;
	private String newPw, oldPw, resOldPw, resAnswer,resQuestion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setTitle("重置密码");
		setContentView(R.layout.res_set_password);

		oldPwEt = (EditText) findViewById(R.id.res_set_pw_ET);
		newPwEt = (EditText) findViewById(R.id.res_set_pw_comfirm_et);
		resAnswerEt = (EditText) findViewById(R.id.res_set_pw_answer);

		resQuestionSpinner = (Spinner) findViewById(R.id.res_question_spinner1);

		resSetPwBtn = (Button) findViewById(R.id.res_set_pw_btn);

		resUtil = new ConfigUtil(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
		
		oldPw = resUtil.getPassword();

		resSetPwBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				resSetPw();
			}
		});
	}

	private void resSetPw() {
		
		newPw = newPwEt.getText().toString();
		resOldPw = oldPwEt.getText().toString();
		resAnswer = resAnswerEt.getText().toString();
		resQuestion = resQuestionSpinner.getSelectedItem().toString();
		
		if (newPw != null && !(newPw.equals("")) && resAnswer != null
				&& !(resAnswer.equals(""))) {
			if (resOldPw.equals(oldPw)) {
				resUtil.setPassword(newPw);
				resUtil.setAnswer(resAnswer);
				resUtil.setQuestion(resQuestion);
				Toast.makeText(this, "密码修改成功", 1).show();
				this.finish();
			} else {
				Toast.makeText(this, "旧密码错误", 1).show();
			}
		} else {
			Toast.makeText(this, "密码或问题不能为空", 1).show();
		}
	}
}
