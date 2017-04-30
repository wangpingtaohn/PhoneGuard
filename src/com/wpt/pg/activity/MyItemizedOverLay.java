package com.wpt.pg.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverLay extends ItemizedOverlay {

	private Context mContext;
	private List<OverlayItem> list = new ArrayList<OverlayItem>();
	public MyItemizedOverLay(Drawable defaultMarker,Context mContext) {
		super(boundCenter(defaultMarker));
		this.mContext = mContext;
	}

	@Override
	protected boolean onTap(int index) {
		OverlayItem item = list.get(index);
		
		AlertDialog.Builder  builder = new AlertDialog.Builder(mContext);
		builder.setTitle(item.getTitle());
		builder.setMessage(item.getSnippet());
		AlertDialog alert = builder.create();
		alert.show();
		return super.onTap(index);
	}

	@Override
	public int size() {
		return list.size();
	}

	public void addOverLay(OverlayItem overlayItem){
		list.add(overlayItem);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		
		return list.get(i);
	}

}
