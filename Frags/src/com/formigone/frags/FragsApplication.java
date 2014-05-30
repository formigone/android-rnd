package com.formigone.frags;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

public class FragsApplication extends Application {
	public static final String TAG = "FragsApplication";
	private static RequestQueue mRequestQueue;

	@Override
	public void onCreate() {
		super.onCreate();
		
		mRequestQueue = Volley.newRequestQueue(this);
	}
	
	public static RequestQueue getRequestQueue() {
		return mRequestQueue;
	}
}
