package com.formigone.frags.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class SimpleService extends IntentService {
	private static final String TAG = "SimpleService";
	
	public static final String LIST_SIZE_MAX = "ListSizeMax";
	
	public static final String ACTION_DATA_READY = "com.formigone.frags.rec.DATA_READY";

	public static final String EXTRA_DATA = "ExtraData";

	public SimpleService() {
		super("SimpleService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		int max = intent.getIntExtra(LIST_SIZE_MAX, 5);
		List<String> items = makeItems(max);
		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_DATA, (Serializable) items);

		Intent locIntent = new Intent(ACTION_DATA_READY);
		locIntent.putExtras(bundle);

		LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(locIntent);
		Log.i(TAG, "Broadcast Sent");
	}
	
	protected List<String> makeItems(int size) {
		List<String> items = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			items.add((i + 1) + "");
		}

		return items;
	}
}
