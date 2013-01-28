package com.formigone.android.rnd;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.formigone.android.rnd.adapter.ArticleAdapter;
import com.formigone.android.rnd.model.Article;
import com.formigone.android.rnd.service.ArticleFactory;
import com.formigone.android.rnd.service.ArticleService;

public class MainActivity extends ListActivity {

	/**********************************************************
	 * 
	 **********************************************************/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		ArticleService articleService = ArticleFactory.getArticleService(this);
		List<Article> articles = articleService.getArticles(10);

		ArticleAdapter adapter = new ArticleAdapter(this,
		R.layout.article_card, articles);

		ListView listView = getListView();
		listView.setAdapter(adapter);
	}
}
