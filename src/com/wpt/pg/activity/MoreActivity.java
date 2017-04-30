package com.wpt.pg.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MoreActivity extends Activity {

	private String[] str = { "重置密码", "软件信息", "联系我们"};
	private AlertDialog alert;
	private AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.more_layout);

		builder = new AlertDialog.Builder(this);
		
		ListView lv = (ListView) findViewById(R.id.listview);

		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, str));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					startActivity(new Intent(getApplication(), ResSetPwActivity.class));
					break;
				case 1:
					softwareMsg();
					break;
				case 2:
					relate();
					break;
				default:
					break;
				}
			}
			
		});

	}
	private void softwareMsg(){
		
		builder.setIcon(R.drawable.wpt);
		builder.setTitle("软件信息");
		builder.setMessage("phoneGuard\nVERSION.1.0");
		builder.setPositiveButton("确定", null);
		
		alert = builder.create();
		alert.show();
	}
	
	private void relate(){
		builder.setIcon(R.drawable.wpt);
		builder.setTitle("联系方式");
		builder.setMessage("wangpingtaohn@gmail.com");
		builder.setPositiveButton("确定", null);
		
		alert = builder.create();
		alert.show();
	}
}
