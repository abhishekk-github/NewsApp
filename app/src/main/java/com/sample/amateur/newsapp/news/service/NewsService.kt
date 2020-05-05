package com.sample.amateur.newsapp.news.service

import com.sample.amateur.core.responsewrapper.ApiResponse
import com.sample.amateur.newsapp.news.model.NewsData
import retrofit2.http.GET

interface NewsService {
    @GET("https://demo9502422.mockable.io/headlines")
    suspend fun getNewsData(): ApiResponse<NewsData>
}