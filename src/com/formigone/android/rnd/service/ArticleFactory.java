package com.formigone.android.rnd.service;

public class ArticleFactory {
	public static final ArticleService getArticleService() {
		return new FakeService();
	}
}
