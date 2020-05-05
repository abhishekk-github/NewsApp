package com.sample.amateur.newsapp.news.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.sample.amateur.core.responsewrapper.*
import com.sample.amateur.newsapp.news.db.ArticleDao
import com.sample.amateur.newsapp.news.model.Article
import com.sample.amateur.newsapp.news.model.NewsData
import com.sample.amateur.newsapp.news.service.NewsService
import com.google.gson.JsonParseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject


typealias NewsResult = ResultWrapper<NewsData>

interface NewsRepository {
    val articleList: LiveData<NewsResult>
    suspend fun getNewsAsync()
}

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsService,
    private val articleDao: ArticleDao
) : NewsRepository {

    private val _articleList = MediatorLiveData<NewsResult>()

    override val articleList: LiveData<NewsResult>
        get() = _articleList

    override suspend fun getNewsAsync() {
        withContext(Dispatchers.IO) {
            val result = fetchUpdatedNewsFromServer()
            saveAndNotifyResult(result)
        }
    }

    private fun saveAndNotifyResult(result: ResultWrapper<NewsData>) {
        if (result.success) {
            val newsListFromNetwork = result.asResult().data.articles
            _articleList.postValue(result.asResult())
            articleDao.saveArticles(newsListFromNetwork)
        } else { // propagate network layer error
            val newsListFromDB = loadFromDB()
            if (!newsListFromDB.isNullOrEmpty()) {
                val newsData = NewsData(newsListFromDB, "success", newsListFromDB.count())
                _articleList.postValue(ResultWrapper.Result(newsData))
            } else {
                _articleList.postValue(ResultWrapper.Error(cause = result.asError().cause))
            }
            //_articleList.postValue(ResultWrapper.Error(cause = result.asError().cause))
        }
    }

    @MainThread
    private fun loadFromDB(): List<Article>? {
        val newsListFromDB = articleDao.loadPopularArticles().value
        return newsListFromDB
    }

    private suspend fun fetchUpdatedNewsFromServer(): ResultWrapper<NewsData> {
        val result = withContext(Dispatchers.IO) {
            ApiResponseUtil.asSafeResultWrapper {
                apiService.getNewsData()
            }
        }
        if (!result.success) {
            return returnError(result)
        }
        return result
    }

    private fun returnError(result: ResultWrapper<NewsData>): ResultWrapper.Error<NewsData> {
        (result as? ResultWrapper.Error)?.cause?.run {
            when (this) {
                is ApiResponse.Error -> {
                    code
                    errorMessage
                }
                is HttpException -> {
                    code()
                    message()
                    response()
                }
                is JsonParseException -> {
                    message
                }
                else -> {
                    message
                }
            }
        }
        val resultError = result as ResultWrapper.Error
        return ResultWrapper.Error(resultError.cause)
    }
}

