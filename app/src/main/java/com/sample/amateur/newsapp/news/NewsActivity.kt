package com.sample.amateur.newsapp.news

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.amateur.core.base.BaseActivity
import com.sample.amateur.core.ext.toast
import com.sample.amateur.core.responsewrapper.DataWrapper
import com.sample.amateur.newsapp.R
import com.sample.amateur.newsapp.news.adapter.NewListAdapter
import com.sample.amateur.newsapp.news.adapter.RecyclerViewClickListener
import com.sample.amateur.newsapp.news.model.Article
import com.sample.amateur.newsapp.news.model.NewsData
import com.sample.amateur.newsapp.news.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : BaseActivity<NewsViewModel>(), RecyclerViewClickListener {

    override val layoutId = R.layout.activity_news

    override fun getViewModelClass() = NewsViewModel::class.java

    override fun observeViewModel() {
        viewModel.newsData.observe(this, observer)

        rvNews.also {
            it.layoutManager = LinearLayoutManager(this)
            it.setHasFixedSize(true)
        }
    }

    private val observer = Observer<DataWrapper<NewsData>> { dataWrapper ->
        dataWrapper.onSuccess(::handleSuccess)

        dataWrapper.onLoading {
            toast("onLoading")
        }

        dataWrapper.onFailure { _, cause ->
            handleFailure(cause)
        }
    }

    private fun handleSuccess(data: NewsData) {
        toast("NewsActivity data  ${data.status}")
        rvNews.adapter = NewListAdapter(data.articles, this)
        loginProgress.visibility = View.GONE
    }

    private fun handleFailure(cause: Throwable? = null) {
        cause?.printStackTrace()
        rvNews.visibility = View.GONE
        loginProgress.visibility = View.GONE
        errorText.visibility = View.VISIBLE
        errorText.text = getString(R.string.txt_something_went_wrong)
    }

    override fun onRecyclerViewItemClick(view: View, article: Article) {
        when (view.id) {
            R.id.ll_container -> {
                val mIntent = Intent(this, WebViewActivity::class.java)
                mIntent.putExtra("URL", article.url)
                startActivity(mIntent)
            }
        }
    }

}
