package com.wpt.pg.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ConfigUtil {

	private SharedPreferences sp;

	public ConfigUtil(Context context) {
		sp = context.getSharedPreferences(Constant.SP_NAME,
				context.MODE_PRIVATE);
	}
	

	public String getMyNumber() {
		if (sp != null) {
			return sp.getString(Constant.MYNUMBER, "");
		}
		return null;
	}


	public void setMyNumber(String myNumber) {
		if (sp != null) {
			Editor editor = sp.edit();
			editor.putString(Constant.MYNUMBER, myNumber);
			editor.commit();
		}
	}


	public String getFriendNumber1() {
		if (sp != null) {
			return sp.getString(Constant.FRIENT_NUMBER1, "");
		}
		return null;
	}

	public void setFriendNumber1(String friendNumber1) {
		if (sp != null) {
			Editor editor = sp.edit();
			editor.putString(Constant.FRIENT_NUMBER1, friendNumber1);
			editor.commit();
		}
	}

	public String getEmail2() {
		if (sp != null) {
			return sp.getString(Constant.EMAIL2, "");
		}
		return null;
	}

	public void setEmail2(String email2) {
		if (sp != null) {
			Editor editor = sp.edit();
			editor.putString(Constant.EMAIL2, email2);
			editor.commit();
		}
	}

	public String getFriendNumber2() {
		if (sp != null) {
			return sp.getString(Constant.FRIENT_NUMBER2, "");
		}
		return null;
	}

	public void setFriendNumber2(String friendNumber2) {
		if (sp != null) {
			Editor editor = sp.edit();
			editor.putString(Constant.FRIENT_NUMBER2, friendNumber2);
			editor.commit();
		}
	}

	public String getPassword() {
		if (sp != null) {
			return sp.getString(Constant.PASSWORD, "");
		}
		return null;
	}

	public void setPassword(String password) {
		if (sp != null) {
			Editor editor = sp.edit();
			editor.putString(Constant.PASSWORD, password);
			editor.commit();
		}
	}

	public String getSimNumber() {
		if (sp != null) {
			return sp.getString(Constant.SIM_NUMBER, "");
		}
		return null;
	}

	public void setSimNumber(String simNumber) {
		if (sp != null) {
			Editor editor = sp.edit();
			editor.putString(Constant.SIM_NUMBER, simNumber);
			editor.commit();
		}
	}

	public String getQuestion() {
		if (sp != null) {
			return sp.getString(Constant.QUESTION, "");
		}
		return null;
	}

	public void setQuestion(String question) {
		if (sp != null) {
			Editor editor = sp.edit();
			editor.putString(Constant.QUESTION, question);
			editor.commit();
		}
	}

	public String getAnswer() {
		if (sp != null) {
			return sp.getString(Constant.ANSWER, "");
		}
		return null;
	}

	public void setAnswer(String answer) {
		if (sp != null) {
			Editor editor = sp.edit();
			editor.putString(Constant.ANSWER, answer);
			editor.commit();
		}
	}

	public String getEmail1() {
		if (sp != null) {
			return sp.getString(Constant.EMAIL1, "");
		}
		return null;
	}

	public void setEmail1(String email) {
		if (sp != null) {
			Editor editor = sp.edit();
			editor.putString(Constant.EMAIL1, email);
			editor.commit();
		}
	}

}
