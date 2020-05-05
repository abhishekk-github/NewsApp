package com.sample.amateur.newsapp.news.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.sample.amateur.newsapp.news.model.Article;

@Database(entities = {Article.class}, version = 2)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
