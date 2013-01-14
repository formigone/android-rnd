package com.formigone.android.rnd.adapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.formigone.android.rnd.ImgDownloader;
import com.formigone.android.rnd.NewsArticleActivity;
import com.formigone.android.rnd.R;
import com.formigone.android.rnd.model.Article;

public class ArticleAdapter extends ArrayAdapter<Article> {

	private List<Article> articles;
	private LruCache<String, Drawable> imgCache;
	private Typeface font;
	private int lastPosition;

	public ArticleAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}
	
	public ArticleAdapter(Context context, int resource, List<Article> articles) {
		super(context, resource, articles);
		this.articles = articles;
		font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
		
		final int memClass = ((ActivityManager) context.getSystemService(
				Context.ACTIVITY_SERVICE)).getMemoryClass();
		final int cacheSize = 1024 * 1024 * memClass / 8;
		
		imgCache = new LruCache<String, Drawable>(cacheSize);
		
		lastPosition = 0;
	}
	
	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		View view = contentView;

		if (view == null)
			view = LayoutInflater.from(getContext()).inflate(R.layout.article_card, null);

		final Article article = articles.get(position);
		
		if (article != null) {
			TextView title = (TextView) view.findViewById(R.id.article_card_txt);
			TextView date = (TextView) view.findViewById(R.id.article_card_date);
			ImageView img = (ImageView) view.findViewById(R.id.article_card_img);

			title.setText(article.getTitle());
			title.setTypeface(font);
			date.setText(article.getDate());
			date.setTypeface(font);
			
			img.setVisibility(ImageView.GONE);
			String imgSrc = article.getImg();

			if (imgSrc != null) {

				img.setVisibility(TextView.VISIBLE);
				img.setImageResource(R.drawable.newspaper_pattern);
				ImgDownloader imgDownloader = new ImgDownloader(img, imgSrc, imgCache);

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
			
			view.setClickable(true);
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(getContext(), NewsArticleActivity.class);
					intent.putExtra("title", article.getTitle());
					intent.putExtra("date", article.getDate());
					intent.putExtra("content", article.getContent());
					intent.putExtra("img", article.getImg());

					getContext().startActivity(intent);
				}
			});
		}

		if (position >= lastPosition && lastPosition > 0) {
			ViewAnimator viewAnim = (ViewAnimator) view.findViewById(R.id.article_card_animator);
			Animation anim;
			
			anim = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
					Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF, 0.0f);

			anim.setDuration(700);
			viewAnim.setAnimation(anim);
		}
		
		lastPosition = position;
		return view;
	}
}
