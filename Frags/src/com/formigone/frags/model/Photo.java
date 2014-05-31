package com.formigone.frags.model;

import com.android.volley.toolbox.ImageLoader;

public class Photo {
	protected String src;
	protected ImageLoader mImageLoader;

	public Photo(String src) {
		this.src = src;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
	
	public void setImgLoader(ImageLoader imgLoader) {
		mImageLoader = imgLoader;
	}
	
	public ImageLoader getImgLoader() {
		return mImageLoader;
	}
}
