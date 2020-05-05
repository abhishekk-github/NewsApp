package com.sample.amateur.newsapp.news.viewmodel

import androidx.lifecycle.*
import com.sample.amateur.core.responsewrapper.DataWrapper
import com.sample.amateur.core.responsewrapper.ResultWrapper
import com.sample.amateur.newsapp.news.model.NewsData
import com.sample.amateur.newsapp.news.repository.NewsRepository
import com.sample.amateur.newsapp.news.repository.NewsResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val newsResult by lazy {
        MutableLiveData<ResultWrapper<NewsData>>().also {
            fetchNewsData()
        }
    }

    val newsData: LiveData<DataWrapper<NewsData>> by lazy {
        Transformations.map(newsResult) {
            when (it) {
                is ResultWrapper.Result -> DataWrapper.Success(it.data)
                is ResultWrapper.Error -> DataWrapper.Failure<NewsData>(cause = it.cause)
            }
        }
    }
    private val _itineraries: LiveData<NewsResult> = MediatorLiveData<NewsResult>().apply {
        addSource(repository.articleList) { resultWrapper ->
            if (resultWrapper is ResultWrapper.Result) {
                if (resultWrapper.data.articles.isEmpty()) {
                    newsResult.value = null
                } else {
                    newsResult.value = resultWrapper
                }
            }

            if (resultWrapper is ResultWrapper.Error) {
                newsResult.value = resultWrapper
            }
        }

    }

    private fun fetchNewsData() {
        viewModelScope.launch {
            repository.getNewsAsync()
            val resultWrapper = repository.articleList.value
            if (resultWrapper is ResultWrapper.Result) {
                if (resultWrapper.data.articles.isEmpty()) {
                    newsResult.value = null
                } else {
                    newsResult.value = resultWrapper
                }
            }

            if (resultWrapper is ResultWrapper.Error) {
                newsResult.value = resultWrapper
            }

        }
    }

}