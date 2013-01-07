package com.formigone.android.rnd;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.formigone.android.rnd.model.Article;
import com.formigone.android.rnd.service.ArticleFactory;
import com.formigone.android.rnd.service.ArticleService;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ArticleService articleService = ArticleFactory.getArticleService();
        List<Article> articles = articleService.getArticles(25);
        List<String> titles = new ArrayList<String>();

        for (Article article : articles)
        	titles.add(article.getTitle());
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.article_card, R.id.article_card_txt, titles);
        
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);
    }
}
