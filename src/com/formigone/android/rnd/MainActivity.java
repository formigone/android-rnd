package com.formigone.android.rnd;

import java.util.List;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.formigone.android.rnd.adapter.ArticleAdapter;
import com.formigone.android.rnd.model.Article;
import com.formigone.android.rnd.service.ArticleFactory;
import com.formigone.android.rnd.service.ArticleService;

public class MainActivity extends ListActivity {

	private List<Article> articles;
	private final ArticleService articleService = ArticleFactory.getArticleService(this);

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
		articles = articleService.getCachedArticles();
		
		if (articles.size() < 1)
			return;

		ArticleAdapter adapter = new ArticleAdapter(this, R.layout.article_card, articles);

		ListView listView = getListView();
		listView.setAdapter(adapter);
	}
	
	/**********************************************************
	 * 
	 **********************************************************/
	private void loadFresh() {
		
		class FreshenUp extends AsyncTask<ArticleService, Integer, List<Article>> {

			@Override
			protected List<Article> doInBackground(ArticleService... articleService) {
				List<Article> freshArticles = articleService[0].getArticles(11);
				return freshArticles;
			}

			@Override
			protected void onPostExecute(List<Article> freshArticles) {
				Toast.makeText(getApplicationContext(), "Fetched new articles", Toast.LENGTH_SHORT).show();
				System.out.println("Total articles before freshenage: " + articles.size());

				boolean isUnique;

				// TODO: wise up this unique merge
				for (Article freshArticle: freshArticles) {
					isUnique = true;

					for (Article oldArticle: articles) {
						if (oldArticle.getTitle().contentEquals(freshArticle.getTitle())) {
							isUnique = false;
							break;
						}
					}

					if (isUnique) {
						articles.add(0, freshArticle);
						System.out.println("Added article " + freshArticle.getTitle());
					}
				}

				System.out.println("Total articles now: " + articles.size());

				ArticleAdapter adapter = new ArticleAdapter(getApplicationContext(), R.layout.article_card, articles);

				ListView listView = getListView();
				listView.setAdapter(adapter);
			}
		}

		new FreshenUp().execute(articleService);
	}
}
