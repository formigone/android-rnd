package com.formigone.android.rnd.repository;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.formigone.android.rnd.model.Article;

public class ArticleRepository {

	private SQLiteOpenHelper dbHelper;
	private SQLiteDatabase db;

	/**********************************************************
	 * 
	 **********************************************************/
	public ArticleRepository(SQLiteOpenHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	/**********************************************************
	 * 
	 **********************************************************/
	public void addArticle(Article article) {
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DbHelper.COL_ARTICLE_TITLE, article.getTitle());
		values.put(DbHelper.COL_ARTICLE_IMG, article.getImg());
		values.put(DbHelper.COL_ARTICLE_DATE, article.getDate());
		values.put(DbHelper.COL_ARTICLE_CONTENT, article.getContent());
		
		db.insert(DbHelper.TABLE_NAME, null, values);
		db.close();
	}
	
	/**********************************************************
	 * 
	 **********************************************************/
	public void empty() {
		db = dbHelper.getWritableDatabase();
		db.execSQL("DELETE FROM " + DbHelper.TABLE_NAME);
		db.close();
	}

	/**********************************************************
	 * 
	 **********************************************************/
	public List<Article> getArticles(int max) {
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME + " LIMIT " + max, null);

		return parseArticles(cursor);
	}

	/**********************************************************
	 * 
	 **********************************************************/
	public List<Article> getAllArticles() {

		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME, null);
		
		return parseArticles(cursor);
	}
	
	/**********************************************************
	 * 
	 **********************************************************/
	private List<Article> parseArticles(Cursor cursor) {
		
		List<Article> articles = new ArrayList<Article>();

		if (cursor.moveToFirst()) {
			int COL_TITLE = cursor.getColumnIndex(DbHelper.COL_ARTICLE_TITLE);
			int COL_IMG = cursor.getColumnIndex(DbHelper.COL_ARTICLE_IMG);
			int COL_DATE = cursor.getColumnIndex(DbHelper.COL_ARTICLE_DATE);
			int COL_CONTENT = cursor.getColumnIndex(DbHelper.COL_ARTICLE_CONTENT);

			do {
				Article article = new Article();
				article.setTitle(cursor.getString(COL_TITLE));
				article.setImg(cursor.getString(COL_IMG));
				article.setDate(cursor.getString(COL_DATE));
				article.setContent(cursor.getString(COL_CONTENT));
				
				articles.add(article);
			} while (cursor.moveToNext());
		}
		
		return articles;
	}
}
