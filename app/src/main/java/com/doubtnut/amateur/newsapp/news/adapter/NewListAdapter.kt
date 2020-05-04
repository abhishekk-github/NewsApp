package com.doubtnut.amateur.newsapp.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.doubtnut.amateur.newsapp.R
import com.doubtnut.amateur.newsapp.databinding.NewsItemBinding
import com.doubtnut.amateur.newsapp.news.model.Article
import com.doubtnut.amateur.core.base.BaseAdapter

class NewListAdapter(
    private var articleList: List<Article>,
    private val listener: RecyclerViewClickListener
) : BaseAdapter<NewListAdapter.NewsViewHolder, Article>() {
    override fun setData(data: List<Article>?) {
        if (data != null) {
            this.articleList = data
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        NewsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.news_item,
                viewGroup,
                false
            )
        )

    override fun getItemCount() = articleList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.newsItemBinding.article = articleList[position]
        holder.newsItemBinding.llContainer.setOnClickListener {
            listener.onRecyclerViewItemClick( holder.newsItemBinding.llContainer, articleList[position])
        }
    }

    inner class NewsViewHolder(
        val newsItemBinding: NewsItemBinding
    ) : RecyclerView.ViewHolder(newsItemBinding.root)
}


