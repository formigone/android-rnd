package com.formigone.frags;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.Volley;

public class FragsApplication extends Application {
	public static final String TAG = "FragsApplication";
	private static RequestQueue mRequestQueue;
	private static ImageCache mImageCache;

	@Override
	public void onCreate() {
		super.onCreate();
		
		mRequestQueue = Volley.newRequestQueue(this);
		mImageCache = new ImageLruCache(1024 * 5);
	}
	
	public static RequestQueue getRequestQueue() {
		return mRequestQueue;
	}
	
	public static ImageCache getImageCache() {
		return mImageCache;
	}
}
