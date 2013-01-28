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

	List<Article> articles;

	/**********************************************************
	 * 
	 **********************************************************/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		loadCache();
		loadFresh();
	}
	
	/**********************************************************
	 * 
	 **********************************************************/
	private void loadCache() {
		ArticleService articleService = ArticleFactory.getArticleService(this);
		
		articles = articleService.getCachedArticles();

		ArticleAdapter adapter = new ArticleAdapter(this, R.layout.article_card, articles);

		ListView listView = getListView();
		listView.setAdapter(adapter);
	}
	
	/**********************************************************
	 * 
	 **********************************************************/
	private void loadFresh() {
		ArticleService articleService = ArticleFactory.getArticleService(this);
		List<Article> freshArticles = articleService.getArticles(5);

		for (Article article: freshArticles) {
			articles.add(0, article);
		}
	}
}
