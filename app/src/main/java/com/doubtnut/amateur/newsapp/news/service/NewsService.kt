package com.doubtnut.amateur.newsapp.news.service

import com.doubtnut.amateur.core.responsewrapper.ApiResponse
import com.doubtnut.amateur.newsapp.news.model.NewsData
import retrofit2.http.GET

interface NewsService {
    @GET("https://demo9502422.mockable.io/headlines")
    suspend fun getNewsData(): ApiResponse<NewsData>
}