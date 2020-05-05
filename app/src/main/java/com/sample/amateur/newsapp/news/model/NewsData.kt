package com.sample.amateur.newsapp.news.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NewsData(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)