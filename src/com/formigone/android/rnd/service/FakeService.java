package com.formigone.android.rnd.service;

import java.util.ArrayList;
import java.util.List;

import com.formigone.android.rnd.model.Article;

public class FakeService implements ArticleService {
	private List<Article> articles;
	
	public FakeService() {
		articles = new ArrayList<Article>();
		
		articles.add(new Article("EnergySolutions to be acquired by Energy Capital Partners"));
		articles.add(new Article("Colo. prosecutors to outline theater shooting case"));
		articles.add(new Article("Recipe: Chicken Carbonara under 500 calories"));
		articles.add(new Article("Syrian troops repulse rebel attack in Aleppo"));
		articles.add(new Article("6 steps to building better relationships"));
		articles.add(new Article("Lenovo to release giant 27-inch 'coffee table PC'"));
		articles.add(new Article("Sources: Obama to nominate Hagel as Pentagon chief"));
		articles.add(new Article("Suspected accomplice of costumed robber identified"));
		articles.add(new Article("Cat used in Brazil prison smuggling try"));
		articles.add(new Article("EnergySolutions to be acquired by Energy Capital Partners"));
		articles.add(new Article("Colo. prosecutors to outline theater shooting case"));
		articles.add(new Article("Recipe: Chicken Carbonara under 500 calories"));
		articles.add(new Article("Syrian troops repulse rebel attack in Aleppo"));
		articles.add(new Article("6 steps to building better relationships"));
		articles.add(new Article("Lenovo to release giant 27-inch 'coffee table PC'"));
		articles.add(new Article("Sources: Obama to nominate Hagel as Pentagon chief"));
		articles.add(new Article("Suspected accomplice of costumed robber identified"));
		articles.add(new Article("Cat used in Brazil prison smuggling try"));
		articles.add(new Article("EnergySolutions to be acquired by Energy Capital Partners"));
		articles.add(new Article("Colo. prosecutors to outline theater shooting case"));
		articles.add(new Article("Recipe: Chicken Carbonara under 500 calories"));
		articles.add(new Article("Syrian troops repulse rebel attack in Aleppo"));
		articles.add(new Article("6 steps to building better relationships"));
		articles.add(new Article("Lenovo to release giant 27-inch 'coffee table PC'"));
		articles.add(new Article("Sources: Obama to nominate Hagel as Pentagon chief"));
		articles.add(new Article("Suspected accomplice of costumed robber identified"));
		articles.add(new Article("Cat used in Brazil prison smuggling try"));
	}
	
	public List<Article> getArticles(int max) {
		if (max < 0)
			max = 1;
		if (max > articles.size())
			max = articles.size();
		
		List<Article> data = new ArrayList<Article>(max);

		for (int i = 0; i < max; i++)
			data.add(articles.get(i));

		return data;
	}
}
