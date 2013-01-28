package com.formigone.android.rnd.service;

import java.util.List;

import com.formigone.android.rnd.model.Article;

public interface ArticleService {
	List<Article> getArticles(int max);
	List<Article> getCachedArticles();
}
