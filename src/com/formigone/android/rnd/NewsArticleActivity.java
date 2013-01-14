package com.formigone.android.rnd;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsArticleActivity extends Activity {

	private LruCache<String, Drawable> imgCache;
	private Typeface font;

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_nowhere, R.anim.slide_to_right);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		overridePendingTransition(R.anim.slide_from_right, R.anim.slide_nowhere);
		
		setContentView(R.layout.article);

		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		final int memClass = ((ActivityManager) getSystemService(
				Context.ACTIVITY_SERVICE)).getMemoryClass();
		final int cacheSize = 1024 * 1024 * memClass / 8;
		
		imgCache = new LruCache<String, Drawable>(cacheSize);

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			ImageView img = (ImageView) findViewById(R.id.article_img);
			String imgSrc = extras.getString("img");
			ImgDownloader imgDownloader = new ImgDownloader(img, imgSrc, imgCache);

			if (imgSrc != null) {
				if (imgCache.get(imgSrc) == null) {
					try {
						URL url = new URL(imgSrc);
						imgDownloader.execute(url);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				} else {
					imgDownloader.loadImage();
				}
			}

			TextView title = (TextView) findViewById(R.id.article_title);
			title.setText(extras.getString("title"));
			title.setTypeface(font);
			
			TextView date = (TextView) findViewById(R.id.article_date);
			date.setText(extras.getString("date"));
			date.setTypeface(font);

			TextView content = (TextView) findViewById(R.id.article_content);
			content.setText(extras.getString("content"));
		}
	}
}
