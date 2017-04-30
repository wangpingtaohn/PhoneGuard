package com.wpt.pg.activity;
/*登陆后的主界面*/

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity implements
		OnCheckedChangeListener {
	TabSpec spec;
	private TabHost tabHost;
	private Intent systemIntent, remoteAssisitIntent, moreIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_tab);

		systemIntent = new Intent(this, SystemSetActivity.class);
		remoteAssisitIntent = new Intent(this, RemoteAssistActivity.class);
		moreIntent = new Intent(this, MoreActivity.class);

		initRadio();
		setIntent();
	}

	public void initRadio() {
		((RadioButton) findViewById(R.id.syetem_radio))
				.setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.remote_radio))
				.setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.more_radio))
				.setOnCheckedChangeListener(this);
	}

	public void setIntent() {
		tabHost = getTabHost();
		TabHost lactionTabl = getTabHost();
		if (lactionTabl != null) {
			lactionTabl.addTab(tabHost.newTabSpec("SystemSet").setIndicator(
					"systemSet")
					.setContent(systemIntent));
			lactionTabl.addTab(tabHost.newTabSpec("RemoteAssisit")
					.setIndicator("remoteAssisit").setContent(
							remoteAssisitIntent));
			lactionTabl.addTab(tabHost.newTabSpec("More").setIndicator(
					"more").setContent(moreIntent));
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.syetem_radio:
				tabHost.setCurrentTabByTag("SystemSet");
				break;
			case R.id.remote_radio:
				tabHost.setCurrentTabByTag("RemoteAssisit");
				break;
			case R.id.more_radio:
				tabHost.setCurrentTabByTag("More");
				break;
			default: break;
			}
		}
	}

}
