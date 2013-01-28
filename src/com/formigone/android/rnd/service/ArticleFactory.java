package com.formigone.android.rnd.service;

import android.content.Context;

public class ArticleFactory {
	public static final ArticleService getArticleService(Context ctx) {
//		return new FakeService();
//		return new KSLRssArticleService();
		return new FakeDbService(ctx);
	}
}
