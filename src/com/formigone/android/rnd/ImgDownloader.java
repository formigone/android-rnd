package com.formigone.android.rnd;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

public class ImgDownloader extends AsyncTask<URL, Integer, Long> {

		private ImageView img;
		private String src;
		private LruCache imgCache;

		public ImgDownloader(ImageView img, String src, LruCache imgCache) {
			this.img = img;
			this.src = src;
			this.imgCache = imgCache;
		}

		@Override
		protected Long doInBackground(URL... url) {
			URL imgUrl = (URL) url[0];
			InputStream in;

			try {
				in = (InputStream) imgUrl.getContent();
				Bitmap bitmap = BitmapFactory.decodeStream(in);
				Drawable drawable = new BitmapDrawable(bitmap);
				imgCache.put(src, drawable);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Long result) {
			super.onPostExecute(result);
			loadImage();
		}
		
		public void loadImage() {
			Drawable drawable = (Drawable) imgCache.get(src);
			img.setImageDrawable(drawable);
		}
	}