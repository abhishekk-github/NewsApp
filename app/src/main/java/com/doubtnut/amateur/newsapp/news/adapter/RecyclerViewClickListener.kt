package com.doubtnut.amateur.newsapp.news.adapter

import android.view.View
import com.doubtnut.amateur.newsapp.news.model.Article

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, article: Article)
}