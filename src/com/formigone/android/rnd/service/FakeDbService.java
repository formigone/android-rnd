package com.formigone.android.rnd.service;

import java.util.List;

import android.content.Context;

import com.formigone.android.rnd.model.Article;
import com.formigone.android.rnd.repository.ArticleRepository;
import com.formigone.android.rnd.repository.DbHelper;

public class FakeDbService implements ArticleService {

	private ArticleRepository repo;
	private Context ctx;

	/**********************************************************
	 * 
	 **********************************************************/
	public FakeDbService (Context ctx) {
		this.ctx = ctx;
	}

	/**********************************************************
	 * 
	 **********************************************************/
	@Override
	public List<Article> getArticles(int max) {
		
		repo = new ArticleRepository(new DbHelper(ctx));
		cleanRepo(repo);
		fillRepo(repo);

		List<Article> articles = repo.getArticles(max);

		return articles;
	}

	/**********************************************************
	 * 
	 **********************************************************/
	private void cleanRepo(ArticleRepository repo) {
		repo.empty();
	}
	
	/**********************************************************
	 * 
	 **********************************************************/
	private void fillRepo(ArticleRepository repo) {
		repo.addArticle(new Article("Pedestrian rescued from underneath TRAX train", "http://img.ksl.com/slc/2504/250482/25048280.jpg", "Mon, 28 Jan 2013 09:03:00 MST", "A man was seriously injured in a TRAX-pedestrian accident Monday morning. Elsewhere, commuters faced."));
		repo.addArticle(new Article("Senators reach agreement on immigration reform", "null", "Mon, 28 Jan 2013 08:44:00 MST", ">> No content here ='("));
		repo.addArticle(new Article("Do you buy stuff to feel better?", "http://img.ksl.com/slc/850/85099/8509959.jpg", "Mon, 28 Jan 2013 06:15:00 MST", "Coach Kim gives some advice on breaking free from the need to shop to feel better."));
		repo.addArticle(new Article("In Wasatch Mountains, man makes dog sledding a way of life", "http://img.ksl.com/slc/2504/250482/25048246.jpg", "Sun, 27 Jan 2013 22:26:00 MST", "When you think of dog sledding, you most likely think of Alaska and Canada."));
	}

	@Override
	public List<Article> getCachedArticles() {
		// TODO Auto-generated method stub
		return null;
	}
}
