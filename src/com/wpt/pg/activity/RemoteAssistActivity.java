package com.wpt.pg.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class RemoteAssistActivity extends Activity {

	private String[] items;
	private LayoutInflater inflater;
	private GridView gridView;
	private Intent intent;
	private String headline;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index_view_layout);
		gridView = (GridView) findViewById(R.id.grid_view);

		intent = new Intent(getApplication(), RemoteaExcuteActivity.class);

		gridView.setAdapter(new MyAdapter());

	}

	public class MyAdapter extends BaseAdapter {

		int count = 0;
		int[] images = { R.drawable.icon1, R.drawable.icon2, R.drawable.icon3,
				R.drawable.icon4, R.drawable.icon5, R.drawable.icon6 };

		public MyAdapter() {
			inflater = LayoutInflater.from(getApplication());
			items = getResources().getStringArray(R.array.remote_arrays);

			count = items.length;
		}

		@Override
		public int getCount() {

			return count;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View v = inflater.inflate(R.layout.grid_item, null);
			ImageView imageView = (ImageView) v.findViewById(R.id.image_view);
			final TextView tagTv = (TextView) v.findViewById(R.id.image_text);

			imageView.setImageResource(images[position]);
			tagTv.setText(items[position]);

			v.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					switch (position) {
					case 0:
						headline = getString(R.string.lock_screen_info);
						intent.putExtra("info", headline);
						intent.putExtra("action", 0);
						intent.putExtra("title", tagTv.getText().toString());
						startActivity(intent);
						break;
					case 1:
						headline = getString(R.string.back_info);
						intent.putExtra("info", headline);
						intent.putExtra("action", 1);
						intent.putExtra("title", tagTv.getText().toString());
						startActivity(intent);
						break;
					case 2:
						headline = getString(R.string.delete_info);
						intent.putExtra("info", headline);
						intent.putExtra("action", 2);
						intent.putExtra("title", tagTv.getText().toString());
						startActivity(intent);
						break;
					case 3:
						headline = getString(R.string.location_info);
						Intent locationintent = new Intent(getApplication(),
								RemoteLocationActivity.class);
						locationintent.putExtra("action", 3);
						locationintent.putExtra("title", tagTv.getText()
								.toString());
						locationintent.putExtra("info", headline);
						startActivity(locationintent);
						break;
					case 4:
						headline = getString(R.string.salarm_info);
						intent.putExtra("info", headline);
						intent.putExtra("action", 4);
						intent.putExtra("title", tagTv.getText().toString());
						startActivity(intent);
						break;
					case 5:
						startActivity(new Intent(getApplicationContext(),
								SystemHelpActivity.class));
					default:
						break;
					}

				}
			});
			return v;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

	}

}
