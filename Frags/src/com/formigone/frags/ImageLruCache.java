package com.formigone.frags;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class ImageLruCache extends LruCache<String, Bitmap> implements ImageCache
{
	public static final String TAG = "ImageLruCache";
	
	public ImageLruCache(int sizeOfCache)
	{
		super(sizeOfCache);
	}
	
	@Override
	public void putBitmap(String key, Bitmap bitmap)
	{
		String actualKey = key;
		
		if (bitmap == null) {
			return;
		}
		
		if (key == null) {
			actualKey = "null";
		}
		
		if (getBitmap(actualKey) == null) {
			this.put(actualKey, bitmap);
		}
	}
	
	@Override
	public Bitmap getBitmap(String key)
	{
		String actualKey = key;
		
		if (key == null) {
			actualKey = "null";
		}
		
		return this.get(actualKey);
	}
	
	@Override
	protected int sizeOf(String key, Bitmap value)
	{
		return value.getRowBytes() * value.getHeight();
	}
}
