package com.sample.amateur.newsapp.news.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.amateur.newsapp.news.model.Article


@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun loadPopularArticles(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArticles(articleEntities: List<Article>)
}