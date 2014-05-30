package com.formigone.frags.loader;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.formigone.frags.service.SimpleService;
import com.formigone.frags.view.Card;

public abstract class SimpleLoader extends BroadcastReceiver {
	private static final String TAG = "SimpleLoader";

	@SuppressWarnings("unchecked")
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "Broadcast Received");
		Bundle bundle = intent.getExtras();
		List<Card> cards = new ArrayList<Card>();
		List<String> vals = (ArrayList<String>) bundle.get(SimpleService.EXTRA_DATA);
		
		for (String val: vals) {
			cards.add(new Card(val));
		}
		
		doOnReceive(cards);
	}
	
	public abstract void doOnReceive(List<Card> data);
}
