package com.doubtnut.amateur.newsapp.news.repository

import com.doubtnut.amateur.core.responsewrapper.ApiResponseUtil
import com.doubtnut.amateur.core.responsewrapper.ResultWrapper
import com.doubtnut.amateur.newsapp.news.model.NewsData
import com.doubtnut.amateur.newsapp.news.service.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface NewsRepository {
    suspend fun getNewsAsync(tripId: String): ResultWrapper<NewsData>
}

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsService
) : NewsRepository {

    override suspend fun getNewsAsync(tripId: String): ResultWrapper<NewsData> =
        withContext(Dispatchers.IO) {
            ApiResponseUtil.asSafeResultWrapper {
                apiService.getNewsData()
            }
        }
}