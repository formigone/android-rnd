package com.formigone.android.rnd.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "articles.db";
	private static final int DB_VERSION = 1;
	
	public static final String TABLE_NAME = "articles";
	public static final String COL_ARTICLE_ID = "article_id";
	public static final String COL_ARTICLE_TITLE = "article_title";
	public static final String COL_ARTICLE_IMG = "article_img";
	public static final String COL_ARTICLE_CONTENT = "article_content";
	public static final String COL_ARTICLE_AUTHOR = "article_author";
	public static final String COL_ARTICLE_DATE = "article_date";
	public static final String COL_ARTICLE_SUMMARY = "article_summary";

	/**********************************************************
	 * 
	 **********************************************************/
	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	/**********************************************************
	 * 
	 **********************************************************/
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "article_id      TEXT,"
				+ "article_title   TEXT,"
				+ "article_img     TEXT,"
				+ "article_content TEXT,"
				+ "article_author  TEXT,"
				+ "article_date    TEXT,"
				+ "article_summary TEXT)");
	}

	/**********************************************************
	 * 
	 **********************************************************/
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
		this.onCreate(db);
	}
}
