package com.doubtnut.amateur.newsapp.news.viewmodel

import androidx.lifecycle.*
import com.doubtnut.amateur.core.responsewrapper.DataWrapper
import com.doubtnut.amateur.core.responsewrapper.ResultWrapper
import com.doubtnut.amateur.newsapp.news.model.NewsData
import com.doubtnut.amateur.newsapp.news.repository.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val newsResult by lazy {
        MutableLiveData<ResultWrapper<NewsData>>().also {
            fetchNewsData("")
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

    private fun fetchNewsData(tripId: String) {
        viewModelScope.launch {
            val resultWrapper = repository.getNewsAsync(tripId)
            if (resultWrapper is ResultWrapper.Result) {
                if (resultWrapper.data.articles.isEmpty()) {
                    newsResult.value = null
                }else{
                    newsResult.value = resultWrapper
                }
            }

            if(resultWrapper is ResultWrapper.Error){
                newsResult.value = resultWrapper
            }

        }
    }

}