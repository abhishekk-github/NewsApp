package com.doubtnut.amateur.newsapp.news.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun loadPopularArticles(): LiveData<List<ArticleEntity?>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArticles(articleEntities: List<ArticleEntity?>?)
}