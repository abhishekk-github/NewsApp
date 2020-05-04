package com.doubtnut.amateur.newsapp.news.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ArticleEntity.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
