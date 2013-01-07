package com.formigone.android.rnd.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.formigone.android.rnd.R;
import com.formigone.android.rnd.model.Article;

public class ArticleAdapter extends ArrayAdapter<Article> {

	private List<Article> articles;

	public ArticleAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}
	
	public ArticleAdapter(Context context, int resource, List<Article> articles) {
		super(context, resource, articles);
		this.articles = articles;
	}
	
	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		View view = contentView;
		
		if (view == null)
			view = LayoutInflater.from(getContext()).inflate(R.layout.article_card, null);

		Article article = articles.get(position);
		
		if (article != null) {
			TextView title = (TextView) view.findViewById(R.id.article_card_txt);
			
			if (title != null)
				title.setText(">> " + article.getTitle());
		}

		return view;
	}
}
